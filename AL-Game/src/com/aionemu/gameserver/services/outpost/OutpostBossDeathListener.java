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
package com.aionemu.gameserver.services.outpost;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.ai2.AbstractAI;
import com.aionemu.gameserver.ai2.eventcallback.OnDieEventCallback;
import com.aionemu.gameserver.dao.OutpostDAO;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.AionObject;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.siege.ArtifactLocation;
import com.aionemu.gameserver.model.siege.SiegeLocation;
import com.aionemu.gameserver.model.team2.TemporaryPlayerTeam;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.OutpostService;
import com.aionemu.gameserver.services.SiegeService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/**
 * Created by Wnkrz on 27/08/2017.
 */

public class OutpostBossDeathListener extends OnDieEventCallback
{
    private final Outpost<?> outpost;
	
    public OutpostBossDeathListener(Outpost outpost) {
        this.outpost = outpost;
    }
	
    @Override
    public void onBeforeDie(AbstractAI obj) {
        AionObject winner = outpost.getBoss().getAggroList().getMostDamage();
        if (winner instanceof Creature) {
            final Creature kill = (Creature) winner;
            if (kill.getRace().isPlayerRace()) {
                outpost.setRace(kill.getRace());
            }
        } else if (winner instanceof TemporaryPlayerTeam) {
            final TemporaryPlayerTeam team = (TemporaryPlayerTeam) winner;
            if (team.getRace().isPlayerRace()) {
                outpost.setRace(team.getRace());
            }
        } else {
            outpost.setRace(Race.NPC);
        }
        OutpostService.getInstance().capture(outpost.getId(), outpost.getRace());
    }
	
    @Override
    public void onAfterDie(AbstractAI obj) {
    }
	
    private OutpostDAO getDAO() {
        return DAOManager.getDAO(OutpostDAO.class);
    }
}