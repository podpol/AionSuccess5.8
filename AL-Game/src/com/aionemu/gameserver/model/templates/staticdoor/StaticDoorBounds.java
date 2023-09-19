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
package com.aionemu.gameserver.model.templates.staticdoor;

import com.aionemu.gameserver.geoEngine.bounding.BoundingBox;
import com.aionemu.gameserver.geoEngine.math.Vector3f;

import javax.xml.bind.annotation.*;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StaticDoorBounds")
public class StaticDoorBounds {

	@XmlAttribute
	private float x1;

	@XmlAttribute
	private float y1;

	@XmlAttribute
	private float z1;

	@XmlAttribute
	private float x2;

	@XmlAttribute
	private float y2;

	@XmlAttribute
	private float z2;

	@XmlTransient
	private BoundingBox boundingBox;

	public BoundingBox getBoundingBox() {
		if (boundingBox == null)
			boundingBox = new BoundingBox(new Vector3f(x1, y1, z1), new Vector3f(x2, y2, z2));
		return boundingBox;
	}
}
