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

/****/
/** Author Rinzler (Encom)
/****/

public class EternalBastionPlayerReward extends InstancePlayerReward
{
	private int scoreAP;
	private int ceramium;
	private int highGradeMaterialBox;
	private int highestGradeMaterialBox;
	private int lowGradeMaterialSupportBundle;
	private int highGradeMaterialSupportBundle;
	private int highestGradeMaterialSupportBundle;
	private boolean isRewarded = false;
	
	public EternalBastionPlayerReward(Integer object) {
		super(object);
	}
	
	public boolean isRewarded() {
		return isRewarded;
	}
	public void setRewarded() {
		isRewarded = true;
	}
	
	public int getScoreAP() {
		return scoreAP;
	}
	public void setScoreAP(int ap) {
		this.scoreAP = ap;
	}
	public int getCeramium() {
		return ceramium;
	}
	public int getHighGradeMaterialBox() {
        return highGradeMaterialBox;
    }
	public int getHighestGradeMaterialBox() {
        return highestGradeMaterialBox;
    }
	public int getLowGradeMaterialSupportBundle() {
        return lowGradeMaterialSupportBundle;
    }
	public int getHighGradeMaterialSupportBundle() {
        return highGradeMaterialSupportBundle;
    }
    public int getHighestGradeMaterialSupportBundle() {
        return highestGradeMaterialSupportBundle;
    }
	
	public void setCeramium(int ceramium) {
		this.ceramium = ceramium;
	}
	public void setHighGradeMaterialBox(int highGradeMaterialBox) {
        this.highGradeMaterialBox = highGradeMaterialBox;
    }
    public void setHighestGradeMaterialBox(int highestGradeMaterialBox) {
        this.highestGradeMaterialBox = highestGradeMaterialBox;
    }
	public void setLowGradeMaterialSupportBundle(int lowGradeMaterialSupportBundle) {
        this.lowGradeMaterialSupportBundle = lowGradeMaterialSupportBundle;
    }
    public void setHighGradeMaterialSupportBundle(int highGradeMaterialSupportBundle) {
        this.highGradeMaterialSupportBundle = highGradeMaterialSupportBundle;
    }
	public void setHighestGradeMaterialSupportBundle(int highestGradeMaterialSupportBundle) {
        this.highestGradeMaterialSupportBundle = highestGradeMaterialSupportBundle;
    }
}