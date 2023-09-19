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
package com.aionemu.gameserver.services.rvrservice;

import com.aionemu.gameserver.model.rvr.RvrLocation;
import com.aionemu.gameserver.services.RvrService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class RvrStartRunnable implements Runnable
{
	private final int id;
	
	public RvrStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Invasion Portal.
		RvrService.getInstance().adventPortalSP(id);
		//An Elyos warship will invade in 10 minutes.
		RvrService.getInstance().DF6G1Spawn01Msg(id);
		//An Asmodian warship will invade in 10 minutes.
		RvrService.getInstance().LF6G1Spawn01Msg(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Invasion Lazer.
				RvrService.getInstance().adventDirectingSP(id);
			}
		}, 180000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				//Invasion Black Sky.
				RvrService.getInstance().adventControlSP(id);
			    //An Elyos Warship will invade in 5 minutes.
				RvrService.getInstance().DF6G1Spawn02Msg(id);
				//An Asmodian Warship will invade in 5 minutes.
				RvrService.getInstance().LF6G1Spawn02Msg(id);
				//Intrusion was detected.
				RvrService.getInstance().F6RaidStart5Minute(id);
			}
		}, 300000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //An Elyos warship will invade in 3 minutes.
				RvrService.getInstance().DF6G1Spawn03Msg(id);
				//An Asmodian warship will invade in 3 minutes.
				RvrService.getInstance().LF6G1Spawn03Msg(id);
			}
		}, 480000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //An Elyos warship will invade in 1 minute.
				RvrService.getInstance().DF6G1Spawn04Msg(id);
				//An Asmodian warship will invade in 1 minute.
				RvrService.getInstance().LF6G1Spawn04Msg(id);
			}
		}, 540000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    Map<Integer, RvrLocation> locations = RvrService.getInstance().getRvrLocations();
				for (final RvrLocation loc: locations.values()) {
					if (loc.getId() == id) {
						//Invasion Light Blue.
						RvrService.getInstance().adventEffectSP(id);
						//Elyos Warship Invasion.
						RvrService.getInstance().DF6G1Spawn05Msg(id);
						//Asmodian Warship Invasion.
						RvrService.getInstance().LF6G1Spawn05Msg(id);
						//Ancient's Weapon Invasion.
						RvrService.getInstance().F6RaidStart(id);
						//Brigade General's Urgent Order.
						RvrService.getInstance().startRvr(loc.getId());
						//The Asmodian Troopers are retreating after the defeat of their officers.
					    RvrService.getInstance().LF6EventG2Start02Msg(id);
						//The Aetos are retreating after the defeat of their officers.
					    RvrService.getInstance().DF6EventG2Start02Msg(id);
					}
				}
			}
		}, 600000);
	}
}