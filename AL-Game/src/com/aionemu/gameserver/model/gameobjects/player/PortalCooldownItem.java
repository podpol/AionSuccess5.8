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

public class PortalCooldownItem
{
	private int worldId;
    private int entryCount;
    private long cooldown;
	
    public PortalCooldownItem(int worldId, int entryCount, long cooldown) {
        this.worldId = worldId;
        this.entryCount = entryCount;
        this.cooldown = cooldown;
    }
	
    public int getWorldId() {
        return worldId;
    }
    public int getEntryCount() {
        return entryCount;
    }
    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }
    public long getCooldown() {
        return cooldown;
    }
    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}