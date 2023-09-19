package com.aionemu.gameserver.model.skinskill;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.templates.SkillSkinTemplate;

/**
 * @author Ghostfur (Aion-Unique)
 */
public abstract class SkillSkinEntry {
	protected final int skinId;
	protected int skillLevel;
	
	SkillSkinEntry(int skinId, int skillLevel) {
		this.skinId = skinId;
		this.skillLevel = skillLevel;
	}
	
	public final int getSkinId() {
		return skinId;
	}
	
	public final int getSkillLevel() {
		return skillLevel;
	}
	
	public final String getSkillName() {
		return DataManager.SKILL_SKIN_DATA.getSkillSkinTemplate(getSkinId()).getName();
	}
	
	public void setSkillLvl(int skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	public final SkillSkinTemplate getSkillSkinTemplate() {
		return DataManager.SKILL_SKIN_DATA.getSkillSkinTemplate(getSkinId());
	}
}
