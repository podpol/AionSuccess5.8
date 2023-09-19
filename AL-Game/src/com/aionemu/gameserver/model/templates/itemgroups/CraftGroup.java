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
package com.aionemu.gameserver.model.templates.itemgroups;

import com.aionemu.gameserver.model.templates.rewards.CraftReward;
import javolution.util.FastMap;
import org.apache.commons.lang.math.IntRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Rolandas
 */
public abstract class CraftGroup extends BonusItemGroup {

	private FastMap<Integer, FastMap<IntRange, List<CraftReward>>> dataHolder;

	public ItemRaceEntry[] getRewards(Integer skillId) {
		if (!dataHolder.containsKey(skillId))
			return new ItemRaceEntry[0];
		List<CraftReward> result = new ArrayList<CraftReward>();
		for (List<CraftReward> items : dataHolder.get(skillId).values())
			result.addAll(items);
		return result.toArray(new ItemRaceEntry[0]);
	}

	public ItemRaceEntry[] getRewards(Integer skillId, Integer skillPoints) {
		if (!dataHolder.containsKey(skillId))
			return new ItemRaceEntry[0];
		List<CraftReward> result = new ArrayList<CraftReward>();
		for (Entry<IntRange, List<CraftReward>> entry : dataHolder.get(skillId).entrySet())
			if (entry.getKey().containsInteger(skillPoints)) {
				result.addAll(entry.getValue());
			}
		return result.toArray(new ItemRaceEntry[0]);
	}

	/**
	 * @return the dataHolder
	 */
	public FastMap<Integer, FastMap<IntRange, List<CraftReward>>> getDataHolder() {
		return dataHolder;
	}

	/**
	 * @param dataHolder the dataHolder to set
	 */
	public void setDataHolder(FastMap<Integer, FastMap<IntRange, List<CraftReward>>> dataHolder) {
		this.dataHolder = dataHolder;
	}
}
