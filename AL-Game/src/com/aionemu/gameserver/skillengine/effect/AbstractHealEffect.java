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
import com.aionemu.gameserver.model.stats.container.StatEnum;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.LOG;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS.TYPE;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.HealType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractHealEffect")
public abstract class AbstractHealEffect extends EffectTemplate
{
	@XmlAttribute
    protected boolean percent;
	
    public void calculate(Effect effect, HealType healType) {
        if (!super.calculate(effect, null, null)) {
            return;
        }
        Creature effector = effect.getEffector();
        int valueWithDelta = value + delta * effect.getSkillLevel();
        int currentValue = getCurrentStatValue(effect);
        int maxCurValue = getMaxStatValue(effect);
        int possibleHealValue = 0;
        if (percent) {
            possibleHealValue = maxCurValue * valueWithDelta / 100;
        } else {
            possibleHealValue = valueWithDelta;
        }
        int finalHeal = possibleHealValue;
        if (healType == HealType.HP) {
            int baseHeal = possibleHealValue;
            if (effect.getItemTemplate() == null) {
                int boostHealAdd = effector.getGameStats().getStat(StatEnum.HEAL_BOOST, 0).getCurrent();
                int boostHeal = (effector.getGameStats().getStat(StatEnum.HEAL_BOOST, baseHeal).getCurrent() - boostHealAdd);
                boostHeal += boostHeal * boostHealAdd / 1000;
                finalHeal = effector.getGameStats().getStat(StatEnum.HEAL_SKILL_BOOST, boostHeal).getCurrent();
            }
            finalHeal = effect.getEffected().getGameStats().getStat(StatEnum.HEAL_SKILL_DEBOOST, finalHeal).getCurrent();
        } if (finalHeal < 0) {
            finalHeal = currentValue > -finalHeal ? finalHeal : -currentValue;
        } else {
            finalHeal = maxCurValue - currentValue < finalHeal ? (maxCurValue - currentValue) : finalHeal;
        } if (healType == HealType.HP && effect.getEffected().getEffectController().isAbnormalSet(AbnormalState.DISEASE)) {
            finalHeal = 0;
        }
        effect.setReservedInt(position, finalHeal);
        effect.setReserved1(-finalHeal);
    }
	
    public void applyEffect(Effect effect, HealType healType) {
        Creature effected = effect.getEffected();
        int healValue = effect.getReservedInt(position);
        if (healValue == 0) {
            return;
        } switch (healType) {
            case HP:
                if (this instanceof ProcHealInstantEffect ||
				    effect.getSkillTemplate().getEffects().isEffectTypePresent(EffectType.HEALINSTANT)) {
                    effected.getLifeStats().increaseHp(TYPE.HP, healValue, 0, LOG.REGULAR);
                } else {
                    if (healValue > 0) {
                        effected.getLifeStats().increaseHp(TYPE.REGULAR, healValue, 0, LOG.REGULAR);
                    } else {
                        effected.getLifeStats().reduceHp(-healValue, effected);
                    }
				}
            break;
            case MP:
                if (this instanceof ProcMPHealInstantEffect) {
                    effected.getLifeStats().increaseMp(TYPE.MP, healValue, 0, LOG.REGULAR);
                } else {
                    effected.getLifeStats().increaseMp(TYPE.HEAL_MP, healValue, 0, LOG.REGULAR);
                }
            break;
            case FP:
                effected.getLifeStats().increaseFp(TYPE.FP, healValue);
            break;
            case DP:
                ((Player) effected).getCommonData().addDp(healValue);
            break;
        }
    }
	
    protected abstract int getCurrentStatValue(Effect effect);
    protected abstract int getMaxStatValue(Effect effect);
}