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
package com.aionemu.gameserver.model.gameobjects.outpost;

import com.aionemu.gameserver.controllers.NpcController;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.npc.NpcTemplate;
import com.aionemu.gameserver.model.templates.spawns.outpostspawns.OutpostSpawnTemplate;

/**
 * Created by Wnkrz on 27/08/2017.
 */

public class OutpostNpc extends Npc
{
    private int outpostId;
	
    public OutpostNpc(int objId, NpcController controller, OutpostSpawnTemplate spawnTemplate, NpcTemplate objectTemplate) {
        super(objId, controller, spawnTemplate, objectTemplate);
        this.outpostId = spawnTemplate.getId();
    }
	
    public int getOutpostId() {
        return outpostId;
    }
	
    @Override
    public OutpostSpawnTemplate getSpawn() {
        return (OutpostSpawnTemplate) super.getSpawn();
    }
	
    @Override
    public boolean isEnemyFrom(Creature creature) {
        if (creature instanceof OutpostNpc) {
            return true;
        } else {
            return super.isEnemyFrom(creature);
        }
    }
}