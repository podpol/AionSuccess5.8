package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Equipment;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_UPDATE_PLAYER_APPEARANCE;
import com.aionemu.gameserver.restrictions.RestrictionsManager;
import com.aionemu.gameserver.skillengine.effect.AbnormalState;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_EQUIP_ITEM extends AionClientPacket
{
    public long slotRead;
    public int itemUniqueId;
    public int action;
	
    private static final Logger log = LoggerFactory.getLogger(CM_EQUIP_ITEM.class);
    public CM_EQUIP_ITEM(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        action = readC();
        slotRead = readQ();
        itemUniqueId = readD();
    }
	
    @Override
    protected void runImpl() {
        final Player activePlayer = getConnection().getActivePlayer();
		activePlayer.getController().cancelUseItem();
		Equipment equipment = activePlayer.getEquipment();
		Item resultItem = null;
		if (!RestrictionsManager.canChangeEquip(activePlayer)) {
			return;
		} if (activePlayer.getEffectController().isAbnormalState(AbnormalState.CANT_ATTACK_STATE)) {
			PacketSendUtility.sendPacket(activePlayer, SM_SYSTEM_MESSAGE.STR_SKILL_CAN_NOT_ACT_WHILE_IN_ABNORMAL_STATE);
			return;
		} switch (action) {
			case 0:
				resultItem = equipment.equipItem(itemUniqueId, slotRead);
			break;
			case 1:
				resultItem = equipment.unEquipItem(itemUniqueId, slotRead);
			break;
			case 2:
				if (activePlayer.getController().hasTask(TaskId.ITEM_USE) && !activePlayer.getController().getTask(TaskId.ITEM_USE).isDone()) {
					PacketSendUtility.sendPacket(activePlayer, SM_SYSTEM_MESSAGE.STR_CANT_EQUIP_ITEM_IN_ACTION);
					return;
				} if (activePlayer.getController().isUnderStance()) {
					activePlayer.getController().stopStance();
				}
				equipment.switchHands();
			break;
		} if (resultItem != null || action == 2) {
			PacketSendUtility.broadcastPacket(activePlayer, new SM_UPDATE_PLAYER_APPEARANCE(activePlayer.getObjectId(), equipment.getEquippedForApparence()), true);
		}
	}
}