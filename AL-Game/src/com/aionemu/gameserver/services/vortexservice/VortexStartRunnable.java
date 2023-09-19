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
package com.aionemu.gameserver.services.vortexservice;

import com.aionemu.gameserver.model.vortex.VortexLocation;
import com.aionemu.gameserver.services.VortexService;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class VortexStartRunnable implements Runnable
{
	private final int id;
	
	public VortexStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		Map<Integer, VortexLocation> locations = VortexService.getInstance().getVortexLocations();
		for (final VortexLocation loc : locations.values()) {
			if (loc.getId() == id) {
				VortexService.getInstance().startInvasion(loc.getId());
			}
		}
	}
}