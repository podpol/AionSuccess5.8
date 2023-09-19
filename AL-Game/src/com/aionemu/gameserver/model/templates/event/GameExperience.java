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
package com.aionemu.gameserver.model.templates.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name="game_experience_item")
@XmlAccessorType(XmlAccessType.NONE)
public class GameExperience
{
	@XmlAttribute(name="id", required = true)
	private int id;
	
	@XmlAttribute(name="account_type", required = true)
	private AccountType accountType;
	
	@XmlAttribute(name="reward_item", required = true)
	private int rewardItem;
	
	public int getId() {
		return id;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public int getRewardItem() {
		return rewardItem;
	}
}