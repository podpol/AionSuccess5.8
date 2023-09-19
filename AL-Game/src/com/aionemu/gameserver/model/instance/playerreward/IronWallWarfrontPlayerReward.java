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
package com.aionemu.gameserver.model.instance.playerreward;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.InstanceBuff;

/****/
/** Author Ranastic (Encom)
/****/

public class IronWallWarfrontPlayerReward extends InstancePlayerReward
{
    private int timeBonus;
    private long logoutTime;
    private float timeBonusModifier;
    private Race race;
    private int rewardAp;
    private int rewardGp;
	private int rewardExp;
    private int bonusAp;
    private int bonusGp;
	private int bonusExp;
    private int medalBundle;
    private int brokenSpinel;
    private int bonusReward;
    private int bonusReward2;
    private float rewardCount;
	private int AdditionalReward;
    private float AdditionalRewardCount;
	private InstanceBuff boostMorale;
	
    public IronWallWarfrontPlayerReward(Integer object, int timeBonus, byte buffId, Race race) {
        super(object);
        this.timeBonus = timeBonus;
        timeBonusModifier = ((float) this.timeBonus / (float) 660000);
        this.race = race;
		boostMorale = new InstanceBuff(buffId);
    }
	
    public float getParticipation() {
        return (float) getTimeBonus() / timeBonus;
    }
	
    public int getScorePoints() {
        return timeBonus + getPoints();
    }
	
    public int getTimeBonus() {
        return timeBonus > 0 ? timeBonus : 0;
    }
	
    public void updateLogOutTime() {
        logoutTime = System.currentTimeMillis();
    }
	
    public void updateBonusTime() {
        int offlineTime = (int) (System.currentTimeMillis() - logoutTime);
        timeBonus -= offlineTime * timeBonusModifier;
    }
	
    public Race getRace() {
        return race;
    }
	
    public int getMedalBundle() {
        return medalBundle;
    }
	
    public int getBrokenSpinel() {
        return brokenSpinel;
    }
	
    public int getBonusReward() {
        return bonusReward;
    }
	
    public int getRewardCount() {
        return (int) rewardCount;
    }
	
    public void setMedalBundle(int reward) {
        this.medalBundle = reward;
    }
	
    public void setBrokenSpinel(int reward) {
        this.brokenSpinel = reward;
    }
	
    public void setBonusReward(int reward) {
        this.bonusReward = reward;
    }
	
    public void setRewardCount(float rewardCount) {
        this.rewardCount = rewardCount;
    }
	
    //Ap
    public int getRewardAp() {
        return rewardAp;
    }
	public void setRewardAp(int rewardAp) {
        this.rewardAp = rewardAp;
    }
    public int getBonusAp() {
        return bonusAp;
    }
    public void setBonusAp(int bonusAp) {
        this.bonusAp = bonusAp;
    }
	
	//Gp
    public int getRewardGp() {
        return rewardGp;
    }
    public void setRewardGp(int rewardGp) {
        this.rewardGp = rewardGp;
    }
	public int getBonusGp() {
		return bonusGp;
	}
	public void setBonusGp(int bonusGp) {
		this.bonusGp = bonusGp;
	}
	
	//Exp
	public int getRewardExp() {
        return rewardExp;
    }
    public void setRewardExp(int rewardExp) {
        this.rewardExp = rewardExp;
    }
	public int getBonusExp() {
		return bonusExp;
	}
	public void setBonusExp(int bonusExp) {
		this.bonusExp = bonusExp;
	}
	
	public int getBonusReward2() {
		return bonusReward2;
	}
	
	public void setBonusReward2(int bonusReward2) {
		this.bonusReward2 = bonusReward2;
	}
	
	public int getAdditionalReward() {
		return AdditionalReward;
	}
	
	public void setAdditionalReward(int additionalReward) {
		this.AdditionalReward = additionalReward;
	}
	
    public int getAdditionalRewardCount() {
        return (int) AdditionalRewardCount;
    }
    
    public void setAdditionalRewardCount(float rewardCount) {
        this.AdditionalRewardCount = rewardCount;
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
}