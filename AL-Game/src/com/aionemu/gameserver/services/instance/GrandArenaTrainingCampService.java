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
package com.aionemu.gameserver.services.instance;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.services.CronService;
import com.aionemu.gameserver.configs.main.AutoGroupConfig;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_AUTO_GROUP;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.AutoGroupService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import javolution.util.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/****/
/** Author Rinzler (Encom)
 /****/

public class GrandArenaTrainingCampService
{
    private static final Logger log = LoggerFactory.getLogger(GrandArenaTrainingCampService.class);
    private boolean registerAvailable;
    private final FastList<Integer> playersWithCooldown = FastList.newInstance();
    public static final byte minLevel = 66, capLevel = 76;
    public static final int maskId = 127;

    public void initGrandArenaTrainingCamp() {
        if (AutoGroupConfig.GRAND_ARENA_TRAINING_CAMP_ENABLED) {
            log.info("Grand Arena Training Camp 5.6");
            //IDTM_LobbyP01 SAT-SUN "6PM-0AM"
            CronService.getInstance().schedule(new Runnable() {
                @Override
                public void run() {
                    startGrandArenaTrainingCampRegistration();
                }
            }, AutoGroupConfig.GRAND_ARENA_TRAINING_CAMP_SCHEDULE_EVENING);
        }
    }

    private void startUregisterGrandArenaTrainingCampTask() {
        ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                registerAvailable = false;
                playersWithCooldown.clear();
                AutoGroupService.getInstance().unRegisterInstance(maskId);
                Iterator<Player> iter = World.getInstance().getPlayersIterator();
                while (iter.hasNext()) {
                    Player player = iter.next();
                    if (player.getLevel() > minLevel) {
                        int instanceMaskId = getInstanceMaskId(player);
                        if (instanceMaskId > 0) {
                            PacketSendUtility.sendPacket(player, new SM_AUTO_GROUP(instanceMaskId, SM_AUTO_GROUP.wnd_EntryIcon, true));
                        }
                    }
                }
            }
        }, AutoGroupConfig.GRAND_ARENA_TRAINING_CAMP_TIMER * 60 * 1000);
    }

    private void startGrandArenaTrainingCampRegistration() {
        this.registerAvailable = true;
        startUregisterGrandArenaTrainingCampTask();
        Iterator<Player> iter = World.getInstance().getPlayersIterator();
        while (iter.hasNext()) {
            Player player = iter.next();
            if (player.getLevel() > minLevel && player.getLevel() < capLevel) {
                int instanceMaskId = getInstanceMaskId(player);
                if (instanceMaskId > 0) {
                    PacketSendUtility.sendPacket(player, new SM_AUTO_GROUP(instanceMaskId, SM_AUTO_GROUP.wnd_EntryIcon));
                    //황금의 템페르 훈련소 파티전에 참가할 수 있습니다.
                    PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_INSTANCE_OPEN_IDTM_Lobbyp_01);
                }
            }
        }
    }

    public boolean isGrandArenaTrainingCampAvailable() {
        return this.registerAvailable;
    }

    public byte getInstanceMaskId(Player player) {
        int level = player.getLevel();
        if (level < minLevel || level >= capLevel) {
            return 0;
        }
        return maskId;
    }

    public void addCoolDown(Player player) {
        this.playersWithCooldown.add(player.getObjectId());
    }

    public boolean hasCoolDown(Player player) {
        return this.playersWithCooldown.contains(player.getObjectId());
    }

    public void showWindow(Player player, byte instanceMaskId) {
        if (getInstanceMaskId(player) != instanceMaskId) {
            return;
        } if (!this.playersWithCooldown.contains(player.getObjectId())) {
            PacketSendUtility.sendPacket(player, new SM_AUTO_GROUP(instanceMaskId));
        }
    }

    private static class SingletonHolder {
        protected static final GrandArenaTrainingCampService instance = new GrandArenaTrainingCampService();
    }

    public static GrandArenaTrainingCampService getInstance() {
        return SingletonHolder.instance;
    }
}