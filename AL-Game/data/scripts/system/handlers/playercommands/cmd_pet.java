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
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;
import com.aionemu.gameserver.services.item.ItemService;

/**
 * Created By Ghostfur (Aion-Unique)
 */
public class cmd_pet extends PlayerCommand {	
	public cmd_pet() {
		super("pet");
	}
		@Override
		public void execute(final Player player, String...param){
        if(param.length < 1){
            PacketSendUtility.sendMessage(player, "syntax : .pet add -- To Add a Buffer Pet");
            return;
			}
	if(param[0].equals("add")){
      		ItemService.addItem(player,190000000, 1); //Pet
			PacketSendUtility.sendMessage(player, "\uE020 You Just Added a Buffer Pet! \uE020");
		}
	 }
	
    public void onFail(Player player, String msg){
        PacketSendUtility.sendMessage(player, " " +
                "syntax : .pet add  -- To Add a Buffer Pet\n");
    }
}

