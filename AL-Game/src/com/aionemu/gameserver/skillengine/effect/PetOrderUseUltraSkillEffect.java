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

import com.aionemu.gameserver.controllers.SummonController;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SUMMON_USESKILL;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer modified by Sippolo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PetOrderUseUltraSkillEffect")
public class PetOrderUseUltraSkillEffect extends EffectTemplate {

	@XmlAttribute
	protected boolean release;

	@Override
	public void applyEffect(Effect effect) {
		Player effector = (Player) effect.getEffector();

		if (effector.getSummon() == null) {
			return;
		}

		int effectorId = effector.getSummon().getObjectId();

		int npcId = effector.getSummon().getNpcId();
		int orderSkillId = effect.getSkillId();

		int petUseSkillId = DataManager.PET_SKILL_DATA.getPetOrderSkill(orderSkillId, npcId);
		int targetId = effect.getEffected().getObjectId();

		// Handle automatic release if skill expects so
		if (release) {
			SummonController controller = effector.getSummon().getController();
			if ((controller instanceof SummonController)) {
				effector.getSummon().getController().setReleaseAfterSkill(petUseSkillId);
			}
		}
		PacketSendUtility.sendPacket(effector, new SM_SUMMON_USESKILL(effectorId, petUseSkillId, 1, targetId));
	}

	@Override
	public void calculate(Effect effect) {
		if (effect.getEffector() instanceof Player && effect.getEffected() != null)
			super.calculate(effect, null, null);
	}
}
