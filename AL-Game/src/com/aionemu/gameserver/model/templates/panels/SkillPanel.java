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
package com.aionemu.gameserver.model.templates.panels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="SkillPanel")
public class SkillPanel
{
    @XmlAttribute(name="panel_id")
    protected byte id;
	
    @XmlAttribute(name="panel_skills")
    protected List<Integer> skills;
	
    public int getPanelId() {
        return id;
    }
	
    public List<Integer> getSkills() {
        return null;
    }
	
    public boolean canUseSkill(int skillId, int level) {
        for (Integer skill: skills) {
            if (skill >> 8 == skillId && (skill & 0xFF) == level) {
                return true;
            }
        }
        return false;
    }
}