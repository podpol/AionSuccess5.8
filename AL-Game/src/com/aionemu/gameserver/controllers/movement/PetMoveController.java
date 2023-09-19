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
package com.aionemu.gameserver.controllers.movement;

import com.aionemu.gameserver.model.gameobjects.Pet;

/**
 * @author ATracer
 */
public class PetMoveController extends CreatureMoveController<Pet> {

	protected float targetDestX;
	protected float targetDestY;
	protected float targetDestZ;
	protected byte heading;
	protected byte movementMask;

	public PetMoveController() {
		super(null);// not used yet
	}

	@Override
	public void moveToDestination() {
	}

	@Override
	public float getTargetX2() {
		return targetDestX;
	}

	@Override
	public float getTargetY2() {
		return targetDestY;
	}

	@Override
	public float getTargetZ2() {
		return targetDestZ;
	}

	@Override
	public void setNewDirection(float x2, float y2, float z2) {
		setNewDirection(x2, y2, z2, (byte) 0);
	}

	@Override
	public void setNewDirection(float x, float y, float z, byte heading) {
		this.targetDestX = x;
		this.targetDestY = y;
		this.targetDestZ = z;
		this.heading = heading;
	}

	@Override
	public void startMovingToDestination() {
	}

	@Override
	public void abortMove() {
	}

	@Override
	public byte getMovementMask() {
		return movementMask;
	}

	@Override
	public boolean isInMove() {
		return true;
	}

	@Override
	public void setInMove(boolean value) {
	}

	@Override
	public void skillMovement() {
		this.movementMask = MovementMask.IMMEDIATE;

	}
}
