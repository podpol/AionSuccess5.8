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
package com.aionemu.gameserver.model.siege;

import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.Race;

/**
 * @author Sarynth
 */
public enum SiegeRace {
	ELYOS(0, 1800481),
	ASMODIANS(1, 1800483),
	BALAUR(2, 1800485);

	private int raceId;
	private DescriptionId descriptionId;
	
	private SiegeRace(int id, int descriptionId) {
		this.raceId = id;
		this.descriptionId = new DescriptionId(descriptionId);
	}

	public int getRaceId() {
		return this.raceId;
	}

	public static SiegeRace getByRace(Race race){
		switch (race){
			case ASMODIANS:
				return SiegeRace.ASMODIANS;
			case ELYOS:
				return SiegeRace.ELYOS;
			default:
				return SiegeRace.BALAUR;
		}
	}

	
	/**
	 * @return the descriptionId
	 */
	public DescriptionId getDescriptionId() {
		return descriptionId;
	}
}
