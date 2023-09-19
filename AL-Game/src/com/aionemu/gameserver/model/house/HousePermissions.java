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
package com.aionemu.gameserver.model.house;

public enum HousePermissions
{
    NOT_SET(0),
    SHOW_OWNER(1 << 0),
    DOOR_OPENED_ALL(1 << 8),
    DOOR_OPENED_FRIENDS(2 << 8),
    DOOR_CLOSED(3 << 8);
	
    private int value;
	
    private HousePermissions(int value) {
        this.value = value;
    }
	
    public byte getPacketValue() {
        int result = value;
        if (value > 1) {
            result >>= 8;
        }
        return (byte) result;
    }
	
    public static HousePermissions getPacketDoorState(int value) {
        value <<= 8;
        for (HousePermissions perm : HousePermissions.values()) {
            if (value == perm.value) {
                return perm;
            }
        }
        return NOT_SET;
    }
	
    public static HousePermissions getDoorState(int value) {
        value &= 0xFF00;
        for (HousePermissions perm : HousePermissions.values()) {
            if (value == perm.value) {
                return perm;
            }
        }
        return NOT_SET;
    }
	
    public static int setDoorState(int value, HousePermissions doorState) {
        int state = doorState.value & 0xFF00;
        return (value & 0x00FF) | state;
    }
	
    public static HousePermissions getNoticeState(int value) {
        if ((value & SHOW_OWNER.value) == SHOW_OWNER.value) {
            return SHOW_OWNER;
        }
        return NOT_SET;
    }
	
    public static int setNoticeState(int value, HousePermissions noticeState) {
        if (noticeState == NOT_SET) {
            return value & 0xFF00;
        }
        int state = noticeState.value & 0xFF;
        return state | value;
    }
}