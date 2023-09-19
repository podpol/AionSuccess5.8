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
package com.aionemu.gameserver.model.templates.recipe;


import com.aionemu.gameserver.model.Race;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LunaTemplate")
public class LunaTemplate
{
	protected List<LunaComponent> luna_component_panel_1;
	protected List<LunaComponent> luna_component_panel_2;
	protected List<LunaComponent> luna_component_panel_3;
	protected List<LunaComponent> luna_component_panel_4;
	protected List<LunaComponent> luna_component_panel_5;
	
	@XmlAttribute(name = "max_production_count")
	protected Integer maxProductionCount;
	
	@XmlAttribute(name = "name")
	protected String name;
	
	@XmlAttribute
	protected int quantity;
	
	@XmlAttribute
	protected int group;
	
	@XmlAttribute(name = "success_rate")
	protected int success_rate;
	
	@XmlAttribute
	protected int productid;
	
	@XmlAttribute
	protected Race race;
	
	@XmlAttribute
	protected int itemid;
	
	@XmlAttribute
	protected int nameid;
	
	@XmlAttribute
	protected int id;
	
	public List<LunaComponent> getLunaComponent() {
		if (luna_component_panel_1 == null) {
			luna_component_panel_1 = new ArrayList<LunaComponent>();
		}
		return this.luna_component_panel_1;
	}
	public List<LunaComponent> getLunaComponent2() {
		if (luna_component_panel_2 == null) {
			luna_component_panel_2 = new ArrayList<LunaComponent>();
		}
		return this.luna_component_panel_2;
	}
	public List<LunaComponent> getLunaComponent3() {
		if (luna_component_panel_3 == null) {
			luna_component_panel_3 = new ArrayList<LunaComponent>();
		}
		return this.luna_component_panel_3;
	}
	public List<LunaComponent> getLunaComponent4() {
		if (luna_component_panel_4 == null) {
			luna_component_panel_4 = new ArrayList<LunaComponent>();
		}
		return this.luna_component_panel_4;
	}
	public List<LunaComponent> getLunaComponent5() {
		if (luna_component_panel_5 == null) {
			luna_component_panel_5 = new ArrayList<LunaComponent>();
		}
		return this.luna_component_panel_5;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public Integer getGroup() {
		return group;
	}
	
	public int getRate() {
		return success_rate;
	}
	
	public Integer getProductid() {
		return productid;
	}
	
	public Race getRace() {
		return race;
	}
	
	public Integer getItemid() {
		return itemid;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNameid() {
		return nameid;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Integer getMaxProductionCount() {
		return maxProductionCount;
	}
}