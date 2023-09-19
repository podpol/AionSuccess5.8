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
import com.aionemu.gameserver.services.item.CoalescenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranastic
 */

public class CM_COALESCENCE extends AionClientPacket
{
	private Logger log = LoggerFactory.getLogger(CM_COALESCENCE.class);
	private int mainItemObjId;
	private int materialCount;
	private List<Integer> materialItemObjId;

	private int ItemSize;
	private int upgradedItemObjectId;
	private int Items;
	private List<Integer> ItemsList = new ArrayList();
	
	public CM_COALESCENCE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		materialItemObjId  = new ArrayList<Integer>();
		mainItemObjId = readD();
		materialCount = readH();
		for (int i=0;i<materialCount;i++) {
			materialItemObjId.add(readD());
		}
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null || !player.isSpawned()) {
			return;
		} if (player.getController().isInShutdownProgress()) {
			return;
		}
		CoalescenceService.getInstance().letsCoalescence(player, mainItemObjId, materialItemObjId);
	}
}