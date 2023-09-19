/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen, ArchSoft,   *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package com.aionemu.gameserver.model.house;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.configs.main.HousingConfig;
import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOUSE_ACQUIRE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOUSE_OWNER_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.HousingBidService;
import com.aionemu.gameserver.services.HousingService;
import com.aionemu.gameserver.services.mail.MailFormatter;
import com.aionemu.gameserver.taskmanager.AbstractCronTask;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import javolution.util.FastList;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class MaintenanceTask extends AbstractCronTask
{
	private static final Logger log = LoggerFactory.getLogger(MaintenanceTask.class);
	private static final FastList<House> maintainedHouses;
	private static MaintenanceTask instance;
	
	static {
		maintainedHouses = FastList.newInstance();
		try {
			instance = new MaintenanceTask(HousingConfig.HOUSE_MAINTENANCE_TIME);
		} catch (ParseException pe) {
		}
	}
	
	public static final MaintenanceTask getInstance() {
		return instance;
	}
	
	private MaintenanceTask(String maintainTime) throws ParseException {
		super(maintainTime);
	}
	
	@Override
	protected long getRunDelay() {
		int left = (int) (getRunTime() - System.currentTimeMillis() / 1000);
		if (left < 0) {
			return 0;
		}
		return left * 1000;
	}
	
	@Override
	protected String getServerTimeVariable() {
		return "houseMaintainTime";
	}
	
    protected boolean canRunOnInit() {
      return false;
    }
	
	public boolean isMaintainTime() {
		return (getRunTime() - System.currentTimeMillis() / 1000) <= 0;
	}
	
	@Override
	protected void preInit() {
		log.info("Initializing House maintenance task...");
	}
	
    @Override
    protected void preRun() {
        updateMaintainedHouses();
        log.info("Executing House maintenance. Maintained Houses: " + maintainedHouses.size());
    }
	
	private void updateMaintainedHouses() {
		maintainedHouses.clear();
		if (!HousingConfig.ENABLE_HOUSE_PAY) {
			return;
		}
		Date now = new Date();
		FastList<House> houses = HousingService.getInstance().getCustomHouses();
		for (House house : houses) {
			if (house.getStatus() == HouseStatus.INACTIVE) {
				continue;
			} if (house.getOwnerId() == 0) {
				continue;
			} if (house.isFeePaid()) {
				if (house.getNextPay() == null || house.getNextPay().before(now)) {
					house.setFeePaid(false);
					if (house.getNextPay() == null) {
						house.setNextPay(new Timestamp((long) getRunTime() * 1000));
					}
					house.save();
				} else {
					continue;
				}
			}
			maintainedHouses.add(house);
		}
	}
	
	@Override
	protected void executeTask() {
		if (!HousingConfig.ENABLE_HOUSE_PAY) {
			return;
		}
		DateTime now = new DateTime();
		DateTime previousRun = now.minus(getPeriod());
		DateTime beforePreviousRun = previousRun.minus(getPeriod());
		for (House house : maintainedHouses) {
			if (house.isFeePaid()) {
				continue;
			}
			long payTime = house.getNextPay().getTime();
			long impoundTime = 0;
			int warnCount = 0;
			PlayerCommonData pcd = null;
			Player player = World.getInstance().findPlayer(house.getOwnerId());
			if (player == null) {
				pcd = DAOManager.getDAO(PlayerDAO.class).loadPlayerCommonData(house.getOwnerId());
			} else {
				pcd = player.getCommonData();
			} if (pcd == null) {
				putHouseToAuction(house, null);
				continue;
			} if (payTime <= beforePreviousRun.getMillis()) {
				DateTime plusDay = beforePreviousRun.minusDays(1);
				if (payTime <= plusDay.getMillis()) {
					impoundTime = now.getMillis();
					warnCount = 3;
					putHouseToAuction(house, pcd);
				} else {
					impoundTime = now.plusDays(1).getMillis();
					warnCount = 2;
				}
			} else if (payTime <= previousRun.getMillis()) {
				impoundTime = now.plus(getPeriod()).plusDays(1).getMillis();
				warnCount = 1;
			} else {
				continue;
			} if (pcd.isOnline()) {
				if (warnCount == 3) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOUSING_SEQUESTRATE);
				} else {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOUSING_OVERDUE);
				}
			}
			MailFormatter.sendHouseMaintenanceMail(house, warnCount, impoundTime);
		}
	}
	
	private void putHouseToAuction(House house, PlayerCommonData playerCommonData) {
		house.revokeOwner();
		HousingBidService.getInstance().addHouseToAuction(house);
		house.save();
		if (playerCommonData == null) {
			return;
		} if (playerCommonData.isOnline()) {
			Player player = playerCommonData.getPlayer();
			player.getHouses().remove(house);
			player.setHouseRegistry(null);
			PacketSendUtility.sendPacket(player, new SM_HOUSE_ACQUIRE(player.getObjectId(), house.getAddress().getId(), false));
			PacketSendUtility.sendPacket(player, new SM_HOUSE_OWNER_INFO(player, null));
		}
	}
}