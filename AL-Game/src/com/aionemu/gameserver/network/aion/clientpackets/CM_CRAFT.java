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

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.craft.CraftService;
import com.aionemu.gameserver.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_CRAFT extends AionClientPacket
{
	private static final Logger log = LoggerFactory.getLogger(CM_CRAFT.class);
	private int itemID;
	private long itemCount;
	private int unk;
	private int targetTemplateId;
	private int recipeId;
	private int targetObjId;
	private int materialsCount;
	private int craftType;
	
	public CM_CRAFT(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		Player player = getConnection().getActivePlayer();
		unk = readC();
		targetTemplateId = readD();
		recipeId = readD();
		targetObjId = readD();
		materialsCount = readH();
		craftType = readC();
		if (craftType == 0) {
			for(int i = 0 ; i < materialsCount ; i++) {
				itemID = readD();
				itemCount = readQ();
				CraftService.checkComponents(player, recipeId, itemID, materialsCount);
			}
		}
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null || !player.isSpawned()) {
			return;
		} if (player.getController().isInShutdownProgress()) {
			return;
		} if (unk != 129) {
			VisibleObject staticObject = player.getKnownList().getKnownObjects().get(targetObjId);
			if (staticObject == null || !MathUtil.isIn3dRange(player, staticObject, 10) ||
			    staticObject.getObjectTemplate().getTemplateId() != targetTemplateId) {
				return;
			}
		}
		CraftService.startCrafting(player, recipeId, targetObjId, craftType);
	}
}