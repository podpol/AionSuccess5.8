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
package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Rinzler
 */

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "Stigma")
public class Stigma
{
	@XmlElement(name = "require_skill")
	protected List<RequireSkill> requireSkill;
	
	@XmlAttribute
	protected List<String> skill;
	
	@XmlAttribute
	protected int shard;
	
	public List<StigmaSkill> getSkills() {
		List<StigmaSkill> list = new ArrayList<StigmaSkill>();
		for (String st : skill) {
			String[] array = st.split(":");
			list.add(new StigmaSkill(Integer.parseInt(array[0]), Integer.parseInt(array[1])));
		}
		return list;
	}
	
	public List<Integer> getSkillIdOnly() {
		List<Integer> ids = new ArrayList<Integer>();
		List<String> skill = this.skill;
		if (skill.size() != 1) {
			String[] tempArray = new String[0];
			for (String parts : skill){
				tempArray = parts.split(":");
				ids.add(Integer.parseInt(tempArray[1]));
			}
			return ids;
		} for (String st : this.skill) {
			String[] array = st.split(":");
			ids.add(Integer.parseInt(array[1]));
		}
		return ids;
	}
	
	public int getShard() {
		return shard;
	}
	
	public List<RequireSkill> getRequireSkill() {
		if (requireSkill == null) {
			requireSkill = new ArrayList<RequireSkill>();
		}
		return this.requireSkill;
	}
	
	public static class StigmaSkill {
		private int skillId;
		private int skillLvl;
		
		public StigmaSkill(int skillLvl, int skillId) {
			this.skillId = skillId;
			this.skillLvl = skillLvl;
		}
		
		public int getSkillLvl() {
			return this.skillLvl;
		}
		
		public int getSkillId() {
			return this.skillId;
		}
	}
}