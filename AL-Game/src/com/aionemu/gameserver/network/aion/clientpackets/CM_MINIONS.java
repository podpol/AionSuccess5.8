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
package com.aionemu.gameserver.network.aion.clientpackets;

import java.util.ArrayList;
import java.util.List;

import com.aionemu.gameserver.GameServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.configs.main.CustomConfig;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.MinionAction;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.actions.AbstractItemAction;
import com.aionemu.gameserver.model.templates.item.actions.AdoptMinionAction;
import com.aionemu.gameserver.model.templates.item.actions.ItemActions;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.NameRestrictionService;
import com.aionemu.gameserver.services.toypet.MinionService;
import com.aionemu.gameserver.services.toypet.PetSpawnService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Falke_34, FrozenKiller
 */
public class CM_MINIONS extends AionClientPacket {

	private static final Logger log = LoggerFactory.getLogger(CM_MINIONS.class);
	List<Integer> material = new ArrayList<Integer>();
	private int actionId;
	private MinionAction action;
	private int minionObjectId;
	private int ItemObjectId;
	private int dopingAction;
	private int dopingSlot;
	private int dopingSlot2;
	private int dopingItemId;
	private int type;
	private int actionType;
	private int activateLoot;
	private int activateAutoSell;
	private int unkMerchand2;
	private int unkMerchand3;
	private int activateCheering;
	private int unkCheer2;
	private int unkCheer3;
	private int objectId;
	private int count;
	private int functId;
	private int unk1;
	private int Upgradeslot;
	private int Upgradeslot2;
	private int Upgradeslot3;
	private int Upgradeslot4;
	private int growthtarget;
	private int growthtarget2;
	private int growthtarget3;
	private int growthtarget4;
	private int growthtarget5;
	private int growthtarget6;
	private int growthtarget7;
	private int growthtarget8;
	private int growthtarget9;
	private int growthtarget10;
	private String rename = "";
	private int lock;

	public CM_MINIONS(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		actionId = readH();
		action = MinionAction.getActionById(actionId);
		switch (action) {
			case ADOPT:
				ItemObjectId = readD(); // Item UniqueId (Minion Contract)
				break;
			case RENAME:
				minionObjectId = readD(); // Minion Unique ID
				rename = readS(); // rename
			case DELETE:
				minionObjectId = readD(); // Minion Unique ID
				break;
			case LOCK:
				minionObjectId = readD(); // Minion Unique ID
                lock = readC(); // lock/unlock Todo
                break;
			case SPAWN:
			case DISMISS:
				minionObjectId = readD(); // Minion Unique ID
				break;
			case GROWTH:
				material.clear();
				minionObjectId = readD();
				growthtarget = readD();
				growthtarget2 = readD();
				growthtarget3 = readD();
				growthtarget4 = readD();
				growthtarget5 = readD();
				growthtarget6 = readD();
				growthtarget7 = readD();
				growthtarget8 = readD();
				growthtarget9 = readD();
				growthtarget10 = readD();

				material.add(growthtarget);
				material.add(growthtarget2);
				material.add(growthtarget3);
				material.add(growthtarget4);
				material.add(growthtarget5);
				material.add(growthtarget6);
				material.add(growthtarget7);
				material.add(growthtarget8);
				material.add(growthtarget9);
				material.add(growthtarget10);
				break;
			case USE_FUNCTION:
		    case STOP_FUNCTION:
		      break;
		    case CHARGE:
		      break;
			case EVOLVE:
				minionObjectId = readD();
				break;
			case COMBINE:
				material.clear();
				Upgradeslot = readD();
				Upgradeslot2 = readD();
				Upgradeslot3 = readD();
				Upgradeslot4 = readD();
				material.add(Upgradeslot);
				material.add(Upgradeslot2);
				material.add(Upgradeslot3);
				material.add(Upgradeslot4);
			case SET_FUNCTION:
				actionType = readD();
				switch (actionType) {
					case 0:
						dopingAction = readD();
						if (dopingAction == 0) {
							minionObjectId = readD();
							dopingItemId = readD();
							dopingSlot = readD();
						} else if (dopingAction == 1) {
							minionObjectId = readD();
							dopingSlot = readD();
							dopingItemId = readD();
						} else if (dopingAction == 2) {
							minionObjectId = readD();
							dopingSlot = readD();
							dopingSlot2 = readD();
						} else if (dopingAction == 3) {
							minionObjectId = readD();
							dopingItemId = readD();
							dopingSlot = readD();
						} else if (dopingAction == 4) {
							minionObjectId = readD();
						}
						break;
					case 1:
						if (dopingAction == 5) {
							minionObjectId = readD();
							activateLoot = readD();
						}
						break;
				}
			default:
				break;

		}
	}

	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null) {
			return;
		}

		switch (action) {
			case ADOPT:
				if (player.getMinionList().getMinions().size() >= CustomConfig.MAX_MINION_LIST) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404322));
					return;
				}

                if (player.getMinionList().getMinions().size() >= 200) {
                    PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1404322));
                    return;
                }

                Item item = player.getInventory().getItemByObjId(ItemObjectId);
                ItemActions itemActions = item.getItemTemplate().getActions();
                player.getObserveController().notifyItemuseObservers(item);
                for (AbstractItemAction itemAction : itemActions.getItemActions()) {
                    if (itemAction instanceof AdoptMinionAction) {
                        AdoptMinionAction action = (AdoptMinionAction) itemAction;
                        action.act(player, item, item);

                    }
                }
                break;
			case DELETE:
				MinionService.getInstance().deleteMinion(player, minionObjectId, false);
				break;
			case RENAME:
				if (NameRestrictionService.isForbiddenWord(rename)) {
					PacketSendUtility.sendMessage(player, "You are trying to use a forbidden name. Choose another one!");
				} else {
					MinionService.getInstance().renameMinion(player, minionObjectId, rename);
				}
				break;
			case LOCK:
				MinionService.getInstance().lockMinion(player, minionObjectId, lock);
				break;
			case SPAWN:
				if (player.getPet() != null) {
					PetSpawnService.dismissPet(player, true);
				}
				MinionService.getInstance().spawnMinion(player, this.minionObjectId, true);
				break;
			case DISMISS:
				MinionService.getInstance().despawnMinion(player, true);
				break;
			case GROWTH:
				MinionService.getInstance().growthUpMinion(player, minionObjectId, material);
				break;
			case EVOLVE:
				MinionService.getInstance().evolutionUpMinion(player, minionObjectId);
				break;
			case COMBINE:
				MinionService.getInstance().CombinationMinion(player, material);
				break;
			case SET_FUNCTION: // TODO
				switch (actionType) {
					case 0:
						if (dopingAction == 2) {
							MinionService.getInstance().relocateDoping(player, minionObjectId, dopingSlot, dopingSlot2);
						} else {
							MinionService.getInstance().buffPlayer(player, dopingAction, minionObjectId, dopingItemId, dopingSlot);
						}
						break;
					case 1:
						if (dopingAction == 5) {
							MinionService.getInstance().activateLoot(player, minionObjectId, activateLoot != 0);
						}
						break;
				}
				break;
			case USE_FUNCTION: // MinionFunction (Activate)
				MinionService.getInstance().activateMinionFunction(player);
				break;
			case CHARGE:
				MinionService.getInstance().chargeSkillPoint(player, false);
				break;
			case STOP_FUNCTION:
				MinionService.getInstance().activateMinionFunction(player);
				break;
		default:
			break;
		}
	}
}
