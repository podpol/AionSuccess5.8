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
package com.aionemu.gameserver.services;

import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;

public enum AccessLevelEnum {
	AccessLevel1(1, AdminConfig.ADMIN_TAG_1, "\uE06F Supporter \uE06F", new int[] { 240, 241, 277 }),
	AccessLevel2(2, AdminConfig.ADMIN_TAG_2, "\uE04E Junior-GM \uE04E", new int[] { 240, 241, 277 }),
	AccessLevel3(3, AdminConfig.ADMIN_TAG_3, "\uE050 Senior-GM \uE050", new int[] { 240, 241, 277 }),
	AccessLevel4(4, AdminConfig.ADMIN_TAG_4, "\uE04A Head-GM \uE04A", new int[] { 240, 241, 277 }),
	AccessLevel5(5, AdminConfig.ADMIN_TAG_5, "\uE0BD Admin \uE0BD", new int[] { 240, 241, 277, 282, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 395, 396 }),
	AccessLevel6(6, AdminConfig.ADMIN_TAG_6, "\uE0BD Developer \uE0BD", new int[] { 240, 241, 277, 282, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 395, 396 }),
	AccessLevel7(7, AdminConfig.ADMIN_TAG_7, "\uE0BD Server-CoAdmin \uE0BD", new int[] { 240, 241, 277, 282, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 395, 396 }),
	AccessLevel8(8, AdminConfig.ADMIN_TAG_8, "\uE0BD Server-Admin \uE0BD", new int[] { 240, 241, 277, 282, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 395, 396 }),
	AccessLevel9(9, AdminConfig.ADMIN_TAG_9, "\uE0BD Server-CoOwner \uE0BD", new int[] { 240, 241, 277, 282, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 395, 396 }),
	AccessLevel10(10, AdminConfig.ADMIN_TAG_10, "\uE0BD Server-Owner \uE0BD", new int[] { 240, 241, 277, 282, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 395, 396 });

	private final int level;
	private final String nameLevel;
	private String status;
	private int[] skills;

	AccessLevelEnum(int id, String name, String status, int[] skills) {
		this.level = id;
		this.nameLevel = name;
		this.status = status;
		this.skills = skills;
	}

	public String getName() {
		return nameLevel;
	}

	public int getLevel() {
		return level;
	}

	public String getStatusName() {
		return status;
	}

	public int[] getSkills() {
		return skills;
	}

	public static AccessLevelEnum getAlType(int level) {
		for (AccessLevelEnum al : AccessLevelEnum.values()) {
			if (level == al.getLevel()) {
				return al;
			}
		}
		return null;
	}

	public static String getAlName(int level) {
		for (AccessLevelEnum al : AccessLevelEnum.values()) {
			if (level == al.getLevel()) {
				return al.getName();
			}
		}
		return "%s";
	}

	public static String getStatusName(Player player) {
		return player.getAccessLevel() > 0 ? AccessLevelEnum.getAlType(player.getAccessLevel()).getStatusName() : player.getLegion().getLegionName();
	}
}
