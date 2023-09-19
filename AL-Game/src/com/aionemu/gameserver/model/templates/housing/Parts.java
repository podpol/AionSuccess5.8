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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parts", propOrder = { "fence", "garden", "frame", "outwall", "roof", "infloor", "inwall", "door" })
public class Parts {

	protected Integer fence;
	protected Integer garden;
	protected Integer frame;
	protected Integer outwall;
	protected Integer roof;
	protected int infloor;
	protected int inwall;
	protected int door;

	/**
	 * Gets the value of the fence property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getFence() {
		return fence;
	}

	/**
	 * Gets the value of the garden property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getGarden() {
		return garden;
	}

	/**
	 * Gets the value of the frame property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getFrame() {
		return frame;
	}

	/**
	 * Gets the value of the outwall property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getOutwall() {
		return outwall;
	}

	/**
	 * Gets the value of the roof property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getRoof() {
		return roof;
	}

	/**
	 * Gets the value of the infloor property.
	 */
	public int getInfloor() {
		return infloor;
	}

	/**
	 * Gets the value of the inwall property.
	 */
	public int getInwall() {
		return inwall;
	}

	/**
	 * Gets the value of the door property.
	 */
	public int getDoor() {
		return door;
	}
}
