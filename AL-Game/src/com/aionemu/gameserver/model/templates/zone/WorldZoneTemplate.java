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
package com.aionemu.gameserver.model.templates.zone;

import com.aionemu.gameserver.configs.main.WorldConfig;
import com.aionemu.gameserver.dataholders.DataManager;

/**
 * @author Rolandas
 */
public class WorldZoneTemplate extends ZoneTemplate {

	public WorldZoneTemplate(int size, Integer mapId) {
		float maxZ = Math.round((float) size / WorldConfig.WORLD_REGION_SIZE) * WorldConfig.WORLD_REGION_SIZE;
		points = new Points(-1, maxZ + 1);
		Point2D point = new Point2D();
		point.x = -1;
		point.y = -1;
		points.getPoint().add(point);
		point = new Point2D();
		point.x = -1;
		point.y = size + 1;
		points.getPoint().add(point);
		point = new Point2D();
		point.x = size + 1;
		point.y = size + 1;
		points.getPoint().add(point);
		point = new Point2D();
		point.x = size + 1;
		point.y = -1;
		points.getPoint().add(point);
		zoneType = ZoneClassName.DUMMY;
		mapid = mapId;
		flags = DataManager.WORLD_MAPS_DATA.getTemplate(mapId).getFlags();
		setXmlName(mapId.toString());
	}

}
