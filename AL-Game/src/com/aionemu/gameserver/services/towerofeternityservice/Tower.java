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
package com.aionemu.gameserver.services.towerofeternityservice;

import com.aionemu.gameserver.model.towerofeternity.TowerOfEternityLocation;
import com.aionemu.gameserver.model.towerofeternity.TowerOfEternityStateType;

/**
 * Created by Wnkrz on 22/08/2017.
 */

public class Tower extends TowerOfEternity<TowerOfEternityLocation>
{
    public Tower(TowerOfEternityLocation towerOfEternity) {
        super(towerOfEternity);
    }
	
    @Override
    protected void startTowerOfEternity() {
        getTowerOfEternityLocation().setActiveTowerOfEternity(this);
        despawn();
        spawn(TowerOfEternityStateType.OPEN);
    }
	
    @Override
    protected void stopTowerOfEternity() {
        getTowerOfEternityLocation().setActiveTowerOfEternity(null);
        despawn();
        spawn(TowerOfEternityStateType.CLOSED);
    }
}