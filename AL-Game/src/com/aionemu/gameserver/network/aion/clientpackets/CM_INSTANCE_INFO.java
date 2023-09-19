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
import com.aionemu.gameserver.network.aion.serverpackets.SM_INSTANCE_INFO;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CM_INSTANCE_INFO extends AionClientPacket
{
    private static Logger log = LoggerFactory.getLogger(CM_INSTANCE_INFO.class);

    @SuppressWarnings("unused")
    private int unk1, unk2;

    public CM_INSTANCE_INFO(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }

    @Override
    protected void readImpl() {
        unk1 = readD();
        unk2 = readC();
    }

    @Override
    protected void runImpl() {
        if (unk2 == 1 && !getConnection().getActivePlayer().isInTeam()) {
            log.debug("Received CM_INSTANCE_INFO with teamdata request but player has no team!");
        } if (unk2 == 1) {
            Player player = getConnection().getActivePlayer();
            if (player.isInAlliance2()) {
                boolean answer = true;
                for (Player p: player.getPlayerAlliance2().getMembers()) {
                    if (answer) {
                        PacketSendUtility.sendPacket(p, new SM_INSTANCE_INFO(p, true, p.getCurrentTeam()));
                        answer = false;
                    } else {
                        PacketSendUtility.sendPacket(p, new SM_INSTANCE_INFO(p, false, p.getCurrentTeam()));
                    }
                }
            } else if (player.isInGroup2()) {
                boolean answer = true;
                for (Player p: player.getPlayerGroup2().getMembers()) {
                    if (answer) {
                        PacketSendUtility.sendPacket(p, new SM_INSTANCE_INFO(p, true, p.getCurrentTeam()));
                        answer = false;
                    } else {
                        PacketSendUtility.sendPacket(p, new SM_INSTANCE_INFO(p, false, p.getCurrentTeam()));
                    }
                }
            }
        } else {
            sendPacket(new SM_INSTANCE_INFO(getConnection().getActivePlayer(), true, getConnection().getActivePlayer().getCurrentTeam()));
        }
    }
}