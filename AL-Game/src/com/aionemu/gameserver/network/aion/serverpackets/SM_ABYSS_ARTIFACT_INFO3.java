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

import com.aionemu.gameserver.model.siege.ArtifactLocation;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.SiegeService;

import java.util.ArrayList;
import java.util.Collection;

public class SM_ABYSS_ARTIFACT_INFO3 extends AionServerPacket
{
	private boolean teleportStatus;
	private Collection<ArtifactLocation> locations;
	
	public SM_ABYSS_ARTIFACT_INFO3(Collection<ArtifactLocation> collection) {
        this.locations = collection;
    }
	
    public SM_ABYSS_ARTIFACT_INFO3(int loc) {
        locations = new ArrayList<ArtifactLocation>();
        locations.add(SiegeService.getInstance().getArtifact(loc));
    }
	
    public SM_ABYSS_ARTIFACT_INFO3(int locationId, boolean teleportStatus) {
        locations = new ArrayList<ArtifactLocation>();
        locations.add(SiegeService.getInstance().getArtifact(locationId));
        this.teleportStatus = teleportStatus;
    }
	
	@Override
	protected void writeImpl(AionConnection con) {
		writeH(locations.size());
		for (ArtifactLocation artifact : locations) {
			writeD(artifact.getLocationId() * 10 + 1);
			writeC(artifact.getStatus().getValue());
			writeD(0);
			writeC(teleportStatus ? 1 : 0);
		}
	}
}