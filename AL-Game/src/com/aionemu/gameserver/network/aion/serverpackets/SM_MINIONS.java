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
package com.aionemu.gameserver.network.aion.serverpackets;

import java.util.Collection;

import com.aionemu.gameserver.model.gameobjects.Minion;
import com.aionemu.gameserver.model.gameobjects.player.MinionCommonData;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Falke_34, FrozenKiller
 */
public class SM_MINIONS extends AionServerPacket {
	private int actionId;
	private Minion minion;
	private Collection<MinionCommonData> minions;
	private MinionCommonData minionsCommonData;
	private int count;
	private int subType;
	private boolean isActing;
	private int lootNpcId;
	private int dopeAction;
	private int dopeSlot;
	private int dopeSlot2;
	private int minionObjectId;
	private int ItemId;
	private boolean asMaterial;
	private Player player = null;

	public SM_MINIONS(int subType, int actionId, int objectId, int count, Minion minion) {
		this.subType = subType;
	    this.actionId = actionId;
	    this.count = count;
	    this.ItemId = objectId;
	    this.minion = minion;
	    this.minionsCommonData = minion.getCommonData();
	}
	  
	public SM_MINIONS(int actionId) {
		this.actionId = actionId;
	}
	  
	public SM_MINIONS(Player player, int actionId) {
	    this.player = player;
	    this.actionId = actionId;
	}
	  
	public SM_MINIONS(int actionId, int subType, int dopeAction, int minionObjectId, int ItemId, int slot, int slot2, boolean isLooting) {
	    this.actionId = actionId;
	    switch (subType) {
	    	case 0:
	    		switch (dopeAction) {
		    	case 0: 
		    		this.dopeAction = 0;
		    		this.minionObjectId = minionObjectId;
		    		this.ItemId = ItemId;
		    		this.dopeSlot = slot;
		    		break;
		    	case 2: 
		    		this.dopeAction = 512;
		    		this.minionObjectId = minionObjectId;
		    		this.dopeSlot = slot;
		    		this.dopeSlot2 = slot2;
		    		break;
		    	case 1: 
		    		this.dopeAction = 256;
		    		this.minionObjectId = minionObjectId;
		    		this.dopeSlot = slot;
		    		break;
		    	case 3: 
		    		this.dopeAction = 768;
		    		this.minionObjectId = minionObjectId;
		    		this.ItemId = ItemId;
		    		this.dopeSlot = slot;
		    		break;
	    		}
	    		break;
	    	case 1:
	    		switch (dopeAction) {
	    			case 5:
	    				this.minionObjectId = minionObjectId;
	    	    		this.isActing = isLooting;
	    	    		break;
	    		}
	    		break;
	    }
	}
	
	public SM_MINIONS(boolean isLooting) {
		this.actionId = 8;
		this.isActing = isLooting;
		this.subType = 1;
	}
	  
	public SM_MINIONS(int actionId, Collection<MinionCommonData> minions) {
		this.actionId = actionId;
		this.minions = minions;
	}
	  
	public SM_MINIONS(int actionId, MinionCommonData minion) {
		this.actionId = actionId;
		this.minionsCommonData = minion;
	}
	  
	public SM_MINIONS(int actionId, MinionCommonData minion, boolean asMaterial) {
	    this.actionId = actionId;
	    this.minionsCommonData = minion;
	    this.asMaterial = asMaterial;
	}
	  
