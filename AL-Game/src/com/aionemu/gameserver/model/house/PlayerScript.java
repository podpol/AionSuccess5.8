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
package com.aionemu.gameserver.model.house;

import com.aionemu.commons.taskmanager.AbstractLockManager;

public final class PlayerScript extends AbstractLockManager
{
    public PlayerScript() {
    }
	
    public PlayerScript(byte[] compressedBytes, int uncompressedSize) {
        this.compressedBytes = compressedBytes;
        this.uncompressedSize = uncompressedSize;
    }
	
    private int uncompressedSize = -1;
    private byte[] compressedBytes = null;
	
    public int getUncompressedSize() {
        return uncompressedSize;
    }
	
    public byte[] getCompressedBytes() {
        return compressedBytes;
    }
	
    public void setData(byte[] compressedBytes, int uncompressedSize) {
        writeLock();
        this.compressedBytes = compressedBytes;
        this.uncompressedSize = uncompressedSize;
        writeUnlock();
    }
}