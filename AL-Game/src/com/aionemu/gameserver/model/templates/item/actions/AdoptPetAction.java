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

import javax.xml.bind.annotation.XmlAttribute;

/****/
/** Author themoose (Encom)
/****/

public class AdoptPetAction extends AbstractItemAction
{
    @XmlAttribute(name = "petId")
    private int petId;
	
    @XmlAttribute(name = "minutes")
    private int expireMinutes;
	
    @XmlAttribute(name = "sidekick")
    private Boolean isSideKick = false;
	
    @Override
    public boolean canAct(Player player, Item parentItem, Item targetItem) {
        return false;
    }
	
    @Override
    public void act(Player player, Item parentItem, Item targetItem) {
    }
	
    public int getPetId() {
        return petId;
    }
	
    public int getExpireMinutes() {
        return expireMinutes;
    }
	
    public Boolean isSideKick() {
        return isSideKick;
    }
}