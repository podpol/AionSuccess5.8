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
package com.aionemu.gameserver.model.gameobjects.player;

import com.aionemu.gameserver.model.gameobjects.Creature;

/**
 * Implemented by handlers of <tt>CM_QUESTION_RESPONSE</tt> responses
 * 
 * @author Ben
 * @modified Lyahim
 */
public abstract class RequestResponseHandler {

	private Creature requester;

	public RequestResponseHandler(Creature requester) {
		this.requester = requester;
	}

	/**
	 * Called when a response is received
	 * 
	 * @param requested
	 *          Player whom requested this response
	 * @param responder
	 *          Player whom responded to this request
	 * @param responseCode
	 *          The response the player gave, usually 0 = no 1 = yes
	 */
	public void handle(Player responder, int response) {
		if (response == 0)
			denyRequest(requester, responder);
		else
			acceptRequest(requester, responder);
	}

	/**
	 * Called when the player accepts a request
	 * 
	 * @param requester
	 *          Creature whom requested this response
	 * @param responder
	 *          Player whom responded to this request
	 */
	public abstract void acceptRequest(Creature requester, Player responder);

	/**
	 * Called when the player denies a request
	 * 
	 * @param requester
	 *          Creature whom requested this response
	 * @param responder
	 *          Player whom responded to this request
	 */
	public abstract void denyRequest(Creature requester, Player responder);

}
