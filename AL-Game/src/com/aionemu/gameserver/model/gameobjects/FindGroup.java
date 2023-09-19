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
package com.aionemu.gameserver.model.gameobjects;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;
import com.aionemu.gameserver.model.team2.alliance.PlayerAlliance;
import com.aionemu.gameserver.model.team2.group.PlayerGroup;

/**
 * Find Group
 * 
 * @author MrPoke
 */
public class FindGroup {

	private AionObject object;
	private String message;
	private int groupType, minMembers, instanceId;
	private int lastUpdate = (int) (System.currentTimeMillis() / 1000);

	public FindGroup(AionObject object, String message, int groupType) {
		this.object = object;
		this.message = message;
		this.groupType = groupType;
	}

	public String getMessage() {
		return message;
	}

	public int getGroupType() {
		return groupType;
	}

	public int getObjectId() {
		return object.getObjectId();
	}

	public int getInstanceId() {
		return instanceId;
	}

	public int getMinMembers() {
		return minMembers;
	}

	public int getClassId() {
		if (object instanceof Player)
			return ((Player) (object)).getPlayerClass().getClassId();
		else if (object instanceof PlayerAlliance)
			((PlayerAlliance) (object)).getLeaderObject().getCommonData().getPlayerClass();
		else if (object instanceof PlayerGroup) {
			((PlayerGroup) object).getLeaderObject().getPlayerClass();
		}
		return 0;
	}

	public int getMinLevel() {
		if (object instanceof Player)
			return ((Player) (object)).getLevel();
		else if (object instanceof PlayerAlliance) {
			int minLvl = 99;
			for (Player member : ((PlayerAlliance) (object)).getMembers()) {
				int memberLvl = member.getCommonData().getLevel();
				if (memberLvl < minLvl)
					minLvl = memberLvl;
			}
			return minLvl;
		}
		else if (object instanceof PlayerGroup) {
			return ((PlayerGroup) object).getMinExpPlayerLevel();
		}
		else if (object instanceof TemporaryPlayerTeam) {
			return ((TemporaryPlayerTeam<?>) object).getMinExpPlayerLevel();
		}
		return 1;
	}

	public int getMaxLevel() {
		if (object instanceof Player)
			return ((Player) (object)).getLevel();
		else if (object instanceof PlayerAlliance) {
			int maxLvl = 1;
			for (Player member : ((PlayerAlliance) (object)).getMembers()) {
				int memberLvl = member.getCommonData().getLevel();
				if (memberLvl > maxLvl)
					maxLvl = memberLvl;
			}
			return maxLvl;
		}
		else if (object instanceof PlayerGroup) {
			return ((PlayerGroup) object).getMaxExpPlayerLevel();
		}
		else if (object instanceof TemporaryPlayerTeam) {
			return ((TemporaryPlayerTeam<?>) object).getMaxExpPlayerLevel();
		}
		return 1;
	}

	public int getUnk() {
		if (object instanceof Player)
			return 65557;
		else
			return 0;
	}

	/**
	 * @return the lastUpdate
	 */
	public int getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		if (object instanceof Player)
			return ((Player) object).getName();
		else if (object instanceof PlayerAlliance)
			return ((PlayerAlliance) object).getLeaderObject().getCommonData().getName();
		else if (object instanceof PlayerGroup) {
			return ((PlayerGroup) object).getLeaderObject().getName();
		}
		return "";
	}

	public int getSize() {
		if (object instanceof Player)
			return 1;
		else if (object instanceof PlayerAlliance)
			return ((PlayerAlliance) object).size();
		else if (object instanceof PlayerGroup) {
			return ((PlayerGroup) object).size();
		}
		return 1;
	}

	public void setMessage(String message) {
		lastUpdate = (int) (System.currentTimeMillis() / 1000);
		this.message = message;
	}
}
