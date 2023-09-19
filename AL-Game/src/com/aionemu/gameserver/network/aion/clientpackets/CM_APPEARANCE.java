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

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.actions.AbstractItemAction;
import com.aionemu.gameserver.model.templates.item.actions.CosmeticItemAction;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.RenameService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author xTz
 */
public class CM_APPEARANCE extends AionClientPacket {

	private int type;

	private int itemObjId;

	private String name;

	public CM_APPEARANCE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		type = readC();
		readC();
		readH();
		itemObjId = readD();
		switch (type) {
			case 0:
			case 1:
				name = readS();
				break;
		}

	}

	@Override
	protected void runImpl() {
		final Player player = getConnection().getActivePlayer();

		switch (type) {
			case 0: // Change Char Name,
				if (RenameService.renamePlayer(player, player.getName(), name, itemObjId)) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400157, name));
				}
				break;
			case 1: // Change Legion Name
				if (RenameService.renameLegion(player, name, itemObjId)) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400158, name));
				}
				break;
			case 2: // cosmetic items
				Item item = player.getInventory().getItemByObjId(itemObjId);
				if (item != null) {
					for (AbstractItemAction action : item.getItemTemplate().getActions().getItemActions()) {
						if (action instanceof CosmeticItemAction) {
							if (!action.canAct(player, null, null)) {
								return;
							}
							action.act(player, null, item);
							break;
						}
					}
				}
				break;
		}
	}
}
