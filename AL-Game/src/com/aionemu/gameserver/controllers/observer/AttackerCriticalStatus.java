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


/**
 * @author kecimis
 *
 */
public class AttackerCriticalStatus {
	private boolean result = false;
	private int count;
	private int value;
	private boolean isPercent;
	
	public AttackerCriticalStatus(boolean result) {
		this.result = result;
	}

	public AttackerCriticalStatus(int count, int value, boolean isPercent) {
		this.count = count;
		this.value = value;
		this.isPercent = isPercent;
	}

	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	
	/**
	 * @return the isPercent
	 */
	public boolean isPercent() {
		return isPercent;
	}


	
	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}


	
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	
	
	
}
