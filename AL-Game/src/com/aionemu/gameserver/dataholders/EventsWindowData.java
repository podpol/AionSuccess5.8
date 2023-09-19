package com.aionemu.gameserver.dataholders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.aionemu.gameserver.model.templates.event.EventsWindow;

import gnu.trove.map.hash.TIntObjectHashMap;

/**
 * 
 * @author Ranastic
 *
 */
@XmlRootElement(name = "events_window")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventsWindowData {

	@XmlElement(name="event_window")
	private List<EventsWindow> events_window;
	
	@XmlTransient
	private TIntObjectHashMap<EventsWindow> eventData = new TIntObjectHashMap<EventsWindow>();
	
	@XmlTransient
	private Map<Integer, EventsWindow> eventDataMap = new HashMap<Integer, EventsWindow>(1);

	void afterUnmarshal(Unmarshaller paramUnmarshaller, Object paramObject) {
		for (EventsWindow event_window : events_window) {
			eventData.put(event_window.getId(), event_window);
			eventDataMap.put(event_window.getId(), event_window);
		}
	}

	public int size() {
		return eventData.size();
	}

	public EventsWindow getEventWindowId(int id) {
		return eventData.get(id);
	}

	public Map<Integer, EventsWindow> getAllEvents() {
		return eventDataMap;
	}
}