	public SM_MINIONS(int actionId, Minion minion) {
		this(0, actionId, 0, 0, minion);
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeH(actionId);
		switch (actionId) {
			case 0:
				writeC(0);
				if (minions == null) {
					writeH(0);
					break;
				}
				writeH(minions.size());
				for (MinionCommonData commonData : minions) {
					writeD(commonData.getObjectId());
					writeD(commonData.getMinionId());
					writeD(0); 
					writeD(commonData.getMasterObjectId());
					writeD(commonData.getMinionId());
					writeS(commonData.getName());
					//writeS(commonData.getGrade());
					//writeD(commonData.getMinionLevel());
					writeD(commonData.getBirthday());
					writeD(0);
					writeD(commonData.getMinionGrowthPoint());
					writeC(commonData.isLock() ? 1 : 0);
					if (commonData.getDopingBag() == null) {
                        writeB(new byte[24]);
                    } else {
                    	int[] scrollBag = commonData.getDopingBag().getScrollsUsed();
                    	writeD(commonData.getDopingBag().getFoodItem());
                    	writeD(commonData.getDopingBag().getDrinkItem());
                    	if (scrollBag.length == 0) {
                    		writeB(new byte[16]);
                    	} else {
                    		writeD(scrollBag[0]);
                    		writeD((scrollBag.length > 1) ? scrollBag[1] : 0);
                    		writeD((scrollBag.length > 2) ? scrollBag[2] : 0);
                    		writeD((scrollBag.length > 3) ? scrollBag[3] : 0);
                    	}
                    }
					writeC(0); 
				}
				break;
			case 1: 
				if (this.minionsCommonData == null) {
					return;
				}
				writeD(subType);
				writeD(0);
				writeH(0);
				writeD(minionsCommonData.getObjectId());
				writeD(minionsCommonData.getMinionId());
				writeD(0);
				writeD(minionsCommonData.getMasterObjectId());
				writeD(minionsCommonData.getMinionId());
				writeS(minionsCommonData.getName());
				//writeS(minionsCommonData.getGrade());
				//writeD(minionsCommonData.getMinionLevel());
				writeD(minionsCommonData.getBirthday());
				writeB(new byte[34]);
				break;
			case 2:
				if (minionsCommonData == null) {
					return;
				}
				writeH(asMaterial ? 1 : 0);
				writeD(minionsCommonData.getObjectId());
				break;
			case 3:
				if (this.minionsCommonData == null) {
					return;
				}
				writeD(minionsCommonData.getObjectId());
				writeS(minionsCommonData.getName());
				break;
			case 4:
                if (minionsCommonData == null) {
                    return;
                }
				writeD(minionsCommonData.getObjectId());
                writeC(minionsCommonData.isLock() ? 1 : 0);
                break;
			case 5:
                if (minion == null) {
                    return;
                }
                writeS(minion.getName());
                writeD(minion.getObjectId());
                writeD(minion.getMinionId());
                writeD(minion.getMaster().getObjectId());
				break;
			case 6:
				if (minion == null) {
					return;
				}
				writeD(minion.getObjectId());
				//if (minion.getMaster().getLifeStats().isAlreadyDead()) {
					//writeC(0);
				//} else {
					writeC(21);
				//}
				break;
			case 7: 
				if (minionsCommonData == null) {
					return;
				}
				writeD(minionsCommonData.getObjectId());
				writeD(minionsCommonData.getMinionLevel());
				writeD(minionsCommonData.getMinionGrowthPoint());
				break;
			case 8: 
				switch (subType) {
					case 0:
						switch (dopeAction) {
							case 0: 
								writeH(this.dopeAction);
								writeD(this.minionObjectId);
								writeD(this.ItemId);
								writeD(this.dopeSlot);
								break;
							case 256: 
								writeH(this.dopeAction);
								writeD(this.minionObjectId);
								writeD(this.dopeSlot);
								break;
							case 768: 
								writeH(this.dopeAction);
								writeD(this.minionObjectId);
								writeD(this.ItemId);
								break;
							case 512: 
								writeH(this.dopeAction);
								writeD(this.minionObjectId);
								writeD(this.dopeSlot);
								writeD(this.dopeSlot2);
						}
						break;
					case 1:
						switch (dopeAction) {
							case 5:
								if (lootNpcId > 0) {
									writeD(minionObjectId);
									writeC(isActing ? 1 : 2); // 0x02 display looted msg.
									writeD(lootNpcId);
								} else {
									writeD(minionObjectId);
									writeC(0);
									writeC(isActing ? 1 : 0);
								}
								break;
						}
						break;
				}
				break;
			case 9:
				writeD(1501224031);
				writeD(1);
				break;
			case 10:
				break;
			case 11:
				writeD(player == null ? 0 : player.getCommonData().getMinionSkillPoints());
				writeC(player.getCommonData().isMinionAutoCharge() ? 1 : player == null ? 0 : 0);
				break;
			case 12:
				writeD(0);
				writeD(0);
				break;
			case 13: 
		    case 14: 
		    case 15: 
		    case 16: 
		    case 17: 
		    case 18: 
		    case 19: 
		    case 20: 
		    case 21: 
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		    	writeD(323045);
		}
	}
}
