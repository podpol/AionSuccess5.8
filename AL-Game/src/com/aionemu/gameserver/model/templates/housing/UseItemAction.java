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
package com.aionemu.gameserver.model.templates.housing;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Rolandas
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UseItemAction")
public class UseItemAction {

	@XmlAttribute(name = "final_reward_id")
	protected Integer finalRewardId;

	@XmlAttribute(name = "reward_id")
	protected Integer rewardId;

	@XmlAttribute(name = "remove_count")
	protected Integer removeCount;

	@XmlAttribute(name = "check_type")
	protected Integer checkType;

	public Integer getFinalRewardId() {
		return finalRewardId;
	}

	public Integer getRewardId() {
		return rewardId;
	}

	public Integer getRemoveCount() {
		return removeCount;
	}

	public Integer getCheckType() {
		return checkType;
	}

}
