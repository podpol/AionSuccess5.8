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

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.controllers.observer.ItemUseObserver;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.teleport.MultiReturnLocationList;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.services.teleport.MultiReturnService;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiReturnAction")
public class MultiReturnAction extends AbstractItemAction
{
   /**
	* 6 ELYOS.
	* 7 ASMODIANS.
	*/
	@XmlAttribute(name = "id")
    private int id;
	
    public int getId() {
        return id;
    }
	
    @Override
    public boolean canAct(Player player, Item parentItem, Item targetItem) {
        return true;
    }
	
    @Override
    public void act(final Player player, final Item parentItem, Item targetItem) {
    }
	
    public void act(final Player player, final Item MultiReturn, final int SelectedMapIndex) {
        PacketSendUtility.sendPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), MultiReturn.getObjectId(), MultiReturn.getItemTemplate().getTemplateId(), 3000, 0, 0));
        player.getController().cancelTask(TaskId.ITEM_USE);
        final ItemUseObserver observer = new ItemUseObserver() {
            @Override
            public void abort() {
                player.getController().cancelTask(TaskId.ITEM_USE);
                PacketSendUtility.sendPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), MultiReturn.getObjectId(), MultiReturn.getItemTemplate().getTemplateId(), 0, 2, 0));
                player.getObserveController().removeObserver(this);
                player.removeItemCoolDown(MultiReturn.getItemTemplate().getUseLimits().getDelayId());
            }
        };
        player.getObserveController().attach(observer);
        player.getController().addTask(TaskId.ITEM_USE, ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                player.getObserveController().removeObserver(observer);
                if (player.getInventory().decreaseByObjectId(MultiReturn.getObjectId(), 1)) {
            		int MultiReturnId = getId();
            		com.aionemu.gameserver.model.templates.teleport.MultiReturn rItem = DataManager.MULTI_RETURN_ITEM_DATA.getMultiReturnById(MultiReturnId);
            		if (rItem != null && rItem.getMultiReturnList() != null) {
            			MultiReturnLocationList ReturnData = rItem.getReturnDataById(SelectedMapIndex);
            			if (ReturnData != null) {
            				int ReturnCount = rItem.getMultiReturnList().size();
           					if (SelectedMapIndex <= (ReturnCount - 1)) {
           						int worldId = ReturnData.getWorldId();
								int LocId = MultiReturnService.getTeleportWorldId(worldId, player.getRace());
           						MultiReturnService.Teleport(player, LocId, worldId);
           					}
            			}
            		}
                }
                PacketSendUtility.sendPacket(player, new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), MultiReturn.getObjectId(), MultiReturn.getItemTemplate().getTemplateId(), 0, 1, 0));
            }
        }, 3000));
    }
}