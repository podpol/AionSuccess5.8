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
package com.aionemu.gameserver.ai2;

import com.aionemu.gameserver.ai2.event.AIEventType;
import com.aionemu.gameserver.ai2.poll.AIAnswer;
import com.aionemu.gameserver.ai2.poll.AIQuestion;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.ItemAttackType;

/**
 * @author ATracer
 */
public interface AI2 {

	void onCreatureEvent(AIEventType event, Creature creature);

	void onCustomEvent(int eventId, Object... args);

	void onGeneralEvent(AIEventType event);

	/**
	 * If already handled dialog return true.
	 */
	boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex);

	void think();

	boolean canThink();

	AIState getState();

	AISubState getSubState();

	String getName();

	boolean poll(AIQuestion question);

	AIAnswer ask(AIQuestion question);

	boolean isLogging();

	long getRemainigTime();

	int modifyDamage(int damage);

	int modifyOwnerDamage(int damage);

	void onIndividualNpcEvent(Creature npc);

	int modifyHealValue(int value);

	int modifyMaccuracy(int value);

	int modifySensoryRange(int value);

	ItemAttackType modifyAttackType(ItemAttackType type);
}