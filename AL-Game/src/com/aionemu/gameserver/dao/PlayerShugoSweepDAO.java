package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * Created by Wnkrz on 24/10/2017.
 */

public abstract class PlayerShugoSweepDAO implements DAO
{
    @Override
    public String getClassName() {
        return PlayerShugoSweepDAO.class.getName();
    }
	
    public abstract void load(Player player);
    public abstract boolean add(final int playerId, int freeDice, int step, int boardId);
    public abstract boolean delete();
    public abstract boolean store(Player player);
    public abstract boolean setShugoSweepByObjId(final int obj, int freeDice, int step, int boardId);
}