package com.aionemu.gameserver.model.instance.playerreward;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.InstanceBuff;

/**
 * 
 * @author Ranastic
 *
 */
public class HallOfTenacityPlayerReward extends InstancePlayerReward {

	private int position;
	private int zone;
	private int timeBonus;
	private InstanceBuff boostMorale;
	private int rewardAp;
	private int rewardExp;
	private int competitionPoint;
	
	public HallOfTenacityPlayerReward(Integer object, int timeBonus, byte buffId) {
		super(object);
		this.timeBonus = timeBonus;
        boostMorale = new InstanceBuff(buffId);
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getZone() {
		return zone;
	}
	
	public void setZone(int zone) {
		this.zone = zone;
	}
	
	public int getScorePoints() {
        return timeBonus + getPoints();
    }

	public float getParticipation() {
        return (float) getTimeBonus() / timeBonus;
    }
	
	public int getTimeBonus() {
        return timeBonus > 0 ? timeBonus : 0;
    }
	
	public boolean hasBoostMorale() {
		return boostMorale.hasInstanceBuff();
	}
	
	public void applyBoostMoraleEffect(Player player) {
		boostMorale.applyEffect(player, 20000);
	}
	
	public void endBoostMoraleEffect(Player player) {
		boostMorale.endEffect(player);
	}
	
	public int getRemaningTime() {
		int time = boostMorale.getRemaningTime();
		if (time >= 0 && time < 20) {
			return 20 - time;
		}
		return 0;
	}
	
	public int getRewardAp() {
        return rewardAp;
    }
	
	public void setRewardAp(int rewardAp) {
        this.rewardAp = rewardAp;
    }
	
	public int getRewardExp() {
        return rewardExp;
    }
	
    public void setRewardExp(int rewardExp) {
        this.rewardExp = rewardExp;
    }
    
    public int getCompetitionPoint() {
        return competitionPoint;
    }
	
	public void setCompetitionPoint(int competitionPoint) {
        this.competitionPoint = competitionPoint;
    }
}