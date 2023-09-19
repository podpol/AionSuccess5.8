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
package com.aionemu.gameserver.model.templates.staticdoor;

import java.util.EnumSet;

/**
 * @author Rolandas
 */
public enum StaticDoorState {
	NONE(0),
	OPENED(1 << 0),
	CLICKABLE(1 << 1),
	CLOSEABLE(1 << 2),
	ONEWAY(1 << 3);

	private StaticDoorState(int flag) {
		this.flag = flag;
	}

	private int flag;

	public int getFlag() {
		return flag;
	}

	public static void setStates(int flags, EnumSet<StaticDoorState> state) {
		for (StaticDoorState states : StaticDoorState.values()) {
			if (states == NONE)
				continue;
			if ((flags & states.flag) == 0)
				state.remove(states);
			else
				state.add(states);
		}
	}

	public static int getFlags(EnumSet<StaticDoorState> doorStates) {
		int result = 0;
		for (StaticDoorState state : StaticDoorState.values()) {
			if (state == NONE)
				continue;
			if (doorStates.contains(state))
				result |= state.flag;
		}
		return result;
	}
}
