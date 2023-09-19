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
package com.aionemu.gameserver.model.team2.alliance.callback;

import com.aionemu.commons.callbacks.Callback;
import com.aionemu.commons.callbacks.CallbackResult;
import com.aionemu.gameserver.model.gameobjects.player.Player;

@SuppressWarnings("rawtypes")
public abstract class PlayerAllianceCreateCallback implements Callback
{
    @Override
    public CallbackResult beforeCall(Object obj, Object[] args) {
        onBeforeAllianceCreate((Player) args[0]);
        return CallbackResult.newContinue();
    }
	
    @Override
    public CallbackResult afterCall(Object obj, Object[] args, Object methodResult) {
        onAfterAllianceCreate((Player) args[0]);
        return CallbackResult.newContinue();
    }
	
    @Override
    public Class<? extends Callback> getBaseClass() {
        return PlayerAllianceCreateCallback.class;
    }
	
    public abstract void onBeforeAllianceCreate(Player player);
    public abstract void onAfterAllianceCreate(Player player);
}