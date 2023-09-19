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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aionemu.gameserver.model.cp.PlayerCPEntry;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.player.CreativityPanel.CreativitySkillService;
import com.aionemu.gameserver.services.player.CreativityPanel.CreativityTransfoService;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Agility;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Health;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Knowledge;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Power;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Precision;
import com.aionemu.gameserver.services.player.CreativityPanel.stats.Will;

/**
 * @author Falke_34, FrozenKiller
 * @Rework By Xnemonix
 */
public class SM_CREATIVITY_POINTS extends AionServerPacket {

	Logger log = LoggerFactory.getLogger(SM_CREATIVITY_POINTS.class);
	private int totalPoint;
	@SuppressWarnings("unused")
	private int dotStep;
	private int size;
	private boolean onLogin;

	public SM_CREATIVITY_POINTS(int totalPoint, int dotStep) {
		this.totalPoint = totalPoint;
		this.dotStep = dotStep;
	}

	public SM_CREATIVITY_POINTS(int dotStep) {
		this.dotStep = dotStep;
	}

	public SM_CREATIVITY_POINTS(int totalPoint, int dotStep, int size, boolean onLogin) {
		this.totalPoint = totalPoint;
		this.dotStep = dotStep;
		this.size = size;
		this.onLogin = onLogin;
	}
    private int CpByExp(int level){
        switch (level){
            case 66:
                return 1+1;
            case 67:
                return 10+10;
            case 68:
                return 19+15;
            case 69:
                return 30+20;
            case 70:
                return 42+25;
            case 71:
                return 55+30;
            case 72:
                return 70+35;
            case 73:
                return 87+40;
            case 74:
                return 107+45;
            case 75:
                return 130+155;


        }
        return 285;
    }
	@Override
	protected void writeImpl(AionConnection con) {
		Player activePlayer = con.getActivePlayer();

		writeD(totalPoint); // Creativity Points Total
		writeD(CpByExp(activePlayer.getLevel()));
		writeH(size);
		if (onLogin) {
			for (PlayerCPEntry ce : activePlayer.getCP().getAllCP()) {
				writeD(ce.getSlot());
				writeH(ce.getPoint());
				if (ce.getSlot() == 1) {
					activePlayer.setCPSlot1(ce.getPoint());
					Power.getInstance().onChange(activePlayer, ce.getPoint());
				}
				if (ce.getSlot() == 2) {
					activePlayer.setCPSlot2(ce.getPoint());
					Health.getInstance().onChange(activePlayer, ce.getPoint());
				}
				if (ce.getSlot() == 3) {
					activePlayer.setCPSlot3(ce.getPoint());
					Agility.getInstance().onChange(activePlayer, ce.getPoint());
				}
				if (ce.getSlot() == 4) {
					activePlayer.setCPSlot4(ce.getPoint());
					Precision.getInstance().onChange(activePlayer, ce.getPoint());
				}
				if (ce.getSlot() == 5) {
					activePlayer.setCPSlot5(ce.getPoint());
					Knowledge.getInstance().onChange(activePlayer, ce.getPoint());
				}
				if (ce.getSlot() == 6) {
					activePlayer.setCPSlot6(ce.getPoint());
					Will.getInstance().onChange(activePlayer, ce.getPoint());
				}
				/*if (ce.getSlot() >= 15 && ce.getSlot() <= 372 && ce.getPoint() != 0) { // Reduce Server Send Packet. its handled on login
					CreativitySkillService.getInstance().enchantSkill(activePlayer, ce.getSlot(), ce.getPoint());
				}
				CreativityTransfoService.getInstance().enchantTransfo(activePlayer, ce.getSlot(), ce.getPoint());
				if (ce.getSlot() >= 373 && ce.getSlot() <= 400) {
					CreativitySkillService.getInstance().loginDaevaSkill(activePlayer, ce.getSlot(), ce.getPoint());
				}*/
			}
		}
		else {
			for (PlayerCPEntry ce : activePlayer.getCP().getAllCP()) {
				if (ce.getSlot() == 1) {
					activePlayer.setCPSlot1(ce.getPoint());
				}
				if (ce.getSlot() == 2) {
					activePlayer.setCPSlot2(ce.getPoint());
				}
				if (ce.getSlot() == 3) {
					activePlayer.setCPSlot3(ce.getPoint());
				}
				if (ce.getSlot() == 4) {
					activePlayer.setCPSlot4(ce.getPoint());
				}
				if (ce.getSlot() == 5) {
					activePlayer.setCPSlot5(ce.getPoint());
				}
				if (ce.getSlot() == 6) {
					activePlayer.setCPSlot6(ce.getPoint());
				}
			}
		}
	}
}
