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

import com.aionemu.gameserver.model.templates.VisibleObjectTemplate;
import com.aionemu.gameserver.model.templates.item.ItemQuality;

import javax.xml.bind.annotation.*;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractHouseObject")
@XmlSeeAlso({ PlaceableHouseObject.class })
public abstract class AbstractHouseObject extends VisibleObjectTemplate {

	@XmlAttribute(name = "talking_distance", required = true)
	protected float talkingDistance;

	@XmlAttribute(required = true)
	protected ItemQuality quality;

	@XmlAttribute(required = true)
	protected HousingCategory category;

	@XmlAttribute(name = "name_id", required = true)
	protected int nameId;

	@XmlAttribute(required = true)
	protected int id;

	@XmlAttribute(name = "can_dye")
	protected boolean canDye;

	@Override
	public int getTemplateId() {
		return id;
	}

	public float getTalkingDistance() {
		return talkingDistance;
	}

	public ItemQuality getQuality() {
		return quality;
	}

	public HousingCategory getCategory() {
		return category;
	}
	
	public boolean getCanDye() {
		return canDye;
	}

	@Override
	public int getNameId() {
		return nameId;
	}
	
	@Override
	public String getName() {
		return null;
	}

}
