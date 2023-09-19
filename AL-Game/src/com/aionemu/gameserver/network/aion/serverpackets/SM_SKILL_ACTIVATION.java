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
package com.aionemu.gameserver.network.aion.serverpackets;


import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Sweetkr
 */
public class SM_SKILL_ACTIVATION extends AionServerPacket {

	private boolean isActive;
	private int unk;
	private int skillId;

	/**
	 * For toggle skills
	 * 
	 * @param skillId
	 * @param isActive
	 */
	public SM_SKILL_ACTIVATION(int skillId, boolean isActive) {
		this.skillId = skillId;
		this.isActive = isActive;
		this.unk = 0;
	}

	/**
	 * For stigma remove should work in 1.5.1.15
	 * 
	 * @param skillId
	 */
	public SM_SKILL_ACTIVATION(int skillId) {
		this.skillId = skillId;
		this.isActive = true;
		this.unk = 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		writeH(skillId);
		writeD(unk);
		writeC(isActive ? 1 : 0);
	}
}
