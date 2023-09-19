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
package com.aionemu.gameserver.services.beritraservice;

import com.aionemu.gameserver.model.beritra.BeritraLocation;
import com.aionemu.gameserver.services.BeritraService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class BeritraStartRunnable implements Runnable
{
	private final int id;
	
	public BeritraStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Beritra Invasion Portal.
		BeritraService.getInstance().adventPortalSP(id);
		//Ereshkigal Invasion Portal.
		BeritraService.getInstance().adventPortalEreshSP(id);
		//The Beritra Legion's Invasion Corridor has appeared.
		BeritraService.getInstance().invasionCorridorMsg(id);
		//The Ereshkigal Legion's Invasion Corridor has been created.
		BeritraService.getInstance().ereshkigalCorridorMsg(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Beritra Invasion Lazer.
				BeritraService.getInstance().adventDirectingSP(id);
				//Ereshkigal Invasion Lazer.
				BeritraService.getInstance().adventDirectingEreshSP(id);
				//The Devil Unit has infiltrated through the Invasion Corridor.
				BeritraService.getInstance().devilUnitThroughMsg(id);
				//The Ereshkigal Legion's Magic weapon has infiltrated through the Invasion Corridor.
				BeritraService.getInstance().ereshkigalLegionThroughMsg(id);
			}
		}, 180000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Beritra Invasion Black Sky.
				BeritraService.getInstance().adventControlSP(id);
				//Ereshkigal Invasion Black Sky.
				BeritraService.getInstance().adventControlEreshSP(id);
			}
		}, 300000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    Map<Integer, BeritraLocation> locations = BeritraService.getInstance().getBeritraLocations();
				for (final BeritraLocation loc: locations.values()) {
					if (loc.getId() == id) {
						//Beritra Invasion Light Blue.
						BeritraService.getInstance().adventEffectSP(id);
						//Ereshkigal Invasion Light Blue.
						BeritraService.getInstance().adventEffectEreshSP(id);
						//Beritra Invasion Start 4.7
						BeritraService.getInstance().beritraInvasionMsg(id);
						//Ereshkigal Invasion Start 4.9.1
						BeritraService.getInstance().ereshkigalInvasionMsg(id);
						//Dredgion Defense.
						BeritraService.getInstance().dredgionDefenseMsg(id);
						BeritraService.getInstance().startBeritraInvasion(loc.getId());
					}
				}
			}
		}, 600000);
	}
}