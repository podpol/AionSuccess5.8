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
package com.aionemu.gameserver.model.templates.panel_cp;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rinzler (Encom)
 */

@XmlType(name = "panel_cp")
@XmlAccessorType(XmlAccessType.NONE)
public class PanelCp
{
	@XmlAttribute
	protected int id;
	@XmlAttribute
	protected String name;
	@XmlAttribute(name="panelType", required = true)
	private PanelCpType panelCpType;
	@XmlAttribute
	protected int learnSkill;
	@XmlAttribute
	protected int additionalSkill;
	@XmlAttribute
	protected int statsId;
	@XmlAttribute
	protected int skillId;
	@XmlAttribute
	protected int statValue;
	@XmlAttribute
	protected int cost;
	@XmlAttribute
	protected int countMax;
	@XmlAttribute
	protected int costAdj;
	@XmlAttribute
	protected int preCondId;
	@XmlAttribute
	protected int preEnchantCount;
	@XmlAttribute
	protected int minLevel;
	
	public int getId() {
        return this.id;
    }
	public String getName() {
		return name;
	}
	public PanelCpType getPanelCpType() {
		return panelCpType;
	}
	public int getLearnSkill() {
		return learnSkill;
	}
	public int getAdditionalSkill() {
		return additionalSkill;
	}
	public int getStatsId() {
		return statsId;
	}
	public int getSkillId() {
		return skillId;
	}
	public int getStatValue() {
		return statValue;
	}
	public int getCost() {
		return cost;
	}
	public int getCountMax() {
		return countMax;
	}
	public int getCostAdj() {
		return costAdj;
	}
	public int getPreCondId() {
		return preCondId;
	}
	public int getPreEnchantCount() {
		return preEnchantCount;
	}
	public int getMinLevel() {
		return minLevel;
	}
}