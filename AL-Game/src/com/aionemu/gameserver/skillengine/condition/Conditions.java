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
package com.aionemu.gameserver.skillengine.condition;

import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.model.stats.calc.functions.IStatFunction;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.Skill;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Conditions", propOrder = { "conditions" })
public class Conditions {

	@XmlElements({ 
		@XmlElement(name = "abnormal", type = AbnormalStateCondition.class), 
		@XmlElement(name = "target", type = TargetCondition.class),
		@XmlElement(name = "mp", type = MpCondition.class), 
		@XmlElement(name = "hp", type = HpCondition.class),
		@XmlElement(name = "dp", type = DpCondition.class), 
		@XmlElement(name = "playermove", type = PlayerMovedCondition.class),
		@XmlElement(name = "onfly", type = OnFlyCondition.class),
		@XmlElement(name = "weapon", type = WeaponCondition.class), 
		@XmlElement(name = "noflying", type = NoFlyingCondition.class),
		@XmlElement(name = "shield", type = ShieldCondition.class), 
		@XmlElement(name = "armor", type = ArmorCondition.class),
		@XmlElement(name = "charge", type = ChargeCondition.class), 
		@XmlElement(name = "targetflying", type = TargetFlyingCondition.class),
		@XmlElement(name = "selfflying", type = SelfFlyingCondition.class), 
		@XmlElement(name = "combatcheck", type = CombatCheckCondition.class),
		@XmlElement(name = "front", type = FrontCondition.class),
		@XmlElement(name = "chain", type = ChainCondition.class), 
		@XmlElement(name = "back", type = BackCondition.class),
		@XmlElement(name = "form", type = FormCondition.class),
		@XmlElement(name = "idianchargeweapon", type = IdianChargeCondition.class) })
	protected List<Condition> conditions;

	/**
	 * Gets the value of the conditions property.
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the conditions property.
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getConditions().add(newItem);
	 * </pre>
	 */
	public List<Condition> getConditions() {
		if (conditions == null) {
			conditions = new ArrayList<Condition>();
		}
		return this.conditions;
	}

	public boolean validate(Skill skill) {
		if (conditions != null) {
			for (Condition condition : getConditions()) {
				if (!condition.validate(skill)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean validate(Stat2 stat, IStatFunction statFunction) {
		if (conditions != null) {
			for (Condition condition : getConditions()) {
				if (!condition.validate(stat, statFunction)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean validate(Effect effect) {
		if (conditions != null) {
			for (Condition condition : getConditions()) {
				if (!condition.validate(effect)) {
					return false;
				}
			}
		}
		return true;
	}
}
