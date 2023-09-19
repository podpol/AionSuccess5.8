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
package com.aionemu.gameserver.services.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.configs.main.EventsConfig;
import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.gameserver.dao.PlayerShugoSweepDAO;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.model.gameobjects.player.PlayerSweep;
import com.aionemu.gameserver.model.templates.shugosweep.ShugoSweepReward;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SHUGO_SWEEP;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/**
 * Created by Ghostfur (Aion-Unique)
 */
public class ShugoSweepService {

    private static final Logger log = LoggerFactory.getLogger(ShugoSweepService.class);
    private final int boardId = EventsConfig.EVENT_SHUGOSWEEP_BOARD;

    public void initShugoSweep() {
        log.info("[ShugoSweepService] is initialized...");
        // TODO
        // String weekly = "0 0 9 ? * WED *";
    }

    public void onLogin(Player player) {
        DAOManager.getDAO(PlayerShugoSweepDAO.class).load(player);
        if (player.getPlayerShugoSweep() == null) {
            PlayerSweep ps = new PlayerSweep(0, EventsConfig.EVENT_SHUGOSWEEP_FREEDICE, boardId);
            ps.setPersistentState(PersistentState.UPDATE_REQUIRED);
            player.setPlayerShugoSweep(ps);
            player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
            DAOManager.getDAO(PlayerShugoSweepDAO.class).add(player.getObjectId(), ps.getFreeDice(), ps.getStep(), ps.getBoardId());
        }

        if (player.getPlayerShugoSweep().getBoardId() != boardId) {
            PlayerSweep ps = new PlayerSweep(0, getPlayerSweep(player).getFreeDice(), boardId);
            ps.setPersistentState(PersistentState.UPDATE_REQUIRED);
            player.setPlayerShugoSweep(ps);
            player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
        }

        PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(getPlayerSweep(player).getBoardId(), getPlayerSweep(player).getStep(), getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), getCommonData(player).getResetBoard(), 0));
    }

    public void onLogout(Player player) {
        DAOManager.getDAO(PlayerShugoSweepDAO.class).store(player);
        player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
    }

    public void launchDice(final Player player) {
        int move = Rnd.get(1, 6);
        int step = getPlayerSweep(player).getStep();
        int newStep = step + move;
        int dice = getPlayerSweep(player).getFreeDice();
        int goldDice = getCommonData(player).getGoldenDice();
        int diff = newStep - 30;
        if (getPlayerSweep(player).getFreeDice() != 0) {
            getPlayerSweep(player).setFreeDice(dice - 1);
            player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
        } else {
            getCommonData(player).setGoldenDice(goldDice - 1);
            DAOManager.getDAO(PlayerDAO.class).storePlayer(player);
        }

        PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(boardId, getPlayerSweep(player).getStep(), getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), 0, 0));

        if (newStep > 30) {
            System.out.println("Step > 30: " + step + " Move: " + move + " NewStep: " + newStep);
            getPlayerSweep(player).setStep(newStep);
            PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(getPlayerSweep(player).getBoardId(), getPlayerSweep(player).getStep(), getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), getCommonData(player).getResetBoard(), move));
            getPlayerSweep(player).setStep(diff);
            rewardPlayer(player, getPlayerSweep(player).getStep(), diff);
            player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
        } else if (newStep == 30) {
            System.out.println("Step = 30: " + step + " Move: " + move + " NewStep: " + newStep);
            getPlayerSweep(player).setStep(newStep);
            PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(getPlayerSweep(player).getBoardId(), getPlayerSweep(player).getStep(), getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), getCommonData(player).getResetBoard(), move));
            rewardPlayer(player, getPlayerSweep(player).getStep(), newStep);
            player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
        } else {
            System.out.println("Step normal " + step + " Move: " + move + " NewStep: " + newStep);
            getPlayerSweep(player).setStep(newStep);
            player.getPlayerShugoSweep().setShugoSweepByObjId(player.getObjectId());
            PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(getPlayerSweep(player).getBoardId(), getPlayerSweep(player).getStep(), getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), getCommonData(player).getResetBoard(), move));
            rewardPlayer(player, getPlayerSweep(player).getStep(), move);
        }
    }

    public void resetBoard(Player player) {
        int reset = getCommonData(player).getResetBoard();
        getCommonData(player).setResetBoard(reset - 1);
        getPlayerSweep(player).setStep(0);
        PacketSendUtility.sendPacket(player, new SM_SHUGO_SWEEP(getPlayerSweep(player).getBoardId(), 0, getPlayerSweep(player).getFreeDice(), getCommonData(player).getGoldenDice(), getCommonData(player).getResetBoard(), 0));
    }

    private void rewardPlayer(final Player player, final int step, final int move) {
        ThreadPoolManager.getInstance().schedule(new Runnable() {
            @Override
            public void run() {
                if (player.isOnline()) {
                    ShugoSweepReward reward = getRewardForBoard(boardId, step);
                    ItemService.addItem(player, reward.getItemId(), reward.getCount());
                }
            }
        }, move * 1200);

    }

    private PlayerCommonData getCommonData(Player player) {
        return player.getCommonData();
    }

    private PlayerSweep getPlayerSweep(Player player) {
        return player.getPlayerShugoSweep();
    }

    private static ShugoSweepReward getRewardForBoard(int boardId, int step) {
        return DataManager.SHUGO_SWEEP_REWARD_DATA.getRewardBoard(boardId, step);
    }

    public static final ShugoSweepService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        protected static final ShugoSweepService instance = new ShugoSweepService();
    }
}