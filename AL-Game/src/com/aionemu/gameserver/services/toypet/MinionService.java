/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.services.toypet;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javolution.util.FastMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.GameServer;
import com.aionemu.gameserver.configs.main.PeriodicSaveConfig;
import com.aionemu.gameserver.controllers.MinionController;
import com.aionemu.gameserver.dao.PlayerMinionsDAO;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.Minion;
import com.aionemu.gameserver.model.gameobjects.player.MinionCommonData;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.minion.MinionBuff;
import com.aionemu.gameserver.model.team2.common.legacy.LootRuleType;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.model.templates.item.ItemUseLimits;
import com.aionemu.gameserver.model.templates.item.actions.AbstractItemAction;
import com.aionemu.gameserver.model.templates.item.actions.ItemActions;
import com.aionemu.gameserver.model.templates.minion.MinionDopingBag;
import com.aionemu.gameserver.model.templates.minion.MinionTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MINIONS;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUEST_ACTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import com.aionemu.gameserver.restrictions.RestrictionsManager;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.spawnengine.VisibleObjectSpawner;
import com.aionemu.gameserver.taskmanager.tasks.ExpireTimerTask;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

public class MinionService {
	private MinionBuff minionbuff;
	private static Logger log = LoggerFactory.getLogger(MinionService.class);

