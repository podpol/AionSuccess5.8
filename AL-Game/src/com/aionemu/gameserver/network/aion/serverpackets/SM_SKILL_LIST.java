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
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.skill.PlayerSkillEntry;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

public class SM_SKILL_LIST extends AionServerPacket
{
	private PlayerSkillEntry[] skillList;
	private int messageId;
	private int skillNameId;
	private String skillLvl;
	public static final int YOU_LEARNED_SKILL = 1300050;
	boolean isNew = false;
	private Player player;
	private int state;
	
	public SM_SKILL_LIST(Player player, PlayerSkillEntry[] basicSkills) {
		this.player = player;
		this.skillList = player.getSkillList().getBasicSkills();
		this.messageId = 0;
	}
	
	public SM_SKILL_LIST(Player player, PlayerSkillEntry[] linkedSkills, int state) {
		this.player = player;
		this.skillList = player.getSkillList().getLinkedSkills();
		this.state = state;
		this.messageId = 0;
		this.isNew = true;
	}
	
	public SM_SKILL_LIST(Player player, PlayerSkillEntry stigmaSkill) {
		this.skillList = new PlayerSkillEntry[] { stigmaSkill };
		this.messageId = 0;
	}
	
	public SM_SKILL_LIST(PlayerSkillEntry skillListEntry, int messageId, boolean isNew) {
		this.skillList = new PlayerSkillEntry[] { skillListEntry };
		this.messageId = messageId;
		this.skillNameId = DataManager.SKILL_DATA.getSkillTemplate(skillListEntry.getSkillId()).getNameId();
		this.skillLvl = String.valueOf(skillListEntry.getSkillLevel());
		this.isNew = isNew;
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		final int size = skillList.length;
		writeH(size);
		if (isNew) {
            writeC(0);
		} else {
            writeC(1);
		} if (size > 0) {
			for (PlayerSkillEntry entry : skillList) {
				writeH(entry.getSkillId());
				writeH(entry.getSkillLevel());
				writeC(0x00);
				int extraLevel = entry.getExtraLvl();
				writeC(extraLevel);
				if (isNew && extraLevel == 0 && !entry.isStigma()) {
					writeD((int) (System.currentTimeMillis() / 1000));
				} else {
					writeD(0);
				} if (entry.isStigma()) {
					writeC(1);
				} else if (entry.isLinked()) {
					writeC(3);
				} else {
					writeC(0);
				}
			}
		}
		writeD(messageId);
		if (messageId != 0) {
			writeH(0x24);
			writeD(skillNameId);
			writeH(0x00);
			writeS(skillLvl);
			writeH(0x00);
		}
	}
}