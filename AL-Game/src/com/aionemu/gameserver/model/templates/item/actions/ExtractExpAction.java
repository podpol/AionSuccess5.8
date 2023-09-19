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

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.RewardType;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtractExpAction")
public class ExtractExpAction extends AbstractItemAction
{
	@XmlAttribute(name = "expextractionrate")
    protected Integer expextractionrate;
	
	@XmlAttribute(name = "reward")
    protected Integer reward;
    
    public ExtractExpAction() {
    }
	
    public ExtractExpAction(Integer expextractionrate) {
        this.expextractionrate = expextractionrate;
    }
	
    public Integer getRate() {
        return expextractionrate;
    }
	
    public void setRate(Integer expextractionrate) {
        this.expextractionrate = expextractionrate;
    }
    
    public Integer getReward() {
        return reward;
    }
	
    public void setReward(Integer reward) {
        this.reward = reward;
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
		ItemTemplate itemTemplate = parentItem.getItemTemplate();
		ItemService.addItem(player, getReward(), 1);
		player.getCommonData().addExp((long) - ((player.getCommonData().getExpNeed() * getRate()) / 100f), RewardType.HUNTING);
		PacketSendUtility.broadcastPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), parentItem.getObjectId(), itemTemplate.getTemplateId()), true);
		player.getInventory().decreaseByObjectId(parentItem.getObjectId(), 1);
    }
}