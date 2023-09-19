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

import com.aionemu.gameserver.model.templates.abyss_bonus.AbyssGroupAttr;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * @Author Rinzler (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"abyssGroupattr"})
@XmlRootElement(name = "abyss_groupattrs")
public class AbyssGroupData
{
	@XmlElement(name = "abyss_groupattr")
	protected List<AbyssGroupAttr> abyssGroupattr;
	
	@XmlTransient
	private TIntObjectHashMap<AbyssGroupAttr> templates = new TIntObjectHashMap<AbyssGroupAttr>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (AbyssGroupAttr template: abyssGroupattr) {
			templates.put(template.getBuffId(), template);
		}
		abyssGroupattr.clear();
		abyssGroupattr = null;
	}
	
	public int size() {
		return templates.size();
	}
	
	public AbyssGroupAttr getInstanceBonusattr(int buffId) {
		return templates.get(buffId);
	}
}