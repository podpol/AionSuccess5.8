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
package com.aionemu.gameserver.model.templates.bonus_service;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlayersBonusServiceAttr", propOrder = {"playersBonusAttr"})
public class PlayersBonusServiceAttr
{
	@XmlElement(name = "apply_bonus")
	protected List<PlayersBonusPenaltyAttr> playersBonusAttr;
	
	@XmlAttribute(name = "buff_id", required = true)
	protected int buffId;
	
	public List<PlayersBonusPenaltyAttr> getPenaltyAttr() {
		if (playersBonusAttr == null) {
			playersBonusAttr = new ArrayList<PlayersBonusPenaltyAttr>();
		}
		return playersBonusAttr;
	}
	
	public int getBuffId() {
		return buffId;
	}
	
	public void setBuffId(int value) {
		buffId = value;
	}
}