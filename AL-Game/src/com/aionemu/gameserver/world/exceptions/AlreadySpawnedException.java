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
 * This exception will be thrown when object will be spawned more than one time (without despawning)
 * 
 * @author -Nemesiss-
 */
@SuppressWarnings("serial")
public class AlreadySpawnedException extends RuntimeException {

	/**
	 * Constructs an <code>AlreadySpawnedException</code> with no detail message.
	 */
	public AlreadySpawnedException() {
		super();
	}

	/**
	 * Constructs an <code>AlreadySpawnedException</code> with the specified detail message.
	 * 
	 * @param s
	 *          the detail message.
	 */
	public AlreadySpawnedException(String s) {
		super(s);
	}

	/**
	 * Creates new error
	 * 
	 * @param message
	 *          exception description
	 * @param cause
	 *          reason of this exception
	 */
	public AlreadySpawnedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates new error
	 * 
	 * @param cause
	 *          reason of this exception
	 */
	public AlreadySpawnedException(Throwable cause) {
		super(cause);
	}
}
