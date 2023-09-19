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
package com.aionemu.gameserver.model.gameobjects.player;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.stats.calc.StatOwner;
import com.aionemu.gameserver.model.templates.stats.ModifiersTemplate;

/**
 * @author Rolandas
 */
public class AbsoluteStatOwner implements StatOwner {

	Player target;
	ModifiersTemplate template;
	boolean isActive = false;

	public AbsoluteStatOwner(Player player, int templateId) {
		this.target = player;
		setTemplate(templateId);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setTemplate(int templateId) {
		if (isActive)
			cancel();
		this.template = DataManager.ABSOLUTE_STATS_DATA.getTemplate(templateId);
	}

	public void apply() {
		if (template == null)
			return;
		target.getGameStats().addEffect(this, template.getModifiers());
		isActive = true;
	}

	public void cancel() {
		if (template == null)
			return;
		target.getGameStats().endEffect(this);
		isActive = false;
	}

}
