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
 * @author ATracer
 */
public enum ObserverType {
	MOVE(1),
	ATTACK(1 << 1),
	ATTACKED(1 << 2),
	EQUIP(1 << 3),
	UNEQUIP(1 << 4),
	SKILLUSE(1 << 5),
	DEATH(1 << 6),
	DOT_ATTACKED(1 << 7),
	ITEMUSE(1 << 8),
	NPCDIALOGREQUEST(1 << 9),
	ABNORMALSETTED(1 << 10),
	SUMMONRELEASE(1 << 11),
	EQUIP_UNEQUIP(EQUIP.observerMask | UNEQUIP.observerMask),
	ATTACK_DEFEND(ATTACK.observerMask | ATTACKED.observerMask),
	MOVE_OR_DIE(MOVE.observerMask | DEATH.observerMask),
	ALL(MOVE.observerMask | ATTACK.observerMask | ATTACKED.observerMask | SKILLUSE.observerMask | DEATH.observerMask | DOT_ATTACKED.observerMask | ITEMUSE.observerMask | NPCDIALOGREQUEST.observerMask | ABNORMALSETTED.observerMask | SUMMONRELEASE.observerMask);

	private int observerMask;

	private ObserverType(int observerMask) {
		this.observerMask = observerMask;
	}

	public boolean matchesObserver(ObserverType observerType) {
		return (observerType.observerMask & observerMask) == observerType.observerMask;
	}
}
