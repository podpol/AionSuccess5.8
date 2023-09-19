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
package com.aionemu.gameserver.model;

import javax.xml.bind.annotation.XmlEnum;

/**
 * @author Alcapwnd
 */
@XmlEnum
public enum AttendType {

    NONE(0), 
    BASIC(1),
    ANNIVERSARY(2);

	private int id;

	private AttendType(int id) {
		this.id = id;
	}

    public static AttendType getLoginTypeById(int id) {
        for (AttendType attendType : values()) {
            if (attendType.getId() == id) {
                return attendType;
            }
        }
        return AttendType.NONE;
    }

	public int getId() {
		return id;
	}
}
