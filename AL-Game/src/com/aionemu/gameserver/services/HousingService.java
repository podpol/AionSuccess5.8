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
import com.aionemu.commons.utils.internal.chmv8.PlatformDependent;
import com.aionemu.gameserver.controllers.HouseController;
import com.aionemu.gameserver.dao.HousesDAO;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.HouseDecoration;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerHouseOwnerFlags;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.house.HouseStatus;
import com.aionemu.gameserver.model.templates.housing.Building;
import com.aionemu.gameserver.model.templates.housing.BuildingType;
import com.aionemu.gameserver.model.templates.housing.HouseAddress;
import com.aionemu.gameserver.model.templates.housing.HousingLand;
import com.aionemu.gameserver.model.templates.spawns.SpawnType;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.spawnengine.SpawnEngine;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldPosition;
import javolution.util.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;

public class HousingService {

	private static final Logger log = LoggerFactory.getLogger(HousingService.class);
	private static final Map<Integer, List<House>> housesByMapId = new HashMap<Integer, List<House>>();
	private final Map<Integer, House> customHouses;
	private final Map<Integer, House> studios;
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {
		protected static final HousingService instance = new HousingService();
	}
	
	public static HousingService getInstance() {
		return SingletonHolder.instance;
	}
	
	private HousingService() {
		log.info("Loading housing data...");
		customHouses = PlatformDependent.newConcurrentHashMap(DAOManager.getDAO(HousesDAO.class).loadHouses(DataManager.HOUSE_DATA.getLands(), false));
		studios = PlatformDependent.newConcurrentHashMap(DAOManager.getDAO(HousesDAO.class).loadHouses(DataManager.HOUSE_DATA.getLands(), true));
		log.info("Housing Service loaded.");
	}
	
	public void spawnHouses(int worldId, int instanceId, int registeredId) {
		Set<HousingLand> lands = DataManager.HOUSE_DATA.getLandsForWorldId(worldId);
		if (lands == null) {
			if (registeredId > 0) {
				House studio;
				synchronized (studios) {
					studio = studios.get(registeredId);
				}
				if (studio == null)
					return;
				HouseAddress addr = studio.getAddress();
				if (addr.getMapId() != worldId)
					return;
				VisibleObject existing = World.getInstance().findVisibleObject(studio.getObjectId());
				WorldPosition position = null;
				if (existing != null)
					position = existing.getPosition();
				if (position == null) {
					position = World.getInstance().createPosition(addr.getMapId(), addr.getX(), addr.getY(), addr.getZ(), (byte) 0, instanceId);
					studio.setPosition(position);
				}
				if (!position.isSpawned())
					SpawnEngine.bringIntoWorld(studio);
				studio.spawn(instanceId);
				Player enteredPlayer = World.getInstance().findPlayer(registeredId);
				if (enteredPlayer != null)
					enteredPlayer.setHouseRegistry(studio.getRegistry());
			}
			return;
		}
		
		int spawnedCounter = 0;
		for (HousingLand land : lands) {
			Building defaultBuilding = land.getDefaultBuilding();
			if (defaultBuilding.getType() == BuildingType.PERSONAL_INS)
				continue;
			for (HouseAddress address : land.getAddresses()) {
				if (address.getMapId() != worldId)
					continue;
				House customHouse = customHouses.get(address.getId());
				if (customHouse == null) {
					customHouse = new House(defaultBuilding, address, instanceId);
					customHouse.setPersistentState(PersistentState.NEW);
				}
				customHouse.spawn(instanceId);
				spawnedCounter++;
				List<House> housesForMap = housesByMapId.get(worldId);
				if (housesForMap == null) {
					housesForMap = new ArrayList<House>();
					housesByMapId.put(worldId, housesForMap);
				}
				housesForMap.add(customHouse);
			}
		}
		if (spawnedCounter > 0) {
			log.info("Spawned houses " + worldId + " [" + instanceId + "] : " + spawnedCounter);
		}
	}
	
	public List<House> searchPlayerHouses(int playerObjId) {
		List<House> houses = new ArrayList<House>();
		synchronized (studios) {
			if (studios.containsKey(playerObjId)) {
				houses.add(studios.get(playerObjId));
				return houses;
			}
		}
		for (House house : customHouses.values()) {
			if (house.getOwnerId() == playerObjId)
				houses.add(house);
		}
		return houses;
	}
	
	public int getPlayerAddress(int playerId) {
		synchronized (studios) {
			if (studios.containsKey(playerId))
				return studios.get(playerId).getAddress().getId();
		}
		for (House house : customHouses.values()) {
			if (house.getStatus() == HouseStatus.INACTIVE)
				continue;
			if (house.getOwnerId() == playerId && (house.getStatus() == HouseStatus.ACTIVE || house.getStatus() == HouseStatus.SELL_WAIT))
				return house.getAddress().getId();
		}
		return 0;
	}
	
	public void resetAppearance(House house) {
		FastList<HouseDecoration> customParts = house.getRegistry().getCustomParts();
		for (HouseDecoration deco : customParts) {
			deco.setPersistentState(PersistentState.DELETED);
		}
		for (HouseDecoration deco : customParts) {
			house.getRegistry().removeCustomPart(deco.getObjectId());
		}
	}
	
	public House getHouseByName(String houseName) {
		for (House house : customHouses.values()) {
			if (house.getName().equals(houseName))
				return house;
		}
		return null;
	}
	
