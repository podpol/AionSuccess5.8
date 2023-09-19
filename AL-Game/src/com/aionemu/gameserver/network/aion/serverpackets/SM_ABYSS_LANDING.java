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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.landing.LandingLocation;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.AbyssLandingService;

import java.util.Map;

/**
 * @author Ranastic & Lightning (Encom)
 */

public class SM_ABYSS_LANDING extends AionServerPacket
{
	private Map<Integer, LandingLocation> locations;
	
	public SM_ABYSS_LANDING() {
		locations = AbyssLandingService.getLandingLocations();
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		for (LandingLocation loc: locations.values()) {
			writeD(loc.getQuestPoints()); //Quest Completion.
			writeD(loc.getSiegePoints()); //Fortress Occupation.
			writeD(loc.getArtifactPoints()); //Artifact Occupation.
			writeD(loc.getBasePoints()); //Base Occupation.
			writeD(loc.getFacilityPoints()); //Facility Control.
			writeD(loc.getMonumentsPoints()); //Monument Control.
			writeD(loc.getCommanderPoints()); //Commander Defense.
		}
	}
}