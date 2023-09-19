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
package com.aionemu.gameserver.controllers.observer;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.MathUtil;

/**
 *
 * @author nrg
 */
public abstract class DialogObserver extends ActionObserver {
    
        private Player responder;
        private Creature requester;
        private int maxDistance;
    
        public DialogObserver(Creature requester, Player responder, int maxDistance) {
            	super(ObserverType.MOVE);
		this.responder = responder;
                this.requester = requester;
                this.maxDistance = maxDistance;
        }
        
        @Override
        public void moved() {
            if(!MathUtil.isIn3dRange(responder, requester, maxDistance))
                tooFar(requester, responder);           
        }
        
        /**
         * Is called when player is too far away from dialog serving object
         */
        public abstract void tooFar(Creature requester, Player responder);
}
