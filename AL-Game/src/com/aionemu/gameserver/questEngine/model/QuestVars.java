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
package com.aionemu.gameserver.questEngine.model;

/**
 * @author MrPoke
 */

public class QuestVars {

	private Integer[] questVars = new Integer[6];

	public QuestVars() {
	}

	public QuestVars(int var) {
		setVar(var);
	}

	/**
	 * @param id
	 * @return Quest var by id.
	 */
	public int getVarById(int id) {
		return questVars[id];
	}

	/**
	 * @param id
	 * @param var
	 */
	public void setVarById(int id, int var) {
		questVars[id] = var;
	}

	/**
	 * @return int value of all values, stored in the array.
	 * Representation: Sum(value_on_index_i * 64^i)
	 */
	public int getQuestVars() {
		int var = 0;
		for (int i = 5; i >= 0; i--) {
			var <<= 0x06;
			var |= questVars[i];
		}
		return var;
	}

	/**
	 * Fill the array with values, based on
	 * @param int value, represented like above
	 */
	public void setVar(int var) {
		for (int i = 0; i <= 5; i++) {
			questVars[i] = var & 0x3F;
			var >>= 0x06;
		}
	}
}
