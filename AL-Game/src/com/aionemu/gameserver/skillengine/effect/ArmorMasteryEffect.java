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
package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.model.stats.calc.functions.StatArmorMasteryFunction;
import com.aionemu.gameserver.model.templates.item.ArmorType;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArmorMasteryEffect")
public class ArmorMasteryEffect extends BuffEffect {

	@XmlAttribute(name = "armor")
	private ArmorType armorType;

	@Override
	public void startEffect(Effect effect) {
		if (change == null)
			return;

		List<IStatFunction> modifiers = getModifiers(effect);
		List<IStatFunction> masteryModifiers = new ArrayList<IStatFunction>(modifiers.size());
		for (IStatFunction modifier : modifiers) {
			masteryModifiers.add(new StatArmorMasteryFunction(armorType, modifier.getName(), modifier.getValue(), modifier.isBonus()));
		}
		if (masteryModifiers.size() > 0) {
			effect.getEffected().getGameStats().addEffect(effect, masteryModifiers);
		}
	}
}
