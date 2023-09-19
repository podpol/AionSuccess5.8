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
package com.aionemu.gameserver.services.instance;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.services.CronService;
import com.aionemu.gameserver.configs.main.AutoGroupConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_AUTO_GROUP;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.AutoGroupService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import javolution.util.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/****/
/** Author Rinzler (Encom)
/****/

public class HallOfTenacityService
{
	private Logger log = LoggerFactory.getLogger(HallOfTenacityService.class);
    private boolean registerAvailable;
    private final FastList<Integer> playersWithCooldown = FastList.newInstance();
    public static final byte minLevel = 66, capLevel = 76;
    public static final int maskId = 125;
	
	public void initHallOfTenacity() {
		if (AutoGroupConfig.HALL_OF_TENACITY_ENABLED) {
            log.info("Hall Of Tenacity 5.3");
			//Hall Of Tenacity SAT-SUN "9AM-5PM"
			CronService.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					startHallOfTenacityRegistration();
				}
			}, AutoGroupConfig.HALL_OF_TENACITY_SCHEDULE_MORNING);
			//Hall Of Tenacity MON-TUE-WED-THU-FRI "6PM-0AM"
			CronService.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					startHallOfTenacityRegistration();
				}
			}, AutoGroupConfig.HALL_OF_TENACITY_SCHEDULE_EVENING);
		}
	}
	
    private void startUregisterHallOfTenacityTask() {
        ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                registerAvailable = false;
                playersWithCooldown.clear();
                AutoGroupService.getInstance().unRegisterInstance(maskId);
                Iterator<Player> iter = World.getInstance().getPlayersIterator();
                while (iter.hasNext()) {
                    Player player = iter.next();
                    if (player.getLevel() > minLevel) {
                        int instanceMaskId = getInstanceMaskId(player);
                        if (instanceMaskId > 0) {
                            PacketSendUtility.sendPacket(player, new SM_AUTO_GROUP(instanceMaskId, SM_AUTO_GROUP.wnd_EntryIcon, true));
                        }
                    }
                }
            }
        }, AutoGroupConfig.HALL_OF_TENACITY_TIMER * 60 * 1000);
    }
	
    public void startHallOfTenacityRegistration() {
        this.registerAvailable = true;
        startUregisterHallOfTenacityTask();
        Iterator<Player> iter = World.getInstance().getPlayersIterator();
        while (iter.hasNext()) {
            Player player = iter.next();
            if (player.getLevel() > minLevel && player.getLevel() < capLevel) {
                int instanceMaskId = getInstanceMaskId(player);
                if (instanceMaskId > 0) {
                    PacketSendUtility.sendPacket(player, new SM_AUTO_GROUP(instanceMaskId, SM_AUTO_GROUP.wnd_EntryIcon));
                    //The Hall of Tenacity is now open.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_INSTANCE_OPEN_IDTM_Lobby01);
                }
            }
        }
    }
	
    public boolean isHallAvailable() {
        return this.registerAvailable;
    }
	
    public byte getInstanceMaskId(Player player) {
        int level = player.getLevel();
        if (level < minLevel || level >= capLevel) {
            return 0;
        }
        return maskId;
    }
	
    public void addCoolDown(Player player) {
        this.playersWithCooldown.add(player.getObjectId());
    }
	
    public boolean hasCoolDown(Player player) {
        return this.playersWithCooldown.contains(player.getObjectId());
    }
	
    public void showWindow(Player player, byte instanceMaskId) {
        if (getInstanceMaskId(player) != instanceMaskId) {
            return;
        } if (!this.playersWithCooldown.contains(player.getObjectId())) {
            PacketSendUtility.sendPacket(player, new SM_AUTO_GROUP(instanceMaskId));
        }
    }
	
    private static class SingletonHolder {
        protected static final HallOfTenacityService instance = new HallOfTenacityService();
    }
	
    public static HallOfTenacityService getInstance() {
        return SingletonHolder.instance;
    }
}