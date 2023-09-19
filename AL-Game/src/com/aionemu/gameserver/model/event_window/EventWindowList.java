package com.aionemu.gameserver.model.event_window;

import java.sql.Timestamp;

import com.aionemu.gameserver.model.gameobjects.Creature;

/**
 * 
 * @author Ranastic
 *
 * @param <T>
 */
public interface EventWindowList<T extends Creature> {
	
	boolean add(T creature, int id, Timestamp lastStamp, int elapsed);
	boolean remove(T creature, int id);
	int size();
}
