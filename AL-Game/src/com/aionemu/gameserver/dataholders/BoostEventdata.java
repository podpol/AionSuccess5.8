package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.event.BoostEvents;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanke on 02/03/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "boost_events")
public class BoostEventdata
{
    @XmlElement(name = "boost_event")
    protected List<BoostEvents> bonusServiceBonusattr;
	
    @XmlTransient
    private TIntObjectHashMap<BoostEvents> templates = new TIntObjectHashMap<BoostEvents>();
	
    @XmlTransient
    private Map<Integer, BoostEvents> templatesMap = new HashMap<Integer, BoostEvents>();
	
    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (BoostEvents template : bonusServiceBonusattr) {
            templates.put(template.getId(), template);
            templatesMap.put(template.getId(), template);
        }
        bonusServiceBonusattr.clear();
        bonusServiceBonusattr = null;
    }
	
    public int size() {
        return templates.size();
    }
	
    public BoostEvents getInstanceBonusattr(int buffId) {
        return templates.get(buffId);
    }
	
    public Map<Integer, BoostEvents> getAll() {
        return templatesMap;
    }
}