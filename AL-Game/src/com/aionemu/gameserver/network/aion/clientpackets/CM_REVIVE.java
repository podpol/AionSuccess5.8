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
import com.aionemu.gameserver.model.gameobjects.player.ReviveType;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.player.PlayerReviveService;

/**
 * @author Ranastic (Encom)
 */

public class CM_REVIVE extends AionClientPacket
{
	private int reviveId;
	
	public CM_REVIVE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		reviveId = readC();
	}
	
	@Override
	protected void runImpl() {
		Player activePlayer = getConnection().getActivePlayer();
		if (!activePlayer.getLifeStats().isAlreadyDead()) {
			return;
		}
		ReviveType reviveType = ReviveType.getReviveTypeById(reviveId, activePlayer);
		switch (reviveType) {
			case BIND_REVIVE:
			case VORTEX_REVIVE:
				PlayerReviveService.bindRevive(activePlayer);
			break;
			case REBIRTH_REVIVE:
				PlayerReviveService.rebirthRevive(activePlayer);
			break;
			case ITEM_SELF_REVIVE:
				PlayerReviveService.itemSelfRevive(activePlayer);
			break;
			case SKILL_REVIVE:
				PlayerReviveService.skillRevive(activePlayer);
			break;
			case KISK_REVIVE:
				PlayerReviveService.kiskRevive(activePlayer);
			break;
			case INSTANCE_REVIVE:
				PlayerReviveService.instanceRevive(activePlayer);
			break;
			case START_POINT_REVIVE:
				PlayerReviveService.startPositionRevive(activePlayer);
			break;
			default:
				break;
		}
	}
}