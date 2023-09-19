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

import com.aionemu.gameserver.model.skinskill.SkillSkin;
import com.aionemu.gameserver.model.skinskill.SkillSkinList;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author Ghostfur (Aion-Unique)
 * @rework FrozenKiller
 */
public class SM_SKILL_ANIMATION extends AionServerPacket {
    private SkillSkinList skillSkinList;
    private int action;
    private int skillSkinId;
    private int expire;
    private int isActive;

    public SM_SKILL_ANIMATION(int skillSkinId, int expire) {
        action = 0;
        this.skillSkinId = skillSkinId;
        this.expire = expire;
        isActive = 1;
    }

    public SM_SKILL_ANIMATION(Player player) {
        action = 1;
        skillSkinList = player.getSkillSkinList();
    }

    protected void writeImpl(AionConnection con) {
        writeC(action);
        switch (action) {
            case 0:
                writeH(1);
                writeH(skillSkinId);
                writeD(expire);
                writeC(isActive);
                break;
            case 1:
                if (skillSkinList != null) {
                    writeH(skillSkinList.size());
                    for (SkillSkin skillSkin : skillSkinList.getSkillSkins()) {
                        writeH(skillSkin.getId());
                        writeD(skillSkin.getExpireTime());
                        writeC(skillSkin.getIsActive());
                    }
                }
                break;
            default:
                break;
        }
    }
}