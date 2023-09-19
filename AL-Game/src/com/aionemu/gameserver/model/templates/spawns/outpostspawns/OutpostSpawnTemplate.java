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
package com.aionemu.gameserver.model.templates.spawns.outpostspawns;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.templates.spawns.SpawnGroup2;
import com.aionemu.gameserver.model.templates.spawns.SpawnSpotTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;

/**
 * Created by Wnkrz on 27/08/2017.
 */

public class OutpostSpawnTemplate extends SpawnTemplate
{
    private int id;
    private Race outpostRace;
	
    public OutpostSpawnTemplate(SpawnGroup2 spawnGroup, SpawnSpotTemplate spot) {
        super(spawnGroup, spot);
    }
	
    public OutpostSpawnTemplate(SpawnGroup2 spawnGroup, float x, float y, float z, byte heading, int randWalk, String walkerId, int entityId, int fly) {
        super(spawnGroup, x, y, z, heading, randWalk, walkerId, entityId, fly);
    }
	
    public int getId() {
        return id;
    }
	
    public void setId(int id) {
        this.id = id;
    }
	
    public Race getOutpostRace() {
        return outpostRace;
    }
	
    public void setOutpostRace(Race baseRace) {
        this.outpostRace = baseRace;
    }
}