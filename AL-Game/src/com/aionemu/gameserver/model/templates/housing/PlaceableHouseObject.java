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
@XmlType(name = "PlaceableHouseObject")
@XmlSeeAlso(
{ 
	HousingJukeBox.class, HousingPicture.class, HousingPostbox.class, 
	HousingChair.class, HousingStorage.class, HousingNpc.class, HousingMoveableItem.class, 
	HousingUseableItem.class, HousingPassiveItem.class 
})
public abstract class PlaceableHouseObject extends AbstractHouseObject {

	@XmlAttribute(name = "use_days")
	protected Integer useDays;

	@XmlAttribute
	protected LimitType limit;

	@XmlAttribute
	protected PlaceLocation location;

	@XmlAttribute
	protected PlaceArea area;

	/**
	 * Gets the value of the useDays property.
	 * 
	 * @return null if not restricted
	 */
	public int getUseDays() {
		if (useDays == null)
			return 0;
		return useDays;
	}

	/**
	 * Where the object is allowed to be placed on?
	 * <p>
	 * <tt>TODO: check if it is needed and not handled by the client</tt>
	 * 
	 * @return {@link LimitType.NONE} if no restriction
	 */
	public LimitType getPlacementLimit() {
		if (limit == null)
			return LimitType.NONE;
		return limit;
	}

	/**
	 * How the object is allowed to be placed (stacks, ground, wall) ?
	 * <p>
	 * <tt>TODO: check if it is needed and not handled by the client</tt>
	 * 
	 * @return possible object is {@link PlaceLocation }
	 */
	public PlaceLocation getLocation() {
		return location;
	}

	/**
	 * Environment where the object is allowed to be placed (interior, exterior)
	 * <p>
	 * <tt>TODO: check if it is needed and not handled by the client</tt>
	 * 
	 * @return possible object is {@link PlaceArea }
	 */
	public PlaceArea getArea() {
		return area;
	}
	
	public abstract byte getTypeId();

}
