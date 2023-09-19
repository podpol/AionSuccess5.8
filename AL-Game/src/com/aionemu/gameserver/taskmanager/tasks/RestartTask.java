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
package com.aionemu.gameserver.taskmanager.tasks;

import com.aionemu.gameserver.ShutdownHook;
import com.aionemu.gameserver.ShutdownHook.ShutdownMode;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.tasks.TaskFromDBHandler;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Divinity
 */
public class RestartTask extends TaskFromDBHandler {

	private static final Logger log = LoggerFactory.getLogger(RestartTask.class);

	private int countDown;
	private int announceInterval;
	private int warnCountDown;

	@Override
	public String getTaskName() {
		return "restart";
	}

	@Override
	public boolean isValid() {
		return params.length == 3;
	}

	@Override
	public void run() {
		log.info("Task[" + id + "] launched : restarting the server !");
		setLastActivation();

		countDown = Integer.parseInt(params[0]);
		announceInterval = Integer.parseInt(params[1]);
		warnCountDown = Integer.parseInt(params[2]);

		World.getInstance().doOnAllPlayers(new Visitor<Player>() {

			@Override
			public void visit(Player player) {
				PacketSendUtility.sendBrightYellowMessageOnCenter(player, "Automatic Task: The server will restart in " + warnCountDown
					+ " seconds ! Please find a safe place and disconnect your character.");
			}
		});

		ThreadPoolManager.getInstance().schedule(new Runnable() {

			@Override
			public void run() {
				ShutdownHook.getInstance().doShutdown(countDown, announceInterval, ShutdownMode.RESTART);
			}
		}, warnCountDown * 1000);
	}
}
