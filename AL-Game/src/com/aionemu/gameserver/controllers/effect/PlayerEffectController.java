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
package com.aionemu.gameserver.controllers.effect;

import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ABNORMAL_STATE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_PLAYER_STANCE;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.SkillTargetSlot;
import com.aionemu.gameserver.skillengine.model.SkillTemplate;
import com.aionemu.gameserver.taskmanager.tasks.PacketBroadcaster.BroadcastMode;
import com.aionemu.gameserver.taskmanager.tasks.TeamEffectUpdater;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.Collection;
import java.util.Collections;

/**
 * @author ATracer
 */
public class PlayerEffectController extends EffectController {

	public PlayerEffectController(Creature owner) {
		super(owner);
	}

	@Override
	public void addEffect(Effect effect) {
		if (checkDuelCondition(effect) && !effect.getIsForcedEffect())
			return;

		super.addEffect(effect);
		updatePlayerIconsAndGroup(effect);
	}

	@Override
	public void clearEffect(Effect effect) {
		super.clearEffect(effect);
		updatePlayerIconsAndGroup(effect);
	}

	@Override
	public Player getOwner() {
		return (Player) super.getOwner();
	}

	/**
	 * @param effect
	 */
	private void updatePlayerIconsAndGroup(Effect effect) {
		if (!effect.isPassive()) {
			updatePlayerEffectIcons();
			if (getOwner().isInTeam()) {
				TeamEffectUpdater.getInstance().startTask(getOwner());
			}
		}
	}
	
	@Override
	public void updatePlayerEffectIcons() {
		getOwner().addPacketBroadcastMask(BroadcastMode.UPDATE_PLAYER_EFFECT_ICONS);
	}
	
	@Override
	public void updatePlayerEffectIconsImpl() {
		Collection<Effect> effects = getAbnormalEffectsToShow();
		PacketSendUtility.sendPacket((Player) getOwner(), new SM_ABNORMAL_STATE(effects, abnormals));
	}

	/**
	 * Effect of DEBUFF should not be added if duel ended (friendly unit)
	 * 
	 * @param effect
	 * @return
	 */
	private boolean checkDuelCondition(Effect effect) {
		Creature creature = effect.getEffector();
		if (creature instanceof Player) {
			if (!getOwner().isEnemy(creature) && effect.getTargetSlot() == SkillTargetSlot.DEBUFF.ordinal()) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param skillId
	 * @param skillLvl
	 * @param currentTime
	 * @param reuseDelay
	 */
	public void addSavedEffect(int skillId, int skillLvl, int remainingTime, long endTime) {
		SkillTemplate template = DataManager.SKILL_DATA.getSkillTemplate(skillId);

		if (remainingTime <= 0)
			return;
		if (CustomConfig.ABYSSXFORM_LOGOUT && template.isDeityAvatar()) {
			
			if (System.currentTimeMillis() >= endTime)
				return;
			else
				remainingTime = (int)(endTime - System.currentTimeMillis());
		} 
		
		Effect effect = new Effect(getOwner(), getOwner(), template, skillLvl, remainingTime);
		abnormalEffectMap.put(effect.getStack(), effect);
		effect.addAllEffectToSucess();
		effect.startEffect(true);

		if (effect.getSkillTemplate().getTargetSlot() != SkillTargetSlot.NOSHOW)
			PacketSendUtility.sendPacket(getOwner(), new SM_ABNORMAL_STATE(Collections.singletonList(effect), abnormals));

	}

	@Override
	public void broadCastEffectsImp() {
		super.broadCastEffectsImp();
		Player player = getOwner();
		if (player.getController().isUnderStance()) {
			PacketSendUtility.sendPacket(player, new SM_PLAYER_STANCE(player, 1));
		}
	}

}
