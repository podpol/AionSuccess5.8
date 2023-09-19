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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.aionemu.gameserver.model.templates.event.AtreianPassport;

import gnu.trove.map.hash.TIntObjectHashMap;

/**
 * @author Alcapwnd
 */
@XmlRootElement(name = "atreian_passports")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtreianPassportData {

	@XmlElement(name = "atreian_passport")
	private List<AtreianPassport> tlist;
	/**
	 * A map containing all teleport location templates
	 */
	private TIntObjectHashMap<AtreianPassport> passportData = new TIntObjectHashMap<AtreianPassport>();
	private Map<Integer, AtreianPassport> passportDataMap = new HashMap<Integer, AtreianPassport>(1);

	/**
	 * @param u
	 * @param parent
	 */
	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (AtreianPassport id : tlist) {
			passportData.put(id.getId(), id);
			passportDataMap.put(id.getId(), id);
		}
	}

	public int size() {
		return passportData.size();
	}

	public AtreianPassport getAtreianPassportId(int id) {
		return passportData.get(id);
	}

	public Map<Integer, AtreianPassport> getAll() {
		return passportDataMap;
	}
}
