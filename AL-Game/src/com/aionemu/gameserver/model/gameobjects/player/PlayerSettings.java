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

import com.aionemu.gameserver.model.gameobjects.PersistentState;

/**
 * @author ATracer
 */
public class PlayerSettings {

	private PersistentState persistentState;

	private byte[] uiSettings;
	private byte[] shortcuts;
	private byte[] houseBuddies;
	private int deny = 0;
	private int display = 0;

	public PlayerSettings() {

	}

	public PlayerSettings(byte[] uiSettings, byte[] shortcuts, byte[] houseBuddies, int deny, int display) {
		this.uiSettings = uiSettings;
		this.shortcuts = shortcuts;
		this.houseBuddies = houseBuddies;
		this.deny = deny;
		this.display = display;
	}

	/**
	 * @return the persistentState
	 */
	public PersistentState getPersistentState() {
		return persistentState;
	}

	/**
	 * @param persistentState
	 *          the persistentState to set
	 */
	public void setPersistentState(PersistentState persistentState) {
		this.persistentState = persistentState;
	}

	/**
	 * @return the uiSettings
	 */
	public byte[] getUiSettings() {
		return uiSettings;
	}

	/**
	 * @param uiSettings
	 *          the uiSettings to set
	 */
	public void setUiSettings(byte[] uiSettings) {
		this.uiSettings = uiSettings;
		persistentState = PersistentState.UPDATE_REQUIRED;
	}

	/**
	 * @return the shortcuts
	 */
	public byte[] getShortcuts() {
		return shortcuts;
	}

	/**
	 * @param shortcuts
	 *          the shortcuts to set
	 */
	public void setShortcuts(byte[] shortcuts) {
		this.shortcuts = shortcuts;
		persistentState = PersistentState.UPDATE_REQUIRED;
	}
	
	/**
	 * @return the houseBuddies
	 */
	public byte[] getHouseBuddies() {
		return houseBuddies;
	}

	/**
	 * @param houseBuddies
	 *          the houseBuddies to set
	 */
	public void setHouseBuddies(byte[] houseBuddies) {
		this.houseBuddies = houseBuddies;
		persistentState = PersistentState.UPDATE_REQUIRED;
	}

	/**
	 * @return the display
	 */
	public int getDisplay() {
		return display;
	}

	/**
	 * @param display
	 *          the display to set
	 */
	public void setDisplay(int display) {
		this.display = display;
		persistentState = PersistentState.UPDATE_REQUIRED;
	}

	/**
	 * @return the deny
	 */
	public int getDeny() {
		return deny;
	}

	/**
	 * @param deny
	 *          the deny to set
	 */
	public void setDeny(int deny) {
		this.deny = deny;
		persistentState = PersistentState.UPDATE_REQUIRED;
	}

	public boolean isInDeniedStatus(DeniedStatus deny) {
		int isDeniedStatus = this.deny & deny.getId();

		if (isDeniedStatus == deny.getId())
			return true;

		return false;
	}
}
