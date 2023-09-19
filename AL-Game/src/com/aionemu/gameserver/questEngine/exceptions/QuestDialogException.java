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
package com.aionemu.gameserver.questEngine.exceptions;

import com.aionemu.gameserver.questEngine.model.QuestEnv;

/**
 * OnDialogEvent exception
 * 
 * @author vlog
 */
public class QuestDialogException extends RuntimeException {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -4323594385872762590L;

	public QuestDialogException(QuestEnv env) {
		super(new String("Info: QuestID: " + env.getQuestId() + ", DialogID: " + env.getDialogId()
			+ env.getVisibleObject().getObjectTemplate().getTemplateId() == null ? "0" : ", TargetID: "
			+ env.getVisibleObject().getObjectTemplate().getTemplateId() + "."
			+ env.getPlayer().getQuestStateList().getQuestState(env.getQuestId()) == null ? " QuestState not initialized."
			: " QuestState: " + env.getPlayer().getQuestStateList().getQuestState(env.getQuestId()).getStatus().toString()
				+ env.getPlayer().getQuestStateList().getQuestState(env.getQuestId()).getQuestVarById(0)));
	}
}
