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

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.RequestResponseHandler;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUESTION_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bio, Sippolo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecallInstantEffect")
public class RecallInstantEffect extends EffectTemplate {

	@Override
	public void applyEffect(Effect effect) {
		final Creature effector = effect.getEffector();
		final Player effected = (Player) effect.getEffected();

		final int worldId = effect.getWorldId();
		final int instanceId = effect.getInstanceId();
		final float locationX = effect.getSkill().getX();
		final float locationY = effect.getSkill().getY();
		final float locationZ = effect.getSkill().getZ();
		final byte locationH = effect.getSkill().getH();

		/**
		 * TODO need to confirm if cannot be summoned while on abnormal effects stunned, sleeping, feared, etc.
		 */
		RequestResponseHandler rrh = new RequestResponseHandler(effector) {

			@Override
			public void denyRequest(Creature effector, Player effected) {

				PacketSendUtility.sendPacket((Player) effector, SM_SYSTEM_MESSAGE.STR_MSG_Recall_Rejected_EFFECT(effected.getName()));
				PacketSendUtility.sendPacket(effected, SM_SYSTEM_MESSAGE.STR_MSG_Recall_Rejected_EFFECT(effector.getName()));
			}

			@Override
			public void acceptRequest(Creature effector, Player effected) {
				TeleportService2.teleportTo(effected, worldId, instanceId, locationX, locationY, locationZ, locationH);
			}
		};

		effected.getResponseRequester().putRequest(SM_QUESTION_WINDOW.STR_SUMMON_PARTY_DO_YOU_ACCEPT_REQUEST, rrh);
		PacketSendUtility.sendPacket(effected, new SM_QUESTION_WINDOW(SM_QUESTION_WINDOW.STR_SUMMON_PARTY_DO_YOU_ACCEPT_REQUEST, 0, 0, effector.getName(), "Summon Group Member", 30));
	}

	@Override
	public void calculate(Effect effect) {
		final Creature effector = effect.getEffector();

		if (!(effect.getEffected() instanceof Player))
			return;
		Player effected = (Player) effect.getEffected();

		if (effected.getController().isInCombat())
			return;

		if (effector.getWorldId() == effected.getWorldId() && !effector.isInInstance() && !(effector.isEnemy(effected))) {
			effect.getSkill().setTargetPosition(effector.getX(), effector.getY(), effector.getZ(), effector.getHeading());
			effect.addSucessEffect(this);
		}
	}
}
