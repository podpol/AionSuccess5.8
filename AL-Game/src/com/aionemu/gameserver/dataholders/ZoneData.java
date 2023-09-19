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

import com.aionemu.gameserver.model.geometry.*;
import com.aionemu.gameserver.model.templates.zone.ZoneClassName;
import com.aionemu.gameserver.model.templates.zone.ZoneInfo;
import com.aionemu.gameserver.model.templates.zone.ZoneTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "zones")
public class ZoneData{
	
	private static final Logger log = LoggerFactory.getLogger(ZoneData.class);

	@XmlElement(name = "zone")
	public List<ZoneTemplate> zoneList;

	@XmlTransient
	private TIntObjectHashMap<List<ZoneInfo>> zoneNameMap = new TIntObjectHashMap<List<ZoneInfo>>();

	@XmlTransient
	private HashMap<ZoneTemplate, Integer> weatherZoneIds = new HashMap<ZoneTemplate, Integer>();

	@XmlTransient
	private int count;

	protected void afterUnmarshal(Unmarshaller u, Object parent) {
		int lastMapId = 0;
		int weatherZoneId = 1;
		for (ZoneTemplate zone : zoneList) {
			Area area = null;
			switch (zone.getAreaType()) {
				case POLYGON:
					area = new PolyArea(zone.getName(), zone.getMapid(), zone.getPoints().getPoint(), zone.getPoints().getBottom(), zone.getPoints()
						.getTop());
					break;
				case CYLINDER:
					area = new CylinderArea(zone.getName(), zone.getMapid(), zone.getCylinder().getX(), zone.getCylinder().getY(), zone.getCylinder()
						.getR(), zone.getCylinder().getBottom(), zone.getCylinder().getTop());
					break;
				case SPHERE:
					area = new SphereArea(zone.getName(), zone.getMapid(), zone.getSphere().getX(), zone.getSphere().getY(), zone.getSphere().getZ(),
						zone.getSphere().getR());
					break;
				case SEMISPHERE:
					area = new SemisphereArea(zone.getName(), zone.getMapid(), zone.getSemisphere().getX(), zone.getSemisphere().getY(), zone.getSemisphere().getZ(),
						zone.getSemisphere().getR());
			}
			if (area != null) {
				List<ZoneInfo> zones = zoneNameMap.get(zone.getMapid());
				if (zones == null) {
					zones = new ArrayList<ZoneInfo>();
					zoneNameMap.put(zone.getMapid(), zones);
				}
				if (zone.getZoneType() == ZoneClassName.WEATHER) {
					if (lastMapId != zone.getMapid()) {
						lastMapId = zone.getMapid();
						weatherZoneId = 1;
					}
					weatherZoneIds.put(zone, weatherZoneId++);
				}
				zones.add(new ZoneInfo(area, zone));
				count++;
			}
		}
		zoneList.clear();
		zoneList = null;
	}

	public TIntObjectHashMap<List<ZoneInfo>> getZones() {
		return zoneNameMap;
	}

	public int size() {
		return count;
	}
	
	/**
	 * Weather zone ID it's an order number (starts from 1)
	 */
	public int getWeatherZoneId(ZoneTemplate template) {
		Integer id = weatherZoneIds.get(template);
		if (id == null)
			return 0;
		return id;
	}

	public void saveData() {
		Schema schema = null;
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		try {
			schema = sf.newSchema(new File("./data/static_data/zones/zones.xsd"));
		}
		catch (SAXException e1) {
			log.error("Error while saving data: " + e1.getMessage(), e1.getCause());
			return;
		}

		File xml = new File("./data/static_data/zones/generated_zones.xml");
		JAXBContext jc;
		Marshaller marshaller;
		try {
			jc = JAXBContext.newInstance(ZoneData.class);
			marshaller = jc.createMarshaller();
			marshaller.setSchema(schema);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, xml);
		}
		catch (JAXBException e) {
			log.error("Error while saving data: " + e.getMessage(), e.getCause());
			return;
		}
	}
}