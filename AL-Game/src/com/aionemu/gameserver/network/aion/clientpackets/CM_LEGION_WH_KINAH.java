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
import com.aionemu.gameserver.model.items.storage.StorageType;
import com.aionemu.gameserver.model.team.legion.Legion;
import com.aionemu.gameserver.model.team.legion.LegionHistoryType;
import com.aionemu.gameserver.model.team.legion.LegionMember;
import com.aionemu.gameserver.model.team.legion.LegionPermissionsMask;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.LegionService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author ATracer
 */
public class CM_LEGION_WH_KINAH extends AionClientPacket {

   public CM_LEGION_WH_KINAH(int opcode, State state, State... restStates) {
	  super(opcode, state, restStates);
   }
   
   private long amount;
   private int operation;

   @Override
   protected void readImpl() {
	  this.amount = readQ();
	  this.operation = readC();
   }

   @Override
   protected void runImpl() {
	  final Player activePlayer = getConnection().getActivePlayer();

	  Legion legion = activePlayer.getLegion();
	  if (legion != null) {
		 LegionMember LM = LegionService.getInstance().getLegionMember(activePlayer.getObjectId());
		 switch (operation) {
			case 0:
			   if (!LM.hasRights(LegionPermissionsMask.WH_DEPOSIT)) {
				  // You do not have the authority to use the Legion warehouse.
				  PacketSendUtility.sendPacket(activePlayer, new SM_SYSTEM_MESSAGE(1300322));
				  return;
			   }
			   if (activePlayer.getStorage(StorageType.LEGION_WAREHOUSE.getId()).tryDecreaseKinah(amount)) {
				  activePlayer.getInventory().increaseKinah(amount);
				  LegionService.getInstance().addHistory(legion, activePlayer.getName(), LegionHistoryType.KINAH_WITHDRAW, 2, Long.toString(amount));
			   }
			   break;
			case 1:
			   if (!LM.hasRights(LegionPermissionsMask.WH_WITHDRAWAL)) {
				  // You do not have the authority to use the Legion warehouse.
				  PacketSendUtility.sendPacket(activePlayer, new SM_SYSTEM_MESSAGE(1300322));
				  return;
			   }
			   if (activePlayer.getInventory().tryDecreaseKinah(amount)) {
				  activePlayer.getStorage(StorageType.LEGION_WAREHOUSE.getId()).increaseKinah(amount);
				  LegionService.getInstance().addHistory(legion, activePlayer.getName(), LegionHistoryType.KINAH_DEPOSIT, 2, Long.toString(amount));
			   }
			   break;
		 }
	  }
   }
}
