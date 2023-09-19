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
package com.aionemu.gameserver.model.instance.instancereward;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.geometry.Point3D;
import com.aionemu.gameserver.model.instance.playerreward.PvPArenaPlayerReward;
import com.aionemu.gameserver.model.instance.playerreward.EvergaleCanyonPlayerReward;
import com.aionemu.gameserver.network.aion.serverpackets.SM_INSTANCE_SCORE;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldMapInstance;
import com.aionemu.gameserver.world.knownlist.Visitor;
import org.apache.commons.lang.mutable.MutableInt;

import java.util.Comparator;
import java.util.List;

import static ch.lambdaj.Lambda.*;

/****/
/** Author Rinzler (Encom)
/****/

public class EvergaleCanyonReward extends InstanceReward<EvergaleCanyonPlayerReward>
{
    private int capPoints;
    private MutableInt asmodiansPoints = new MutableInt(0);
    private MutableInt elyosPoins = new MutableInt(0);
    private MutableInt asmodiansPvpKills = new MutableInt(0);
    private MutableInt elyosPvpKills = new MutableInt(0);
    private Race race;
    private Point3D asmodiansStartPosition;
    private Point3D elyosStartPosition;
    protected WorldMapInstance instance;
    private long instanceTime;
    private int bonusTime;
	private final byte buffId;
	
    public EvergaleCanyonReward(Integer mapId, int instanceId, WorldMapInstance instance) {
        super(mapId, instanceId);
        this.instance = instance;
        capPoints = 500000;
        bonusTime = 12000;
		buffId = 10;
        setStartPositions();
    }
	
	public int AbyssReward(boolean isWin, boolean isCommanderKilled) {
    	int CommanderKilled = 1993;
    	int Win = 3163;
    	int Loss = 1031;
    	if (isCommanderKilled) {
    		return isWin ? (Win + CommanderKilled) : (Loss + CommanderKilled);
    	} else {
    		return isWin ? Win : Loss;
    	}
    }
	public int GloryReward(boolean isWin, boolean isCommanderKilled) {
    	int CommanderKilled = 50;
    	int Win = 150;
    	int Loss = 30;
    	if (isCommanderKilled) {
    		return isWin ? (Win + CommanderKilled) : (Loss + CommanderKilled);
    	} else {
    		return isWin ? Win : Loss;
    	}
    }
	public int ExpReward(boolean isWin, boolean isCommanderKilled) {
    	int CommanderKilled = 20000;
    	int Win = 10000;
    	int Loss = 5000;
    	if (isCommanderKilled) {
    		return isWin ? (Win + CommanderKilled) : (Loss + CommanderKilled);
    	} else {
    		return isWin ? Win : Loss;
    	}
    }
	
    public List<EvergaleCanyonPlayerReward> sortPoints() {
        return sort(getInstanceRewards(), on(PvPArenaPlayerReward.class).getScorePoints(), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 != null ? o2.compareTo(o1) : -o1.compareTo(o2);
            }
        });
    }
	
    private void setStartPositions() {
        Point3D a = new Point3D(1094.0016f, 752.5455f, 336.30457f);
        Point3D e = new Point3D(402.67786f, 751.9347f, 336.30457f);
        asmodiansStartPosition = a;
        elyosStartPosition = e;
    }
	
    public void portToPosition(Player player) {
        if (player.getRace() == Race.ASMODIANS) {
            TeleportService2.teleportTo(player, mapId, instanceId, asmodiansStartPosition.getX(), asmodiansStartPosition.getY(), asmodiansStartPosition.getZ());
        } else {
            TeleportService2.teleportTo(player, mapId, instanceId, elyosStartPosition.getX(), elyosStartPosition.getY(), elyosStartPosition.getZ());
        }
    }
	
    public MutableInt getPointsByRace(Race race) {
        return (race == Race.ELYOS) ? elyosPoins : (race == Race.ASMODIANS) ? asmodiansPoints : null;
    }
	
    public void addPointsByRace(Race race, int points) {
        MutableInt racePoints = getPointsByRace(race);
        racePoints.add(points);
        if (racePoints.intValue() < 0) {
            racePoints.setValue(0);
        }
    }
	
    public MutableInt getPvpKillsByRace(Race race) {
        return (race == Race.ELYOS) ? elyosPvpKills : (race == Race.ASMODIANS) ? asmodiansPvpKills : null;
    }
	
    public void addPvpKillsByRace(Race race, int points) {
        MutableInt racePoints = getPvpKillsByRace(race);
        racePoints.add(points);
        if (racePoints.intValue() < 0) {
            racePoints.setValue(0);
        }
    }
	
    public void setWinnerRace(Race race) {
        this.race = race;
    }
	
    public Race getWinnerRace() {
        return race;
    }
	
    public Race getWinnerRaceByScore() {
        return asmodiansPoints.compareTo(elyosPoins) > 0 ? Race.ASMODIANS : Race.ELYOS;
    }
	
    @Override
    public void clear() {
        super.clear();
    }
	
    public void regPlayerReward(Player player) {
        if (!containPlayer(player.getObjectId())) {
            addPlayerReward(new EvergaleCanyonPlayerReward(player.getObjectId(), bonusTime, buffId, player.getRace()));
        }
    }
	
    @Override
    public void addPlayerReward(EvergaleCanyonPlayerReward reward) {
        super.addPlayerReward(reward);
    }
	
    @Override
    public EvergaleCanyonPlayerReward getPlayerReward(Integer object) {
        return (EvergaleCanyonPlayerReward) super.getPlayerReward(object);
    }
	
    public void sendPacket(final int type, final Integer object) {
        instance.doOnAllPlayers(new Visitor<Player>() {
            @Override
            public void visit(Player player) {
                PacketSendUtility.sendPacket(player, new SM_INSTANCE_SCORE(type, getTime(), getInstanceReward(), object));
            }
        });
    }
	
    public int getTime() {
        long result = System.currentTimeMillis() - instanceTime;
        if (result < 90000) {
            return (int) (90000 - result);
        } else if (result < 1800000) { //30-Mins
            return (int) (1800000 - (result - 90000));
        }
        return 0;
    }
	
	public byte getBuffId() {
		return buffId;
	}
	
    public void setInstanceStartTime() {
        this.instanceTime = System.currentTimeMillis();
    }
	
    public int getCapPoints() {
        return capPoints;
    }
	
    public boolean hasCapPoints() {
        return maxFrom(getInstanceRewards()).getPoints() >= capPoints;
    }
}