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
@XmlType(name = "HousingUseableItem", propOrder = { "action" })
public class HousingUseableItem extends PlaceableHouseObject {

	@XmlElement(required = true)
	protected UseItemAction action;

	@XmlAttribute(required = true)
	protected boolean owner;

	@XmlAttribute
	protected Integer cd;

	@XmlAttribute(required = true)
	protected int delay;

	@XmlAttribute(name = "use_count")
	protected Integer useCount;

	@XmlAttribute(name = "required_item")
	protected Integer requiredItem;

	public UseItemAction getAction() {
		return action;
	}

	/**
	 * Can the object be used only by the owner or visitors too
	 */
	public boolean isOwnerOnly() {
		return owner;
	}

	/**
	 * @return null if no Cooltime is used
	 */
	public Integer getCd() {
		return cd;
	}

	public int getDelay() {
		return delay;
	}

	/**
	 * @return null if use is not restricted
	 */
	public Integer getUseCount() {
		return useCount;
	}

	/**
	 * @return null if no item is required
	 */
	public Integer getRequiredItem() {
		return requiredItem;
	}

	@Override
	public byte getTypeId() {
		return 1;
	}

}
