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

import com.aionemu.gameserver.model.templates.event.EventTemplate;
import gnu.trove.map.hash.THashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * <p>
 * Java class for EventData complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="event" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{}EventTemplate">
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventData", propOrder = { "active", "events" })
@XmlRootElement(name = "events_config")
public class EventData {

	@XmlElement(required = true)
	protected String active;

	@XmlElementWrapper(name = "events")
	@XmlElement(name = "event")
	protected List<EventTemplate> events;

	@XmlTransient
	private THashMap<String, EventTemplate> activeEvents = new THashMap<String, EventTemplate>();

	@XmlTransient
	private THashMap<String, EventTemplate> allEvents = new THashMap<String, EventTemplate>();

	@XmlTransient
	private int counter = 0;

	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (active == null || events == null)
			return;

		counter = 0;
		allEvents.clear();
		activeEvents.clear();

		Set<String> ae = new HashSet<String>();
		Collections.addAll(ae, active.split(";"));

		for (EventTemplate ev : events) {
			if (ae.contains(ev.getName()) && ev.isActive()) {
				activeEvents.put(ev.getName(), ev);
				counter++;
			}
			allEvents.put(ev.getName(), ev);
		}

		events.clear();
		events = null;
		active = null;
	}

	public int size() {
		return counter;
	}

	public String getActiveText() {
		return active;
	}

	public List<EventTemplate> getAllEvents() {
		List<EventTemplate> result = new ArrayList<EventTemplate>();
		synchronized (allEvents) {
			result.addAll(allEvents.values());
		}

		return result;
	}

	public void setAllEvents(List<EventTemplate> events, String active) {
		if (events == null)
			events = new ArrayList<EventTemplate>();
		this.events = events;
		this.active = active;

		for (EventTemplate et : this.events) {
			if (allEvents.containsKey(et.getName())) {
				EventTemplate oldEvent = allEvents.get(et.getName());
				if (oldEvent.isActive() && oldEvent.isStarted())
					et.setStarted();
			}
		}
		afterUnmarshal(null, null);
	}

	public List<EventTemplate> getActiveEvents() {
		List<EventTemplate> result = new ArrayList<EventTemplate>();
		synchronized (activeEvents) {
			result.addAll(activeEvents.values());
		}

		return result;
	}

	public boolean Contains(String eventName) {
		return activeEvents.containsKey(eventName);
	}

}
