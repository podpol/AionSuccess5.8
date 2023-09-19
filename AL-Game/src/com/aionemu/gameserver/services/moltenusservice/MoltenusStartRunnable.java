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
package com.aionemu.gameserver.services.moltenusservice;

import com.aionemu.gameserver.model.moltenus.MoltenusLocation;
import com.aionemu.gameserver.services.MoltenusService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class MoltenusStartRunnable implements Runnable
{
	private final int id;
	
	public MoltenusStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Enraged Sulfur Guardian will appear in 10 minutes.
		MoltenusService.getInstance().sulfurFortressMsg(id);
		//Enraged Western Guardian will appear in 10 minutes.
		MoltenusService.getInstance().westernFortressMsg(id);
		//Enraged Eastern Guardian will appear in 10 minutes.
		MoltenusService.getInstance().easternFortressMsg(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    Map<Integer, MoltenusLocation> locations = MoltenusService.getInstance().getMoltenusLocations();
				for (final MoltenusLocation loc: locations.values()) {
					if (loc.getId() == id) {
						MoltenusService.getInstance().startMoltenus(loc.getId());
					}
				}
			}
		}, 600000);
	}
}