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
package com.aionemu.gameserver.model.gameobjects;

import gnu.trove.map.hash.TIntObjectHashMap;

/**
 * @author ATracer
 */
public enum PetAction {
	ADOPT(1),
	SURRENDER(2),
	SPAWN(3),
	DISMISS(4),
	TALK_WITH_MERCHANT(6),
	TALK_WITH_MINDER(7),
	FOOD(9),
	RENAME(10),
	MOOD(12),
	UNKNOWN(255);

	private static TIntObjectHashMap<PetAction> petActions;

	static {
		petActions = new TIntObjectHashMap<PetAction>();
		for (PetAction action : values()) {
			petActions.put(action.getActionId(), action);
		}
	}

	private int actionId;

	private PetAction(int actionId) {
		this.actionId = actionId;
	}

	public int getActionId() {
		return actionId;
	}

	public static PetAction getActionById(int actionId) {
		PetAction action = petActions.get(actionId);
		return action != null ? action : UNKNOWN;
	}
}
