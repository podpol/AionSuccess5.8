package com.aionemu.gameserver.model.event_window;

import java.sql.Timestamp;

import com.aionemu.gameserver.model.gameobjects.PersistentState;

/**
 * 
 * @author Ranastic
 *
 */
public class PlayerEventWindowEntry extends EventWindowEntry {

	private PersistentState persistentState;
	
	public PlayerEventWindowEntry(int id, Timestamp lastStamp, int elapsed, PersistentState persistentState) {
		super(id, lastStamp, elapsed);
		this.persistentState = persistentState;
	}

	public PersistentState getPersistentState() {
		return persistentState;
	}
	
	public void setPersistentState(PersistentState persistentState) {
		switch (persistentState) {
			case DELETED:
				if (this.persistentState == PersistentState.NEW) {
					this.persistentState = PersistentState.NOACTION;
				} else {
					this.persistentState = PersistentState.DELETED;
				}
			break;
			case UPDATE_REQUIRED:
				if (this.persistentState != PersistentState.NEW) {
					this.persistentState = PersistentState.UPDATE_REQUIRED;
				}
			break;
			case NOACTION:
			break;
			default:
			this.persistentState = persistentState;
		}
	}
}
