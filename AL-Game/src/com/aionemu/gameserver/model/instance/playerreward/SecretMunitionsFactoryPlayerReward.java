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

public class SecretMunitionsFactoryPlayerReward extends InstancePlayerReward
{
	private int scoreAP;
	private int mechaturerkSecretBox;
	private int mechaturerkSpecialTreasureBox;
	private int mechaturerkNormalTreasureChest;
	private boolean isRewarded = false;
	
	public SecretMunitionsFactoryPlayerReward(Integer object) {
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
	
	public int getMechaturerkSecretBox() {
		return mechaturerkSecretBox;
	}
	public int getMechaturerkSpecialTreasureBox() {
		return mechaturerkSpecialTreasureBox;
	}
	public int getMechaturerkNormalTreasureChest() {
		return mechaturerkNormalTreasureChest;
	}
	
	public void setMechaturerkSecretBox(int mechaturerkSecretBox) {
		this.mechaturerkSecretBox = mechaturerkSecretBox;
	}
	public void setMechaturerkSpecialTreasureBox(int mechaturerkSpecialTreasureBox) {
		this.mechaturerkSpecialTreasureBox = mechaturerkSpecialTreasureBox;
	}
	public void setMechaturerkNormalTreasureChest(int mechaturerkNormalTreasureChest) {
		this.mechaturerkNormalTreasureChest = mechaturerkNormalTreasureChest;
	}
}