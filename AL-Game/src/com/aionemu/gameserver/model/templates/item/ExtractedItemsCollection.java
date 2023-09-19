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
package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author antness
 */
@XmlType(name = "ExtractedItemsCollection")
public class ExtractedItemsCollection extends ResultedItemsCollection {

	@XmlAttribute(name = "chance")
	protected float chance = 100;
	@XmlAttribute(name = "minlevel")
	protected int minLevel;
	@XmlAttribute(name = "maxlevel")
	protected int maxLevel;

	public final float getChance() {
		return chance;
	}

	public final int getMinLevel() {
		return minLevel;
	}

	public final int getMaxLevel() {
		return maxLevel;
	}

}
