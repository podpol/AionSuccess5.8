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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Manages the asking of and responding to <tt>SM_QUESTION_WINDOW</tt>
 * 
 * @author Ben
 */
public class ResponseRequester {

	private Player player;
	private HashMap<Integer, RequestResponseHandler> map = new HashMap<Integer, RequestResponseHandler>();
	private static Logger log = LoggerFactory.getLogger(ResponseRequester.class);

	public ResponseRequester(Player player) {
		this.player = player;
	}

	/**
	 * Adds this handler to this messageID, returns false if there already exists one
	 * 
	 * @param messageId
	 *          ID of the request message
	 * @return true or false
	 */
	public synchronized boolean putRequest(int messageId, RequestResponseHandler handler) {
		if (map.containsKey(messageId))
			return false;

		map.put(messageId, handler);
		return true;
	}

	/**
	 * Responds to the given message ID with the given response Returns success
	 * 
	 * @param messageId
	 * @param response
	 * @return Success
	 */
	public synchronized boolean respond(int messageId, int response) {
		RequestResponseHandler handler = map.get(messageId);
		if (handler != null) {
			map.remove(messageId);
			log.debug("RequestResponseHandler triggered for response code " + messageId + " from " + player.getName());
			handler.handle(player, response);
			return true;
		}
		return false;
	}

	/**
	 * Automatically responds 0 to all requests, passing the given player as the responder
	 */
	public synchronized void denyAll() {
		for (RequestResponseHandler handler : map.values()) {
			handler.handle(player, 0);
		}

		map.clear();
	}
}
