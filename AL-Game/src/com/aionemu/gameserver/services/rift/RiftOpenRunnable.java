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
package com.aionemu.gameserver.services.rift;

import com.aionemu.gameserver.model.rift.RiftLocation;
import com.aionemu.gameserver.services.RiftService;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import java.util.Map;

/****/
/** Author Rinzler (Encom)
/****/

public class RiftOpenRunnable implements Runnable
{
	private final int worldId;
	private final boolean guards;
	
	public RiftOpenRunnable(int worldId, boolean guards) {
		this.worldId = worldId;
		this.guards = guards;
	}
	
	@Override
    public void run() {
        Map<Integer, RiftLocation> locations = RiftService.getInstance().getRiftLocations();
        for (RiftLocation loc: locations.values()) {
            if (loc.getWorldId() == worldId) {
                RiftService.getInstance().openRifts(loc, guards);
            }
        }
        ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                RiftService.getInstance().closeRifts();
            }
        }, RiftService.getInstance().getDuration() * 3540 * 1000);
        RiftInformer.sendRiftsInfo(worldId);
    }
}