	public House getHouseByAddress(int address) {
		for (House house : customHouses.values()) {
			if (house.getAddress().getId() == address)
				return house;
		}
		return null;
	}
	
	public House activateBoughtHouse(int playerId) {
		for (House house : customHouses.values()) {
			if (house.getOwnerId() == playerId && house.getStatus() == HouseStatus.INACTIVE) {
				house.revokeOwner();
				house.setOwnerId(playerId);
				house.setFeePaid(true);
				house.setNextPay(null);
				house.setSellStarted(null);
				house.reloadHouseRegistry();
				house.save();
				return house;
			}
		}
		return null;
	}
	
	public House getPlayerStudio(int playerId) {
		synchronized (studios) {
			if (studios.containsKey(playerId))
			return studios.get(playerId);
		}
		return null;
	}
	
	public void removeStudio(int playerId) {
		if (playerId != 0) {
			synchronized (studios) {
				studios.remove(playerId);
			}
		}
	}
	
	public void registerPlayerStudio(Player player) {
		createStudio(player);
	}
	
	public void recreatePlayerStudio(Player player) {
		HousingLand land = DataManager.HOUSE_DATA.getLand(329001);
		final long fee = land.getSaleOptions().getGoldPrice();
		if (player.getInventory().getKinah() < fee) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_NOT_ENOUGH_MONEY);
			return;
		}
		createStudio(player);
		player.getInventory().decreaseKinah(fee);
	}
	
	private void createStudio(Player player) {
		if (!searchPlayerHouses(player.getObjectId()).isEmpty()) //should not happen
			return;
		HousingLand land = DataManager.HOUSE_DATA.getLand(player.getRace() == Race.ELYOS ? 329001 : 339001);
		House studio = new House(land.getDefaultBuilding(), land.getAddresses().get(0), 0);
		studio.setOwnerId(player.getObjectId());
		synchronized (studios) {
			studios.put(player.getObjectId(), studio);
		}
		studio.setStatus(HouseStatus.ACTIVE);
		studio.setAcquiredTime(new Timestamp(System.currentTimeMillis()));
		studio.setFeePaid(true);
		studio.setNextPay(null);
		studio.setPersistentState(PersistentState.NEW);
		player.setBuildingOwnerState(PlayerHouseOwnerFlags.HOUSE_OWNER.getId());
		PacketSendUtility.sendPacket(player, new SM_HOUSE_ACQUIRE(player.getObjectId(), studio.getAddress().getId(), true));
		PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_HOUSING_INS_OWN_SUCCESS);
		PacketSendUtility.sendPacket(player, new SM_HOUSE_OWNER_INFO(player, studio));
	}
	
	public void switchHouseBuilding(House currentHouse, int newBuildingId) {
		Building otherBuilding = DataManager.HOUSE_BUILDING_DATA.getBuilding(newBuildingId);
		currentHouse.setBuilding(otherBuilding);
        // currentHouse.getRegistry().despawnObjects(false);
        currentHouse.getRegistry().save();
		currentHouse.reloadHouseRegistry(); // load new defaults
		DAOManager.getDAO(HousesDAO.class).storeHouse(currentHouse);
		HouseController controller = ((HouseController) currentHouse.getController());
		controller.broadcastAppearance();
		controller.spawnObjects();
	}
	
	public FastList<House> getCustomHouses() {
		FastList<House> houses = FastList.newInstance();
		for (List<House> mapHouses : housesByMapId.values())
			houses.addAll(mapHouses);
		return houses;
	}
	
	public void onInstanceDestroy(int ownerId) {
		House studio;
		synchronized (studios) {
			studio = studios.get(ownerId);
		}
		if (studio != null) {
			studio.setSpawn(SpawnType.MANAGER, null);
			studio.setSpawn(SpawnType.TELEPORT, null);
			studio.setSpawn(SpawnType.SIGN, null);
			studio.save();
		}
	}
	
	public void onPlayerLogin(Player player) {
        House activeHouse = null;
        byte buildingState = PlayerHouseOwnerFlags.BUY_STUDIO_ALLOWED.getId();
        for (House house : player.getHouses()) {
            if (house.getStatus() == HouseStatus.ACTIVE || house.getStatus() == HouseStatus.SELL_WAIT) {
                activeHouse = house;
            }
        } if (activeHouse == null) {
            QuestState qs;
            qs = player.getQuestStateList().getQuestState(player.getRace() == Race.ELYOS ? 18802 : 28802);
            if (qs != null && qs.getStatus().equals(QuestStatus.COMPLETE)) {
                buildingState |= PlayerHouseOwnerFlags.BIDDING_ALLOWED.getId();
            }
        } else {
            if (activeHouse.getStatus() == HouseStatus.SELL_WAIT) {
                buildingState = PlayerHouseOwnerFlags.SELLING_HOUSE.getId();
            } else {
                buildingState = PlayerHouseOwnerFlags.HOUSE_OWNER.getId();
            }
        }
        player.setBuildingOwnerState(buildingState);
        PacketSendUtility.sendPacket(player, new SM_HOUSE_OWNER_INFO(player, activeHouse));
        if (!player.getFriendList().getIsFriendListSent()) {
            PacketSendUtility.sendPacket(player, new SM_FRIEND_LIST());
        }
        PacketSendUtility.sendPacket(player, new SM_MARK_FRIENDLIST());
    }
}