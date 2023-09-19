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
package com.aionemu.gameserver.skillengine.task;

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.configs.main.CraftConfig;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.gameobjects.Gatherable;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.gather.GatherableTemplate;
import com.aionemu.gameserver.model.templates.gather.Material;
import com.aionemu.gameserver.network.aion.serverpackets.SM_GATHER_STATUS;
import com.aionemu.gameserver.network.aion.serverpackets.SM_GATHER_UPDATE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class GatheringTask extends AbstractCraftTask
{
    private GatherableTemplate template;
    private Material material;
	
    public GatheringTask(Player requestor, Gatherable gatherable, Material material, int skillLvlDiff) {
        super(requestor, gatherable, skillLvlDiff);
        this.template = gatherable.getObjectTemplate();
        this.material = material;
        this.itemQuality = DataManager.ITEM_DATA.getItemTemplate(this.material.getItemid()).getItemQuality();
        currentSuccessValue = 0;
        currentFailureValue = 0;
        maxSuccessValue = (this.itemQuality.getQualityId() + 1) * 20;
        maxFailureValue = (this.itemQuality.getQualityId() + 1) * 30;
    }
	
    @Override
    protected void onInteractionAbort() {
        PacketSendUtility.sendPacket(requestor, new SM_GATHER_UPDATE(template, material, 0, 0, 5));
        PacketSendUtility.broadcastPacket(requestor, new SM_GATHER_STATUS(requestor.getObjectId(), responder.getObjectId(), 2));
    }
	
    @Override
    protected void onInteractionFinish() {
        ((Gatherable) responder).getController().completeInteraction();
    }
	
    @Override
    protected void onInteractionStart() {
        PacketSendUtility.sendPacket(requestor, new SM_GATHER_UPDATE(template, material, maxSuccessValue, maxFailureValue, 0));
        this.onInteraction();
        PacketSendUtility.broadcastPacket(requestor, new SM_GATHER_STATUS(requestor.getObjectId(), responder.getObjectId(), 0), true);
        PacketSendUtility.broadcastPacket(requestor, new SM_GATHER_STATUS(requestor.getObjectId(), responder.getObjectId(), 1), true);
    }
    /**
     * Perform interaction calculation
     */
    @Override
    protected void analyzeInteraction() {
        int critVal = (int) (Rnd.get(55000) / (skillLvlDiff + 1));
        if (critVal < CraftConfig.CRAFT_CHANCE_PURPLE_CRIT) {
            critType = CraftCritType.PURPLE;
            currentSuccessValue = maxSuccessValue;
            return;
        } else if (critVal < CraftConfig.CRAFT_CHANCE_BLUE_CRIT) {
            critType = CraftCritType.BLUE;
        } else if (critVal < CraftConfig.CRAFT_CHANCE_INSTANT) {
            critType = CraftCritType.INSTANT;
            currentSuccessValue = maxSuccessValue;
            return;
        } if (CraftConfig.CRAFT_CHECK_TASK) {
            if (this.task == null) {
                return;
            }
        }
        double mod = Math.sqrt((double) skillLvlDiff / 450f) * 100f + Rnd.nextGaussian() * 10f;
        mod -= (double) this.itemQuality.getQualityId();
        if (mod < 0) {
            currentFailureValue -= (int) mod;
        } else {
            currentSuccessValue += (int) mod;
        } if (currentSuccessValue >= maxSuccessValue) {
            currentSuccessValue = maxSuccessValue;
        } else if (currentFailureValue >= maxFailureValue) {
            currentFailureValue = maxFailureValue;
        }
    }
	
    @Override
    protected void sendInteractionUpdate() {
        PacketSendUtility.sendPacket(requestor, new SM_GATHER_UPDATE(template, material, currentSuccessValue, currentFailureValue, this.critType.getPacketId()));
        if (this.critType == CraftCritType.BLUE) {
            this.critType = CraftCritType.NONE;
        }
    }
	
    @Override
    protected boolean onInteraction() {
        if (currentSuccessValue == maxSuccessValue) {
            return onSuccessFinish();
        } if (currentFailureValue == maxFailureValue) {
            onFailureFinish();
            return true;
        }
        analyzeInteraction();
        sendInteractionUpdate();
        return false;
    }
	
    @Override
    protected void onFailureFinish() {
        PacketSendUtility.sendPacket(requestor, new SM_GATHER_UPDATE(template, material, currentSuccessValue, currentFailureValue, 1));
        PacketSendUtility.sendPacket(requestor, new SM_GATHER_UPDATE(template, material, currentSuccessValue, currentFailureValue, 7));
        PacketSendUtility.broadcastPacket(requestor, new SM_GATHER_STATUS(requestor.getObjectId(), responder.getObjectId(), 3), true);
    }
	
    @Override
    protected boolean onSuccessFinish() {
        PacketSendUtility.sendPacket(requestor, SM_SYSTEM_MESSAGE.STR_EXTRACT_GATHER_SUCCESS_1_BASIC(new DescriptionId(material.getNameid())));
        PacketSendUtility.broadcastPacket(requestor, new SM_GATHER_STATUS(requestor.getObjectId(), responder.getObjectId(), 2), true);
        PacketSendUtility.sendPacket(requestor, new SM_GATHER_UPDATE(template, material, currentSuccessValue, currentFailureValue, 6));
        if (template.getEraseValue() > 0) {
            requestor.getInventory().decreaseByItemId(template.getRequiredItemId(), template.getEraseValue());
        }
        ItemService.addItem(requestor, material.getItemid(), requestor.getRates().getGatheringCountRate());
        if (requestor.isInInstance()) {
            requestor.getPosition().getWorldMapInstance().getInstanceHandler().onGather(requestor, (Gatherable) responder);
        }
        ((Gatherable) responder).getController().rewardPlayer(requestor);
        return true;
    }
}