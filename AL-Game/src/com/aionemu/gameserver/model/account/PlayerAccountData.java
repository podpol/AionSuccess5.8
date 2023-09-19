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
package com.aionemu.gameserver.model.account;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.PlayerAppearance;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.model.team.legion.Legion;
import com.aionemu.gameserver.model.team.legion.LegionMember;

import java.sql.Timestamp;
import java.util.List;

/**
 * This class is holding information about player, that is displayed on char selection screen, such as: player
 * commondata, player's appearance and creation/deletion time.
 * 
 * @see PlayerCommonData
 * @see PlayerAppearance
 * @author Luno
 */
public class PlayerAccountData {

	private CharacterBanInfo cbi;
	private PlayerCommonData playerCommonData;
	private PlayerAppearance appereance;
	private List<Item> equipment;
	private Timestamp creationDate;
	private Timestamp deletionDate;
	private LegionMember legionMember;

	public PlayerAccountData(PlayerCommonData playerCommonData, CharacterBanInfo cbi, PlayerAppearance appereance, List<Item> equipment,
		LegionMember legionMember) {
		this.playerCommonData = playerCommonData;
		this.cbi = cbi;
		this.appereance = appereance;
		this.equipment = equipment;
		this.legionMember = legionMember;
	}
	
	public CharacterBanInfo getCharBanInfo() {
		return cbi;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets deletion date.
	 * 
	 * @param deletionDate
	 */
	public void setDeletionDate(Timestamp deletionDate) {
		this.deletionDate = deletionDate;
	}

	/**
	 * Get deletion date.
	 * 
	 * @return Timestamp date when char should be deleted.
	 */
	public Timestamp getDeletionDate() {
		return deletionDate;
	}

	/**
	 * Get time in seconds when this player will be deleted ( 0 if player was not set to be deleted )
	 * 
	 * @return deletion time in seconds
	 */
	public int getDeletionTimeInSeconds() {
		return deletionDate == null ? 0 : (int) (deletionDate.getTime() / 1000);
	}

	/**
	 * @return the playerCommonData
	 */
	public PlayerCommonData getPlayerCommonData() {
		return playerCommonData;
	}

	/**
	 * @param playerCommonData
	 *          the playerCommonData to set
	 */
	public void setPlayerCommonData(PlayerCommonData playerCommonData) {
		this.playerCommonData = playerCommonData;
	}

	public PlayerAppearance getAppereance() {
		return appereance;
	}

	/**
	 * @param timestamp
	 */
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the legionMember
	 */
	public Legion getLegion() {
		return legionMember.getLegion();
	}

	/**
	 * Returns true if player is a legion member
	 * 
	 * @return true or false
	 */
	public boolean isLegionMember() {
		return legionMember != null;
	}

	/**
	 * @return the equipment
	 */
	public List<Item> getEquipment() {
		return equipment;
	}

	/**
	 * @param equipment
	 *          the equipment to set
	 */
	public void setEquipment(List<Item> equipment) {
		this.equipment = equipment;
	}
}
