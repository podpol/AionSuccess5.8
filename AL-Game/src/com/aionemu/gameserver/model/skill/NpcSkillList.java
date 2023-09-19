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
package com.aionemu.gameserver.model.skill;

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.templates.npcskill.NpcSkillTemplate;
import com.aionemu.gameserver.model.templates.npcskill.NpcSkillTemplates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ATracer
 */
public class NpcSkillList implements SkillList<Npc> {

	private List<NpcSkillEntry> skills;

	public NpcSkillList(Npc owner) {
		initSkillList(owner.getNpcId());
	}

	private void initSkillList(int npcId) {
		NpcSkillTemplates npcSkillList = DataManager.NPC_SKILL_DATA.getNpcSkillList(npcId);
		if (npcSkillList != null) {
			initSkills();
			for (NpcSkillTemplate template : npcSkillList.getNpcSkills()) {
				skills.add(new NpcSkillTemplateEntry(template));
			}
		}
	}

	@Override
	public boolean addSkill(Npc creature, int skillId, int skillLevel) {
		initSkills();
		skills.add(new NpcSkillParameterEntry(skillId, skillLevel));
		return true;
	}

	@Override
	public boolean removeSkill(int skillId) {
		Iterator<NpcSkillEntry> iter = skills.iterator();
		while (iter.hasNext()) {
			NpcSkillEntry next = iter.next();
			if (next.getSkillId() == skillId) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isSkillPresent(int skillId) {
		if (skills == null) {
			return false;
		}
		return getSkill(skillId) != null;
	}

	@Override
	public int getSkillLevel(int skillId) {
		return getSkill(skillId).getSkillLevel();
	}

	@Override
	public int size() {
		return skills != null ? skills.size() : 0;
	}

	private void initSkills() {
		if (skills == null) {
			skills = new ArrayList<NpcSkillEntry>();
		}
	}

	public NpcSkillEntry getRandomSkill() {
		if (skills == null || skills.size() == 0)
			return null;
		return skills.size() == 1 ? skills.get(0) : skills.get(Rnd.get(0, skills.size() - 1));
	}

	private SkillEntry getSkill(int skillId) {
		for (SkillEntry entry : skills) {
			if (entry.getSkillId() == skillId) {
				return entry;
			}
		}
		return null;
	}
	
	public NpcSkillEntry getUseInSpawnedSkill() {
		if(this.skills == null)
			return null;
		Iterator<NpcSkillEntry> iter = skills.iterator();
		while (iter.hasNext()) {
			NpcSkillEntry next = iter.next();
			NpcSkillTemplateEntry tmpEntry = (NpcSkillTemplateEntry) next;
			if (tmpEntry.UseInSpawned()) {
				return next;
			}
		}
		return null;
	}

	@Override
	public boolean addLinkedSkill(Npc creature, int skillId) {
		// TODO Auto-generated method stub
		return false;
	}
}
