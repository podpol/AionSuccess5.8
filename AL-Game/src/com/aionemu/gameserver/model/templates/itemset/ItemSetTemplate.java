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
package com.aionemu.gameserver.model.templates.itemset;

import com.aionemu.gameserver.model.stats.calc.StatOwner;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author ATracer, modified by Antivirus
 */
@XmlRootElement(name = "itemset")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemSetTemplate implements StatOwner {

	@XmlElement(required = true)
	protected List<ItemPart> itempart;
	@XmlElement(required = true)
	protected List<PartBonus> partbonus;
	protected FullBonus fullbonus;
	@XmlAttribute
	protected String name;
	@XmlAttribute
	protected int id;

	/**
	 * @param u
	 * @param parent
	 */
	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (fullbonus != null) {
			// Set number of items to apply the full bonus
			fullbonus.setNumberOfItems(itempart.size());
		}
	}

	/**
	 * @return the itempart
	 */
	public List<ItemPart> getItempart() {
		return itempart;
	}

	/**
	 * @return the partbonus
	 */
	public List<PartBonus> getPartbonus() {
		return partbonus;
	}

	/**
	 * @return the fullbonus
	 */
	public FullBonus getFullbonus() {
		return fullbonus;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
