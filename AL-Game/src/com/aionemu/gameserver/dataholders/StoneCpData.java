package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.panel_cp.StoneCP;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ghostfur (Aion-Unique)
 */
@XmlRootElement(name = "stones_cp")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoneCpData
{
    @XmlElement(name="stone_cp")
    private List<StoneCP> stonelist;

    @XmlTransient
    private TIntObjectHashMap<StoneCP> stoneData = new TIntObjectHashMap<StoneCP>();

    @XmlTransient
    private Map<Integer, StoneCP> stoneDataMap = new HashMap<Integer, StoneCP>(1);

    void afterUnmarshal(Unmarshaller paramUnmarshaller, Object paramObject) {
        for (StoneCP stoneCp: stonelist) {
            stoneData.put(stoneCp.getId(), stoneCp);
            stoneDataMap.put(stoneCp.getId(), stoneCp);
        }
    }

    public int size() {
        return stoneData.size();
    }

    public StoneCP getStoneCpId(int id) {
        return stoneData.get(id);
    }

    public Map<Integer, StoneCP> getAll() {
        return stoneDataMap;
    }
}