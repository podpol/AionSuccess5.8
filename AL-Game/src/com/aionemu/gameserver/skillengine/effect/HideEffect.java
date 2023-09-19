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
package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.configs.main.SecurityConfig;
import com.aionemu.gameserver.controllers.attack.AttackUtil;
import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.ObserverType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureVisualState;
import com.aionemu.gameserver.network.aion.serverpackets.SM_PLAYER_STATE;
import com.aionemu.gameserver.services.player.PlayerVisualStateService;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sweetkr
 * @author Cura
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HideEffect")
public class HideEffect extends BuffEffect {

	@XmlAttribute
	protected CreatureVisualState state;
	@XmlAttribute(name = "buffcount")
	protected int buffCount;

	@XmlAttribute
	protected int type = 0;

	@Override
	public void applyEffect(Effect effect) {
		effect.addToEffectedController();
	}

	@Override
	public void endEffect(Effect effect) {
		super.endEffect(effect);

		Creature effected = effect.getEffected();
		effected.getEffectController().unsetAbnormal(AbnormalState.HIDE.getId());

		effected.unsetVisualState(state);

		if ((effected instanceof Player)) {
			ActionObserver observer = effect.getActionObserver(position);
			effect.getEffected().getObserveController().removeObserver(observer);
		}

		PacketSendUtility.broadcastPacketAndReceive(effected, new SM_PLAYER_STATE(effected));
		if ((SecurityConfig.INVIS) && ((effected instanceof Player)))
			PlayerVisualStateService.hideValidate((Player) effected);
	}

	@Override
	public void startEffect(final Effect effect) {
		super.startEffect(effect);

		final Creature effected = effect.getEffected();
		effected.getEffectController().setAbnormal(AbnormalState.HIDE.getId());
		effect.setAbnormal(AbnormalState.HIDE.getId());

		effected.setVisualState(state);

		AttackUtil.cancelCastOn(effected);

		PacketSendUtility.broadcastPacketAndReceive(effected, new SM_PLAYER_STATE(effected));

		ThreadPoolManager.getInstance().schedule(new Runnable() {

			public void run() {
				AttackUtil.removeTargetFrom(effected, true);
			}
		}, 500L);

		if ((effected instanceof Player)) {
			if (SecurityConfig.INVIS) {
				PlayerVisualStateService.hideValidate((Player) effected);
			}

			// Remove Hide when use skill
			ActionObserver observer = new ActionObserver(ObserverType.SKILLUSE) {

				int bufNumber = 1;

				@Override
				public void skilluse(Skill skill) {
					// [2.5] Allow self buffs = (buffCount - 1)
					if (skill.isSelfBuff() && bufNumber++ < buffCount) {
						return;
					}
					effect.endEffect();
				}
			};
			effected.getObserveController().addObserver(observer);
			effect.setActionObserver(observer, position);

			if (type == 0) {
				effect.setCancelOnDmg(true);
			}

			// Remove Hide when attacking
			effected.getObserveController().attach(new ActionObserver(ObserverType.ATTACK) {

				@Override
				public void attack(Creature creature) {
					effect.endEffect();
				}
			});
			/**
			 * for player adding: Remove Hide when using any item action Remove hide when requesting dialog to any npc
			 */
			effected.getObserveController().attach(new ActionObserver(ObserverType.ITEMUSE) {

				@Override
				public void itemused(Item item) {
					effect.endEffect();
				}
			});
			effected.getObserveController().attach(new ActionObserver(ObserverType.NPCDIALOGREQUEST) {

				@Override
				public void npcdialogrequested(Npc npc) {
					effect.endEffect();
				}

			});
		}
		else if (type == 0) {
			effect.setCancelOnDmg(true);

			effected.getObserveController().attach(new ActionObserver(ObserverType.ATTACK) {

				public void attack(Creature creature) {
					effect.endEffect();
				}
			});
			effected.getObserveController().attach(new ActionObserver(ObserverType.SKILLUSE) {

				public void skilluse(Skill skill) {
					effect.endEffect();
				}
			});
		}
	}
}
