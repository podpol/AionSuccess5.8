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

package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.chest.ChestTemplate;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wakizashi
 */
@XmlRootElement(name = "chest_templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChestData {

	@XmlElement(name = "chest")
	private List<ChestTemplate> chests;

	/** A map containing all npc templates */
	private TIntObjectHashMap<ChestTemplate> chestData = new TIntObjectHashMap<ChestTemplate>();
	private TIntObjectHashMap<ArrayList<ChestTemplate>> instancesMap = new TIntObjectHashMap<ArrayList<ChestTemplate>>();
	private THashMap<String, ChestTemplate> namedChests = new THashMap<String, ChestTemplate>();

	/**
	 * - Inititialize all maps for subsequent use - Don't nullify initial chest list as it will be used during reload
	 * 
	 * @param u
	 * @param parent
	 */
	void afterUnmarshal(Unmarshaller u, Object parent) {
		chestData.clear();
		instancesMap.clear();
		namedChests.clear();

		for (ChestTemplate chest : chests) {
			chestData.put(chest.getNpcId(), chest);
			if (chest.getName() != null && !chest.getName().isEmpty())
				namedChests.put(chest.getName(), chest);
		}
	}

	public int size() {
		return chestData.size();
	}

	/**
	 * @param npcId
	 * @return
	 */
	public ChestTemplate getChestTemplate(int npcId) {
		return chestData.get(npcId);
	}

	/**
	 * @return the chests
	 */
	public List<ChestTemplate> getChests() {
		return chests;
	}

	/**
	 * @param chests
	 *          the chests to set
	 */
	public void setChests(List<ChestTemplate> chests) {
		this.chests = chests;
		afterUnmarshal(null, null);
	}
}
