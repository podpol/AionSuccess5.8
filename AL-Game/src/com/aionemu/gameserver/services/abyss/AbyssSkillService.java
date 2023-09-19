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
package com.aionemu.gameserver.services.abyss;

import com.aionemu.gameserver.model.gameobjects.player.AbyssRank;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.stats.AbyssRankEnum;

/**
 * @author ATracer
 */
public class AbyssSkillService {

	/**
	 * @param player
	 */
	public static final void updateSkills(Player player) {
		AbyssRank abyssRank = player.getAbyssRank();
		if (abyssRank == null) {
			return;
		}
		AbyssRankEnum rankEnum = abyssRank.getRank();
		// remove all abyss skills first
		for (AbyssSkills abyssSkill : AbyssSkills.values()) {
			if (abyssSkill.getRace() == player.getRace()) {
				for (int skillId : abyssSkill.getSkills()) {
					player.getSkillList().removeSkill(skillId);
				}
			}
		}
		// add new skills
		if (abyssRank.getRank().getId() >= AbyssRankEnum.STAR5_OFFICER.getId()) {
			for (int skillId : AbyssSkills.getSkills(player.getRace(), rankEnum)) {
				player.getSkillList().addAbyssSkill(player, skillId, 1);
			}
		}
	}

	/**
	 * @param player
	 */
	public static final void onEnterWorld(Player player) {
		updateSkills(player);
	}
}