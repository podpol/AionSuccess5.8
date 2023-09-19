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
package com.aionemu.gameserver.services.teleport;

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.ObserverType;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_HOTSPOT_TELEPORT;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.skillengine.effect.AbnormalState;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ranastic
 */

public class HotspotTeleportService
{
	private static final Logger log = LoggerFactory.getLogger(HotspotTeleportService.class);
	
	public static HotspotTeleportService getInstance() {
        return SingletonHolder.instance;
    }
	
	private HotspotTeleportService() {
        int hotspotList = DataManager.HOTSPOT_LOCATION_DATA.size();
        log.info(hotspotList + " <Hotspot Location> loaded");
    }
	
	public void doTeleport(final Player player, final int teleportId, final int price) {
		final int worldId = DataManager.HOTSPOT_LOCATION_DATA.getHotspotlocationTemplate(teleportId).getMapId();
		final float getX = DataManager.HOTSPOT_LOCATION_DATA.getHotspotlocationTemplate(teleportId).getX();
		final float getY = DataManager.HOTSPOT_LOCATION_DATA.getHotspotlocationTemplate(teleportId).getY();
		final float getZ = DataManager.HOTSPOT_LOCATION_DATA.getHotspotlocationTemplate(teleportId).getZ();
		//KR - Update December 16th 2015
		//- Base teleportation cooldown has been reduced from 10min to 1min.
		final int cooldown = 60; //1 Minute = 60 Seconds
		player.getController().addTask(TaskId.HOTSPOT_TELEPORT, ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
            	PacketSendUtility.broadcastPacketAndReceive(player, new SM_HOTSPOT_TELEPORT(3, player.getObjectId(), teleportId));
            	player.getController().addTask(TaskId.HOTSPOT_TELEPORT, ThreadPoolManager.getInstance().schedule(new Runnable() {
                    @Override
                    public void run() {
                    	TeleportService2.teleportTo(player, worldId, getX, getY, getZ);
						player.getInventory().decreaseKinah(price);
						PacketSendUtility.sendPacket(player, new SM_HOTSPOT_TELEPORT(player, 3, teleportId, cooldown));
                    }
            	}, 1000));
            	ActionObserver attackedObserver = new ActionObserver(ObserverType.ATTACKED) {
                    @Override
                    public void attacked(Creature creature) {
                    	player.getController().cancelTask(TaskId.HOTSPOT_TELEPORT);
                    }
                };
                player.getObserveController().addObserver(attackedObserver);
                player.setHotTeleObservers(attackedObserver);
                ActionObserver rideObserver = new ActionObserver(ObserverType.ABNORMALSETTED) {
                    @Override
                    public void abnormalsetted(AbnormalState state) {
                        if (state.getId() > 0) {
                        	PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402444));
                        	player.getController().cancelTask(TaskId.HOTSPOT_TELEPORT);
                        }
                    }
                };
                player.getObserveController().addObserver(rideObserver);
                player.setHotTeleObservers(rideObserver);
                ActionObserver dotAttackedObserver = new ActionObserver(ObserverType.DOT_ATTACKED) {
                    @Override
                    public void dotattacked(Creature creature, Effect dotEffect) {
                    	player.getController().cancelTask(TaskId.HOTSPOT_TELEPORT);
                    }
                };
                player.getObserveController().addObserver(dotAttackedObserver);
                player.setHotTeleObservers(dotAttackedObserver);
            }
		}, 10000));
		PacketSendUtility.broadcastPacketAndReceive(player, new SM_HOTSPOT_TELEPORT(1, player.getObjectId(), teleportId));
	}
	
	@SuppressWarnings("synthetic-access")
    private static class SingletonHolder {
        protected static final HotspotTeleportService instance = new HotspotTeleportService();
    }
}