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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_FIND_GROUP;
import com.aionemu.gameserver.services.FindGroupService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cura, MrPoke
 */
public class CM_FIND_GROUP extends AionClientPacket {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(CM_FIND_GROUP.class);

	private int action;
	private int playerObjId;
	private String message;
	private int groupType;
	@SuppressWarnings("unused")
	private int classId;
	@SuppressWarnings("unused")
	private int level;
	private int unk;
	private int instanceId;
	private int minMembers;

	public CM_FIND_GROUP(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	@Override
	protected void readImpl() {
		action = readC();

		switch (action) {
			case 0x00: // recruit list
				break;
			case 0x01: // offer delete
				playerObjId = readD();
				unk = readD(); // unk(65557)
				break;
			case 0x02: // send offer
				playerObjId = readD();
				message = readS();
				groupType = readC();
				break;
			case 0x03: // recruit update
				playerObjId = readD();
				unk = readD(); // unk(65557)
				message = readS();
				groupType = readC();
				break;
			case 0x04: // apply list
				break;
			case 0x05: // post delete
				playerObjId = readD();
				break;
			case 0x06: // apply create
				playerObjId = readD();
				message = readS();
				groupType = readC();
				classId = readC();
				level = readC();
				break;
			case 0x07: // apply update
				// TODO need packet check
				break;
			case 0x08: //register InstanceGroup
				instanceId = readD();
				groupType = readC();//need to be tested
				message = readS();//text
				minMembers = readC();//minMembers chosen by writer
				break;
			case 0x0A:
				break;
			default:
				//log.error("Unknown find group packet? 0x" + Integer.toHexString(action).toUpperCase());
				break;
		}
	}

	@Override
	protected void runImpl() {
		final Player player = this.getConnection().getActivePlayer();
		switch (action) {
			case 0x00:
			case 0x04:
				FindGroupService.getInstance().sendFindGroups(player, action);
				break;
			case 0x01:
			case 0x05:
				FindGroupService.getInstance().removeFindGroup(player.getRace(), action - 1, playerObjId);
				break;
			case 0x02:
			case 0x06:
				FindGroupService.getInstance().addFindGroupList(player, action, message, groupType);
				break;
			case 0x03:
				FindGroupService.getInstance().updateFindGroupList(player, message, playerObjId);
				break;
			case 0x08:
				FindGroupService.getInstance().registerInstanceGroup(player, 0x0E, instanceId, message, minMembers, groupType);
				break;
			case 0x0A:
				FindGroupService.getInstance().sendFindGroups(player, action);
				break;
			default:
				PacketSendUtility.sendPacket(player, new SM_FIND_GROUP(action, playerObjId, unk));
				break;
		}
	}
}
