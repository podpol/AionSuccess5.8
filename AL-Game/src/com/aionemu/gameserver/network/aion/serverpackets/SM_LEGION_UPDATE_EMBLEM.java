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


import com.aionemu.gameserver.model.team.legion.LegionEmblemType;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Simple modified cura
 */
public class SM_LEGION_UPDATE_EMBLEM extends AionServerPacket {

	/** Legion emblem information **/
	private int legionId;
	private int emblemId;
	private int color_r;
	private int color_g;
	private int color_b;
	private LegionEmblemType emblemType;

	/**
	 * This constructor will handle legion emblem info
	 * 
	 * @param legionId
	 * @param emblemId
	 * @param color_r
	 * @param color_g
	 * @param color_b
	 * @param emblemType
	 */
	public SM_LEGION_UPDATE_EMBLEM(int legionId, int emblemId, int color_r, int color_g, int color_b,
		LegionEmblemType emblemType) {
		this.legionId = legionId;
		this.emblemId = emblemId;
		this.color_r = color_r;
		this.color_g = color_g;
		this.color_b = color_b;
		this.emblemType = emblemType;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(legionId);
		writeC(emblemId);
		writeC(emblemType.getValue());
		writeC(0xFF); // Fixed
		writeC(color_r);
		writeC(color_g);
		writeC(color_b);
	}
}
