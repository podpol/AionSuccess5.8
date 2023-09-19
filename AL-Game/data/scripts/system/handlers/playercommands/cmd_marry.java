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

import com.aionemu.gameserver.cache.HTMLCache;
import com.aionemu.gameserver.configs.main.WeddingsConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.HTMLService;
import com.aionemu.gameserver.services.WeddingService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.Util;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;
import com.aionemu.gameserver.world.World;

/**
 * @author synchro2
 * @rework Eloann
 */
public class cmd_marry extends PlayerCommand {

    public cmd_marry() {
        super("marry");
    }

    @Override
    public void execute(Player admin, String... params) {
        if (!WeddingsConfig.WEDDINGS_ENABLE) {
            PacketSendUtility.sendMessage(admin, "Weddings disabled.");
            return;
        }

        if (params == null || params.length != 2) {
            PacketSendUtility.sendMessage(admin, "syntax .marry <characterName1> <characterName2>");
            return;
        }

        Player partner1 = World.getInstance().findPlayer(Util.convertName(params[0]));
        Player partner2 = World.getInstance().findPlayer(Util.convertName(params[1]));
        if (partner1 == null || partner2 == null) {
            PacketSendUtility.sendMessage(admin, "The specified player is not online.");
            return;
        }
        if (partner1.equals(partner2)) {
            PacketSendUtility.sendMessage(admin, "You can't marry player on himself.");
            return;
        }
        if (partner1.getWorldId() == 510010000 || partner1.getWorldId() == 520010000 || partner2.getWorldId() == 510010000 || partner2.getWorldId() == 520010000) {
            PacketSendUtility.sendMessage(admin, "One of the players is in prison.");
            return;
        }

        PacketSendUtility.sendMessage(admin, "Question sended.");
        PacketSendUtility.sendMessage(partner1, "You want marry on " + partner2.getName() + "?");
        HTMLService.showHTML(partner1, HTMLCache.getInstance().getHTML("weddings.xhtml"));
        PacketSendUtility.sendMessage(partner2, "You want marry on " + partner1.getName() + "?");
        HTMLService.showHTML(partner2, HTMLCache.getInstance().getHTML("weddings.xhtml"));

        WeddingService.getInstance().registerOffer(partner1, partner2, admin);
    }

    @Override
    public void onFail(Player admin, String message) {
        PacketSendUtility.sendMessage(admin, "syntax .marry <characterName1> <characterName2>");
    }
}
