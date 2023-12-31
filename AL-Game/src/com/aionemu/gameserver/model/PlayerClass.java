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

import com.aionemu.gameserver.model.gameobjects.player.Player;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum PlayerClass
{
	WARRIOR(0, true),
	GLADIATOR(1),
	TEMPLAR(2),
	SCOUT(3, true),
	ASSASSIN(4),
	RANGER(5),
	MAGE(6, true),
	SORCERER(7),
	SPIRIT_MASTER(8),
	PRIEST(9, true),
	CLERIC(10),
	CHANTER(11),

	//News Class 4.3/4.5
	TECHNIST(12, true),
	AETHERTECH(13),
	GUNSLINGER(14),
	MUSE(15, true),
	SONGWEAVER(16),
	ALL(17);

	private byte classId;
	private int idMask;
	private boolean startingClass;

	private PlayerClass(int classId) {
		this(classId, false);
	}

	private PlayerClass(int classId, boolean startingClass) {
		this.classId = (byte) classId;
		this.startingClass = startingClass;
		this.idMask = (int) Math.pow(2, classId);
	}

	public byte getClassId() {
		return classId;
	}

	public static PlayerClass getPlayerClassById(byte classId) {
		for (PlayerClass pc : values()) {
			if (pc.getClassId() == classId)
				return pc;
		}
		throw new IllegalArgumentException("There is no player class with id " + classId);
	}

	public boolean isStartingClass() {
		return startingClass;
	}

	public static PlayerClass getStartingClassFor(PlayerClass pc) {
		switch (pc) {
		case ASSASSIN:
		case RANGER:
			return SCOUT;
		case GLADIATOR:
		case TEMPLAR:
			return WARRIOR;
		case CHANTER:
		case CLERIC:
			return PRIEST;
		case SORCERER:
		case SPIRIT_MASTER:
			return MAGE;
			//News Class 4.3/4.5
		case SONGWEAVER:
			return MUSE;
		case AETHERTECH:
		case GUNSLINGER:
			return TECHNIST;
		case SCOUT:
		case WARRIOR:
		case PRIEST:
		case MAGE:
		case MUSE:
		case TECHNIST:
			return pc;
		default:
			throw new IllegalArgumentException("Given player class is starting class: " + pc);
		}
	}

	public static PlayerClass getPlayerClassByString(String fieldName) {
		for (PlayerClass pc: values()) {
			if (pc.toString().equals(fieldName))
				return pc;
		}
		return null;
	}

	public int getMask() {
		return idMask;
	}

	public String getClassType(Player player) {
		String type = null;
		switch(player.getPlayerClass()) {
		case TEMPLAR:
		case ASSASSIN:
		case RANGER:
		case GLADIATOR:
		case GUNSLINGER:
			type = "PHYSICAL";
			break;
		case SORCERER:
		case CHANTER:
		case CLERIC:
		case SPIRIT_MASTER:
		case SONGWEAVER:
		case AETHERTECH:
			type = "MAGICAL";
			break;
		default: break;
		}
		return type;
	}
}