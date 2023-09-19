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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_AETHERFORGING_PLAYER;
import com.aionemu.gameserver.services.craft.CraftService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Ranastic
 */

public class CM_AETHERFORGING extends AionClientPacket
{
	private int itemID;
	@SuppressWarnings("unused")
	private long itemCount;
	private int actionId;
	private int targetTemplateId;
	private int recipeId;
	private int targetObjId;
	private int materialsCount;
	private int craftType;
	
	public CM_AETHERFORGING(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		Player player = getConnection().getActivePlayer();
		actionId = readC();
		switch(actionId) {
			case 0:
			    targetTemplateId = readD();
				recipeId = readD();
				targetObjId = readD();
				materialsCount = readH();
				craftType = readC();
			break;
			case 1:
			    targetTemplateId = readD();
				recipeId = readD();
				targetObjId = readD();
				materialsCount = readH();
				craftType = readC();
				for (int i = 0 ; i < materialsCount ; i++) {
				    itemID = readD();
				    itemCount = readQ();
				    CraftService.checkComponents(player, recipeId, itemID, materialsCount);
				}
			break;
		}
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
        if (player == null || !player.isSpawned()) {
            return;
        } if (player.getController().isInShutdownProgress()) {
            return;
        } switch(actionId) {
			case 0:
			    CraftService.stopAetherforging(player, recipeId);
				PacketSendUtility.sendPacket(player, new SM_AETHERFORGING_PLAYER(player, actionId));
			break;
			case 1:
			    CraftService.startAetherforging(player, recipeId, craftType);
				PacketSendUtility.sendPacket(player, new SM_AETHERFORGING_PLAYER(player, actionId));
			break;
		}
	}
}