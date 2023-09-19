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
package com.aionemu.gameserver.world.exceptions;

/**
 * This Exception will be thrown when some object is referencing to Instance that do not exist now.
 * 
 * @author -Nemesiss-
 */
@SuppressWarnings("serial")
public class InstanceNotExistException extends RuntimeException {

	/**
	 * Constructs an <code>InstanceNotExistException</code> with no detail message.
	 */
	public InstanceNotExistException() {
		super();
	}

	/**
	 * Constructs an <code>InstanceNotExistException</code> with the specified detail message.
	 * 
	 * @param s
	 *          the detail message.
	 */
	public InstanceNotExistException(String s) {
		super(s);
	}
}
