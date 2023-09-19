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
package com.aionemu.gameserver.model.templates.materials;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.Summon;
import com.aionemu.gameserver.model.gameobjects.player.Player;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rolandas
 */
@XmlType(name = "MaterialTarget")
@XmlEnum
public enum MaterialTarget {

	ALL,
	NPC,
	PLAYER,
	PLAYER_WITH_PET;

	public String value() {
		return name();
	}

	public static MaterialTarget fromValue(String value) {
		return valueOf(value);
	}

	public boolean isTarget(Creature creature) {
		if (this == ALL)
			return true;
		if (this == NPC)
			return creature instanceof Npc;
		if (this == PLAYER)
			return creature instanceof Player;
		if (this == PLAYER_WITH_PET)
			return creature instanceof Player || creature instanceof Summon && ((Summon) creature).getMaster() != null;
		return false;
	}

}
