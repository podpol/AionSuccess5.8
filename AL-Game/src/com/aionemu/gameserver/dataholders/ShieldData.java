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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.shield.ShieldTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Wakizashi
 */
@XmlRootElement(name = "shields")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShieldData {

	@XmlElement(name = "shield")
	private List<ShieldTemplate> shieldTemplates;

	public int size() {
		if (shieldTemplates == null) {
			shieldTemplates = new ArrayList<ShieldTemplate>();
			return 0;
		}
		return shieldTemplates.size();
	}

	public List<ShieldTemplate> getShieldTemplates() {
		if (shieldTemplates == null) {
			return new ArrayList<ShieldTemplate>();
		}
		return shieldTemplates;
	}

	public void addAll(Collection<ShieldTemplate> templates) {
		if (shieldTemplates == null) {
			shieldTemplates = new ArrayList<ShieldTemplate>();
		}
		shieldTemplates.addAll(templates);
	}
}
