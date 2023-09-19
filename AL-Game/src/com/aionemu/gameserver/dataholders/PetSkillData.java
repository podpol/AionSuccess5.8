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

import com.aionemu.gameserver.model.templates.petskill.PetSkillTemplate;
import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author ATracer
 */
@XmlRootElement(name = "pet_skill_templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class PetSkillData {

	@XmlElement(name = "pet_skill")
	private List<PetSkillTemplate> petSkills;

	/** A map containing all npc skill templates */
	private TIntObjectHashMap<TIntIntHashMap> petSkillData = new TIntObjectHashMap<TIntIntHashMap>();

	private TIntObjectHashMap<TIntArrayList> petSkillsMap = new TIntObjectHashMap<TIntArrayList>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (PetSkillTemplate petSkill : petSkills) {
			TIntIntHashMap orderSkillMap = petSkillData.get(petSkill.getOrderSkill());
			if (orderSkillMap == null) {
				orderSkillMap = new TIntIntHashMap();
				petSkillData.put(petSkill.getOrderSkill(), orderSkillMap);
			}
			orderSkillMap.put(petSkill.getPetId(), petSkill.getSkillId());

			TIntArrayList skillList = petSkillsMap.get(petSkill.getPetId());
			if (skillList == null) {
				skillList = new TIntArrayList();
				petSkillsMap.put(petSkill.getPetId(), skillList);
			}
			skillList.add(petSkill.getSkillId());
		}
	}

	public int size() {
		return petSkillData.size();
	}

	public int getPetOrderSkill(int orderSkill, int petNpcId) {
		return petSkillData.get(orderSkill).get(petNpcId);
	}

	public boolean petHasSkill(int petNpcId, int skillId) {
		return petSkillsMap.get(petNpcId).contains(skillId);
	}
}
