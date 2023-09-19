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

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * Created by Ghostfur
 */
public class SM_SHUGO_SWEEP extends AionServerPacket {

	private int tableId;
	private int currentStep;
	private int diceLeft;
	private int diceGolden;
	private int unkButton;
	private int moveStep;

	@SuppressWarnings("unused")
	private int unk;

	// sweep player infos
	public SM_SHUGO_SWEEP(int tableId, int currentStep, int diceLeft, int diceGolden, int unkButton, int moveStep) {
		this.currentStep = currentStep;
		this.diceLeft = diceLeft;
		this.diceGolden = diceGolden;
		this.unkButton = unkButton;
		this.moveStep = moveStep;
		this.tableId = tableId;
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeD(tableId); // table id
		writeD(currentStep); // current step
		writeH(0); // reward ??
		writeH(0); // reward ??
		writeD(0);
		writeD(diceLeft); // dice left
		writeD(diceGolden); // dice golden
		writeD(unkButton); // button near dice left
		writeD(432000);
		writeD(0);
		writeD(432000);
		writeD(0);
		writeD(moveStep); // move step
	}
}