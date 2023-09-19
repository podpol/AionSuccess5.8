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
package com.aionemu.gameserver.model.templates.pet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author IlBuono
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "petfunction")
public class PetFunction {

	@XmlAttribute(name = "type")
	private PetFunctionType type;
	@XmlAttribute(name = "id")
	private int id;
	@XmlAttribute(name = "slots")
	private int slots;

	public PetFunctionType getPetFunctionType() {
		return type;
	}

	public int getId() {
		return id;
	}

	public int getSlots() {
		return slots;
	}

	public static PetFunction CreateEmpty() {
		PetFunction result = new PetFunction();
		result.type = PetFunctionType.NONE;
		return result;
	}

}
