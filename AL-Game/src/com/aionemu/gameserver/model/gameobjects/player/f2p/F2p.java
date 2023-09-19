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
package com.aionemu.gameserver.model.gameobjects.player.f2p;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.F2pDAO;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.taskmanager.tasks.ExpireTimerTask;

/****/
/** Author Ranastic (Encom)
/****/

public class F2p
{
	private Player owner;
	private F2pAccount f2pAccount;
	
	public F2p(Player owner) {
		this.owner = owner;
	}
	
	public void add(F2pAccount f2pacc, boolean isNew) {
		f2pAccount = f2pacc;
		f2pacc.setActive(true);
		if (isNew) {
			if (f2pacc.getExpireTime() != 0) {
				ExpireTimerTask.getInstance().addTask(f2pacc, owner);
			}
			DAOManager.getDAO(F2pDAO.class).storeF2p(owner.getObjectId().intValue(), f2pacc.getExpireTime());
		}
	}

	public void update(F2pAccount f2pacc, boolean isNew) {
		f2pAccount = f2pacc;
		f2pacc.setActive(true);
		if (isNew) {
			if (f2pacc.getExpireTime() != 0) {
				ExpireTimerTask.getInstance().addTask(f2pacc, owner);
			}
			DAOManager.getDAO(F2pDAO.class).storeF2p(owner.getObjectId().intValue(), f2pacc.getExpireTime());
		}
	}
	
	public F2pAccount getF2pAccount() {
		return f2pAccount;
	}
	
	public boolean remove() {
		if (f2pAccount != null) {
			f2pAccount.setActive(false);
			DAOManager.getDAO(F2pDAO.class).deleteF2p(owner.getObjectId().intValue());
			owner.getEquipment().checkRankLimitItems();
			return true;
		}
		return false;
	}
}