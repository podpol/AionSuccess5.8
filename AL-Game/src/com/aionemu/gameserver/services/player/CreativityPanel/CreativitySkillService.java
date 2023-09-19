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
package com.aionemu.gameserver.services.player.CreativityPanel;

import com.aionemu.gameserver.GameServer;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.panel_cp.PanelCp;
import com.aionemu.gameserver.network.aion.serverpackets.SM_CREATIVITY_POINTS_APPLY;
import com.aionemu.gameserver.services.SkillLearnService;
import com.aionemu.gameserver.utils.PacketSendUtility;

public class CreativitySkillService {

	public void enchantSkill(Player player, int id, int point) {
		PanelCp pcp = DataManager.PANEL_CP_DATA.getPanelCpId(id);
		if (point == 0) {
			player.getSkillList().addSkill(player, pcp.getSkillId(), 1);
			player.getCP().removePoint(player, id);
		} else {
		    if (pcp.getSkillId() <= 0){
                player.getSkillList().addSkill(player, pcp.getLearnSkill(), point + 1);
            }else{
                player.getSkillList().addSkill(player, pcp.getSkillId(), point + 1);
            }

			player.getCP().addPoint(player, id, point);
		}
        PacketSendUtility.sendPacket(player, new SM_CREATIVITY_POINTS_APPLY(0,1, id, point));
	}

	public void learnSkill(Player player, int id, int point) { // TODO
		PanelCp pcp = DataManager.PANEL_CP_DATA.getPanelCpId(id);
        if (point >= 1) {
            player.getSkillList().addSkill(player, pcp.getLearnSkill(), point + 1);
            player.getCP().addPoint(player, id, point);
        }
        else if (point == 0) {
			SkillLearnService.removeSkill(player, pcp.getLearnSkill());
            player.getCP().removePoint(player, id);
        }
        PacketSendUtility.sendPacket(player, new SM_CREATIVITY_POINTS_APPLY(1, 1, id, point));
	}

	public void loginDaevaSkill(Player player, int id, int point) {
		PanelCp pcp = DataManager.PANEL_CP_DATA.getPanelCpId(id);
		if (point >= 1) {
			player.getSkillList().addSkill(player, pcp.getSkillId(), point + 1);
			player.getCP().addPoint(player, id, point);
		}
		else if (point == 0) {
			player.getSkillList().addSkill(player, pcp.getSkillId(), 1);
			player.getCP().removePoint(player, id);
		}
        PacketSendUtility.sendPacket(player, new SM_CREATIVITY_POINTS_APPLY(id, point));
	}

	public static CreativitySkillService getInstance() {
		return NewSingletonHolder.INSTANCE;
	}

	private static class NewSingletonHolder {

		private static final CreativitySkillService INSTANCE = new CreativitySkillService();
	}
}
