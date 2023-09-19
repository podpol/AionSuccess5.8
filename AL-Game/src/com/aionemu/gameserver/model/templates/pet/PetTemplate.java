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
package com.aionemu.gameserver.model.templates.pet;

import com.aionemu.gameserver.model.templates.stats.PetStatsTemplate;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IlBuono
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "pet")
public class PetTemplate {

	@XmlAttribute(name = "id", required = true)
	private int id;

	@XmlAttribute(name = "name", required = true)
	private String name;

	@XmlAttribute(name = "nameid", required = true)
	private int nameId;

	@XmlAttribute(name = "condition_reward")
	private int conditionReward;

	@XmlElement(name = "petfunction")
	private List<PetFunction> petFunctions;

	@XmlElement(name = "petstats")
	private PetStatsTemplate petStats;

	@XmlTransient
	Boolean hasPlayerFuncs = null;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNameId() {
		return nameId;
	}

	public List<PetFunction> getPetFunctions() {
		if (hasPlayerFuncs == null) {
			hasPlayerFuncs = false;
			if (petFunctions == null) {
				List<PetFunction> result = new ArrayList<PetFunction>();
				result.add(PetFunction.CreateEmpty());
				petFunctions = result;
			}
			else {
				for (PetFunction func : petFunctions) {
					if (func.getPetFunctionType().isPlayerFunction()) {
						hasPlayerFuncs = true;
						break;
					}
				}
				if (!hasPlayerFuncs)
					petFunctions.add(PetFunction.CreateEmpty());
			}
		}
		return petFunctions;
	}

	public PetFunction getWarehouseFunction() {
		if (petFunctions == null)
			return null;
		for (PetFunction pf : petFunctions) {
			if (pf.getPetFunctionType() == PetFunctionType.WAREHOUSE)
				return pf;
		}
		return null;
	}

	/**
	 * Used to write to SM_PET packet, so checks only needed ones
	 */
	public boolean ContainsFunction(PetFunctionType type) {
		if (type.getId() < 0)
			return false;

		for (PetFunction t : getPetFunctions()) {
			if (t.getPetFunctionType() == type)
				return true;
		}
		return false;
	}

	/**
	 * Returns function if found, otherwise null
	 */
	public PetFunction getPetFunction(PetFunctionType type) {
		for (PetFunction t : getPetFunctions()) {
			if (t.getPetFunctionType() == type)
				return t;
		}
		return null;
	}

	public PetStatsTemplate getPetStats() {
		return petStats;
	}

	public final int getConditionReward() {
		return conditionReward;
	}

}
