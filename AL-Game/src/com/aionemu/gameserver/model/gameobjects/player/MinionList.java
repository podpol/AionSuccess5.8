/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.model.gameobjects.player;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerMinionsDAO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MINIONS;
import com.aionemu.gameserver.taskmanager.tasks.ExpireTimerTask;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javolution.util.FastMap;

public class MinionList {
	private final Player player;
	private int lastUsedObjId;
	private FastMap<Integer, MinionCommonData> minions = new FastMap<Integer, MinionCommonData>();

	public MinionList(Player player) {
		this.player = player;
		loadMinions();
	}

	public void loadMinions() {
		List<MinionCommonData> playerMinions = DAOManager.getDAO(PlayerMinionsDAO.class).getPlayerMinions(player);
		MinionCommonData lastUsedMinions = null;
		for (MinionCommonData minion : playerMinions) {
			if (minion.getExpireTime() > 0) {
				ExpireTimerTask.getInstance().addTask(minion, player);
			}
			
			minions.put(minion.getObjectId(), minion);
			if (lastUsedMinions == null || minion.getDespawnTime().after(lastUsedMinions.getDespawnTime())) {
				lastUsedMinions = minion;
			}
		} if (lastUsedMinions != null) {
			lastUsedObjId = lastUsedMinions.getMinionId();
	    }
	}

	public Collection<MinionCommonData> getMinions() {
		return minions.values();
	}

	public MinionCommonData getMinion(int minionObjId) {
		return minions.get(minionObjId);
	}
	
	public MinionCommonData addNewMinion (Player player, int minionId, String name, String grade, int level, int growthPoints, int expireTime) {
		return addNewMinion(player, minionId, System.currentTimeMillis(), name, grade, level, growthPoints, expireTime);
	}

	public MinionCommonData addNewMinion(Player player, int minionId, long birthday, String name, String grade, int level, int growthPoints, int expireTime) {
		MinionCommonData minionCommonData = new MinionCommonData(minionId, player.getObjectId(), name, grade, level, 0, expireTime);
		DAOManager.getDAO(PlayerMinionsDAO.class).saveBirthday(minionCommonData);
		minionCommonData.setName(name);
		minionCommonData.setGrade(grade);
		minionCommonData.setMinionLevel(level);
		minionCommonData.setMinionGrowthPoint(growthPoints);
		minionCommonData.setDespawnTime(new Timestamp(System.currentTimeMillis()));
		DAOManager.getDAO(PlayerMinionsDAO.class).insertPlayerMinion(minionCommonData);
		minions.put(minionCommonData.getObjectId(), minionCommonData);
		return minionCommonData;
	}

	public boolean hasMinion(int n) {
		return minions.containsKey(n);
	}

	public void deleteMinion(int minionObjId, Timestamp brithday) {
		if (hasMinion(minionObjId)) {
            DAOManager.getDAO(PlayerMinionsDAO.class).removePlayerMinion(player, minionObjId, brithday);
			minions.remove(minionObjId);
		}
	}
	
	public void setLastUsed(int lastUsedObjId) {
		this.lastUsedObjId = lastUsedObjId;
	}

	public MinionCommonData getLastUsed() {
		return getMinion(lastUsedObjId);
	}

	public void updateMinionsList() {
		minions.clear();
		for (MinionCommonData minionCommonData : DAOManager.getDAO(PlayerMinionsDAO.class).getPlayerMinions(player)) {
			minions.put(minionCommonData.getObjectId(), minionCommonData);
		}
		if(minions !=null) {
			PacketSendUtility.sendPacket(player, new SM_MINIONS(0, player.getMinionList().getMinions()));
		}
		return;
	}
}
