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
package com.aionemu.gameserver.world.zone;

import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Rolandas
 */
public final class ZoneName {

	private final static Logger log = LoggerFactory.getLogger(ZoneName.class);

	private static final FastMap<String, ZoneName> zoneNames = new FastMap<String, ZoneName>();
	public static final String NONE = "NONE";
	public static final String ABYSS_CASTLE = "_ABYSS_CASTLE_AREA_";

	static {
		zoneNames.put(NONE, new ZoneName(NONE));
		zoneNames.put(ABYSS_CASTLE, new ZoneName(ABYSS_CASTLE));
	}

	private String _name;

	private ZoneName(String name) {
		this._name = name;
	}

	public String name() {
		return _name;
	}

	public int id() {
		return _name.hashCode();
	}

	public static final ZoneName createOrGet(String name) {
		name = name.toUpperCase();
		if (zoneNames.containsKey(name))
			return zoneNames.get(name);
		ZoneName newZone = new ZoneName(name);
		zoneNames.put(name, newZone);
		return newZone;
	}

	public static final int getId(String name) {
		name = name.toUpperCase();
		if (zoneNames.containsKey(name))
			return zoneNames.get(name).id();
		return zoneNames.get(NONE).id();
	}

	public static final ZoneName get(String name) {
		name = name.toUpperCase();
		if (zoneNames.containsKey(name))
			return zoneNames.get(name);
		log.warn("Missing zone : " + name);
		return zoneNames.get(NONE);
	}

	@Override
	public String toString() {
		return _name;
	}

}
