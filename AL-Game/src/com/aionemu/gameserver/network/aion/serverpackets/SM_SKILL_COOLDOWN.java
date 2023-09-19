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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

import java.util.ArrayList;
import java.util.Map;

public class SM_SKILL_COOLDOWN extends AionServerPacket
{
    private Map<Integer, Long> cooldowns;

    public SM_SKILL_COOLDOWN(Map<Integer, Long> cooldowns) {
        this.cooldowns = cooldowns;
    }
    
	@Override
    protected void writeImpl(AionConnection con) {
    	writeH(calculateSize());
        writeC(1);
        long currentTime = System.currentTimeMillis();
        for (Map.Entry<Integer, Long> entry : cooldowns.entrySet()) {
            int left = (int) ((entry.getValue() - currentTime) / 1000);
            ArrayList<Integer> skillsWithCooldown = DataManager.SKILL_DATA.getSkillsForDelayId(entry.getKey());
            for (int index = 0; index < skillsWithCooldown.size(); index++) {
                int skillId = skillsWithCooldown.get(index);
                writeH(skillId);
                writeD(left > 0 ? left : 0);
                writeD(DataManager.SKILL_DATA.getSkillTemplate(skillId).getCooldown());
            }
        }
    }
	
	private int calculateSize() {
        int size = 0;
        for (Map.Entry<Integer, Long> entry : cooldowns.entrySet()) {
            size += DataManager.SKILL_DATA.getSkillsForDelayId(entry.getKey()).size();
        }
        return size;
    }
}