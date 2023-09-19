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

import com.aionemu.gameserver.model.templates.npcskill.NpcSkillTemplates;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.slf4j.LoggerFactory;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author ATracer
 */
@XmlRootElement(name = "npc_skill_templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class NpcSkillData {

	@XmlElement(name = "npcskills")
	private List<NpcSkillTemplates> npcSkills;

	/** A map containing all npc skill templates */
	private TIntObjectHashMap<NpcSkillTemplates> npcSkillData = new TIntObjectHashMap<NpcSkillTemplates>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (NpcSkillTemplates npcSkill : npcSkills) {
			npcSkillData.put(npcSkill.getNpcId(), npcSkill);

			if (npcSkill.getNpcSkills() == null)
				LoggerFactory.getLogger(NpcSkillData.class).error("NO SKILL");
		}

	}

	public int size() {
		return npcSkillData.size();
	}

	public NpcSkillTemplates getNpcSkillList(int id) {
		return npcSkillData.get(id);
	}
}
