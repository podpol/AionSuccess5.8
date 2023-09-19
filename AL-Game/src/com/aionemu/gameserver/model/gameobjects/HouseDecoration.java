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
package com.aionemu.gameserver.model.gameobjects;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.templates.housing.HousePart;

public class HouseDecoration extends AionObject
{
    private int templateId;
    private byte floor;
    private boolean isUsed;
    private PersistentState persistentState;
	
    public HouseDecoration(int objectId, int templateId) {
        this(objectId, templateId, -1);
    }
	
    public HouseDecoration(int objectId, int templateId, int floor) {
        super(objectId);
        this.templateId = templateId;
        this.floor = (byte) floor;
        this.persistentState = PersistentState.NEW;
    }
	
    public HousePart getTemplate() {
        return DataManager.HOUSE_PARTS_DATA.getPartById(templateId);
    }
	
    public PersistentState getPersistentState() {
        return persistentState;
    }
	
    public void setPersistentState(PersistentState persistentState) {
        this.persistentState = persistentState;
    }
	
    @Override
    public String getName() {
        return getTemplate().getName();
    }
	
    public byte getFloor() {
        return floor;
    }
	
    public void setFloor(int value) {
        if (value != floor) {
            floor = (byte) value;
            if (persistentState != PersistentState.NEW && persistentState != PersistentState.NOACTION) {
                persistentState = PersistentState.UPDATE_REQUIRED;
            }
        }
    }
	
    public boolean isUsed() {
        return isUsed;
    }
	
    public void setUsed(boolean isUsed) {
        if (this.isUsed != isUsed && persistentState != PersistentState.DELETED) {
            this.isUsed = isUsed;
            if (persistentState != PersistentState.NEW && persistentState != PersistentState.NOACTION) {
                persistentState = PersistentState.UPDATE_REQUIRED;
            }
        }
    }
	
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HouseDecoration)) {
            return false;
        } else {
            return ((HouseDecoration) object).getObjectId().equals(this.getObjectId());
        }
    }
	
    @Override
    public int hashCode() {
        return this.getObjectId();
    }
}