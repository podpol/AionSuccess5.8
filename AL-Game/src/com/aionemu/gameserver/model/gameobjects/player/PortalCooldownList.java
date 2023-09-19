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
package com.aionemu.gameserver.model.gameobjects.player;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.network.aion.serverpackets.SM_INSTANCE_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import javolution.util.FastMap;

public class PortalCooldownList
{
    private Player owner;
    private FastMap<Integer, PortalCooldownItem> portalCooldowns;
    
    PortalCooldownList(Player owner) {
        this.owner = owner;
    }
    
    public boolean isPortalUseDisabled(int worldId) {
        if (portalCooldowns == null || !portalCooldowns.containsKey(worldId)) {
            return false;
        }
        PortalCooldownItem coolDown = portalCooldowns.get(worldId);
        if (coolDown == null) {
            return false;
        } if (DataManager.INSTANCE_COOLTIME_DATA.getInstanceEntranceCountByWorldId(worldId) == 0 || coolDown.getEntryCount() < DataManager.INSTANCE_COOLTIME_DATA.getInstanceEntranceCountByWorldId(worldId)) {
            return false;
        } if (coolDown.getCooldown() < System.currentTimeMillis()) {
            portalCooldowns.remove(worldId);
            return false;
        }
        return true;
    }
    
    public long getPortalCooldown(int worldId) {
        if (portalCooldowns == null || !portalCooldowns.containsKey(worldId)) {
            return 0;
        }
        return portalCooldowns.get(worldId).getCooldown();
    }
    
    public long getEntryCount(int worldId) {
        if (portalCooldowns == null || !portalCooldowns.containsKey(worldId)) {
            return 0;
        }
        return portalCooldowns.get(worldId).getEntryCount();
    }
    
    public PortalCooldownItem getPortalCooldownItem(int worldId) {
        if (portalCooldowns == null || !portalCooldowns.containsKey(worldId)) {
            return null;
        }
        return portalCooldowns.get(worldId);
    }
    
    public FastMap<Integer, PortalCooldownItem> getPortalCoolDowns() {
        return portalCooldowns;
    }
    
    public void setPortalCoolDowns(FastMap<Integer, PortalCooldownItem> portalCoolDowns) {
        this.portalCooldowns = portalCoolDowns;
    }
    
    public void addPortalCooldown(int worldId, int entryCount, long useDelay) {
        if (portalCooldowns == null) {
            portalCooldowns = new FastMap<Integer, PortalCooldownItem>();
        }
        portalCooldowns.put(worldId, new PortalCooldownItem(worldId, entryCount, useDelay));
        if (owner.isInTeam()) {
            owner.getCurrentTeam().sendPacket(new SM_INSTANCE_INFO(owner, worldId));
        } else {
            PacketSendUtility.sendPacket(owner, new SM_INSTANCE_INFO(owner, worldId));
        }
    }
    
    public void removePortalCoolDown(int worldId) {
        if (portalCooldowns != null) {
            portalCooldowns.remove(worldId);
        } if (owner.isInTeam()) {
            owner.getCurrentTeam().sendPacket(new SM_INSTANCE_INFO(owner, worldId));
        } else {
            PacketSendUtility.sendPacket(owner, new SM_INSTANCE_INFO(owner, worldId));
            //You can enter %0 area now.
            PacketSendUtility.sendPacket(owner, new SM_SYSTEM_MESSAGE(1400031, worldId));
        }
    }
    
    public void addEntry(int worldId) {
        int floor = owner.getFloor();
        if (floor != 0) {
            return;
        } if (portalCooldowns != null && portalCooldowns.containsKey(worldId)) {
            portalCooldowns.get(worldId).setEntryCount(portalCooldowns.get(worldId).getEntryCount() +1);
        } if (owner.isInTeam()) {
            owner.getCurrentTeam().sendPacket(new SM_INSTANCE_INFO(owner, worldId));
        } else {
            PacketSendUtility.sendPacket(owner, new SM_INSTANCE_INFO(owner, worldId));
        }
    }
    
    public void reduceEntry(int worldId) {
        if (portalCooldowns != null && portalCooldowns.containsKey(worldId)) {
            portalCooldowns.get(worldId).setEntryCount(portalCooldowns.get(worldId).getEntryCount() -1);
        } if (portalCooldowns.get(worldId).getEntryCount() == 0) {
            removePortalCoolDown(worldId);
            return;
        } if (owner.isInTeam()) {
            owner.getCurrentTeam().sendPacket(new SM_INSTANCE_INFO(owner, worldId));
        } else {
            PacketSendUtility.sendPacket(owner, new SM_INSTANCE_INFO(owner, worldId));
        }
    }
    
    public boolean hasCooldowns() {
        return portalCooldowns != null && portalCooldowns.size() > 0;
    }
    
    public int size() {
        return portalCooldowns != null ? portalCooldowns.size() : 0;
    }
}