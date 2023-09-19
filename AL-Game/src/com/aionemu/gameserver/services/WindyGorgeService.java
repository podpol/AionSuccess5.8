package com.aionemu.gameserver.services;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EVERGALE_CANYON;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * Created by Wnkrz on 11/08/2017.
 */

public class WindyGorgeService
{
    public void onLogin(Player player){
        PacketSendUtility.sendPacket(player, new SM_EVERGALE_CANYON(2));
        PacketSendUtility.sendPacket(player, new SM_EVERGALE_CANYON(4));
    }
	
    public static final WindyGorgeService getInstance() {
        return SingletonHolder.instance;
    }
	
    @SuppressWarnings("synthetic-access")
    private static class SingletonHolder {
        protected static final WindyGorgeService instance = new WindyGorgeService();
    }
}