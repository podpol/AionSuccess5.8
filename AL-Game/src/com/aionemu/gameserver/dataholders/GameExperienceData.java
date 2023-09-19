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

import com.aionemu.gameserver.model.templates.event.GameExperience;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name = "game_experience_items")
@XmlAccessorType(XmlAccessType.FIELD)
public class GameExperienceData
{
	@XmlElement(name="game_experience_item")
	private List<GameExperience> glist;
	
	@XmlTransient
	private TIntObjectHashMap<GameExperience> experienceData = new TIntObjectHashMap<GameExperience>();
	
	@XmlTransient
	private Map<Integer, GameExperience> experienceDataMap = new HashMap<Integer, GameExperience>(1);
	
	void afterUnmarshal(Unmarshaller paramUnmarshaller, Object paramObject) {
		for (GameExperience gameExperience: glist) {
			experienceData.put(gameExperience.getId(), gameExperience);
			experienceDataMap.put(gameExperience.getId(), gameExperience);
		}
	}
	
	public int size() {
		return experienceData.size();
	}
	
	public GameExperience getGameExperienceId(int id) {
		return experienceData.get(id);
	}
	
	public Map<Integer, GameExperience> getAll() {
		return experienceDataMap;
	}
}