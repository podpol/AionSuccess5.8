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

import com.aionemu.gameserver.model.templates.gather.GatherableTemplate;
import com.aionemu.gameserver.model.templates.gather.Material;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * @author ATracer
 * @author orz
 * @author Antraxx
 */
public class SM_GATHER_UPDATE extends AionServerPacket {

	private GatherableTemplate template;
	private int action;
	private int itemId;
	private int success;
	private int failure;
	private int nameId;

	public SM_GATHER_UPDATE(GatherableTemplate template, Material material, int success, int failure, int action) {
		this.action = action;
		this.template = template;
		this.itemId = material.getItemid();
		this.success = success;
		this.failure = failure;
		this.nameId = material.getNameid();
	}

	@Override
	protected void writeImpl(AionConnection con) {
		writeH(template.getHarvestSkill());
		writeC(action);
		writeD(itemId);

		switch (action) {
			case 0: {
				writeQ(3);
				writeD(success);
				writeD(failure);
				writeD(0);
				writeD(1200); // timer??
				writeD(1330011); // ??text??skill??
				writeH(0x24); // 0x24
				writeD(this.nameId);
				writeH(0); // 0x24
				break;
			}
			case 1: {
				writeQ(3);
				writeD(success);
				writeD(failure);
				writeD(700); // unk timer??
				writeD(1200); // unk timer??
				writeD(0); // unk timer??writeD(700);
				writeH(0);
				break;
			}
			case 2: {
				writeQ(3);
				writeD(success);
				writeD(failure);
				writeD(700);// unk timer??
				writeD(1200); // unk timer??
				writeD(0); // unk timer??writeD(700);
				writeH(0);
				break;
			}
			case 3: {
				writeQ(3);
				writeD(success);
				writeD(failure);
				writeD(700);// unk timer??
				writeD(1200); // unk timer??
				writeD(0); // unk timer??writeD(700);
				writeH(0);
				break;
			}
			case 5: // you have stopped gathering
			{
				writeQ(3);
				writeD(0);
				writeD(0);
				writeD(700);// unk timer??
				writeD(1200); // unk timer??
				writeD(1330080); // unk timer??writeD(700);
				writeH(0);
				break;
			}
			case 6: {
				writeQ(3);
				writeD(success);
				writeD(failure);
				writeD(700); // unk timer??
				writeD(1200); // unk timer??
				writeD(0); // unk timer??writeD(700);
				writeH(0);
				break;
			}
			case 7: {
				writeQ(3);
				writeD(success);
				writeD(failure);
				writeD(0);
				writeD(1200); // timer??
				writeD(1330079); // ??text??skill??
				writeH(0x24); // 0x24
				writeD(nameId);
				writeH(0); // 0x24
				break;
			}
		}
	}
}