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

public interface Restrictions
{
	public boolean isRestricted(Player player, Class<? extends Restrictions> callingRestriction);
	public boolean canAttack(Player player, VisibleObject target);
	public boolean canAffectBySkill(Player player, VisibleObject target, Skill skill);
	public boolean canUseSkill(Player player, Skill skill);
	public boolean canChat(Player player);
	public boolean canInviteToGroup(Player player, Player target);
	public boolean canInviteToAlliance(Player player, Player target);
	public boolean canInviteToLeague(Player player, Player target);
	public boolean canChangeEquip(Player player);
	public boolean canUseWarehouse(Player player);
	public boolean canTrade(Player player);
	public boolean canUseItem(Player player, Item item);
}