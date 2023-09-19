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
package com.aionemu.gameserver.services.toypet;

/**
 * @author Rolandas
 */
public enum PetHungryLevel {
	HUNGRY(0),
	CONTENT(1),
	SEMIFULL(2),
	FULL(3);

	private byte value;

	PetHungryLevel(int value) {
		this.value = (byte) value;
	}

	/**
	 * @return the value
	 */
	public byte getValue() {
		return value;
	}
	
	public PetHungryLevel getNextValue() {
		byte levelValue = value;
		switch (levelValue) {
			case 0:
				return CONTENT;
			case 1:
				return SEMIFULL;
			case 2:
				return FULL;
			case 3:
				return HUNGRY;
			default:
				return HUNGRY;
		}
	}
	
	public static PetHungryLevel fromId(int value) {
		return PetHungryLevel.values()[value];
	}

}
