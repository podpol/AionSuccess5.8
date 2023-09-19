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
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.model.gameobjects.player.PlayerSweep;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SHUGO_SWEEP;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Wnkrz on 24/10/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShugoSweepAction")
public class ShugoSweepAction extends AbstractItemAction
{
    @XmlAttribute(name = "type") //1 reset ; 2 gold dice
    protected int type;
	
    @XmlAttribute(name = "count")
    protected boolean count;
	
    @Override
    public boolean canAct(Player player, Item parentItem, Item targetItem) {
        if (type == 1) {
            if (getCommonData(player).getResetBoard() != 0) {
                player.sendMessage("You have already one Reset Board");
                return false;
            }
        }
        return true;
    }
	
    @Override
    public void act(Player player, Item parentItem, Item targetItem) {
        if (player.getInventory().decreaseByObjectId(parentItem.getObjectId(), 1)) {
            if (type == 1) {
                getCommonData(player).setResetBoard(getCommonData(player).getResetBoard() + 1);
                player.sendMessage("You have received one Reset Board");
            } if (type == 2) {
                getCommonData(player).setGoldenDice(getCommonData(player).getGoldenDice() + 1);
                player.sendMessage("You have received one Golden Dice");
            }
            PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(getPlayerSweep(player).getBoardId(), getPlayerSweep(player).getStep(), getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), getCommonData(player).getResetBoard(), 0));
        }
    }
	
    public PlayerCommonData getCommonData(Player player) {
        return player.getCommonData();
    }
	
    public PlayerSweep getPlayerSweep(Player player) {
        return player.getPlayerShugoSweep();
    }
}