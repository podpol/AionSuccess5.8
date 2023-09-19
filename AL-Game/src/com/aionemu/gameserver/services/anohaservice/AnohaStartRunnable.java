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
package com.aionemu.gameserver.services.anohaservice;

import com.aionemu.gameserver.model.anoha.AnohaLocation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.AnohaService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class AnohaStartRunnable implements Runnable
{
	private final int id;
	
	public AnohaStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Berserk Anoha Sword Effect.
		AnohaService.getInstance().adventSwordEffectSP(id);
		//Berserk Anoha will return to Kaldor in 30 minutes.
		AnohaService.getInstance().berserkAnohaMsg1(id);
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				AnohaService.getInstance().sendRequest(player);
			}
		});
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Enraged Wealhtheow Guardian will appear in 5 minutes.
				AnohaService.getInstance().wealhtheowGuardianMsg1(id);
			}
		}, 1500000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Enraged Wealhtheow Guardian will appear in 3 minutes.
				AnohaService.getInstance().wealhtheowGuardianMsg2(id);
			}
		}, 1620000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Enraged Wealhtheow Guardian will appear in 1 minute.
				AnohaService.getInstance().wealhtheowGuardianMsg3(id);
			}
		}, 1740000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    Map<Integer, AnohaLocation> locations = AnohaService.getInstance().getAnohaLocations();
				for (final AnohaLocation loc : locations.values()) {
					if (loc.getId() == id) {
						AnohaService.getInstance().startAnoha(loc.getId());
					}
				}
				World.getInstance().doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						//Summon Berserk Anoha.
						PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_Anoha_Spawn);
					}
				});
			}
		}, 1800000);
	}
}