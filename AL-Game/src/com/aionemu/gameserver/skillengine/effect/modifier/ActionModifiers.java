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

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionModifiers")
public class ActionModifiers {

	@XmlElements({ 
		@XmlElement(name = "frontdamage", type = FrontDamageModifier.class),
		@XmlElement(name = "backdamage", type = BackDamageModifier.class), 
		@XmlElement(name = "abnormaldamage", type = AbnormalDamageModifier.class),
		@XmlElement(name = "targetrace", type = TargetRaceDamageModifier.class),
		@XmlElement(name = "targetclass", type = TargetClassDamageModifier.class) })
	protected List<ActionModifier> actionModifiers;

	/**
	 * Gets the value of the actionModifiers property.
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link StumbleDamageModifier } {@link FrontDamageModifier }
	 * {@link BackDamageModifier } {@link StunDamageModifier } {@link PoisonDamageModifier } {@link TargetRaceDamageModifier }
	 */
	public List<ActionModifier> getActionModifiers() {
		if (actionModifiers == null) {
			actionModifiers = new ArrayList<ActionModifier>();
		}
		return this.actionModifiers;
	}
}
