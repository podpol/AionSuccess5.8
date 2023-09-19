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
 * @author -orz-, MrPoke
 */
public class SM_PLAY_MOVIE extends AionServerPacket {

	private int type = 1; // if 1: CutSceneMovies else CutScenes
	private int movieId = 0;
	private int id = 0; // id scene ?
	private int restrictionId;
	private int objectId;

	public SM_PLAY_MOVIE(int type, int movieId) {
		this.type = type;
		this.movieId = movieId;	
	}

	public SM_PLAY_MOVIE(int type, int id, int movieId, int restrictionId) {
		this(type, movieId);
		this.id = id;
		this.restrictionId = restrictionId;
	}

	public SM_PLAY_MOVIE(int type, int id, int movieId, int restrictionId, int objectId) {
		this(type, id, movieId, restrictionId);
		this.objectId = objectId;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeC(type);
		writeD(objectId);
		writeD(id);
		writeH(movieId);
		writeD(restrictionId);
	}
}
