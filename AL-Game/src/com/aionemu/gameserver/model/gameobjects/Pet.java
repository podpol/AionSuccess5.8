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

import com.aionemu.gameserver.controllers.PetController;
import com.aionemu.gameserver.controllers.movement.MoveController;
import com.aionemu.gameserver.controllers.movement.PetMoveController;
import com.aionemu.gameserver.model.gameobjects.player.PetCommonData;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.pet.PetTemplate;
import com.aionemu.gameserver.world.WorldPosition;

/**
 * @author ATracer
 */
public class Pet extends VisibleObject {

	private final Player master;
	private MoveController moveController;
	private final PetTemplate petTemplate;

	/**
	 * @param petTemplate
	 * @param controller
	 * @param commonData
	 * @param master
	 */
	public Pet(PetTemplate petTemplate, PetController controller, PetCommonData commonData, Player master) {
		super(commonData.getObjectId(), controller, null, commonData, new WorldPosition(master.getWorldId()));
		controller.setOwner(this);
		this.master = master;
		this.petTemplate = petTemplate;
		this.moveController = new PetMoveController();
	}

	public Player getMaster() {
		return master;
	}

	public int getPetId() {
		return objectTemplate.getTemplateId();
	}

	@Override
	public String getName() {
		return objectTemplate.getName();
	}

	public final PetCommonData getCommonData() {
		return (PetCommonData) objectTemplate;
	}

	public final MoveController getMoveController() {
		return moveController;
	}

	public final PetTemplate getPetTemplate() {
		return petTemplate;
	}

}
