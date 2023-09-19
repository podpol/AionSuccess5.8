package com.aionemu.gameserver.model;

import java.util.NoSuchElementException;

public enum SellLimit
{
	LIMIT_1_65(1, 65, 292000047L),
	LIMIT_66_75(66, 75, 392000047L);
	
	private int playerMinLevel;
	private int playerMaxLevel;
	private long limit;
	
	private SellLimit(int playerMinLevel, int playerMaxLevel, long limit) {
		this.playerMinLevel = playerMinLevel;
		this.playerMaxLevel = playerMaxLevel;
		this.limit = limit;
	}
	
	public static long getSellLimit(int playerLevel) {
		for (SellLimit sellLimit : values()) {
			if (sellLimit.playerMinLevel <= playerLevel && sellLimit.playerMaxLevel >= playerLevel) {
				return sellLimit.limit;
			}
		}
		throw new NoSuchElementException("Sell limit for player level: " + playerLevel + " was not found");
	}
}