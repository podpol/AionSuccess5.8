package com.aionemu.gameserver.model.gameobjects.player;

import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.dao.PlayerShugoSweepDAO;
import com.aionemu.gameserver.model.gameobjects.PersistentState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wnkrz on 23/10/2017.
 */

public class PlayerSweep
{
    Logger log = LoggerFactory.getLogger(PlayerSweep.class);
    private PersistentState persistentState;
	
    private int step;
    private int freeDice;
    private int boardId;
	
    public PlayerSweep(int step, int freeDice, int boardId) {
        this.step = step;
        this.freeDice = freeDice;
        this.boardId = boardId;
        this.persistentState = PersistentState.NEW;
    }
	
    public PlayerSweep() {
    }
	
    public int getFreeDice() {
        return freeDice;
    }
	
    public void setFreeDice(int dice) {
        this.freeDice = dice;
    }
	
    public int getStep() {
        return step;
    }
	
    public void setStep(int step) {
        this.step = step;
    }
	
    public int getBoardId() {
        return boardId;
    }
	
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
	
    public PersistentState getPersistentState() {
        return persistentState;
    }
	
    public void setShugoSweepByObjId(int playerId) {
        DAOManager.getDAO(PlayerShugoSweepDAO.class).setShugoSweepByObjId(playerId, getFreeDice(), getStep(), getBoardId());
    }
	
    public void setPersistentState(PersistentState persistentState) {
        switch (persistentState) {
            case UPDATE_REQUIRED:
                if (this.persistentState == PersistentState.NEW)
				break;
            default:
                this.persistentState = persistentState;
        }
    }
}