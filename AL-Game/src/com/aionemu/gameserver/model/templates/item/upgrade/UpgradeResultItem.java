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
package com.aionemu.gameserver.model.templates.item.upgrade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Ranastic (Encom)
 */

@XmlRootElement(name = "UpgradeResultItem")
@XmlAccessorType(XmlAccessType.FIELD)
public class UpgradeResultItem
{
	@XmlAttribute(name = "item_id")
	private int item_id;
	
	@XmlAttribute(name = "check_enchant_count")
	private int check_enchant_count;
	
	@XmlAttribute(name = "check_authorize_count")
	private int check_authorize_count;
	
	private UpgradeMaterials upgrade_materials;
	
	private NeedAbyssPoint need_abyss_point;
	
	private NeedKinah need_kinah;
	
	public int getCheck_enchant_count() {
		return check_enchant_count;
	}
	
	public int getCheck_authorize_count() {
		return check_authorize_count;
	}
	
	public int getItem_id() {
		return item_id;
	}
	
	public UpgradeMaterials getUpgrade_materials() {
		return upgrade_materials;
	}
	
	public NeedAbyssPoint getNeed_abyss_point() {
		return need_abyss_point;
	}
	
	public NeedKinah getNeed_kinah() {
		return need_kinah;
	}
}