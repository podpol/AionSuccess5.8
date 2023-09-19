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

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class ShutdownRestrictions extends AbstractRestrictions
{
	@Override
	public boolean isRestricted(Player player, Class<? extends Restrictions> callingRestriction) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You are in shutdown progress!");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean canAttack(Player player, VisibleObject target) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot attack in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	@Override
    public boolean canAffectBySkill(Player player, VisibleObject target, Skill skill) {
		return true;
	}
	
	@Override
	public boolean canUseSkill(Player player, Skill skill) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot use skills in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canChat(Player player) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot chat in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canInviteToGroup(Player player, Player target) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot invite members to group in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canInviteToAlliance(Player player, Player target) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot invite members to alliance in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canInviteToLeague(Player player, Player target) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot invite members to league in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canChangeEquip(Player player) {
		if (isInShutdownProgress(player)) {
			PacketSendUtility.sendMessage(player, "You cannot equip / unequip item in Shutdown progress!");
			return false;
		}
		return true;
	}
	
	private boolean isInShutdownProgress(Player player) {
		return player.getController().isInShutdownProgress();
	}
}