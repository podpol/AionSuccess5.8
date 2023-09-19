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

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "caps")
public class BuildingCapabilities {

	@XmlAttribute(required = true)
	protected boolean addon;

	@XmlAttribute(required = true)
	protected int emblemId;

	@XmlAttribute(required = true)
	protected boolean floor;

	@XmlAttribute(required = true)
	protected boolean room;

	@XmlAttribute(required = true)
	protected int interior;

	@XmlAttribute(required = true)
	protected int exterior;

	public boolean canHaveAddon() {
		return addon;
	}

	public int getEmblemId() {
		return emblemId;
	}

	public boolean canChangeFloor() {
		return floor;
	}

	public boolean canChangeRoom() {
		return room;
	}

	public int canChangeInterior() {
		return interior;
	}

	public int canChangeExterior() {
		return exterior;
	}

}
