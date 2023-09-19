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

import com.aionemu.gameserver.ai2.handler.ActivateEventHandler;
import com.aionemu.gameserver.ai2.handler.DiedEventHandler;
import com.aionemu.gameserver.ai2.handler.ShoutEventHandler;
import com.aionemu.gameserver.ai2.handler.SpawnEventHandler;
import com.aionemu.gameserver.ai2.poll.AIAnswer;
import com.aionemu.gameserver.ai2.poll.AIAnswers;
import com.aionemu.gameserver.ai2.poll.AIQuestion;
import com.aionemu.gameserver.ai2.poll.NpcAIPolls;
import com.aionemu.gameserver.configs.main.AIConfig;
import com.aionemu.gameserver.controllers.attack.AggroList;
import com.aionemu.gameserver.controllers.effect.EffectController;
import com.aionemu.gameserver.controllers.movement.NpcMoveController;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.TribeClass;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.skill.NpcSkillList;
import com.aionemu.gameserver.model.stats.container.NpcLifeStats;
import com.aionemu.gameserver.model.templates.npc.NpcTemplate;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.knownlist.KnownList;

/**
 * @author ATracer
 */
@AIName("npc")
public class NpcAI2 extends AITemplate {
	
	@Override
	public Npc getOwner() {
		return (Npc) super.getOwner();
	}

	protected NpcTemplate getObjectTemplate() {
		return getOwner().getObjectTemplate();
	}

	protected SpawnTemplate getSpawnTemplate() {
		return getOwner().getSpawn();
	}

	protected NpcLifeStats getLifeStats() {
		return getOwner().getLifeStats();
	}

	protected Race getRace() {
		return getOwner().getRace();
	}

	protected TribeClass getTribe() {
		return getOwner().getTribe();
	}

	protected EffectController getEffectController() {
		return getOwner().getEffectController();
	}

	protected KnownList getKnownList() {
		return getOwner().getKnownList();
	}

	protected AggroList getAggroList() {
		return getOwner().getAggroList();
	}
	
	protected NpcSkillList getSkillList() {
		return getOwner().getSkillList();
	}

	protected VisibleObject getCreator() {
		return getOwner().getCreator();
	}

	/**
	 * DEPRECATED as movements will be processed as commands only from ai
	 */
	protected NpcMoveController getMoveController() {
		return getOwner().getMoveController();
	}

	protected int getNpcId() {
		return getOwner().getNpcId();
	}

	protected int getCreatorId() {
		return getOwner().getCreatorId();
	}
	
	protected boolean isInRange(VisibleObject object, int range) {
		return MathUtil.isIn3dRange(getOwner(), object, range);
	}

	@Override
	protected void handleActivate() {
		ActivateEventHandler.onActivate(this);
	}

	@Override
	protected void handleDeactivate() {
		ActivateEventHandler.onDeactivate(this);
	}

	@Override
	protected void handleSpawned() {
		SpawnEventHandler.onSpawn(this);
	}

	@Override
	protected void handleRespawned() {
		SpawnEventHandler.onRespawn(this);
	}

	@Override
	protected void handleDespawned() {
		if (poll(AIQuestion.CAN_SHOUT))
			ShoutEventHandler.onBeforeDespawn(this);
		SpawnEventHandler.onDespawn(this);
	}

	@Override
	protected void handleDied() {
		DiedEventHandler.onSimpleDie(this);
	}

	@Override
	protected void handleMoveArrived() {
		if (!poll(AIQuestion.CAN_SHOUT) || getSpawnTemplate().getWalkerId() == null)
			return;
		ShoutEventHandler.onReachedWalkPoint(this);
	}

	@Override
	protected void handleTargetChanged(Creature creature) {
		super.handleMoveArrived();
		if (!poll(AIQuestion.CAN_SHOUT))
			return;
		ShoutEventHandler.onSwitchedTarget(this, creature);
	}

	@Override
	protected AIAnswer pollInstance(AIQuestion question) {
		switch (question) {
			case SHOULD_DECAY:
				return NpcAIPolls.shouldDecay(this);
			case SHOULD_RESPAWN:
				return NpcAIPolls.shouldRespawn(this);
			case SHOULD_REWARD:
				return AIAnswers.POSITIVE;
			case CAN_SHOUT:
				return isMayShout() ? AIAnswers.POSITIVE : AIAnswers.NEGATIVE;
			default:
				return null;
		}
	}

	@Override
	public boolean isMayShout() {
		// temp fix, we shouldn't rely on it because of inheritance
		if (AIConfig.SHOUTS_ENABLE)
			return getOwner().mayShout(0);
		return false;
	}

	public boolean isMoveSupported() {
		return getOwner().getGameStats().getMovementSpeedFloat() > 0 && !this.isInSubState(AISubState.FREEZE);
	}

}
