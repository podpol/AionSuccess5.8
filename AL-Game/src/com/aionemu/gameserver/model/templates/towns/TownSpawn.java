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
package com.aionemu.gameserver.model.templates.towns;

import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;
import java.util.List;


/**
 * @author ViAl
 *
 */
@XmlType(name = "town_spawn")
public class TownSpawn {
	@XmlAttribute(name = "town_id")
	private int townId;
	@XmlElement(name = "town_level")
	private List<TownLevel> townLevels;
	
	private TIntObjectHashMap<TownLevel> townLevelsData = new TIntObjectHashMap<TownLevel>();
	
	/**
	 * @param u
	 * @param parent
	 */
	void afterUnmarshal(Unmarshaller u, Object parent) {
		townLevelsData.clear();

		for (TownLevel level : townLevels) {
			townLevelsData.put(level.getLevel(), level);
		}
		townLevels.clear();
		townLevels = null;
	}
	
	/**
	 * @return the townId
	 */
	public int getTownId() {
		return townId;
	}
	
	public TownLevel getSpawnsForLevel(int level) {
		return townLevelsData.get(level);
	}
	
	public Collection<TownLevel> getTownLevels() {
		return this.townLevelsData.valueCollection();
	}

}
