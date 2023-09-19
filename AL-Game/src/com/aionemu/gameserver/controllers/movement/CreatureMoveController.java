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

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MOVE;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ATracer
 */
public abstract class CreatureMoveController<T extends VisibleObject> implements MoveController {

	protected T owner;
	protected byte heading;
	protected long lastMoveUpdate = System.currentTimeMillis();
	protected boolean isInMove = false;
	protected transient AtomicBoolean started = new AtomicBoolean(false);

	// TODO [AT] not good ...
	public byte movementMask;
	protected float targetDestX;
	protected float targetDestY;
	protected float targetDestZ;

	public CreatureMoveController(T owner) {
		this.owner = owner;
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
	public void setNewDirection(float x, float y, float z, byte heading) {
		this.heading = heading;
		setNewDirection(x, y, z);
	}

	protected void setNewDirection(float x, float y, float z) {
		this.targetDestX = x;
		this.targetDestY = y;
		this.targetDestZ = z;
	}

	@Override
	public void startMovingToDestination() {
	}

	@Override
	public void abortMove() {
	}

	protected void setAndSendStopMove(Creature owner) {
		movementMask = MovementMask.IMMEDIATE;
		PacketSendUtility.broadcastPacket(owner, new SM_MOVE(owner));
	}

	public final void updateLastMove() {
		lastMoveUpdate = System.currentTimeMillis();
	}

	/**
	 * @return the lastMoveUpdate
	 */
	public long getLastMoveUpdate() {
		return lastMoveUpdate;
	}

	@Override
	public byte getMovementMask() {
		return movementMask;
	}

	@Override
	public boolean isInMove() {
		return isInMove;
	}

	@Override
	public void setInMove(boolean value) {
		isInMove = value;
	}

}
