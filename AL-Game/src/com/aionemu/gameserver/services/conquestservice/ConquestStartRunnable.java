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
package com.aionemu.gameserver.services.conquestservice;

import com.aionemu.gameserver.model.conquest.ConquestLocation;
import com.aionemu.gameserver.services.ConquestService;

import java.util.Map;
/**
 * @author Rinzler (Encom)
 */

public class ConquestStartRunnable implements Runnable
{
	private final int id;
	
	public ConquestStartRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		//Shugo Emperor's Vault 4.7.5 is now open !!!
		ConquestService.getInstance().emperorVaultMsg(id);
		//Emperor Trillirunerk's Safe 4.9.1 is now open !!!
		ConquestService.getInstance().trillirunerkSafeMsg(id);
		//Smoldering Fire Temple 5.1 is now open !!!
		ConquestService.getInstance().smolderingFireTempleMsg(id);
		//Kumuki Cave 5.3 is now open !!!
		ConquestService.getInstance().kumukiCaveMsg(id);
		//IDEventDefMsg 5.6 is now open !!!
		ConquestService.getInstance().IDEventDefMsg(id);
		//Tiamaranta's Eye is now open !!!
		ConquestService.getInstance().tiamarantaMsg(id);
		//Conquest/Offering a rare monster appeared !!!
		ConquestService.getInstance().conquestOfferingMsg(id);
		Map<Integer, ConquestLocation> locations = ConquestService.getInstance().getConquestLocations();
		for (final ConquestLocation loc : locations.values()) {
			if (loc.getId() == id) {
				ConquestService.getInstance().startConquest(loc.getId());
			}
		}
	}
}