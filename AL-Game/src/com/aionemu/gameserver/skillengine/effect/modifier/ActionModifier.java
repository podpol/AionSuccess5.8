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
package com.aionemu.gameserver.skillengine.effect.modifier;

import com.aionemu.gameserver.skillengine.change.Func;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionModifier")
public abstract class ActionModifier {

	@XmlAttribute
	protected int delta;
	@XmlAttribute(required = true)
	protected int value;

	@XmlAttribute
	protected Func mode = Func.ADD;

	/**
	 * Applies modifier to original value
	 * 
	 * @param effect
	 * @param originalValue
	 * @return int
	 */
	public abstract int analyze(Effect effect);

	/**
	 * Performs check of condition
	 * 
	 * @param effect
	 * @return true or false
	 */
	public abstract boolean check(Effect effect);

	public Func getFunc() {
		return mode;
	}
}
