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
package com.aionemu.gameserver.services.zorshivdredgionservice;

import com.aionemu.gameserver.model.zorshivdredgion.ZorshivDredgionLocation;
import com.aionemu.gameserver.services.ZorshivDredgionService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

public class DredgionStartRunnable implements Runnable
{
	private final int id;
	
	public DredgionStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Invasion Portal.
		ZorshivDredgionService.getInstance().adventPortalSP(id);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    //Invasion Lazer.
				ZorshivDredgionService.getInstance().adventDirectingSP(id);
			}
		}, 180000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				//Invasion Black Sky.
				ZorshivDredgionService.getInstance().adventControlSP(id);
			}
		}, 300000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
			    Map<Integer, ZorshivDredgionLocation> locations = ZorshivDredgionService.getInstance().getZorshivDredgionLocations();
				for (ZorshivDredgionLocation loc : locations.values()) {
					if (loc.getId() == id) {
						//Invasion Light Blue.
						ZorshivDredgionService.getInstance().adventEffectSP(id);
						//The Balaur Dredgion has appeared at levinshor.
						ZorshivDredgionService.getInstance().levinshorMsg(id);
						//The Balaur Dredgion has appeared at inggison.
						ZorshivDredgionService.getInstance().inggisonMsg(id);
						ZorshivDredgionService.getInstance().startZorshivDredgion(loc.getId());
					}
				}
			}
		}, 600000);
	}
}