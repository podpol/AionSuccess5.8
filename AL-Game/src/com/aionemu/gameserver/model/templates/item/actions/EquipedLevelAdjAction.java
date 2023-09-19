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

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.controllers.observer.ItemUseObserver;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.EnchantService;
import com.aionemu.gameserver.services.item.ItemPacketService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipedLevelAdjAction")
public class EquipedLevelAdjAction extends AbstractItemAction
{
    public boolean canAct(Player player, Item parentItem, Item targetItem) {
        if (parentItem == null || targetItem == null) {
            //No items for recommended level reduction could be found.
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_EQUIPLEVEL_ADJ_NO_TARGET_ITEM);
            return false;
        } if (!targetItem.isArchDaevaItem()) {
            //You cannot reduce the recommended level of %0.
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_EQUIPLEVEL_ADJ_CANNOT(parentItem.getNameId()));
            return false;
        } if (targetItem.isPacked()) {
            //You cannot reduce the recommended level of packed items.
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_EQUIPLEVEL_ADJ_WRONG_PACK);
            return false;
        } if (targetItem.hasRetuning()) {
            //You need to use tuning if you want to use the recommended level reduction function.
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_EQUIPLEVEL_ADJ_NEED_IDENTIFY);
            return false;
        } if (targetItem.getReductionLevel() > 5) {
            //You cannot reduce the recommended level of %0 any further.
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_EQUIPLEVEL_ADJ_WRONG_MAX(targetItem.getNameId()));
            return false;
        }
        return true;
    }
	
    public void act(final Player player, final Item parentItem, final Item targetItem) {
        PacketSendUtility.broadcastPacketAndReceive(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), parentItem.getObjectId(), parentItem.getItemTemplate().getTemplateId(), 3000, 0, 0));
        final ItemUseObserver observer = new ItemUseObserver() {
            @Override
            public void abort() {
                player.getController().cancelTask(TaskId.ITEM_USE);
                player.getObserveController().removeObserver(this);
                PacketSendUtility.sendPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId().intValue(), parentItem.getObjectId().intValue(), parentItem.getItemTemplate().getTemplateId(), 0, 3, 0));
                ItemPacketService.updateItemAfterInfoChange(player, targetItem);
                //The reduction of %0‘s recommended level was canceled.
                PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_EQUIPLEVEL_ADJ_CANCEL(targetItem.getNameId()));
            }
        };
        player.getObserveController().attach(observer);
        final boolean isReductionSuccess = isReductionSuccess(player);
        final int reductionCount = reductionCount(player);
        player.getController().addTask(TaskId.ITEM_USE, ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                if (player.getInventory().decreaseByItemId(parentItem.getItemId(), 1)) {
                    player.getController().cancelTask(TaskId.ITEM_USE);
                    player.getObserveController().removeObserver(observer);
                    EnchantService.reductItemAct(player, parentItem, targetItem, targetItem.getReductionLevel(), isReductionSuccess, reductionCount);
                }
			   /**
				* Masterwork Level Reduction Stone 5.8
				* When used on an item that can reduce the recommended level, it will have a 100% chance to reduce the level by 1.
				*/
				else if (parentItem.getItemId() == 165061001 && parentItem.getItemId() == 165061002) {
                    player.getController().cancelTask(TaskId.ITEM_USE);
                    player.getObserveController().removeObserver(observer);
					player.getInventory().decreaseByObjectId(parentItem.getObjectId(), 1);
                    EnchantService.reductItemAct(player, parentItem, targetItem, targetItem.getReductionLevel(), isReductionSuccess, reductionCount);
                }
            }
        }, 3000));
    }
	
    public boolean isReductionSuccess(Player player) {
        int reduction = Rnd.get(1, 1000);
        if (reduction < 600) {
            if (player.getAccessLevel() > 0) {
                PacketSendUtility.sendMessage(player, "Success! Reduction Level: " + reduction + " Lucky: 600");
            }
            return true;
        } else {
            if (player.getAccessLevel() > 0) {
                PacketSendUtility.sendMessage(player, "Fail! Reduction Level: " + reduction + " Lucky: 600");
            }
            return false;
        }
    }
	
    public int reductionCount(Player player) {
        return Rnd.get(1, 3);
    }
}