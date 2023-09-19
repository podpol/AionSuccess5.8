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
package com.aionemu.gameserver.skillengine.change;

import com.aionemu.gameserver.model.stats.container.StatEnum;
import com.aionemu.gameserver.skillengine.condition.Conditions;

import javax.xml.bind.annotation.*;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Change")
public class Change {

	@XmlAttribute(required = true)
	private StatEnum stat;
	@XmlAttribute(required = true)
	private Func func;
	@XmlAttribute(required = true)
	private int value;
	@XmlAttribute
	private int delta;
	@XmlElement(name = "conditions")
	private Conditions conditions;

	public final StatEnum getStat() {
		return stat;
	}

	public final Func getFunc() {
		return func;
	}

	public final int getValue() {
		return value;
	}

	public final int getDelta() {
		return delta;
	}

	public final Conditions getConditions() {
		return conditions;
	}

}
