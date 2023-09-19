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

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.skillengine.model.SkillTemplate;

import java.sql.Timestamp;

/**
 * @author ATracer
 */
public abstract class SkillEntry {

	protected final int skillId;
	protected int skillLevel;
	protected int skinId;
	protected Timestamp activeSkinTime;
	protected int expireTime;
	protected boolean isActivated;

	SkillEntry(int skillId, int skillLevel, int skinId, Timestamp activeSkinTime, int expireTime, boolean isActivated) {
		this.skillId = skillId;
		this.skillLevel = skillLevel;
		this.skinId = skinId;
		this.activeSkinTime = activeSkinTime;
		this.expireTime = expireTime;
		this.isActivated = isActivated;
	}

	public final int getSkillId() {
		return skillId;
	}

	public final int getSkillLevel() {
		return skillLevel;
	}
	
	public final int getSkinId() {
		return skinId;
	}
	
	public final Timestamp getSkinActiveTime() {
		return activeSkinTime;
	}
	
	public void setSkinActiveTime(Timestamp activeSkinTime) {
		this.activeSkinTime = activeSkinTime;
	}
	
	public final int getSkinExpireTime() {
		return expireTime;
	}

	public final String getSkillName() {
		return DataManager.SKILL_DATA.getSkillTemplate(getSkillId()).getName();
	}

	public void setSkillLvl(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public final SkillTemplate getSkillTemplate() {
		return DataManager.SKILL_DATA.getSkillTemplate(getSkillId());
	}

	public void setSkinId(int skinId) {
		this.skinId = skinId;
	}
	
	public void setSkinExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isActivated(){
		return isActivated;
	}

	public void setActivated(boolean activated){
		this.isActivated = activated;
	}
}
