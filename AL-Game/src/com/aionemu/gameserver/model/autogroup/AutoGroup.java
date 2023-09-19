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
package com.aionemu.gameserver.model.autogroup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AutoGroup")
public class AutoGroup
{
    @XmlAttribute(required = true)
    protected int id;
	
    @XmlAttribute(required = true)
    protected int instanceId;
	
    @XmlAttribute(name = "name_id")
    protected int nameId;
	
    @XmlAttribute(name = "title_id")
    protected int titleId;
	
    @XmlAttribute(name = "min_lvl")
    protected int minLvl;
	
    @XmlAttribute(name = "max_lvl")
    protected int maxLvl;
	
    @XmlAttribute(name = "register_fast")
    protected boolean registerFast;
	
    @XmlAttribute(name = "register_group")
    protected boolean registerGroup;
	
	@XmlAttribute(name = "special_purpose")
    protected boolean specialPurpose;
	
	@XmlAttribute(name = "register_new")
    protected boolean registerNew;
	
    @XmlAttribute(name = "npc_ids")
    protected List<Integer> npcIds;
	
    public int getId() {
        return id;
    }
	
    public int getInstanceId() {
        return instanceId;
    }
	
    public int getNameId() {
        return nameId;
    }
	
    public int getTitleId() {
        return titleId;
    }
	
    public int getMinLvl() {
        return minLvl;
    }
	
    public int getMaxLvl() {
        return maxLvl;
    }
	
    public boolean hasRegisterFast() {
        return registerFast;
    }
	
    public boolean hasRegisterGroup() {
        return registerGroup;
    }
	
	public boolean hasSpecialPurpose() {
        return specialPurpose;
    }
	
	public boolean hasRegisterNew() {
        return registerNew;
    }
	
    public List<Integer> getNpcIds() {
        if (npcIds == null) {
            npcIds = Collections.emptyList();
        }
        return this.npcIds;
    }
}