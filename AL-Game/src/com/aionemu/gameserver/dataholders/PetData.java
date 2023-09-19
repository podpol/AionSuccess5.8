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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.pet.PetTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This is a container holding and serving all {@link PetTemplate} instances.<br>
 * 
 * @author IlBuono
 */
@XmlRootElement(name = "pets")
@XmlAccessorType(XmlAccessType.FIELD)
public class PetData {

	@XmlElement(name = "pet")
	private List<PetTemplate> pets;

	/** A map containing all pet templates */
	private TIntObjectHashMap<PetTemplate> petData = new TIntObjectHashMap<PetTemplate>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (PetTemplate pet : pets) {
			petData.put(pet.getId(), pet);
		}
		pets.clear();
		pets = null;
	}

	public int size() {
		return petData.size();
	}

	/**
	 * /** Returns an {@link PetTemplate} object with given id.
	 * 
	 * @param id
	 *          id of Pet
	 * @return PetTemplate object containing data about Pet with that id.
	 */
	public PetTemplate getPetTemplate(int id) {
		return petData.get(id);
	}

}
