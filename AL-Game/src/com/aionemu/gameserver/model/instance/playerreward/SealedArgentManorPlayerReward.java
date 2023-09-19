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

public class SealedArgentManorPlayerReward extends InstancePlayerReward
{
	private int scoreAP;
	private int argentManorBox;
	private int lesserArgentManorBox;
	private int greaterArgentManorBox;
	private boolean isRewarded = false;
	
	public SealedArgentManorPlayerReward(Integer object) {
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
	
	public int getArgentManorBox() {
		return argentManorBox;
	}
	public int getLesserArgentManorBox() {
		return lesserArgentManorBox;
	}
	public int getGreaterArgentManorBox() {
		return greaterArgentManorBox;
	}
	
	public void setArgentManorBox(int argentManorBox) {
		this.argentManorBox = argentManorBox;
	}
	public void setLesserArgentManorBox(int lesserArgentManorBox) {
		this.lesserArgentManorBox = lesserArgentManorBox;
	}
	public void setGreaterArgentManorBox(int greaterArgentManorBox) {
		this.greaterArgentManorBox = greaterArgentManorBox;
	}
}