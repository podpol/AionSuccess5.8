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
import com.aionemu.gameserver.network.aion.serverpackets.SM_SKILL_COOLDOWN;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.HashMap;

/**
 * @author Dr.Nism
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SkillCooltimeResetEffect")
public class SkillCooltimeResetEffect extends EffectTemplate
{
    @XmlAttribute(name = "first_cd", required = true)
    protected int firstCd;
	
    @XmlAttribute(name = "second_cd", required = true)
    protected int secondCd;
	
    @Override
    public void applyEffect(Effect effect) {
        Creature effected = effect.getEffected();
        HashMap<Integer, Long> resetSkillCoolDowns = new HashMap<>();
        for (int i = firstCd; i <= secondCd; i++) {
            long delay = effected.getSkillCoolDown(i) - System.currentTimeMillis();
            if (delay <= 0) {
                continue;
            } if (delta > 0) {
                delay -= delay * (delta / 100);
            } else {
                delay -= value;
            }
            effected.setSkillCoolDown(i, delay + System.currentTimeMillis());
            resetSkillCoolDowns.put(i, delay + System.currentTimeMillis());
        } if (effected instanceof Player) {
            if (resetSkillCoolDowns.size() > 0) {
                PacketSendUtility.sendPacket((Player) effected, new SM_SKILL_COOLDOWN(resetSkillCoolDowns));
            }
        }
    }
}