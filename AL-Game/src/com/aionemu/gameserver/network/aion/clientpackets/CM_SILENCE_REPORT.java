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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUESTION_WINDOW;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;

import java.util.concurrent.Future;

/**
 * @author Ranastic (Encom)
 */

public class CM_SILENCE_REPORT extends AionClientPacket
{
	private int report;
	private String targetName;
	
	public CM_SILENCE_REPORT(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		targetName = readS();
	}
	
	@Override
	protected void runImpl() {
		Player activePlayer = getConnection().getActivePlayer();
		final Player targetPlayer = World.getInstance().findPlayer(targetName);
		if (targetPlayer == null || targetPlayer.isGM()) {
			PacketSendUtility.sendPacket(activePlayer, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_MSG_REPORT_CHAT_CANT_NO_ENTER, 0, 0, 0));
			return;
		} else if (activePlayer.getSilenceReportCount() > 0) {
			PacketSendUtility.sendPacket(activePlayer, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_MSG_REPORT_CHAT_CANT_NO_USER, 0, 0, 0));
			return;
		} else if (activePlayer.getLevel() < 10) {
			PacketSendUtility.sendPacket(activePlayer, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_MSG_REPORT_CHAT_CANT_LOW_LEVEL, 0, 0, 0));
			return;
		} else if (activePlayer.getCommonData().getRace() != targetPlayer.getCommonData().getRace()) {
			PacketSendUtility.sendPacket(activePlayer, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_MSG_REPORT_CHAT_CANT_OTHER_RACE, 0, 0, 0));
			return;
		} else {
			report++;
			if (activePlayer.isGM()) {
				activePlayer.setSilenceReportCount(0);
			} else {
				targetPlayer.setSilenceReportCount(report++);
			}
			PacketSendUtility.sendPacket(activePlayer, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_MSG_REPORT_CHAT_CONFIRM, 0, 0, targetPlayer.getName()));
			PacketSendUtility.sendMessage(activePlayer, "Silence report to "+targetPlayer.getName()+" count "+report);
			if (targetPlayer.getSilenceReportCount() >= 2) { //TODO: dont know in Official set how much count of reporter for started silencing.
				targetPlayer.setGagged(true);
				PacketSendUtility.sendMessage(targetPlayer, "You got silence punish, because some players were reported you spamming.");
				Future<?> task = targetPlayer.getController().getTask(TaskId.GAG);
				if (task != null) {
					targetPlayer.getController().cancelTask(TaskId.GAG);
				}
				targetPlayer.getController().addTask(TaskId.GAG, ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						targetPlayer.setGagged(false);
						PacketSendUtility.sendMessage(targetPlayer, "Silence punish is no longer apllied on you.");
					}
				}, 10 * 60000L)); //TODO: dont know in Official set how long for end player's silenced.s
			}
		}
	}
}