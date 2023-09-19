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

/**
 * @author FrozenKiller
 */
public class CM_SKILL_ANIMATION extends AionClientPacket {

    private int SkillId;
    private int SkillSkinId;

    public CM_SKILL_ANIMATION(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }

    protected void readImpl() {
        SkillId = readH();
        SkillSkinId = readH();
    }

    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        if (SkillSkinId > 0) {
            player.getSkillSkinList().setActive(SkillSkinId);
        } else {
            player.getSkillSkinList().setDeactive(SkillId);
        }
    }
}
