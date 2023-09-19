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

public enum PlayerHouseOwnerFlags
{
    IS_OWNER(1 << 0),
    HAS_OWNER(1 << 0),
    BUY_STUDIO_ALLOWED(1 << 1),
    SINGLE_HOUSE(1 << 1),
    BIDDING_ALLOWED(1 << 2),
    HOUSE_OWNER((IS_OWNER.getId() | BIDDING_ALLOWED.getId()) & ~BUY_STUDIO_ALLOWED.getId()),
    SELLING_HOUSE(IS_OWNER.getId() | BUY_STUDIO_ALLOWED.getId()),
    
	//Player Status
    SOLD_HOUSE(BIDDING_ALLOWED.getId() | BUY_STUDIO_ALLOWED.getId());
    private byte id;
	
    private PlayerHouseOwnerFlags(int id) {
        this.id = (byte) (id & 0xFF);
    }
	
    public byte getId() {
        return id;
    }
}