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
package com.aionemu.gameserver.skillengine.action;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Actions", propOrder = { "actions" })
public class Actions {

	@XmlElements({ 
		@XmlElement(name = "itemuse", type = ItemUseAction.class), 
		@XmlElement(name = "mpuse", type = MpUseAction.class),
		@XmlElement(name = "hpuse", type = HpUseAction.class), 
		@XmlElement(name = "dpuse", type = DpUseAction.class) })
	protected List<Action> actions;

	/**
	 * Gets the value of the actions property.
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
	 * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
	 * the actions property.
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getActions().add(newItem);
	 * </pre>
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link ItemUseAction } {@link MpUseAction }
	 * {@link HpUseAction } {@link DpUseAction }
	 */
	public List<Action> getActions() {
		if (actions == null) {
			actions = new ArrayList<Action>();
		}
		return this.actions;
	}

}
