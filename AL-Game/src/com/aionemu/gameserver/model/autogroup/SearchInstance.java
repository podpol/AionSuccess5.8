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
package com.aionemu.gameserver.model.autogroup;

import com.aionemu.gameserver.model.gameobjects.player.Player;

import java.util.Collection;
import java.util.List;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;

public class SearchInstance
{
	private long registrationTime = System.currentTimeMillis();
	private int instanceMaskId;
	private EntryRequestType ert;
	private List<Integer> members;
	
	public SearchInstance(int instanceMaskId, EntryRequestType ert, Collection<Player> members) {
		this.instanceMaskId = instanceMaskId;
		this.ert = ert;
		if (members != null) {
			this.members = extract(members, on(Player.class).getObjectId());
		}
	}
	
	public List<Integer> getMembers() {
		return members;
	}
	
	public int getInstanceMaskId() {
		return instanceMaskId;
	}
	
	public int getRemainingTime() {
		return (int) (System.currentTimeMillis() - registrationTime) / 1000 * 256;
	}
	
	public EntryRequestType getEntryRequestType() {
		return ert;
	}
	
	public boolean isDredgion() {
		return instanceMaskId == 1 || instanceMaskId == 2 || instanceMaskId == 3;
	}
	public boolean isKamar() {
		return instanceMaskId == 107;
	}
	public boolean isOphidan() {
		return instanceMaskId == 108;
	}
	public boolean isBastion() {
		return instanceMaskId == 109;
	}
	public boolean isIdgelDome() {
		return instanceMaskId == 111;
	}
	public boolean isAsyunatar() {
	    return instanceMaskId == 121;
	}
	public boolean isSuspiciousOphidan() {
		return instanceMaskId == 122;
	}
	public boolean isIdgelDomeLandmark() {
		return instanceMaskId == 123;
	}
	public boolean isHallOfTenacity() {
		return instanceMaskId == 125;
	}
	public boolean isGrandArenaTrainingCamp() {
		return instanceMaskId == 127;
	}
	public boolean isIDRun() {
		return instanceMaskId == 131;
	}
}