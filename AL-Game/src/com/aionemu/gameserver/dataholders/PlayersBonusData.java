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

import com.aionemu.gameserver.model.templates.bonus_service.PlayersBonusServiceAttr;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;


/**
 * @author Ranastic (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"playersServiceBonusattr"})
@XmlRootElement(name = "players_service_bonusattrs")
public class PlayersBonusData
{
	@XmlElement(name = "players_service_bonusattr")
	protected List<PlayersBonusServiceAttr> playersServiceBonusattr;
	
	@XmlTransient
	private TIntObjectHashMap<PlayersBonusServiceAttr> templates = new TIntObjectHashMap<PlayersBonusServiceAttr>();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (PlayersBonusServiceAttr template : playersServiceBonusattr) {
			templates.put(template.getBuffId(), template);
		}
		playersServiceBonusattr.clear();
		playersServiceBonusattr = null;
	}
	
	public int size() {
		return templates.size();
	}
	
	public PlayersBonusServiceAttr getInstanceBonusattr(int buffId) {
		return templates.get(buffId);
	}
}