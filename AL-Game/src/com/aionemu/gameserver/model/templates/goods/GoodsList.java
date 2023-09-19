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
package com.aionemu.gameserver.model.templates.goods;

import com.aionemu.gameserver.model.limiteditems.LimitedItem;
import javolution.util.FastList;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GoodsList")
public class GoodsList {

	@XmlElement(name = "item")
	private List<Item> items;
	@XmlAttribute(name = "id")
	private int id;
	@XmlElement(name = "salestime")
	private String salesTime;

	private List<Integer> itemIdList;

	void afterUnmarshal(Unmarshaller u, Object parent) {
		itemIdList = new ArrayList<Integer>();
		if (items == null)
			return;

		for (Item item : items) {
			itemIdList.add(item.getId());
		}
	}

	/**
	 * return the limitedItems.
	 */
	public FastList<LimitedItem> getLimitedItems() {
		FastList<LimitedItem> limitedItems = new FastList<LimitedItem>();
		if (items != null) {
			for (Item item : items) {
				if (item.getBuyLimit() != null && item.getSellLimit() != null) {
					limitedItems.add(new LimitedItem(item.getId(), item.getSellLimit(), item.getBuyLimit(), salesTime));
				}
			}
		}
		return limitedItems;
	}

	/**
	 * Gets the value of the id property.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the itemIdList
	 */
	public List<Integer> getItemIdList() {
		return itemIdList;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * <p>
	 * The following schema fragment specifies the expected content contained within this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Item {

		@XmlAttribute
		private int id;
		@XmlAttribute(name = "sell_limit")
		private Integer sellLimit;
		@XmlAttribute(name = "buy_limit")
		private Integer buyLimit;

		/**
		 * Gets the value of the id property.
		 */
		public int getId() {
			return id;
		}

		/**
		 * return sellLimit.
		 */
		public Integer getSellLimit() {
			return sellLimit;
		}

		/**
		 * return buyLimit.
		 */
		public Integer getBuyLimit() {
			return buyLimit;
		}
	}
}
