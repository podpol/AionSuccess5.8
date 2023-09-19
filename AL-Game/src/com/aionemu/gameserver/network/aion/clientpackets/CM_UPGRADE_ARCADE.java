package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.events.ArcadeUpgradeService;

/**
 * @author Ranastic
 */

public class CM_UPGRADE_ARCADE extends AionClientPacket
{
	private int action;
	@SuppressWarnings("unused")
	private int sessionId;
	
	public CM_UPGRADE_ARCADE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		action = readC();
		sessionId = readD();
	}
	
	@Override
	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		if (player == null) {
			return;
		} switch(action) {
			case 0:
				ArcadeUpgradeService.getInstance().startArcadeUpgrade(player);
			break;
			case 1:
				ArcadeUpgradeService.getInstance().closeWindow(player);
			break;
			case 2:
				ArcadeUpgradeService.getInstance().tryArcadeUpgrade(player);
			break;
			case 3:
				ArcadeUpgradeService.getInstance().getReward(player);
			break;
			case 4:
				player.getUpgradeArcade().setReTry(true);
				ArcadeUpgradeService.getInstance().tryArcadeUpgrade(player);
			break;
			case 5:
				ArcadeUpgradeService.getInstance().showRewardList(player);
			break;
			default:
			break;
		}
	}
}