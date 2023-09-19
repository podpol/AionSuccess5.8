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
package com.aionemu.gameserver.model.templates.item.actions;

import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.abyss.AbyssPointsService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
  Author Rinzler (Encom)
* Player receive "AP" everytime, if he uses any of these items:
* Abyss Armor 35% Extraction Tools.
* Abyss Accessory 35% Extraction Tools.
* Abyss Equipment 35% Extraction Tools.
* Abyss Weapon 35% Extraction Tools.
* Abyss Wing 35% Extraction Tools.
* Vindachinerk's Durable Abyss Armor Extraction Tools.
* Vindachinerk's Durable Abyss Weapon Extraction Tools.
* Vindachinerk's Noble Abyss Armor Extraction Tools.
* Vindachinerk's Noble Abyss Weapon Extraction Tools.
*/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtractAbyssAction")
public class ExtractAbyssAction extends AbstractItemAction
{
    @XmlAttribute(name = "apextractionrate")
    protected Integer apextractionrate;
    
    protected String itemCategory;
	
    public ExtractAbyssAction() {
    }
	
    public ExtractAbyssAction(Integer apextractionrate, String itemCategory) {
        this.apextractionrate = apextractionrate;
        this.itemCategory = itemCategory;
    }
	
    public Integer getRate() {
        return apextractionrate;
    }
	
    public void setRate(Integer apextractionrate) {
        this.apextractionrate = apextractionrate;
    }
	
    public String getItemCategory() {
        return itemCategory;
    }
	
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }
	
	@Override
	public boolean canAct(Player player, Item parentItem, Item targetItem) {
		if (parentItem == null) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_ITEM_COLOR_ERROR);
			return false;
		}
		return true;
	}
	
    @Override
	public void act(final Player player, final Item parentItem, final Item targetItem) {
		player.getController().cancelUseItem();
		PacketSendUtility.sendPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), parentItem.getObjectId(), parentItem.getItemTemplate().getTemplateId(), 3000, 0, 0));
		player.getController().addTask(TaskId.ITEM_USE, ThreadPoolManager.getInstance().schedule(new Runnable() {
		    @Override
			public void run() {
				if (targetItem.getItemTemplate().getAcquisition().getRequiredAp() != 0) {
					AbyssPointsService.addAp(player,(int)(targetItem.getItemTemplate().getAcquisition().getRequiredAp()*(getRate() / 1000f)));
					player.getInventory().decreaseByObjectId(parentItem.getObjectId(), 1);
					player.getInventory().decreaseItemCount(targetItem, 1);
				}
				PacketSendUtility.broadcastPacketAndReceive(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), parentItem.getObjectId(), parentItem.getItemId(), 0, 1, 0));
			}
		}, 3000));
    }
}