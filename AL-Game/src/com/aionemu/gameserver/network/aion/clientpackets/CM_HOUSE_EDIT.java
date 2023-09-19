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
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.model.templates.item.actions.DecorateAction;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOUSE_EDIT;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOUSE_REGISTRY;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.services.HousingService;
import com.aionemu.gameserver.services.item.HouseObjectFactory;
import com.aionemu.gameserver.services.item.ItemPacketService.ItemDeleteType;
import com.aionemu.gameserver.utils.audit.AuditLogger;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_HOUSE_EDIT extends AionClientPacket
{
    private static final Logger log = LoggerFactory.getLogger(CM_HOUSE_EDIT.class);
    private HousingAction action;
    private int actionId;
    int itemObjectId;
    float x, y, z;
    int rotation;
    int buildingId;
	
    public CM_HOUSE_EDIT(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        actionId = readC();
        action = HousingAction.getActionTypeById(actionId);
        if (action == HousingAction.ADD_ITEM
            || action == HousingAction.DELETE_ITEM
            || action == HousingAction.DESPAWN_OBJECT) {
            itemObjectId = readD();
        } else if (action == HousingAction.SPAWN_OBJECT
            || action == HousingAction.MOVE_OBJECT) {
            itemObjectId = readD();
            x = readF();
            y = readF();
            z = readF();
            rotation = readH();
        } else if (action == HousingAction.CHANGE_APPEARANCE) {
            buildingId = readD();
        } else if (action == HousingAction.ENTER_DECORATION || action == HousingAction.EXIT_DECORATION) {
        } else {
            log.error("Unknown housing action type? 0x" + Integer.toHexString(actionId).toUpperCase());
        }
    }
	
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        if (player == null) {
            return;
        } if (action == HousingAction.ENTER_DECORATION) {
            sendPacket(new SM_HOUSE_EDIT(actionId));
            sendPacket(new SM_HOUSE_REGISTRY(actionId));
            sendPacket(new SM_HOUSE_REGISTRY(actionId + 1));
        } else if (action == HousingAction.EXIT_DECORATION) {
            sendPacket(new SM_HOUSE_EDIT(actionId));
        } else if (action == HousingAction.ADD_ITEM) {
            Item item = player.getInventory().getItemByObjId(itemObjectId);
            if (item == null) {
                return;
            }
            ItemTemplate template = item.getItemTemplate();
            player.getInventory().delete(item, ItemDeleteType.REGISTER);
            DecorateAction decorateAction = template.getActions().getDecorateAction();
            if (decorateAction != null) {
                HouseDecoration decor = new HouseDecoration(IDFactory.getInstance().nextId(), decorateAction.getTemplateId());
                player.getHouseRegistry().putCustomPart(decor);
                sendPacket(new SM_HOUSE_EDIT(actionId, 2, decor.getObjectId()));
            } else {
                House house = player.getHouseRegistry().getOwner();
                HouseObject<?> obj = HouseObjectFactory.createNew(house, template);
                player.getHouseRegistry().putObject(obj);
                sendPacket(new SM_HOUSE_EDIT(actionId, 1, obj.getObjectId()));
            }
        } else if (action == HousingAction.DELETE_ITEM) {
            player.getHouseRegistry().removeObject(itemObjectId);
            sendPacket(new SM_HOUSE_EDIT(actionId, 1, itemObjectId));
            sendPacket(new SM_HOUSE_EDIT(4, 1, itemObjectId));
        } else if (action == HousingAction.SPAWN_OBJECT) {
            HouseObject<?> obj = player.getHouseRegistry().getObjectByObjId(itemObjectId);
            if (obj == null) {
                return;
            }
            obj.setX(x);
            obj.setY(y);
            obj.setZ(z);
            obj.setRotation(rotation);
            sendPacket(new SM_HOUSE_EDIT(actionId, itemObjectId, x, y, z, rotation));
            obj.spawn();
            player.getHouseRegistry().setPersistentState(PersistentState.UPDATE_REQUIRED);
            sendPacket(new SM_HOUSE_EDIT(4, 1, itemObjectId));
			QuestEngine.getInstance().onHouseItemUseEvent(new QuestEnv(null, player, 0, 0), obj.getObjectTemplate().getTemplateId());
        } else if (action == HousingAction.MOVE_OBJECT) {
            HouseObject<?> obj = player.getHouseRegistry().getObjectByObjId(itemObjectId);
            if (obj == null) {
                return;
            }
            sendPacket(new SM_HOUSE_EDIT(actionId + 1, 0, itemObjectId));
            obj.getController().onDelete();
            obj.setX(x);
            obj.setY(y);
            obj.setZ(z);
            obj.setRotation(rotation);
            if (obj.getPersistentState() == PersistentState.UPDATE_REQUIRED) {
                player.getHouseRegistry().setPersistentState(PersistentState.UPDATE_REQUIRED);
            }
            sendPacket(new SM_HOUSE_EDIT(actionId - 1, itemObjectId, x, y, z, rotation));
            obj.spawn();
        } else if (action == HousingAction.DESPAWN_OBJECT) {
            HouseObject<?> obj = player.getHouseRegistry().getObjectByObjId(itemObjectId);
            if (obj == null) {
                return;
            }
            sendPacket(new SM_HOUSE_EDIT(actionId, 0, itemObjectId));
            obj.getController().onDelete();
            obj.removeFromHouse();
            obj.clearKnownlist();
            player.getHouseRegistry().setPersistentState(PersistentState.UPDATE_REQUIRED);
            sendPacket(new SM_HOUSE_EDIT(3, 1, itemObjectId));
        } else if (action == HousingAction.ENTER_RENOVATION) {
            sendPacket(new SM_HOUSE_EDIT(14));
        } else if (action == HousingAction.EXIT_RENOVATION) {
            sendPacket(new SM_HOUSE_EDIT(15));
        } else if (action == HousingAction.CHANGE_APPEARANCE) {
            House house = player.getHouseRegistry().getOwner();
            if (!removeRenovationCoupon(player, house)) {
                AuditLogger.info(player, "Try house renovation without coupon");
                return;
            }
            HousingService.getInstance().switchHouseBuilding(house, buildingId);
            player.setHouseRegistry(house.getRegistry());
            ((HouseController) house.getController()).updateAppearance();
        }
    }
	
    private boolean removeRenovationCoupon(Player player, House house) {
        int typeId = house.getHouseType().getId();
        if (typeId == 0) {
            return false;
        }
        int itemId = (player.getRace().equals(Race.ELYOS) ? 169661004 : 169661000) - typeId;
        if (player.getInventory().getItemCountByItemId(itemId) > 0) {
            return player.getInventory().decreaseByItemId(itemId, 1);
        }
        return false;
    }
}