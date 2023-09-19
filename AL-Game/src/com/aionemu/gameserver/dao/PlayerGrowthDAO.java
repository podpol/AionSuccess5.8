package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * Created by wanke on 26/02/2017.
 */

public abstract class PlayerGrowthDAO implements DAO
{
    @Override
    public String getClassName() {
        return PlayerGrowthDAO.class.getName();
    }
	
    public abstract void load(Player player);
    public abstract boolean add(final int playerId, boolean isFree, int rechargeCount);
    public abstract boolean delete();
    public abstract boolean store(Player player);
    public abstract boolean setGrowthByObjId(final int obj, boolean isFree, int rechargeCount);
}