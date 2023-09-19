package com.aionemu.gameserver.model.event_window;

import java.sql.Timestamp;

/**
 * 
 * @author Ranastic
 *
 */
public class EventWindowEntry {

	private int id;
	private Timestamp lastStamp;
	private int elapsed;
	
	public EventWindowEntry(int id, Timestamp lastStamp, int elapsed) {
		this.id = id;
		this.lastStamp = lastStamp;
		this.elapsed = elapsed;
	}
	
	public int getId() {
		return id;
	}
	
	public Timestamp getLastStamp() {
		return lastStamp;
	}
	
	public int getElapsed() {
		return elapsed;
	}
}
