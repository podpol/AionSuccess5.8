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

import com.aionemu.gameserver.model.templates.flyring.FlyRingTemplate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author M@xx
 */
@XmlRootElement(name = "fly_rings")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlyRingData {

	@XmlElement(name = "fly_ring")
	private List<FlyRingTemplate> flyRingTemplates;

	public int size() {
		if (flyRingTemplates == null) {
			flyRingTemplates = new ArrayList<FlyRingTemplate>();
			return 0;
		}
		return flyRingTemplates.size();
	}

	public List<FlyRingTemplate> getFlyRingTemplates() {
		if (flyRingTemplates == null) {
			return new ArrayList<FlyRingTemplate>();
		}
		return flyRingTemplates;
	}

	public void addAll(Collection<FlyRingTemplate> templates) {
		if (flyRingTemplates == null) {
			flyRingTemplates = new ArrayList<FlyRingTemplate>();
		}
		flyRingTemplates.addAll(templates);
	}
}
