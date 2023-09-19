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

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * Created by Ghostfur/Nimwey
 */
public class cmd_help extends PlayerCommand {

    public cmd_help() {
        super("help");
    }

    @Override
    public void execute(Player player, String... params){
        if (params.length != 0) {
            onFail(player, null);
            return;
        }


        if (player.getRace() == Race.ASMODIANS ||player.getRace() == Race.ELYOS){
            PacketSendUtility.sendMessage(player, "" +
					"\n" +
                    "==============================\n" +
                    "Available .[dot] Commands for Players!" +
                    "\n==============================\n" +
                    " .skills : refresh or get new skills.\n" +                         
                    " .givestigma add : to get your class stigma's.\n" +
                    " .ffa : to join or leave free for all\n" +
                    " .vs : to join or leave 1v1 battles\n" +
					//" .pk : to make pk xform\n" +
					" .pvp : brings you to the pvp map\n" +
					//" .mixfight : to join of leave mixfights.\n" +
					" .siege : brings you to the siege map\n" +
                    " .clean <item id/link> : to delete an item\n" +
                    " .toll : shows current toll you have in your account.\n" +
                    " .insanepack info : informs you with some important information about how to get gears!\n" +
                    " .dye <color> : to dye yourself.\n" +
					" .augmentme : to augment or condition your whole equipment.\n" +
                    " .unstuck : go to obelisk location\n" +
                    " .skin : will remove your candy look,\n" +
					" .reskinvip : reskin two handed weapons with use of tiamat bloody tear [VIP ONLY]");
                    PacketSendUtility.sendMessage(player,
                    ".faction : asmodian/elyos world chat\n" +
                    " .world : open world chat\n" +
                    " .enchant 15 : will enchant your equipment to 15.\n" +
                    " .gmlist : shows available gm's \n" +
					" .marry : marry another player \n" +
                    " .divorce : divorces from a player\n" +
                    " .pet add : adds You a scroll Buffer Pet.\n" +
                    " .job : Makes all craft available\n" +
                    " .queue : registers you in an on-going event hosted by a gm.\n" +               
					" .remodel : cross remodel with use of tiamat bloody tears\n");

        }

    }
    public void onFail(Player player, String msg){
        PacketSendUtility.sendMessage(player, "Syntax : .help");
    }
}


