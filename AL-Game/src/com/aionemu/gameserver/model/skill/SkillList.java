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
package com.aionemu.gameserver.model.skill;

import com.aionemu.gameserver.model.gameobjects.Creature;

/**
 * @author ATracer
 */
public interface SkillList<T extends Creature> {

	/**
	 * Add skill to list
	 * 
	 * @return true if operation was successful
	 */
	boolean addSkill(T creature, int skillId, int skillLevel);
	
	boolean addLinkedSkill(T creature, int skillId);

	/**
	 * Remove skill from list
	 * 
	 * @return true if operation was successful
	 */
	boolean removeSkill(int skillId);

	/**
	 * Check whether skill is present in list
	 */
	boolean isSkillPresent(int skillId);

	int getSkillLevel(int skillId);

	/**
	 * Size of skill list
	 */
	int size();

}
