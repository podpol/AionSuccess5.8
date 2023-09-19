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
package com.aionemu.gameserver;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.ItemCategory;
import com.aionemu.gameserver.services.item.ItemPacketService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.chathandlers.PlayerCommand;

/**
 * @author Tago modified by Wakizashi and Ney, Maestros, Eloann
 */
public class cmd_enchant extends PlayerCommand {

	public cmd_enchant() {
		super("enchant");
	}

    @Override
    public void execute(Player player, String... params) {
        int enchant = 0;
 
        try {
            enchant = params[0] == null ? enchant : Integer.parseInt(params[0]);
        } catch (Exception ex) {
            onFail(player, "Fail");
            return;
        }
        if(enchant <= 15){
            enchant(player, enchant);
        } else{
            PacketSendUtility.sendMessage(player, "You cannot enchant higher than +15!");
        }
    }

	private void enchant(Player player, int enchant) {
		for (Item targetItem : player.getEquipment().getEquippedItemsWithoutStigma()) {
			if (isUpgradeble(targetItem)) {
				int enchantLevel = 15, maxEnchant = targetItem.getItemTemplate().getMaxEnchantLevel();
				if (maxEnchant > 0 && enchantLevel > maxEnchant) {
					enchantLevel = maxEnchant;
				}

				targetItem.setEnchantLevel(enchantLevel);

				if (targetItem.isEquipped()) {
					player.getGameStats().updateStatsVisually();
				}
				ItemPacketService.updateItemAfterInfoChange(player, targetItem);
			}
		}
		PacketSendUtility.sendMessage(player, "All your items have been enchanted to: " + enchant);
	}

	/**
	 * Verify if the item is enchantble and/or socketble
	 */
	public static boolean isUpgradeble(Item item) {
		if (item.getItemTemplate().isNoEnchant()) {
			return false;
		}
		if (item.getItemTemplate().isWeapon()) {
			return true;
		}
		if (item.getItemTemplate().getCategory() == ItemCategory.STIGMA) {
			return false;
		}
		if (item.getEnchantLevel() == 15) {
			return false;
		}
		if (item.getItemTemplate().isArmor()) {
			int at = item.getItemTemplate().getItemSlot();
			if (at == 1 || /* Main Hand */at == 2 || /* Sub Hand */at == 8 || /* Jacket */at == 16 || /* Gloves */at == 32 || /* Boots */at == 2048 || /* Shoulder */at == 4096 || /* Pants */at == 131072
				|| /*
					 * Main Off Hand
					 */at == 262144) /*
									 * Sub Off Hand
									 */ {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onFail(Player player, String message) {
		PacketSendUtility.sendMessage(player, "Syntax .enchant : \n" + "  Syntax .enchant <value>.\n" + "Info: This command all your enchanted items on <value>!" + " For example, would enchant all your items to 15 (eq 15.)");
	}
}
