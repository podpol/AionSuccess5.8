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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.controllers.HouseController;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.house.HousePermissions;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOUSE_ACQUIRE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.HousingService;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.nio.charset.Charset;

public class CM_HOUSE_SETTINGS extends AionClientPacket
{
	int doorState;
    int displayOwner;
    String signNotice;
	
    public CM_HOUSE_SETTINGS(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        doorState = readC();
        displayOwner = readC();
        signNotice = readS();
    }
	
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        if (player == null) {
            return;
        }
        House house = HousingService.getInstance().getPlayerStudio(player.getObjectId());
        if (house == null) {
            int address = HousingService.getInstance().getPlayerAddress(player.getObjectId());
            house = HousingService.getInstance().getHouseByAddress(address);
        }
        HousePermissions doorPermission = HousePermissions.getPacketDoorState(doorState);
        house.setDoorState(doorPermission);
        house.setNoticeState(HousePermissions.getNoticeState(displayOwner));
        house.setSignNotice(signNotice.getBytes(Charset.forName("UTF-16LE")));
        PacketSendUtility.sendPacket(player, new SM_HOUSE_ACQUIRE(player.getObjectId(), house.getAddress().getId(), true));
        HouseController controller = (HouseController) house.getController();
        controller.updateAppearance();
		
        if (doorPermission == HousePermissions.DOOR_OPENED_ALL) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOUSING_ORDER_OPEN_DOOR);
        } else if (doorPermission == HousePermissions.DOOR_OPENED_FRIENDS) {
            house.getController().kickVisitors(player, false, true);
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOUSING_ORDER_CLOSE_DOOR_WITHOUT_FRIENDS);
        } else if (doorPermission == HousePermissions.DOOR_CLOSED) {
            house.getController().kickVisitors(player, true, true);
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOUSING_ORDER_CLOSE_DOOR_ALL);
        }
    }
}