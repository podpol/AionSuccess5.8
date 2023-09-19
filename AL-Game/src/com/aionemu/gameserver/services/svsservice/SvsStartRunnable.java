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
package com.aionemu.gameserver.services.svsservice;

import com.aionemu.gameserver.model.svs.SvsLocation;
import com.aionemu.gameserver.services.SvsService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class SvsStartRunnable implements Runnable
{
	private final int id;
	
	public SvsStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Advance Corridor [Transidium Annex].
		SvsService.getInstance().transidiumAnnexMsg(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Advance Corridor [Transidium Annex].
				SvsService.getInstance().advanceCorridorSP(id);
			}
		}, 480000);
		Map<Integer, SvsLocation> locations = SvsService.getInstance().getSvsLocations();
		for (final SvsLocation loc : locations.values()) {
			if (loc.getId() == id) {
				SvsService.getInstance().startSvs(loc.getId());
			}
		}
	}
}