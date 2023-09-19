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
 * @author Falke_34
 */
@XmlRootElement(name = "atreian_passport_reward")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtreianPassportRewards {

	@XmlAttribute(name = "name")
	private String name = "";
	@XmlAttribute(name = "reward_item", required = true)
	private int rewardItem;
	@XmlAttribute(name = "reward_item_count", required = true)
	private int rewardItemCount;
	@XmlAttribute(name = "reward_item_num", required = true)
	private int rewardItemNum;
	@XmlAttribute(name = "reward_permit_level")
	private int rewardPermitLevel;

	public String getName() {
		return name;
	}

	public int getRewardItem() {
		return rewardItem;
	}

	public int getRewardItemCount() {
		return rewardItemCount;
	}

	public int getRewardItemNum() {
		return rewardItemNum;
	}

	public int getRewardPermitLevel() {
		return rewardPermitLevel;
	}
}
