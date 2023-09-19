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
package com.aionemu.gameserver.model.gameobjects;

import com.aionemu.gameserver.controllers.StaticObjectController;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.templates.spawns.SpawnTemplate;
import com.aionemu.gameserver.model.templates.staticdoor.StaticDoorState;
import com.aionemu.gameserver.model.templates.staticdoor.StaticDoorTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.geo.GeoService;

import java.util.EnumSet;


/**
 * @author MrPoke
 *
 */
public class StaticDoor extends StaticObject {

	private EnumSet<StaticDoorState> states;
	private String doorName;

	/**
	 * @param objectId
	 * @param controller
	 * @param spawnTemplate
	 * @param objectTemplate
	 */
	public StaticDoor(int objectId, StaticObjectController controller, SpawnTemplate spawnTemplate, StaticDoorTemplate objectTemplate,
		int instanceId) {
		super(objectId, controller, spawnTemplate, objectTemplate);
		states = EnumSet.copyOf(getObjectTemplate().getInitialStates());
		if (objectTemplate.getMeshFile() != null) {
			doorName = GeoService.getInstance().getDoorName(spawnTemplate.getWorldId(), objectTemplate.getMeshFile(), objectTemplate.getX(),
				objectTemplate.getY(), objectTemplate.getZ());
		}
	}

	/**
	 * @return the open state from states set
	 */
	public boolean isOpen() {
		return states.contains(StaticDoorState.OPENED);
	}

	public EnumSet<StaticDoorState> getStates() {
		return states;
	}

	/**
	 * @param open
	 *          the open state to set
	 */
	public void setOpen(boolean open) {
		EmotionType emotion;
		int packetState = 0; // not important IMO, similar to internal state
		if (open) {
			emotion = EmotionType.OPEN_DOOR;
			states.remove(StaticDoorState.CLICKABLE);
			states.add(StaticDoorState.OPENED); // 1001
			packetState = 0x9;
		}
		else {
			emotion = EmotionType.CLOSE_DOOR;
			if (getObjectTemplate().getInitialStates().contains(StaticDoorState.CLICKABLE))
				states.add(StaticDoorState.CLICKABLE);
			states.remove(StaticDoorState.OPENED); // 1010
			packetState = 0xA;
		}
		if (doorName != null) {
			GeoService.getInstance().setDoorState(getWorldId(), getInstanceId(), doorName, open);
		}
		// int stateFlags = StaticDoorState.getFlags(states);
		PacketSendUtility.broadcastPacket(this, new SM_EMOTION(this.getSpawn().getEntityId(), emotion, packetState));
	}

	public void changeState(boolean open, int state) {
		state = state & 0xF;
		StaticDoorState.setStates(state, states);
		EmotionType emotion = open ? emotion = EmotionType.OPEN_DOOR : EmotionType.CLOSE_DOOR;
		PacketSendUtility.broadcastPacket(this, new SM_EMOTION(this.getSpawn().getEntityId(), emotion, state));
	}

	@Override
	public StaticDoorTemplate getObjectTemplate() {
		return (StaticDoorTemplate) super.getObjectTemplate();
	}

	public String getDoorName() {
		return doorName;
	}
}
