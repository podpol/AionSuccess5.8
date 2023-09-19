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
package playercommands;

import java.util.Map;

import javolution.util.FastMap;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.events.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

public class cmd_solo extends PlayerCommand
{
    private static Map<Integer, Long> nextUse = new FastMap<Integer, Long>();
    private static final int REGISTRATION_DELAY = 8 * 60 * 1000;
    
	public cmd_solo() {
        super("vs");
    }
	
    @Override
    public void execute(Player player, String... params) {
        if (!LadderService.getInstance().isInQueue(player)) {
            if (LadderService.getInstance().registerForSolo(player)) {
                PacketSendUtility.sendSys3Message(player, "\uE00E", "You are now registered in queue <1Vs1>");
            } else {
                PacketSendUtility.sendSys3Message(player, "\uE00E", "Failed to save in queue <1Vs1>");
            }
        } else {
            LadderService.getInstance().unregisterFromQueue(player);
            PacketSendUtility.sendSys3Message(player, "\uE00E", "You are now unsubscribed from queue <1Vs1>");
        }
    }
}