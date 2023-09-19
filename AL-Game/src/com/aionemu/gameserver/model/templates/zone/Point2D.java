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

package com.aionemu.gameserver.model.templates.zone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Point2D")
public class Point2D {

	@XmlAttribute(name = "y")
	protected float y;
	@XmlAttribute(name = "x")
	protected float x;

	
	/**
	 * @param x
	 * @param y
	 */
	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point2D() {
		super();
	}


	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}
}
