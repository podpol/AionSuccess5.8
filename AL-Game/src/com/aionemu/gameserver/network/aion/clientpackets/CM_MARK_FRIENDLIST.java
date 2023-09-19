package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_FRIEND_LIST;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MARK_FRIENDLIST;

/**
 * 
 * @developer_note_dont_remove goi ra khi su dung teleport toi nha ban be trong housing
 *
 */
public class CM_MARK_FRIENDLIST extends AionClientPacket
{
	public CM_MARK_FRIENDLIST(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
	}
	
	@Override
	protected void runImpl() {
		final Player activePlayer = getConnection().getActivePlayer();
		if (activePlayer != null) {
			if (!activePlayer.getFriendList().getIsFriendListSent())
			getConnection().sendPacket(new SM_FRIEND_LIST());
			getConnection().sendPacket(new SM_MARK_FRIENDLIST());
		}
	}
}