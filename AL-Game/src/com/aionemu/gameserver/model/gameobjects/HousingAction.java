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
package com.aionemu.gameserver.model.gameobjects;

public enum HousingAction
{
    UNK(-1),
    ENTER_DECORATION(1),
    EXIT_DECORATION(2),
    ADD_ITEM(3),
    DELETE_ITEM(4),
    SPAWN_OBJECT(5),
    MOVE_OBJECT(6),
    DESPAWN_OBJECT(7),
    ENTER_RENOVATION(14),
    EXIT_RENOVATION(15),
    CHANGE_APPEARANCE(16);
	
    private int id;
	
    private HousingAction(int id) {
        this.id = id;
    }
	
    public int getTypeId() {
        return id;
    }
	
    public static HousingAction getActionTypeById(int id) {
        for (HousingAction actionType : values()) {
            if (actionType.getTypeId() == id) {
                return actionType;
            }
        }
        return UNK;
    }
}