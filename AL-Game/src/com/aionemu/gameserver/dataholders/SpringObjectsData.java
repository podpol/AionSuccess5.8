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

import com.aionemu.gameserver.model.templates.springzones.SpringTemplate;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"springObject"})
@XmlRootElement(name = "spring_objects")
public class SpringObjectsData
{
	@XmlElement(name = "spring_object")
	protected List<SpringTemplate> springObject;
	
	@XmlTransient
	private List<SpringTemplate> springObjects = new ArrayList<SpringTemplate>();
	
	void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
		for (SpringTemplate template : springObject)
		springObjects.add(template);
	}
	
	public int size() {
		return springObjects.size();
	}
	
	public List<SpringTemplate> getSpringObject() {
		return springObjects;
	}
}