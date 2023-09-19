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
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/****/
/** Author Rinzler (Encom)
/** Modified by Ranastic
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BonusAddExpAction")
public class BonusAddExpAction extends AbstractItemAction
{
    @XmlAttribute(name = "rate")
    protected Integer rate;

    @XmlAttribute()
    protected boolean isPercent = true;

    public BonusAddExpAction() {
    }
	
    public BonusAddExpAction(Integer rate) {
        this.rate = rate;
    }
	
    public Integer getRate() {
        return rate;
    }
	
    public void setRate(Integer rate) {
        this.rate = rate;
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
        long exp = player.getCommonData().getExpNeed();
        long expPercent = isPercent ? exp * rate / 100 : rate;
        if (player.getInventory().decreaseByObjectId(parentItem.getObjectId().intValue(), 1)) {
            player.getCommonData().setExp(player.getCommonData().getExp() + expPercent, false);
            player.getObserveController().notifyItemuseObservers(parentItem);
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_GET_EXP2(expPercent));
            ItemTemplate itemTemplate = parentItem.getItemTemplate();
            PacketSendUtility.broadcastPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId().intValue(), parentItem.getObjectId().intValue(), itemTemplate.getTemplateId()), true);
        }
    }
}