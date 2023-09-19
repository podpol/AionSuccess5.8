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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.actions.ExtractAction;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_ENCHANTMENT_EXTRACTION extends AionClientPacket
{
    Logger log = LoggerFactory.getLogger(CM_ENCHANMENT_STONES.class);
	
    private int itemId;
	
    public CM_ENCHANTMENT_EXTRACTION(int opcode, AionConnection.State state, AionConnection.State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        itemId = readD();
    }
	
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        Item item = player.getInventory().getItemByObjId(itemId);
        ExtractAction action = new ExtractAction();
        if (action.canAct(player, item, item)) {
            action.act(player, item, item);
        }
    }
}