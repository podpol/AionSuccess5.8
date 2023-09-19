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

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.RequestResponseHandler;
import com.aionemu.gameserver.model.items.storage.Storage;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author Chuck
 * @rework Ever'
 */
public class cmd_reskin extends PlayerCommand {

	public cmd_reskin() {
		super("reskin");
	}

	@Override
	public void execute(Player player, String... params) {
		if (params.length != 2) {
			PacketSendUtility.sendMessage(player, "syntax .reskin <Old Item> <New Item>");
			return;
		}

		Player target = player;
		VisibleObject creature = player.getTarget();
		if (player.getTarget() instanceof Player) {
			target = (Player) creature;
		}

		int oldItemId = 0;
		int newItemId = 0;

		try {
			String item = params[0];

			if (item.startsWith("[item:")) {
				Pattern id = Pattern.compile("\\[item:(\\d{9})");
				Matcher result = id.matcher(item);

				if (result.find()) {
					oldItemId = Integer.parseInt(result.group(1));
				}
				else {
					oldItemId = Integer.parseInt(params[0]);
				}
				item = params[1];
				if (item.startsWith("[item:")) {
					id = Pattern.compile("\\[item:(\\d{9})");
					result = id.matcher(item);

					if (result.find()) {
						newItemId = Integer.parseInt(result.group(1));
					}
					else {
						newItemId = Integer.parseInt(params[0]);
					}
				}
				else {
					PacketSendUtility.sendMessage(player, "syntax .reskin <Old Item> <New Item>");
					return;
				}
			}
			else {
				PacketSendUtility.sendMessage(player, "syntax .reskin <Old Item> <New Item>");
				return;
			}
		}
		catch (NumberFormatException e) {
			PacketSendUtility.sendMessage(player, "syntax .reskin <Old Item> <New Item>");
			return;
		}

		Storage storage = player.getInventory();
		List<Item> oldItems = player.getInventory().getItemsByItemId(oldItemId);
		List<Item> newItems = player.getInventory().getItemsByItemId(newItemId);
		// Iterator Ancien Item
		Iterator<Item> oldIter = oldItems.iterator();
		Item oldItem = oldIter.next();
		// Iterator Nouveau Item
		Iterator<Item> newIter = newItems.iterator();
		Item newItem = newIter.next();
		// verification que l'ancien item est dans l'inventaire
		if (oldItems.isEmpty()) {
			PacketSendUtility.sendMessage(player, "You do not have this item in your inventory.");
			return;
		}
		// verification que les items sont du même type.
		if (newItem.getItemTemplate().isWeapon() && oldItem.getItemTemplate().isWeapon()) {
			if (newItem.getItemTemplate().getWeaponType() != oldItem.getItemTemplate().getWeaponType()) {
				PacketSendUtility.sendMessage(player, "You can not remodel different types of item.");
				return;
			}
		}
		else if (newItem.getItemTemplate().isArmor() && oldItem.getItemTemplate().isArmor()) {
			if (newItem.getItemTemplate().getItemSlot() == oldItem.getItemTemplate().getItemSlot()) {
				if (newItem.getItemTemplate().getArmorType() != oldItem.getItemTemplate().getArmorType()) {
					PacketSendUtility.sendMessage(player, "You can not remodel different types of item.");
					return;
				}
			}
			else {
				PacketSendUtility.sendMessage(player, "You can not remodel different types of item.");
				return;
			}
		}

		final int tollPrice = 750;
		final long tolls = player.getClientConnection().getAccount().getToll();
		RequestResponseHandler responseHandler = new RequestResponseHandler(player) {

			@Override
			public void acceptRequest(Creature p2, Player p) {
				if (tolls < tollPrice) {
					PacketSendUtility.sendMessage(p, "You don't have enought Vote Points (" + tolls + "). You need : " + tollPrice + " Vote Points.");
					return;
				}
				p.getClientConnection().getAccount().setToll(tolls - tollPrice);

			}

			@Override
			public void denyRequest(Creature p2, Player p) {
			}
		};

		boolean requested = player.getResponseRequester().putRequest(902247, responseHandler);
		if (requested) {
			oldItem.setItemSkinTemplate(DataManager.ITEM_DATA.getItemTemplate(newItemId));
			storage.decreaseByItemId(newItemId, storage.getItemCountByItemId(newItemId));
			PacketSendUtility.sendBrightYellowMessage(player, "Your item " + params[0] + " just take the appearance of the item " + params[1] + ".");
			PacketSendUtility.sendMessage(player, "For changing the skin, you have use " + tollPrice + " Vote Points!");
		}
	}

	@Override
	public void onFail(Player player, String message) {
		PacketSendUtility.sendMessage(player, "syntax : .reskin <Old Item> <New Item>");
	}
}
