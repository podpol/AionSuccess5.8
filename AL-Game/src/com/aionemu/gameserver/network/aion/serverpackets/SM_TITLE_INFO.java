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


import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.title.Title;
import com.aionemu.gameserver.model.gameobjects.player.title.TitleList;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author cura, xTz
 */
public class SM_TITLE_INFO extends AionServerPacket {

	private TitleList titleList;
	private int bonusTitleId;
	private int action; // 0: list, 1: self set, 3: broad set
	private int titleId;
	private int playerObjId;

	/**
	 * title list
	 * 
	 * @param player
	 */
	public SM_TITLE_INFO(Player player) {
		this.action = 0;
		this.titleList = player.getTitleList();
	}

	/**
	 * self title set
	 * 
	 * @param titleId
	 */
	public SM_TITLE_INFO(int titleId) {
		this.action = 1;
		this.titleId = titleId;
	}

	/**
	 * broad title set
	 * 
	 * @param player
	 * @param titleId
	 */
	public SM_TITLE_INFO(Player player, int titleId) {
		this.action = 3;
		this.playerObjId = player.getObjectId();
		this.titleId = titleId;
	}

	public SM_TITLE_INFO(boolean flag) {
		this.action = 4;
		this.titleId = flag ? 1 : 0;
	}
	
	public SM_TITLE_INFO(Player player, boolean flag) {
		this.action = 5;
		this.playerObjId = player.getObjectId();
		this.titleId = flag ? 1 : 0;
	}

	public SM_TITLE_INFO(int action, int bonusTitleId) {
        this.action = action;
        this.bonusTitleId = bonusTitleId;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionConnection con) {
		writeC(action);
		switch (action) {
			case 0:
				writeC(0x00);
				writeC(0x01);//5.0
				writeH(titleList.size());
				for (Title title : titleList.getTitles()) {
					writeD(title.getId());
					writeD(title.getRemainingTime());
				}
				break;
			case 1: // self set
				writeH(titleId);
				break;
			case 3: // broad set
				writeD(playerObjId);
				writeH(titleId);
				break;
			case 4: // Mentor flag self
				writeH(titleId);
				break;
			case 5: // broad set mentor fleg
				writeD(playerObjId);
				writeH(titleId);
				break;
			case 6: // bonus title
                writeH(bonusTitleId);
                break;
		}
	}
}
