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
package com.aionemu.gameserver.restrictions;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.model.Skill;

/**
 * @author lord_rex
 */
public abstract class AbstractRestrictions implements Restrictions {

	public void activate() {
		RestrictionsManager.activate(this);
	}

	public void deactivate() {
		RestrictionsManager.deactivate(this);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	/**
	 * To avoid accidentally multiple times activated restrictions.
	 */
	@Override
	public boolean equals(Object obj) {
		return getClass().equals(obj.getClass());
	}

	@Override
	@DisabledRestriction
	public boolean isRestricted(Player player, Class<? extends Restrictions> callingRestriction) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canAttack(Player player, VisibleObject target) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canAffectBySkill(Player player, VisibleObject target, Skill skill) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canUseSkill(Player player, Skill skill) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canChat(Player player) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canInviteToGroup(Player player, Player target) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canChangeEquip(Player player) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canUseWarehouse(Player player) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canTrade(Player player) {
		throw new AbstractMethodError();
	}

	@Override
	@DisabledRestriction
	public boolean canUseItem(Player player, Item item) {
		throw new AbstractMethodError();
	}

}
