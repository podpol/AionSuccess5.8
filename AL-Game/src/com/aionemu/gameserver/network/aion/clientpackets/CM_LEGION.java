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
import com.aionemu.gameserver.model.team.legion.Legion;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.network.aion.serverpackets.SM_LEGION_INFO;
import com.aionemu.gameserver.services.LegionService;
import com.aionemu.gameserver.services.NameRestrictionService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Simple
 */
public class CM_LEGION extends AionClientPacket {

	private static final Logger log = LoggerFactory.getLogger(CM_LEGION.class);

	/**
	 * exOpcode and the rest
	 */
	private int exOpcode;
	private short deputyPermission;
	private short centurionPermission;
	private short legionarPermission;
	private short volunteerPermission;
	private int rank;
	private String legionName;
	private String charName;
	private String newNickname;
	private String announcement;
	private String newSelfIntro;
	private String joinDescription;
    private int joinType;
    private int minLevel;
    private int playerId;

	/**
	 * Constructs new instance of CM_LEGION packet
	 * 
	 * @param opcode
	 */
	public CM_LEGION(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl() {
		exOpcode = readC();

		switch (exOpcode) {
			/** Create a legion **/
			case 0x00:
				readD(); // 00 78 19 00 40
				legionName = readS();
				break;
			/** Invite to legion **/
			case 0x01:
				readD(); // empty
				charName = readS();
				break;
			/** Leave legion **/
			case 0x02:
				readD(); // empty
				readH(); // empty
				break;
			/** Kick member from legion **/
			case 0x04:
				readD(); // empty
				charName = readS();
				break;
			/** Appoint a new Brigade General **/
			case 0x05:
				readD();
				charName = readS();
				break;
			/** Appoint Centurion **/
			case 0x06:
				rank = readD();
				charName = readS();
				break;
			/** Demote to Legionary **/
			case 0x07:
				readD(); // char id? 00 78 19 00 40
				charName = readS();
				break;
			/** Refresh legion info **/
			case 0x08:
				readD();
				readH();
				break;
			/** Edit announcements **/
			case 0x09:
				readD(); // empty or char id?
				announcement = readS();
				break;
			/** Change self introduction **/
			case 0x0A:
				readD(); // empty char id?
				newSelfIntro = readS();
				break;
			/** Edit permissions **/
			case 0x0D:
				deputyPermission = (short) readH();
				centurionPermission = (short) readH();
				legionarPermission = (short) readH();
				volunteerPermission = (short) readH();
				break;
			/** Level legion up **/
			case 0x0E:
				readD(); // empty
				readH(); // empty
				break;
			case 0x0F:
				charName = readS();
				newNickname = readS();
				break;
			/** Stonespear Reach **/		
			case 0x10:
				readD();
				break;
			case 0x11:
            	joinDescription = readS();
            	break;
			case 0x12:
            	joinType = readC();
            	break;
            case 0x13:
            	minLevel = readH();
            	break;
            case 0x14:
            	playerId = readD();
            	break;
            case 0x15:
            	playerId = readD();
            	break;
			default:
				log.info("Unknown Legion exOpcode? 0x" + Integer.toHexString(exOpcode).toUpperCase());
				break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl() {
		final Player activePlayer = getConnection().getActivePlayer();
		if (activePlayer.isLegionMember()) {
			final Legion legion = activePlayer.getLegion();

			if (charName != null) {
				LegionService.getInstance().handleCharNameRequest(exOpcode, activePlayer, charName, newNickname, rank);
			}
			else {
				switch (exOpcode) {
					/** Refresh legion info **/
					case 0x08:
						sendPacket(new SM_LEGION_INFO(legion));
						break;
					/** Edit announcements **/
					case 0x09:
						LegionService.getInstance().handleLegionRequest(exOpcode, activePlayer, announcement);
						break;
					/** Stonespear Reach **/	
					case 0x10:
						break;
					/** Change self introduction **/
					case 0x0A:
						LegionService.getInstance().handleLegionRequest(exOpcode, activePlayer, newSelfIntro);
						break;
					/** Edit permissions **/
					case 0x0D:
						if (activePlayer.getLegionMember().isBrigadeGeneral())
							LegionService.getInstance().changePermissions(legion, deputyPermission, centurionPermission, legionarPermission, volunteerPermission);
						break;
					case 0x11:
                    	if (activePlayer.getLegionMember().isBrigadeGeneral())
                    	    LegionService.getInstance().setJoinDescription(activePlayer, joinDescription);
                    	break;
					case 0x12:
                    	if (activePlayer.getLegionMember().isBrigadeGeneral())
                    		LegionService.getInstance().setJoinType(activePlayer, joinType);
                        break;
					case 0x13:
                    	if (activePlayer.getLegionMember().isBrigadeGeneral())
                    	    LegionService.getInstance().setJoinMinLevel(activePlayer, minLevel);
                        break;
                    case 0x14:
                        if (activePlayer.getLegionMember().isBrigadeGeneral())
                    	    LegionService.getInstance().handleJoinRequestGiveAnswer(activePlayer, playerId, true);
                        break;
                    case 0x15:
                	    if (activePlayer.getLegionMember().isBrigadeGeneral())
                		    LegionService.getInstance().handleJoinRequestGiveAnswer(activePlayer, playerId, false);
                        break;
					/** Misc. **/
					default:
						LegionService.getInstance().handleLegionRequest(exOpcode, activePlayer);
						break;
				}
			}
		}
		else {
			switch (exOpcode) {
				/** Create a legion **/
				case 0x00:
					if (NameRestrictionService.isForbiddenWord(legionName)) {
						PacketSendUtility.sendMessage(activePlayer, "You are trying to use a forbidden name. Choose another one!");
					}
					else {
						LegionService.getInstance().createLegion(activePlayer, legionName);
					}
					break;
			}
		}
	}
}
