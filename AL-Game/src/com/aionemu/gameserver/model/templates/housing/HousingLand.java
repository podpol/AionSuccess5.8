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
package com.aionemu.gameserver.model.templates.housing;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Land", propOrder = { "addresses", "buildings", "sale", "fee", "caps" })
public class HousingLand {

	@XmlElementWrapper(name = "addresses", required = true)
	@XmlElement(name ="address")
	protected List<HouseAddress> addresses;
	
	@XmlElementWrapper(name = "buildings", required = true)
	@XmlElement(name ="building")
	protected List<Building> buildings;
	
	@XmlElement(required = true)
	protected Sale sale;
	
	@XmlElement(required = true)
	protected long fee;
	
	@XmlElement(required = true)
	protected BuildingCapabilities caps;
	
	@XmlAttribute(name = "sign_nosale", required = true)
	protected int signNosale;
	
	@XmlAttribute(name = "sign_sale", required = true)
	protected int signSale;
	
	@XmlAttribute(name = "sign_waiting", required = true)
	protected int signWaiting;
	
	@XmlAttribute(name = "sign_home", required = true)
	protected int signHome;
	
	@XmlAttribute(name = "manager_npc", required = true)
	protected int managerNpc;
	
	@XmlAttribute(name = "teleport_npc", required = true)
	protected int teleportNpc;
	
	@XmlAttribute(required = true)
	protected int id;

	public List<HouseAddress> getAddresses() {
		return addresses;
	}

	public List<Building> getBuildings() {
		return buildings;
	}
	
	public Building getDefaultBuilding() {
		for (Building building : buildings) {
			if (building.isDefault())
				return building;
		}
		return buildings.get(0); // fail
	}

	public Sale getSaleOptions() {
		return sale;
	}

	public long getMaintenanceFee() {
		return fee;
	}

	public BuildingCapabilities getCapabilities() {
		return caps;
	}

	public int getNosaleSignNpcId() {
		return signNosale;
	}

	public int getSaleSignNpcId() {
		return signSale;
	}

	public void setSignSale(int value) {
		this.signSale = value;
	}

	public int getWaitingSignNpcId() {
		return signWaiting;
	}

	public int getHomeSignNpcId() {
		return signHome;
	}

	public int getManagerNpcId() {
		return managerNpc;
	}

	public int getTeleportNpcId() {
		return teleportNpc;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return id;
	}

}
