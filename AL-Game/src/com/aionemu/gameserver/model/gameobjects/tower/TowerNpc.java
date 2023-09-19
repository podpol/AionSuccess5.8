package com.aionemu.gameserver.model.gameobjects.tower;

import com.aionemu.gameserver.controllers.NpcController;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.npc.NpcTemplate;
import com.aionemu.gameserver.model.templates.spawns.towerofeternityspawns.TowerOfEternitySpawnTemplate;

/**
 * Created by Wnkrz on 27/08/2017.
 */

public class TowerNpc extends Npc
{
    private int towerId;

    public TowerNpc(int objId, NpcController controller, TowerOfEternitySpawnTemplate spawnTemplate, NpcTemplate objectTemplate) {
        super(objId, controller, spawnTemplate, objectTemplate);
        this.towerId = spawnTemplate.getId();
    }
	
    public int getEternityTowerId() {
        return towerId;
    }
	
    @Override
    public TowerOfEternitySpawnTemplate getSpawn() {
        return (TowerOfEternitySpawnTemplate) super.getSpawn();
    }
	
    @Override
    public boolean isEnemyFrom(Creature creature) {
        if (creature instanceof TowerNpc) {
            return true;
        } else {
            return super.isEnemyFrom(creature);
        }
    }
}