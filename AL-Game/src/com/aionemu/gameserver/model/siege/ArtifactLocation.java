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
package com.aionemu.gameserver.model.siege;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.templates.siegelocation.ArtifactActivation;
import com.aionemu.gameserver.model.templates.siegelocation.SiegeLocationTemplate;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.skillengine.model.SkillTemplate;

/**
 * @author Source
 */
public class ArtifactLocation extends SiegeLocation {

	private ArtifactStatus status;

	public ArtifactLocation() {
		this.status = ArtifactStatus.IDLE;
	}

	public ArtifactLocation(SiegeLocationTemplate template) {
		super(template);
		// Artifacts Always Vulnerable
		setVulnerable(true);
	}

	@Override
	public int getNextState() {
		return STATE_VULNERABLE;
	}

	public long getLastActivation() {
		return this.lastArtifactActivation;
	}

	public void setLastActivation(long paramLong) {
		this.lastArtifactActivation = paramLong;
	}

	public int getCoolDown() {
		long i = this.template.getActivation().getCd();
		long l = System.currentTimeMillis() - this.lastArtifactActivation;
		if (l > i)
			return 0;
		else
			return (int) ((i - l) / 1000);
	}

	/**
	 * Returns DescriptionId that describes name of this artifact.<br>
	 *
	 * @return DescriptionId with name
	 */
	public DescriptionId getNameAsDescriptionId() {
		// Get Skill id, item, count and target defined for each artifact.
		ArtifactActivation activation = getTemplate().getActivation();
		int skillId = activation.getSkillId();
		SkillTemplate skillTemplate = DataManager.SKILL_DATA.getSkillTemplate(skillId);
		return new DescriptionId(skillTemplate.getNameId());
	}

	public boolean isStandAlone() {
		return !SiegeService.getInstance().getFortresses().containsKey(getLocationId());
	}

	public FortressLocation getOwningFortress() {
		return SiegeService.getInstance().getFortress(getLocationId());
	}

	/**
	 * @return the status
	 */
	public ArtifactStatus getStatus() {
		return status != null ? status : ArtifactStatus.IDLE;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ArtifactStatus status) {
		this.status = status;
	}

}