/*
 * =====================================================================================*
 * This file is part of Aion-Unique (Aion-Unique Home Software Development)             *
 * Aion-Unique Development is a closed Aion Project that use Old Aion Project Base      *
 * Like Aion-Lightning, Aion-Engine, Aion-Core, Aion-Extreme, Aion-NextGen,      	    *
 * Aion-Ger, U3J, Encom And other Aion project, All Credit Content                      *
 * That they make is belong to them/Copyright is belong to them. And All new Content    *
 * that Aion-Unique make the copyright is belong to Aion-Unique                         *
 * You may have agreement with Aion-Unique Development, before use this Engine/Source   *
 * You have agree with all of Term of Services agreement with Aion-Unique Development   *
 * =====================================================================================*
 */
package com.aionemu.gameserver.services.events;

import com.aionemu.gameserver.configs.main.*;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.events.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * Created by Ghostfur (Aion-Unique)
 */

public class cmd_ffa extends PlayerCommand
{
    public cmd_ffa() {
        super("ffa");
    }

    @Override
    public void execute(Player player, String... params) {
        if (!FFAConfig.FFA_ENABLED) {
            PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> is disabled!!!");
            return;
        } if (player.getLevel() < 10) {
            PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> You must reached lvl 10!");
            return;
        } if (player.isInInstance() && !FFAService.getInstance().isInArena(player) && !player.isFFA()) {
            PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> You can't use <FFA> mod in instance!!!");
            return;
        } if (player.getBattleground() != null || LadderService.getInstance().isInQueue(player) || player.isSpectating()||player.getLifeStats().isAlreadyDead()) {
            PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> You cannot enter <FFA> while in a battleground, in the queue, while spectating or being dead !!!");
            return;
        } if (FFAService.getInstance().isInArena(player)) {
            PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> You will be leaving <FFA> in 10 seconds!");
            FFAService.getInstance().leaveArena(player);
        } else {
            if (player.getController().isInCombat()) {
                PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> You cannot enter <FFA> while in combat.");
                return;
            }
            PacketSendUtility.sendSys3Message(player, "\uE00B", "<FFA> You will be entering <FFA> in 10 seconds. To leave <FFA> write .ffa!!!");
            FFAService.getInstance().enterArena(player, false);
        }
    }
}