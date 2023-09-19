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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.item.ItemChargeService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * Created by Kill3r
 */
public class cmd_augmentme extends PlayerCommand {
    public cmd_augmentme() {
        super("augmentme");
    }
    public void execute(Player player, String...params){

        ItemChargeService.chargeItems(player,player.getEquipment().getEquippedItems(), 2);
        PacketSendUtility.sendMessage(player, "You've Successfuly Augmented you're Gear!");
        player.getInventory().decreaseKinah(500000);
    }
}
