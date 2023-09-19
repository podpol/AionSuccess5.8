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

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerTransformDAO;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.Summon;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_TRANSFORM;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.TransformType;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Sweetkr, kecimis
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransformEffect")
public abstract class TransformEffect extends EffectTemplate {

	@XmlAttribute
	protected int model;

	@XmlAttribute
	protected TransformType type = TransformType.NONE;

	@XmlAttribute
	protected int panelid;

	@XmlAttribute
	protected int itemId;

	@Override
	public void applyEffect(Effect effect) {
		effect.addToEffectedController();
	}

	public void endEffect(Effect effect, AbnormalState state) {
		final Creature effected = effect.getEffected();

		if (state != null)
			effected.getEffectController().unsetAbnormal(state.getId());

		if (effected instanceof Player) {
			int newModel = 0;
			TransformType transformType = TransformType.PC;
			for (Effect tmp : effected.getEffectController().getAbnormalEffects()) {
				for (EffectTemplate template : tmp.getEffectTemplates()) {
					if (template instanceof TransformEffect) {
						if (((TransformEffect) template).getTransformId() == model)
							continue;
						newModel = ((TransformEffect) template).getTransformId();
						transformType = ((TransformEffect) template).getTransformType();
						break;
					}
				}
			}
			effected.getTransformModel().setModelId(newModel);
			effected.getTransformModel().setTransformType(transformType);
			effected.getTransformModel().setItemId(0);
			DAOManager.getDAO(PlayerTransformDAO.class).deletePlTransfo(effected.getObjectId());
		}
		else if (effected instanceof Summon) {
			effected.getTransformModel().setModelId(0);
		}
		else if (effected instanceof Npc) {
			effected.getTransformModel().setModelId(effected.getObjectTemplate().getTemplateId());
		}
		effected.getTransformModel().setPanelId(0);
		PacketSendUtility.broadcastPacketAndReceive(effected, new SM_TRANSFORM(effected, 0, false, 0));

		if (effected instanceof Player) {
			((Player) effected).setTransformed(false);
			((Player) effected).setTransformedModelId(0);
			((Player) effected).setTransformedItemId(0);
			((Player) effected).setTransformedPanelId(0);
		}
	}

	public void startEffect(Effect effect, AbnormalState effectId) {
		final Creature effected = effect.getEffected();

		if (effectId != null) {
			effect.setAbnormal(effectId.getId());
			effected.getEffectController().setAbnormal(effectId.getId());
		}

		effected.getTransformModel().setModelId(model);
		effected.getTransformModel().setPanelId(panelid);
		effected.getTransformModel().setItemId(itemId);
		effected.getTransformModel().setTransformType(effect.getTransformType());
		PacketSendUtility.broadcastPacketAndReceive(effected, new SM_TRANSFORM(effected, panelid, true, itemId));

		if (effected instanceof Player) {
			((Player) effected).setTransformed(true);
			((Player) effected).setTransformedModelId(model);
			((Player) effected).setTransformedItemId(itemId);
			((Player) effected).setTransformedItemId(panelid);
			DAOManager.getDAO(PlayerTransformDAO.class).storePlTransfo(effected.getObjectId(), panelid, itemId);
		}
	}

	public TransformType getTransformType() {
		return type;
	}

	public int getTransformId() {
		return model;
	}

	public int getPanelId() {
		return panelid;
	}
}
