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
package ai.instance.dredgion;

import ai.GeneralNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.NpcType;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_CUSTOM_SETTINGS;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("shulack_drudge")
public class Shulack_DrudgeAI2 extends GeneralNpcAI2
{
	@Override
	protected void handleDialogFinish(Player player) {
		addItems(player);
		super.handleDialogFinish(player);
	}
	
	@Override
	protected void handleDialogStart(Player player) {
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
	}
	
	private void addItems(Player player) {
	    int itemId = player.getRace() == Race.ELYOS ? 182212606 : 182212607;
	    Item dredgionSupplies = player.getInventory().getFirstItemByItemId(itemId);
	    if (dredgionSupplies == null) {
	  	    ItemService.addItem(player, itemId, 1);
	  	    getOwner().setNpcType(NpcType.NON_ATTACKABLE);
	  	    getKnownList().doOnAllPlayers(new Visitor<Player>() {
			    @Override
			    public void visit(Player player) {
				    PacketSendUtility.sendPacket(player, new SM_CUSTOM_SETTINGS(getOwner().getObjectId(), 0, NpcType.NON_ATTACKABLE.getId(), 0));
			    }
		    });
	    }
	}
}