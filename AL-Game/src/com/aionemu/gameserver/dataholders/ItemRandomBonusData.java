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

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.model.items.RandomBonusResult;
import com.aionemu.gameserver.model.templates.item.bonuses.RandomBonus;
import com.aionemu.gameserver.model.templates.item.bonuses.StatBonusType;
import com.aionemu.gameserver.model.templates.stats.ModifiersTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"randomBonuses"})
@XmlRootElement(name = "random_bonuses")
public class ItemRandomBonusData
{
	@XmlElement(name = "random_bonus", required = true)
	protected List<RandomBonus> randomBonuses;
	
	@XmlTransient
	private TIntObjectHashMap<RandomBonus> inventoryRandomBonusData = new TIntObjectHashMap<RandomBonus>();
	
	@XmlTransient
	private TIntObjectHashMap<RandomBonus> polishRandomBonusData = new TIntObjectHashMap<RandomBonus>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (RandomBonus bonus : randomBonuses) {
			getBonusMap(bonus.getBonusType()).put(bonus.getId(), bonus);
		}
		randomBonuses.clear();
		randomBonuses = null;
	}
	
	private TIntObjectHashMap<RandomBonus> getBonusMap(StatBonusType bonusType) {
		if (bonusType == StatBonusType.INVENTORY)
			return inventoryRandomBonusData;
		return polishRandomBonusData;
	}
	
	public RandomBonusResult getRandomModifiers(StatBonusType bonusType, int rndOptionSet) {
		RandomBonus bonus = getBonusMap(bonusType).get(rndOptionSet);
		if (bonus == null)
			return null;
		List<ModifiersTemplate> modifiersGroup = bonus.getModifiers();
		int chance = Rnd.get(10000);
		int current = 0;
		ModifiersTemplate template = null;
		int number = 0;
		for (int i = 0; i < modifiersGroup.size(); i++) {
			ModifiersTemplate modifiers = modifiersGroup.get(i);
			current += modifiers.getChance() * 100;
			if (current >= chance) {
				template = modifiers;
				number = i + 1;
				break;
			}
		}
		return template == null ? null : new RandomBonusResult(template, number);
	}
	
	public ModifiersTemplate getTemplate(StatBonusType bonusType, int rndOptionSet, int number) {
		RandomBonus bonus = getBonusMap(bonusType).get(rndOptionSet);
		if (bonus == null)
			return null;
		return bonus.getModifiers().get(number - 1);
	}
	
	public int size() {
		return inventoryRandomBonusData.size() + polishRandomBonusData.size();
	}
}