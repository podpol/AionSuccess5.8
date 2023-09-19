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
package com.aionemu.gameserver.services;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PetitionDAO;
import com.aionemu.gameserver.model.Petition;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_PETITION;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author zdead
 */
public class PetitionService {

	private static Logger log = LoggerFactory.getLogger(PetitionService.class);

	private static SortedMap<Integer, Petition> registeredPetitions = new TreeMap<Integer, Petition>();

	public static final PetitionService getInstance() {
		return SingletonHolder.instance;
	}

	public PetitionService() {
		log.info("Loading PetitionService ...");
		Set<Petition> petitions = DAOManager.getDAO(PetitionDAO.class).getPetitions();
		for (Petition p : petitions) {
			registeredPetitions.put(p.getPetitionId(), p);
		}
		log.info("Successfully loaded " + registeredPetitions.size() + " database petitions");
	}

	public Collection<Petition> getRegisteredPetitions() {
		return registeredPetitions.values();
	}

	public void deletePetition(int playerObjId) {
		Set<Petition> petitions = new HashSet<Petition>();
		for (Petition p : registeredPetitions.values()) {
			if (p.getPlayerObjId() == playerObjId)
				petitions.add(p);
		}
		for (Petition p : petitions)
			if(registeredPetitions.containsKey(p.getPetitionId()))
				registeredPetitions.remove(p.getPetitionId());

		DAOManager.getDAO(PetitionDAO.class).deletePetition(playerObjId);
		if (playerObjId > 0 && World.getInstance().findPlayer(playerObjId) != null) {
			Player p = World.getInstance().findPlayer(playerObjId);
			PacketSendUtility.sendPacket(p, new SM_PETITION());
		}
		rebroadcastPlayerData();
	}

	public void setPetitionReplied(int petitionId) {
		int playerObjId = registeredPetitions.get(petitionId).getPlayerObjId();
		DAOManager.getDAO(PetitionDAO.class).setReplied(petitionId);
		registeredPetitions.remove(petitionId);
		rebroadcastPlayerData();
		if (playerObjId > 0 && World.getInstance().findPlayer(playerObjId) != null) {
			Player p = World.getInstance().findPlayer(playerObjId);
			PacketSendUtility.sendPacket(p, new SM_PETITION());
		}
	}

	public synchronized Petition registerPetition(Player sender, int typeId, String title, String contentText,
		String additionalData) {
		int id = DAOManager.getDAO(PetitionDAO.class).getNextAvailableId();
		Petition ptt = new Petition(id, sender.getObjectId(), typeId, title, contentText, additionalData, 0);
		DAOManager.getDAO(PetitionDAO.class).insertPetition(ptt);
		registeredPetitions.put(ptt.getPetitionId(), ptt);
		broadcastMessageToGM(sender, ptt.getPetitionId());
		return ptt;
	}

	private void rebroadcastPlayerData() {
		for (Petition p : registeredPetitions.values()) {
			Player player = World.getInstance().findPlayer(p.getPlayerObjId());
			if (player != null)
				PacketSendUtility.sendPacket(player, new SM_PETITION(p));
		}
	}

	private void broadcastMessageToGM(Player sender, int petitionId) {
		Iterator<Player> players = World.getInstance().getPlayersIterator();
		while (players.hasNext()) {
			Player p = players.next();
			if (p.getAccessLevel() > 0) {
				PacketSendUtility
					.sendBrightYellowMessageOnCenter(p, "New Support Petition from: " + sender.getName() + " (#" + petitionId + ")");
			}
		}
	}

	public boolean hasRegisteredPetition(Player player) {
		return hasRegisteredPetition(player.getObjectId());
	}

	public boolean hasRegisteredPetition(int playerObjId) {
		boolean result = false;
		for (Petition p : registeredPetitions.values()) {
			if (p.getPlayerObjId() == playerObjId)
				result = true;
		}
		return result;
	}

	public Petition getPetition(int playerObjId) {
		for (Petition p : registeredPetitions.values()) {
			if (p.getPlayerObjId() == playerObjId)
				return p;
		}
		return null;
	}

	public synchronized int getNextAvailablePetitionId() {
		return 0;
	}

	public int getWaitingPlayers(int playerObjId) {
		int counter = 0;
		for (Petition p : registeredPetitions.values()) {
			if (p.getPlayerObjId() == playerObjId)
				break;
			counter++;
		}
		return counter;
	}

	public int calculateWaitTime(int playerObjId) {
		int timePerPetition = 15;
		int timeBetweenPetition = 30;
		int result = timeBetweenPetition;
		for (Petition p : registeredPetitions.values()) {
			if (p.getPlayerObjId() == playerObjId)
				break;
			result += timePerPetition;
			result += timeBetweenPetition;
		}
		return result;
	}

	public void onPlayerLogin(Player player) {
		if (hasRegisteredPetition(player))
			PacketSendUtility.sendPacket(player, new SM_PETITION(getPetition(player.getObjectId())));
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {

		protected static final PetitionService instance = new PetitionService();
	}

}
