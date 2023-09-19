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
package com.aionemu.gameserver.model.templates.tasks;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.TaskFromDBDAO;

/**
 * @author Divinity
 */
public abstract class TaskFromDBHandler implements Runnable {

	protected int id;
	protected String params[];

	/**
	 * Task's id
	 * 
	 * @param int
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Task's param(s)
	 * 
	 * @param params
	 *          String[]
	 */
	public void setParam(String params[]) {
		this.params = params;
	}

	/**
	 * The task's name This allow to check with the table column "task"
	 */
	public abstract String getTaskName();

	/**
	 * Check if the task's parameters are valid
	 * 
	 * @return true if valid, false otherwise
	 */
	public abstract boolean isValid();

	/**
	 * Retuns {@link com.aionemu.gameserver.dao.TaskFromDBDAO} , just a shortcut
	 * 
	 * @return {@link com.aionemu.gameserver.dao.TaskFromDBDAO}
	 */
	protected void setLastActivation() {
		DAOManager.getDAO(TaskFromDBDAO.class).setLastActivation(id);
	}
}
