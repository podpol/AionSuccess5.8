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
package com.aionemu.gameserver.model.templates.stats;

import com.aionemu.gameserver.model.stats.calc.functions.StatFunction;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author xavier
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "modifiers")
public class ModifiersTemplate {

	@XmlElements({ 
		@XmlElement(name = "sub", type = com.aionemu.gameserver.model.stats.calc.functions.StatSubFunction.class),
		@XmlElement(name = "add", type = com.aionemu.gameserver.model.stats.calc.functions.StatAddFunction.class),
		@XmlElement(name = "rate", type = com.aionemu.gameserver.model.stats.calc.functions.StatRateFunction.class),
		@XmlElement(name = "set", type = com.aionemu.gameserver.model.stats.calc.functions.StatSetFunction.class) })
	private List<StatFunction> modifiers;

	@XmlAttribute
	private float chance = 100;
	
	@XmlAttribute
	private int level;

	public List<StatFunction> getModifiers() {
		return modifiers;
	}

	public float getChance() {
		return chance;
	}
	
	public float getLevel() {
		return level;
	}
}
