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
package com.aionemu.gameserver.model.templates.item;

import com.aionemu.gameserver.model.PlayerClass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanke on 01/03/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ItemSkillEnhance")
public class ItemSkillEnhance
{
    @XmlAttribute(name="id")
    protected int id;
	
    @XmlAttribute(name="skill_id")
    protected List<Integer> skillId;
	
    @XmlAttribute(name="player_class")
    private PlayerClass classId = PlayerClass.ALL;
	
    public int getId() {
        return this.id;
    }
	
    public List<Integer> getSkillId() {
        if (skillId == null) {
            skillId = new ArrayList<Integer>();
        }
        return skillId;
    }
	
    public PlayerClass getClassId() {
        return classId;
    }
}