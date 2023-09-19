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
package com.aionemu.gameserver.model.account;

/**
 * @author cura
 */
public class CharacterPasskey {

	private int objectId;
	private int wrongCount = 0;
	private boolean isPass = false;
	private ConnectType connectType;

	/**
	 * @return the objectId
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *          the objectId to set
	 */
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the wrongCount
	 */
	public int getWrongCount() {
		return wrongCount;
	}

	/**
	 * @param count
	 *          the wrongCount to set
	 */
	public void setWrongCount(int count) {
		this.wrongCount = count;
	}

	/**
	 * @return the isPass
	 */
	public boolean isPass() {
		return isPass;
	}

	/**
	 * @param isPass
	 *          the isPass to set
	 */
	public void setIsPass(boolean isPass) {
		this.isPass = isPass;
	}

	/**
	 * @return the connectType
	 */
	public ConnectType getConnectType() {
		return connectType;
	}

	/**
	 * @param connectType
	 *          the connectType to set
	 */
	public void setConnectType(ConnectType connectType) {
		this.connectType = connectType;
	}

	public enum ConnectType {
		ENTER,
		DELETE
	}
}
