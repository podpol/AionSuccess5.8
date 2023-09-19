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

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.stats.container.StatEnum;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MANTRA_EFFECT;
import com.aionemu.gameserver.services.DuelService;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.SkillTemplate;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.utils.audit.AuditLogger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuraEffect")
public class AuraEffect extends EffectTemplate
{
	@XmlAttribute
    protected int distance;
	
    @XmlAttribute(name = "skill_id")
    protected int skillId;
	
    @Override
    public void applyEffect(Effect effect) {
        final Player effector = (Player) effect.getEffector();
        if (effector.getEffectController().isNoshowPresentBySkillId(effect.getSkillId())) {
            AuditLogger.info(effector, "Player might be abusing CM_CASTSPELL mantra effect Player kicked skill id: " + effect.getSkillId());
            effector.getClientConnection().closeNow();
            return;
        }
        effect.addToEffectedController();
    }
	
    @Override
    public void onPeriodicAction(final Effect effect) {
        final Player effector = (Player) effect.getEffector();
        if (!effector.isOnline()) {
            return;
        } if (effector.isInGroup2() || effector.isInAlliance2()) {
            Collection<Player> onlinePlayers = effector.isInGroup2() ? effector.getPlayerGroup2().getOnlineMembers() : effector.getPlayerAllianceGroup2().getOnlineMembers();
            final int actualRange = (int) (distance * effector.getGameStats().getStat(StatEnum.BOOST_MANTRA_RANGE, 100).getCurrent() / 100f);
            for (Player player : onlinePlayers) {
                if (MathUtil.isIn3dRange(effector, player, actualRange)) {
                    if (!DuelService.getInstance().isDueling(player.getObjectId()) && player != effector) {
                        applyAuraTo(player, effect);
                    } if (DuelService.getInstance().isDueling(effector.getObjectId()) && DuelService.getInstance().isDueling(player.getObjectId())) {
                        applyAuraTo(effector, effect);
                    } else {
                        applyAuraTo(effector, effect);
                    }
                }
            }
        } else {
            applyAuraTo(effector, effect);
        }
        PacketSendUtility.broadcastPacket(effector, new SM_MANTRA_EFFECT(effector, skillId));
    }
	
    private void applyAuraTo(Player effected, Effect effect) {
        SkillTemplate template = DataManager.SKILL_DATA.getSkillTemplate(skillId);
        Effect e = new Effect(effected, effected, template, template.getLvl(), 0);
        e.initialize();
        e.applyEffect();
    }
	
    @Override
    public void startEffect(final Effect effect) {
        effect.setPeriodicTask(ThreadPoolManager.getInstance().scheduleAtFixedRate(new AuraTask(effect), 0, 6500), position);
    }
	
    private class AuraTask implements Runnable {
        private Effect effect;
        public AuraTask(Effect effect) {
            this.effect = effect;
        }
        @Override
        public void run() {
            onPeriodicAction(effect);
            Thread.yield();
        }
    }
	
    @Override
    public void endEffect(Effect effect) {
    }
}