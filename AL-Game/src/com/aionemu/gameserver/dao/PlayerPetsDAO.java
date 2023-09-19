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
package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.gameobjects.player.PetCommonData;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.pet.PetDopingBag;

import java.util.List;

/**
 * @author Xitanium, Kamui, Rolandas
 */
public abstract class PlayerPetsDAO implements DAO {

	@Override
	public final String getClassName() {
		return PlayerPetsDAO.class.getName();
	}

	public abstract void insertPlayerPet(PetCommonData petCommonData);

	public abstract void removePlayerPet(Player player, int petId);

	public abstract void updatePetName(PetCommonData petCommonData);

	public abstract List<PetCommonData> getPlayerPets(Player player);

	public abstract void setTime(Player player, int petId, long time);
	
	public abstract void saveFeedStatus(Player player, int petId, int hungryLevel, int feedProgress, long reuseTime);

	public abstract boolean savePetMoodData(PetCommonData petCommonData);
	
	public abstract void saveDopingBag(Player player, int petId, PetDopingBag bag);
}