	public void addMinion(Player player, int minionId, String name, String grade, int level, int growthPoints, int expireTime) {
		MinionCommonData minionCommonData = player.getMinionList().addNewMinion(player, minionId, name, grade, level, growthPoints, expireTime);
		if (minionCommonData != null) {
			PacketSendUtility.sendPacket(player, new SM_MINIONS(1, minionCommonData));
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404316, name));
			if (expireTime > 0) {
				ExpireTimerTask.getInstance().addTask(minionCommonData, player);
			}
		}
		
		int questId;
		if (player.getRace() == Race.ASMODIANS) {
			questId = 25545;
		} else {
	    	questId = 15545;
	    }
		
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		if (qs != null && qs.getQuestVars().getQuestVars() == 0) {
			qs.setStatus(QuestStatus.REWARD);
			qs.setStatus(QuestStatus.COMPLETE);
			qs.setCompleteCount(qs.getCompleteCount() + 1);
			qs.setQuestVar(1);
			ItemService.addItem(player, 190080012, 1);
			PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(questId, qs.getStatus(), qs.getQuestVars().getQuestVars()));
			if ((qs.getStatus() == QuestStatus.COMPLETE) || (qs.getStatus() == QuestStatus.REWARD)) {
				player.getController().updateNearbyQuests();
			}
		}
	}
	
	private static boolean validateAdoption(Player player, ItemTemplate template, int minionId) {
		if (template == null || template.getActions() == null || template.getActions().getAdoptMinionAction() == null) {
			GameServer.log.info("Null Action minionId: " + minionId);
			return false;
		} if (DataManager.MINION_DATA.getMinionTemplate(minionId) == null) {
			log.warn("Trying adopt minion without template. PetId:" + minionId);
			return false;
		}
		return true;
	}
	
	public void onPlayerLogin(Player player) {
		Collection<MinionCommonData> playerMinions = player.getMinionList().getMinions();
		if (playerMinions != null && playerMinions.size() > 0) {
			PacketSendUtility.sendPacket(player, new SM_MINIONS(0, playerMinions));
			PacketSendUtility.sendPacket(player, new SM_MINIONS(9, playerMinions));
			PacketSendUtility.sendPacket(player, new SM_MINIONS(player, 11));
			PacketSendUtility.sendPacket(player, new SM_MINIONS(12, playerMinions));
		}
	}
	
	public void renameMinion(Player player, int minionObjId, String name) {
		MinionCommonData minionCommonData = player.getMinionList().getMinion(minionObjId);
	    if (minionCommonData != null) {
	    	minionCommonData.setName(name);
	    	DAOManager.getDAO(PlayerMinionsDAO.class).updateMinionName(minionCommonData);
	    	PacketSendUtility.broadcastPacket(player, new SM_MINIONS(3, minionCommonData), true);
	    }
	}	
	
	public void chargeSkillPoint(Player player, boolean isAuto) {
		int playerSkillPoint = player.getCommonData().getMinionSkillPoints();
		long prices = (50000 - playerSkillPoint) * 20;
		player.getInventory().decreaseKinah(prices, player);
		player.getCommonData().setMinionSkillPoints(50000);
		if (isAuto) {
			player.getCommonData().setMinionAutoCharge(true);
		}
		PacketSendUtility.sendPacket(player, new SM_MINIONS(player, 11));
		PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404327));
	}
	
	public void synthesisMinion(Player player, int slot1, int slot2, int slot3, int slot4) {
		PacketSendUtility.sendPacket(player, new SM_MINIONS(player, 13));
	}
	
	public void adoptMinion(Player player, Item item, String grade) {
		FastMap<Integer, MinionTemplate> minionTemplate = new FastMap<Integer, MinionTemplate>();
		int minionId = 0;
		int minionLvl = 0;
		int minionGrowthPoints = 0;
		String minionName = "";
		String minionGrade ="";
		for (MinionTemplate template : DataManager.MINION_DATA.getMinionData().valueCollection()) {
			if (template.getGrade().equalsIgnoreCase(grade) && template.getLevel() == 1) {
				minionTemplate.put(template.getId(), template);
			}
		}
		
		int rnd = Rnd.get(1, minionTemplate.size());
		int i = 1;
		for (MinionTemplate mt : minionTemplate.values()) {
			if (i == rnd) {
				minionId = mt.getId();
				minionName = mt.getName();
				minionGrade = mt.getGrade();
				minionLvl = mt.getLevel();
				minionGrowthPoints = mt.getGrowthPt();
				break;
			}
			i++;
		} if (!validateAdoption(player, item.getItemTemplate(), minionId)) {
			return;
	    }
		
		int expireTime = item.getItemTemplate().getActions().getAdoptMinionAction().getExpireMinutes() != 0 ? (int)(System.currentTimeMillis() / 1000L + item.getItemTemplate().getActions().getAdoptMinionAction().getExpireMinutes() * 60) : 0;
	    
	    addMinion(player, minionId, minionName, minionGrade, minionLvl, minionGrowthPoints, expireTime);
	    player.getMinionList().updateMinionsList();
	}
	
	public void adoptMinion(Player player, Item item, int minionId) {
		String minionName = DataManager.MINION_DATA.getMinionTemplate(minionId).getName();
		String minionGrade = DataManager.MINION_DATA.getMinionTemplate(minionId).getGrade();
		int minionLvl = DataManager.MINION_DATA.getMinionTemplate(minionId).getLevel();
		int minionGrowthPoints = DataManager.MINION_DATA.getMinionTemplate(minionId).getGrowthPt();
		if (!validateAdoption(player, item.getItemTemplate(), minionId)) {
			return;
		}
		
		int expireTime = item.getItemTemplate().getActions().getAdoptMinionAction().getExpireMinutes() != 0 ? (int)(System.currentTimeMillis() / 1000L + item.getItemTemplate().getActions().getAdoptMinionAction().getExpireMinutes() * 60) : 0;
	    
		addMinion(player, minionId, minionName, minionGrade, minionLvl, minionGrowthPoints, expireTime);
	}
	
	public void deleteMinion(Player player, int minionObjId, boolean isMaterial) { 
		for (MinionCommonData list : player.getMinionList().getMinions()) {
			if (list.getObjectId() == minionObjId) {
				player.getMinionList().getMinions().remove(list.getObjectId());
				DAOManager.getDAO(PlayerMinionsDAO.class).removePlayerMinion(player, list.getMinionId(), list.getBirthdayTimestamp());
				PacketSendUtility.broadcastPacket(player, new SM_MINIONS(2, list, isMaterial), true);
				break;
			}
		}
	}

	public void spawnMinion(Player player, int minionObjId, boolean isManualSpawn) {
		int minionObjtId = minionObjId;
		for (MinionCommonData list : player.getMinionList().getMinions()) {
			if (list.getObjectId() == minionObjId) {
				minionObjId = list.getMinionId();
				break;
			}
		}
		
		MinionCommonData lastMinionCommonData;
		
		if (player.getMinion() != null) {
			if (player.getMinion().getMinionId() == minionObjId) {
				PacketSendUtility.broadcastPacket(player, new SM_MINIONS(5, player.getMinion()), true);
				return;
			}
			lastMinionCommonData = player.getMinion().getCommonData();
			despawnMinion(player, isManualSpawn);
		} else {
			lastMinionCommonData = player.getMinionList().getLastUsed();
		} if (lastMinionCommonData != null) {
			if (minionObjId == lastMinionCommonData.getMinionId()) {}
		}
		
		player.getController().addTask(TaskId.MINION_UPDATE, ThreadPoolManager.getInstance().scheduleAtFixedRate(new MinionController.MinionUpdateTask(player), PeriodicSaveConfig.PLAYER_PETS * 1000, PeriodicSaveConfig.PLAYER_PETS * 1000));
	    
		Minion minions = VisibleObjectSpawner.spawnMinion(player, minionObjtId);
	    
	    int skillId1 = DataManager.MINION_DATA.getMinionTemplate(minionObjId).getSkill1();
	    int skillId2 = DataManager.MINION_DATA.getMinionTemplate(minionObjId).getSkill2();
		if (skillId1 != 0) {
			player.getSkillList().addSkillWithoutSave(player, skillId1, 1);
		}

	    if (skillId2 != 0) {
	      player.getSkillList().addSkillWithoutSave(player, skillId2, 1);
	    }
	    
	    minionbuff = new MinionBuff(minionObjId);
		minionbuff.apply(player);
		
		if (minions != null) {
			if (System.currentTimeMillis() - minions.getCommonData().getDespawnTime().getTime() > 600000L) {}
			player.getMinionList().setLastUsed(minionObjId);
		}
		
	}

	public void despawnMinion(Player player, boolean isManualDespawn) {
		Minion minions = player.getMinion();
	    if (minions != null) {
	    	MinionDopingBag bag = minions.getCommonData().getDopingBag();
	    	if (bag != null && bag.isDirty()) {
	    		DAOManager.getDAO(PlayerMinionsDAO.class).saveDopingBag(player, minions.getMinionId(), bag);
	    	}
			if (minionbuff != null) {
				minionbuff.end(player);
			}
			//PacketSendUtility.broadcastPacket(player, new SM_MINIONS(6, player.getMinion()), true);
	    	player.getController().cancelTask(TaskId.MINION_UPDATE);
	    	if (isManualDespawn) {
	    		minions.getCommonData().setDespawnTime(new Timestamp(System.currentTimeMillis()));
	    	}
	    	player.setMinion(null);
	    	minions.getController().delete();

	    }
	  

	}
	
	public void onPlayerLogout(Player player) {
		
	}

	public void growthUpMinion(Player player, int minionObjectId, List<Integer> material) {
		int growthPoint = 0;
        long growthCost = 0;
        String grade = "";
        
        MinionCommonData playerMinion = player.getMinionList().getMinion(minionObjectId);
        grade = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getGrade();
        for (MinionCommonData list : player.getMinionList().getMinions()) {
            for (int matObjt : material) {
                if (list.getObjectId() == matObjt) {
                    int minionGrowth = 0;
                    if (DataManager.MINION_DATA.getMinionTemplate(list.getMinionId()).getGrade().equalsIgnoreCase(grade)) {
                        minionGrowth = DataManager.MINION_DATA.getMinionTemplate(list.getMinionId()).getGrowthPt() * 2;
                    }
                    else {
                        minionGrowth = DataManager.MINION_DATA.getMinionTemplate(list.getMinionId()).getGrowthPt();
                    }
                    growthPoint += minionGrowth;
                    growthCost += DataManager.MINION_DATA.getMinionTemplate(list.getMinionId()).getGrowthCost();
                }
            }
        }
        if (growthPoint <= 0) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_FAMILIAR_GROWTH_MSG_NOTSELECT);
            return;
        }
        if (player.getInventory().getKinah() < growthCost) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_FAMILIAR_GROWTH_MSG_NOGOLD);
            return;
        }
        player.getInventory().decreaseKinah(growthCost);
        playerMinion.setMinionGrowthPoint(playerMinion.getMinionGrowthPoint() + growthPoint);
        DAOManager.getDAO(PlayerMinionsDAO.class).updatePlayerMinionGrowthPoint(playerMinion);
        PacketSendUtility.broadcastPacket(player, new SM_MINIONS(7, playerMinion), true);
        for (int matObjt2 : material) {
            deleteMinion(player, matObjt2, true);
        }
        player.getMinionList().updateMinionsList();
	}

	public void evolutionUpMinion(Player player, int minionObjId) { 
		MinionCommonData playerMinion = player.getMinionList().getMinion(minionObjId);
		
		String groupset = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getGroupSet();
		int minionLevel = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getLevel();
	    int minionMaxGrowthLevel = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getMaxGrowthValue();
		int evoItemId = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getItemId();
		int evolveItemCount = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getEvolvedNum();
		long evoCost = DataManager.MINION_DATA.getMinionTemplate(playerMinion.getMinionId()).getEvolvedCost();
		if (minionLevel >= 4) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_FAMILIAR_EVOLVE_MSG_NOEVOLVE);
            return;
        }
		if (player.getInventory().getKinah() < evoCost) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_FAMILIAR_EVOLVE_MSG_NOGOLD);
            return;
        }
        if (player.getInventory().getItemCountByItemId(evoItemId) < evolveItemCount) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_FAMILIAR_EVOLVE_MSG_LACK_ITEM);
            return;
        }
        
        player.getInventory().decreaseKinah(evoCost);
        player.getInventory().decreaseByItemId(evoItemId, evolveItemCount);
        
        int minionNewId = 0;
        //int minionNewLvl = 0;
        for (MinionTemplate template : DataManager.MINION_DATA.getMinionData().valueCollection()) {
        	if (template.getLevel() == minionLevel + 1 && template.getGroupSet().equalsIgnoreCase(groupset)) {
        		minionNewId = template.getId();
        		//minionNewLvl = template.getLevel();
        		break;
        	}
        }
        
        playerMinion.setMinionId(minionNewId);
        //playerMinion.setMinionLevel(minionNewLvl);
        playerMinion.setMinionGrowthPoint(playerMinion.getMinionGrowthPoint() - minionMaxGrowthLevel);
        DAOManager.getDAO(PlayerMinionsDAO.class).evolutionMinion(player, minionNewId, playerMinion);
        PacketSendUtility.broadcastPacket(player, new SM_MINIONS(7, playerMinion), true);
        
        PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404350, playerMinion.getName(), minionLevel + 1));
        
        Collection<MinionCommonData> playerMinions = player.getMinionList().getMinions();
        if (playerMinions != null && playerMinions.size() > 0) {
        	PacketSendUtility.sendPacket(player, new SM_MINIONS(0, playerMinions));
        }
	}

	public void lockMinion(Player player, int minionObjId, int lock) {
		MinionCommonData playerMinion = player.getMinionList().getMinion(minionObjId);
		if (lock == 1) {
			playerMinion.setLock(true);
			DAOManager.getDAO(PlayerMinionsDAO.class).lockMinions(player, minionObjId, 1);
	        PacketSendUtility.broadcastPacket(player, new SM_MINIONS(4, playerMinion), true);
		} else {
			playerMinion.setLock(false);
			DAOManager.getDAO(PlayerMinionsDAO.class).lockMinions(player, minionObjId, 0);
	        PacketSendUtility.broadcastPacket(player, new SM_MINIONS(4, playerMinion), true);
		}
	}

	public void activateMinionFunction(Player player) {
		long kinah = 25000000;
		player.getCommonData().setMinionFunctionTime(new Timestamp(System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000)));
		Collection<MinionCommonData> playerMinions = player.getMinionList().getMinions();
		if (player.getInventory().getKinah() >= kinah) {
			if (playerMinions != null && playerMinions.size() > 0) {
				PacketSendUtility.sendPacket(player, new SM_MINIONS(9, playerMinions));
				PacketSendUtility.sendPacket(player, new SM_MINIONS(12, playerMinions));
			}
			player.getInventory().decreaseKinah(kinah);
		} else {
			PacketSendUtility.sendMessage(player, "not enough kinah !!");
			return;
		}
	}
	
	public void buffPlayer(final Player player, int action, final int minionObjectId, int itemId, final int slot) {
		Minion minion = player.getMinion();
		if (minion == null || minion.getCommonData().getDopingBag() == null) {
            return;
        }
		
		if (action < 2) {
			minion.getCommonData().getDopingBag().setItem(itemId, slot);
			action = 0;
		} else if (action == 3){
			List<Item> items = player.getInventory().getItemsByItemId(itemId);
			for (; ;) {
				Item useItem = items.get(0);
				ItemActions itemActions = useItem.getItemTemplate().getActions();
				ItemUseLimits limits = new ItemUseLimits();
				int useDelay = player.getItemCooldown(useItem.getItemTemplate()) / 3;
				if (useDelay < 3000) {
					useDelay = 3000;
				}
				
				limits.setDelayId(useItem.getItemTemplate().getUseLimits().getDelayId());
				limits.setDelayId(useDelay);
				
				if (player.isItemUseDisabled(limits)) {
					final int useAction = action;
					final int useItemId = itemId;
					final int useSlot = slot;
					
					ThreadPoolManager.getInstance().schedule(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, useAction, minionObjectId, useItemId, useSlot, 0, false), true);
						}
							
					}, useDelay);
					return;
				} if (!RestrictionsManager.canUseItem(player, useItem) || player.isProtectionActive()) {
					player.addItemCoolDown(limits.getDelayId(), System.currentTimeMillis() + useDelay, useDelay / 1000);
					break;
				}
					
				player.getController().cancelCurrentSkill();
				for (AbstractItemAction itemAction : itemActions.getItemActions()) {
					if (itemAction.canAct(player, useItem, null)) {
						itemAction.act(player, useItem, null);
					}
				}
				break;
			}
		}
		
		PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, action, minionObjectId, itemId, slot, 0, false), true);
		
		itemId = minion.getCommonData().getDopingBag().getFoodItem();
		long totalDoping = player.getInventory().getItemCountByItemId(itemId);
		
		itemId = minion.getCommonData().getDopingBag().getDrinkItem();
		totalDoping += player.getInventory().getItemCountByItemId(itemId);
		
		int[] scroolbag = minion.getCommonData().getDopingBag().getScrollsUsed();
		for (int i = 0; i < scroolbag.length;i++) {
			if (scroolbag[i] != 0) {
				totalDoping += player.getInventory().getItemCountByItemId(scroolbag[i]);
			}
		}
		
		if (totalDoping == 0) {
			minion.getCommonData().setIsBuffing(false);
			PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, action, minionObjectId, itemId, slot, 0, false), false);
		}
		
	}
	
	//TODO
	public void relocateDoping(Player player, int minionObjectId, int targetSlot, int destinationSlot) {
		MinionCommonData minions = player.getMinionList().getMinion(minionObjectId);
        if (minions == null || minions.getDopingBag() == null) {
            return;
        }
        int[] scrollBag = minions.getDopingBag().getScrollsUsed();
        int targetItem = scrollBag[targetSlot - 2];
        if (destinationSlot - 2 > scrollBag.length - 1) {
            minions.getDopingBag().setItem(targetItem, destinationSlot);
            PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, 0, minionObjectId, targetItem, targetSlot, destinationSlot, false), true);
            minions.getDopingBag().setItem(0, targetSlot);
            PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, 0, minionObjectId, targetItem, targetSlot, 0, false), true);
        }
        else {
            minions.getDopingBag().setItem(scrollBag[destinationSlot - 2], targetSlot);
            PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, 0, minionObjectId, scrollBag[(destinationSlot - 2)], targetSlot, 0, false), true);
            minions.getDopingBag().setItem(targetItem, destinationSlot);
            PacketSendUtility.broadcastPacket(player, new SM_MINIONS(8, 0, 0, minionObjectId, targetItem, 0, destinationSlot, false), true);
        }
    }
	
	public void activateLoot(Player player, int minionObjectId, boolean activate) {
		if (player.getMinion() == null) {
			return;
		}

		if (activate) {
			if (player.isInTeam()) {
				LootRuleType lootType = player.getLootGroupRules().getLootRule();
				if (lootType == LootRuleType.FREEFORALL) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LOOTING_PET_MESSAGE03);
					return;
				}
			}
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_LOOTING_PET_MESSAGE01);
		}

		player.getMinion().getCommonData().setIsLooting(activate);
		PacketSendUtility.sendPacket(player, new SM_MINIONS(8, 1, 5, minionObjectId, 0, 0, 0, activate));
	}
	
	public void CombinationMinion(Player player, List<Integer> minionObjIds) {
		
		if (player.getInventory().getKinah() < 50000) {
            PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404348));
            return;
        }
        
        MinionCommonData minion;
        int point=0;
		int level=0;
        for(int minions : minionObjIds) {
        	minion = player.getMinionList().getMinion(minions);
        	point += minion.getMinionGrowthPoint();
        	level += minion.getMinionLevel();
        }
        
		int minionId = 0, lvl = 0, points= 0;
		String name="", grade="";
        
		grade = player.getMinionList().getMinion(minionObjIds.get(0)).getMinionGrade();
		
		int rnd = Rnd.get(0,200) + ((point / level ) / 1000) + (level / 4);
		
		boolean result;
		if(rnd < 125){
			result = false;
			rnd = Rnd.get(0,3);
			switch(grade){
				case "D":
					minionId = 980010;
					break;
				case "C":
					minionId = player.getMinionList().getMinion(minionObjIds.get(rnd)).getMinionId();	
					break;
				case "B":
					minionId = player.getMinionList().getMinion(minionObjIds.get(rnd)).getMinionId();
					break;
			}
		} else {
			result = true;
			switch(grade){
				case "D":
					minionId = minionId(Rnd.get(36,420));
					break;
				case "C":
					if(player.getMinionList().getMinion(minionObjIds.get(0)).getMinionId() == 980011) {
						minionId = minionId(Rnd.get(141,700));
					} else {
						minionId = minionId(Rnd.get(421,700));
					}
					break;
				case "B":
					if(player.getMinionList().getMinion(minionObjIds.get(0)).getMinionId() == 980012) {
						minionId = minionId(Rnd.get(421,980));
					} else {
						minionId = minionId(Rnd.get(701,980));
					}
					break;
			}
		}
		
		if (player.getAccessLevel() > 5) {
			PacketSendUtility.sendMessage(player, (result ? "Success" : "Fail") + " Rnd:" + rnd + " Luck:" + 125);
		}
		
		MinionTemplate minionTemplate = DataManager.MINION_DATA.getMinionTemplate(minionId);
		grade = minionTemplate.getGrade();
		int expireTime = 0;
		lvl = minionTemplate.getLevel();
		name = minionTemplate.getName();
		points = minionTemplate.getGrowthPt();
		
		MinionCommonData addNewMinion = player.getMinionList().addNewMinion(player, minionId, name, grade, lvl, points, expireTime);
		if (addNewMinion != null) {
			//PacketSendUtility.sendPacket(player, new SM_MINIONS(1, addNewMinion, (result ? 2 : 3)));
		}
		for(int minionid : minionObjIds){
			deleteMinion(player, minionid, true);
		}
		player.getInventory().decreaseKinah(50000);
		minionObjIds.clear();
		player.getMinionList().updateMinionsList();
	}
	
	private static int minionId(int rnd) {
		if (rnd <= 35) {
			return 980010; //Kerubar D
		} else if (rnd >= 36 && rnd <= 70) {
			return 980011; //Kerubian C
		} else if (rnd >= 71 && rnd <= 105) {
			return 980012; //Kerubiel B
		} else if (rnd >= 106 && rnd <= 140) {
			return 980013; //Arch Kerubiel A
		} else if (rnd >= 141 && rnd <= 175) {
			return 980020; //Seiren D
		} else if (rnd >= 176 && rnd <= 210) {
			return 980021; //Seiren C
		} else if (rnd >= 211 && rnd <= 245) {
			return 980022; //Seiren B
		} else if (rnd >= 246 && rnd <= 280) {
			return 980023; //Seiren A
		} else if (rnd >= 281 && rnd <= 315) {
			return 980030; //Steel Rose D
		} else if (rnd >= 316 && rnd <= 350) {
			return 980031; //Steel Rose C
		} else if (rnd >= 351 && rnd <= 385) {
			return 980032; //Steel Rose B
		} else if (rnd >= 386 && rnd <= 420) {
			return 980033; //Steel Rose A
		} else if (rnd >= 421 && rnd <= 455) {
			return 980040; //Abija D
		} else if (rnd >= 456 && rnd <= 490) {
			return 980041; //Abija C
		} else if (rnd >= 491 && rnd <= 525) {
			return 980042; //Abija B
		} else if (rnd >= 526 && rnd <= 560) {
			return 980043; //Abija A
		} else if (rnd >= 561 && rnd <= 595) {
			return 980050; //Hamerun D
		} else if (rnd >= 596 && rnd <= 630) {
			return 980051; //Hamerun C
		} else if (rnd >= 631 && rnd <= 665) {
			return 980052; //Hamerun B
		} else if (rnd >= 666 && rnd <= 700) {
			return 980053; //Hamerun A
		} else if (rnd >= 701 && rnd <= 735) {
			return 980060; //Grendal D
		} else if (rnd >= 736 && rnd <= 770) {
			return 980061; //Grendal C
		} else if (rnd >= 771 && rnd <= 805) {
			return 980062; //Grendal B
		} else if (rnd >= 806 && rnd <= 840) {
			return 980063; //Grendal A
		} else if (rnd >= 841 && rnd <= 875) {
			return 980070; //Sita D
		} else if (rnd >= 876 && rnd <= 910) {
			return 980071; //Sita C 
		} else if (rnd >= 911 && rnd <= 945) {
			return 980072; //Sita B
		} else if (rnd >= 946 && rnd <= 980) {
			return 980073; //Sita A
		} else return 0;
	}
	
	public static MinionService getInstance() {
		return SingletonHolder.instance;
	}

	private static class SingletonHolder {

		protected static final MinionService instance = new MinionService();
	}
}
