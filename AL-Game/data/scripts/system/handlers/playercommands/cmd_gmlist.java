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

import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.model.gameobjects.player.FriendList;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eloann
 * @reworked Kill3r
 */
public class cmd_gmlist extends PlayerCommand {

    public cmd_gmlist() {
        super("gmlist");
    }

    @Override
    public void execute(Player player, String... params) {
        final List<Player> admins = new ArrayList<Player>();
        final List<Player> helpers = new ArrayList<Player>();
        World.getInstance().doOnAllPlayers(new Visitor<Player>() {
            @Override
            public void visit(Player object) {
                if (object.getAccessLevel() >= 2 && object.getFriendList().getStatus() != FriendList.Status.OFFLINE) {
                    admins.add(object);
                }
            }
        });
        World.getInstance().doOnAllPlayers(new Visitor<Player>() {
            @Override
            public void visit(Player player) {
                if (player.getAccessLevel() == 1){
                    helpers.add(player);
                }
            }
        });

        if (helpers.size() > 0) {
            PacketSendUtility.sendMessage(player, "===== Helpers =====");
            if (helpers.size() == 1){
                PacketSendUtility.sendMessage(player, "There is only 1 Helper Online!");
            } else {
                PacketSendUtility.sendMessage(player, "There are some Helper's Online!");
            }

            for (Player helper : helpers){
                String tag = "";
                if (helper.getAccessLevel() == 1){
                    tag = AdminConfig.ADMIN_TAG_1;
                }
                tag = tag.substring(0, tag.length() - 2);
                PacketSendUtility.sendMessage(player, tag + helper.getName());
            }
            PacketSendUtility.sendMessage(player, "===================");
        }else{
            PacketSendUtility.sendMessage(player, "====== Helpers =====");
            PacketSendUtility.sendMessage(player, "There are no Helpers Online!");
            PacketSendUtility.sendMessage(player, "====================");
        }

        if (admins.size() > 0) {
            PacketSendUtility.sendMessage(player, "==== GameMasters ===");
            if (admins.size() == 1) {
                PacketSendUtility.sendMessage(player, "There is only 1 GM Online!");
            } else {
                PacketSendUtility.sendMessage(player, "There are some GM's Online!");
            }

            for (Player admin : admins) {
                String tag = "";
                String tagEnd = "";
                if (admin.getAccessLevel() == 2){ //trialGM
                    tag = AdminConfig.ADMIN_TAG_2;
                }else if (admin.getAccessLevel() == 3){
                    tag = AdminConfig.ADMIN_TAG_3;
                }else if (admin.getAccessLevel() == 4){
                    tag = AdminConfig.ADMIN_TAG_4;
                }else if (admin.getAccessLevel() == 5){
                    tag = AdminConfig.ADMIN_TAG_5;
                }else if (admin.getAccessLevel() == 6){
                    tag = AdminConfig.ADMIN_TAG_6;
                }else if (admin.getAccessLevel() == 7){
                    tag = AdminConfig.ADMIN_TAG_7;
                }else if (admin.getAccessLevel() == 8){
                    tag = AdminConfig.ADMIN_TAG_8;
                }else if (admin.getAccessLevel() == 9){
                    tag = AdminConfig.ADMIN_TAG_9;
                }else if (admin.getAccessLevel() == 10){
                    tag = AdminConfig.ADMIN_TAG_10;
                }
                tagEnd = tag.substring(tag.length() - 2);
                tag = tag.substring(0, tag.length() - 4);
                PacketSendUtility.sendMessage(player, tag + admin.getName() + tagEnd);
            }
            PacketSendUtility.sendMessage(player, "====================");

        } else {
            PacketSendUtility.sendMessage(player, "==== GameMasters ===");
            PacketSendUtility.sendMessage(player, "There are no GM Online!");
            PacketSendUtility.sendMessage(player, "====================");
        }

    }
}
