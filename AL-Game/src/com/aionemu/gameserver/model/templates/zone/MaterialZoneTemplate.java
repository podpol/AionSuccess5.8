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

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.geoEngine.bounding.BoundingBox;
import com.aionemu.gameserver.geoEngine.math.Vector3f;
import com.aionemu.gameserver.geoEngine.scene.Spatial;

/**
 * @author Rolandas
 */
public class MaterialZoneTemplate extends ZoneTemplate {

	public MaterialZoneTemplate(Spatial geometry, int mapId) {
		mapid = mapId;
		flags = DataManager.WORLD_MAPS_DATA.getTemplate(mapId).getFlags();
		setXmlName(geometry.getName() + "_" + mapId);
		BoundingBox box = (BoundingBox) geometry.getWorldBound();
		Vector3f center = box.getCenter();
		// don't use polygons for small areas, they are bugged in Java API
		if (geometry.getName().indexOf("CYLINDER") != -1 || geometry.getName().indexOf("CONE") != -1 ||
			geometry.getName().indexOf("H_COLUME") != -1) {
			areaType = AreaType.CYLINDER;
			cylinder = new Cylinder(center.x, center.y, Math.max(box.getXExtent(), box.getYExtent() + 1), center.z + box.getZExtent() + 1, center.z
				- box.getZExtent() - 1);
		}
		else if (geometry.getName().indexOf("SEMISPHERE") != -1) {
			areaType = AreaType.SEMISPHERE;
			semisphere = new Semisphere(center.x, center.y, center.z, Math.max(Math.max(box.getXExtent(), box.getYExtent()), box.getZExtent()) + 1);
		}
		else {
			areaType = AreaType.SPHERE;
			sphere = new Sphere(center.x, center.y, center.z, Math.max(Math.max(box.getXExtent(), box.getYExtent()), box.getZExtent()) + 1);
		}
	}

}
