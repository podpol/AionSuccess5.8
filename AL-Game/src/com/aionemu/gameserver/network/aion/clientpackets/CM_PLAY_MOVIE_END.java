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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;

public class CM_PLAY_MOVIE_END extends AionClientPacket
{
	@SuppressWarnings("unused")
    private int type;
    @SuppressWarnings("unused")
    private int targetObjectId;
    @SuppressWarnings("unused")
    private int dialogId;
    private int movieId;
    @SuppressWarnings("unused")
    private int unk;

	public CM_PLAY_MOVIE_END(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
    protected void readImpl() {
        type = readC();
        targetObjectId = readD();
        dialogId = readD();
        movieId = readH();
        unk = readD();
    }
	
	@Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        QuestEngine.getInstance().onMovieEnd(new QuestEnv(null, player, 0, 0), movieId);
        player.getPosition().getWorldMapInstance().getInstanceHandler().onPlayMovieEnd(player, movieId);
    }
}