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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerHouseOwnerFlags;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.house.MaintenanceTask;
import com.aionemu.gameserver.model.templates.housing.HouseType;
import com.aionemu.gameserver.model.town.Town;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.TownService;
import org.joda.time.DateTime;

import java.sql.Timestamp;

public class SM_HOUSE_OWNER_INFO extends AionServerPacket
{
	private Player player;
	private House activeHouse;
	
	public SM_HOUSE_OWNER_INFO(Player player, House activeHouse) {
		this.player = player;
		this.activeHouse = activeHouse;
	}
	
	@Override
    protected void writeImpl(AionConnection con) {
    	if (activeHouse == null) {
            writeD(0);
            writeD(player.isBuildingInState(PlayerHouseOwnerFlags.BUY_STUDIO_ALLOWED) ? 355000 : 0);
        } else {
            writeD(activeHouse.getAddress().getId());
            writeD(activeHouse.getBuilding().getId());
        }
        writeC(player.getBuildingOwnerStates());
        int townLevel = 1;
        if (activeHouse != null && activeHouse.getAddress().getTownId() != 0) {
            Town town = TownService.getInstance().getTownById(activeHouse.getAddress().getTownId());
            townLevel = town.getLevel();
        }
        writeC(townLevel);
        if (activeHouse == null || !activeHouse.isFeePaid() || activeHouse.getHouseType() == HouseType.STUDIO) {
            writeC(0);
        } else {
            Timestamp nextPay = activeHouse.getNextPay();
            float diff;
            if (nextPay == null) {
                diff = MaintenanceTask.getInstance().getPeriod();
            } else {
                long paytime = activeHouse.getNextPay().getTime();
                diff = paytime - ((long) MaintenanceTask.getInstance().getRunTime() * 1000);
            } if (diff < 0) {
                writeC(0);
            } else {
                int weeks = (int) (Math.round(diff / MaintenanceTask.getInstance().getPeriod()));
                if (DateTime.now().getDayOfWeek() != 7) {
                    weeks++;
                }
                writeC(weeks);
            }
        }
		writeD(0);
		writeD(0);
		writeD(0);
		writeC(0);
		writeC(0);
		writeC(0);
		writeC(0);
	}
}