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

import com.aionemu.gameserver.model.ChatType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_LUNA_SYSTEM_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MESSAGE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.player.LunaShopService;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class CM_LUNA_SYSTEM extends AionClientPacket {

	private int actionId;
	private int indun_id;
	private int indun_unk;
	private int recipe_id;
	private int material_item_id;
	private long material_item_count;
	private int teleportId;
	private int slot;
	private int ItemObjId;
	@SuppressWarnings("unused")
	private int lunaCost;

	public CM_LUNA_SYSTEM(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		actionId = readC();
		switch (actionId) {
			case 0: // Taki's Missions Teleport.
				indun_id = readD();
				indun_unk = readC();
				break;
			case 2: // Karunerk's Workshop.
				recipe_id = readD();
				break;
			case 4: // Buy Necessary Materials.
				material_item_id = readD();
				material_item_count = readQ();
				break;
			case 6:
			case 7:
				this.teleportId = readD();
				break;
			case 8: // Dorinerk's Wardrobe.
				break;
			case 9: // Expand wardrobe slot
				break;
			case 10: // Apply wardrobe appearance
				slot = readC();
				ItemObjId = readD();
				break;
			case 11: // Modify appearance
				slot = readC();
				ItemObjId = readD();
				lunaCost = readC();
				break;
			case 12: // Open Chest.
				break;
			case 14: // Taki's Adventure.
				indun_id = readD();
				break;
			case 15: //Luna Dice Game
				break;
			case 16://Luna Dice Game Reward
				break;
		}
	}

	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null) {
			return;
		}
		switch (actionId) {
			case 0:
				if (player.getLevel() <= 9) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_CANT_INSTANCE_ENTER_LEVEL);
					return;
				}
				else if (player.isInGroup2()) {
					PacketSendUtility.broadcastPacket(player, new SM_MESSAGE(player, "You must leave your group or alliance to enter <Luna Instance>", ChatType.BRIGHT_YELLOW_CENTER), true);
					return;
				}
				else {
					LunaShopService.getInstance().takiAdventureTeleport(player, indun_unk, indun_id);
				}
				break;
			case 2: // Karunerk's Workshop
				LunaShopService.getInstance().specialDesign(player, recipe_id);
				break;
			case 3:
				LunaShopService.getInstance().craftBox(player);
				break;
			case 4: // Buy Necessary Materials
				LunaShopService.getInstance().buyMaterials(player, material_item_id, material_item_count);
				break;
			case 5:
				PacketSendUtility.sendPacket(player, new SM_LUNA_SYSTEM_INFO(actionId));
				break;
			case 6:
			case 7:
				LunaShopService.getInstance().teleport(player, actionId, teleportId);
				break;
			case 8:
				LunaShopService.getInstance().dorinerkWardrobeLoad(player);
				break;
			case 9:
				LunaShopService.getInstance().dorinerkWardrobeExtendSlots(player);
				break;
			case 10:
				LunaShopService.getInstance().dorinerkWardrobeAct(player, slot, ItemObjId);
				break;
			case 11:
				LunaShopService.getInstance().dorinerkWardrobeModifyAppearance(player, slot, ItemObjId);
				break;
			case 12:
				LunaShopService.getInstance().munirunerksTreasureChamber(player);
				break;
			case 14:
				LunaShopService.getInstance().takiAdventure(player, indun_id);
				break;
			case 15:
				LunaShopService.getInstance().diceGame(player);
				break;
			case 16:
				LunaShopService.getInstance().diceGameReward(player);
				break;
			default:
				System.out.println("UNKOWN ACTION-ID: " + actionId);
				break;
		}
	}
}
