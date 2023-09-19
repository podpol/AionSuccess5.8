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
package com.aionemu.gameserver.spawnengine;

/**
 * @author Rolandas
 */
public class WalkerGroupShift {

	private float sagittalShift;	// left and right (sides)
	private float coronalShift;		// or dorsoventral (back and front)
	private float angle;					// if positioned in circle
	
	public static final float DISTANCE = 2; // 2 meters distance by default

	public WalkerGroupShift(float leftRight, float backFront) {
		sagittalShift = leftRight;
		coronalShift = backFront;
	}

	public WalkerGroupShift(float angle) {
		this.angle = angle;
	}

	/**
	 * left and right (sides)
	 * 
	 * @return the sagittalShift
	 */
	public float getSagittalShift() {
		return sagittalShift;
	}

	/**
	 * dorsoventral (back and front)
	 * 
	 * @return the coronalShift
	 */
	public float getCoronalShift() {
		return coronalShift;
	}

	/**
	 * @return the angle
	 */
	public float getAngle() {
		return angle;
	}

}
