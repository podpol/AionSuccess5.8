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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.panel_cp.PanelCp;
import com.aionemu.gameserver.model.templates.panel_cp.PanelCpType;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_STATS_INFO;
import com.aionemu.gameserver.services.player.CreativityPanel.CreativityEssenceService;
import com.aionemu.gameserver.services.player.CreativityPanel.CreativitySkillService;
import com.aionemu.gameserver.services.player.CreativityPanel.CreativityStatsService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Falke_34
 */
public class CM_CREATIVITY_POINTS extends AionClientPacket {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CM_CREATIVITY_POINTS.class);

	private Player activePlayer;
	private int type;
	private int plusSize;
	private int id;
	private int point;

	public CM_CREATIVITY_POINTS(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		activePlayer = getConnection().getActivePlayer();
		type = readC();
		switch (type) {
			case 0: // Apply
				plusSize = readH();
				for (int i = 0; i < plusSize; i++) {
					id = readD();
					point = readH();
                    PanelCp pcp = DataManager.PANEL_CP_DATA.getPanelCpId(id);

                    if (pcp.getPanelCpType() == PanelCpType.STAT_UP){
                        if (point <= 255) {
                            CreativityStatsService.getInstance().onEssenceApply(activePlayer, type, plusSize, id, point);
                        }
                        else if (point > 255) {
                            PacketSendUtility.sendBrightYellowMessageOnCenter(activePlayer, "Essence bug detected... Please reset points or relog for solv this issue!");
                        }
                    }else if(pcp.getPanelCpType() == PanelCpType.LEARN_SKILL){
                        CreativitySkillService.getInstance().learnSkill(activePlayer, id, point);
                    }else if (pcp.getPanelCpType() == PanelCpType.ENCHANT_SKILL){
                    	if(point > pcp.getCountMax()){
							log.warn("Allocated essence bug on enchant skill, allowed max point: " + pcp.getCountMax() + " Player Point: " + point + "Essence ID: " + id + " Player Name: " + activePlayer.getName());
                    		return;
						}
                        CreativitySkillService.getInstance().enchantSkill(activePlayer, id, point);
                    }
				}
				PacketSendUtility.sendPacket(activePlayer, new SM_STATS_INFO(activePlayer));
				break;
			case 1: // Reset
				plusSize = readH();
				break;
			default:
				break;
		}
	}

	@Override
	protected void runImpl() {
		if (activePlayer == null) {
			return;
		}
		if (activePlayer.getLifeStats().isAlreadyDead()) {
			return;
		}
		if (type == 1) {
			CreativityEssenceService.getInstance().onResetEssence(activePlayer, plusSize);
		}
	}
}
