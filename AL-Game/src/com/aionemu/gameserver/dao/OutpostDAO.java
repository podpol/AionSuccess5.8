package com.aionemu.gameserver.dao;

import com.aionemu.commons.database.dao.DAO;
import com.aionemu.gameserver.model.outpost.OutpostLocation;

import java.util.Map;

/**
 * Created by Wnkrz on 27/08/2017.
 */

public abstract class OutpostDAO implements DAO
{
    public abstract boolean loadOutposLocations(Map<Integer, OutpostLocation> locations);
    public abstract boolean updateOutpostLocation(OutpostLocation location);
	
    public void updateLocation(final OutpostLocation location) {
        updateOutpostLocation(location);
    }
	
    @Override
    public String getClassName() {
        return OutpostDAO.class.getName();
    }
}