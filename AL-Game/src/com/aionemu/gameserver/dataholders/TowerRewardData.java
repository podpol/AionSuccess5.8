package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.tower_reward.TowerStageRewardTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wnkrz on 17/10/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tower_reward_templates")
public class TowerRewardData
{
    @XmlElement(name = "tower_reward_template")
    private List<TowerStageRewardTemplate> TowerReward;
	
    @XmlTransient
    private TIntObjectHashMap<TowerStageRewardTemplate> templates = new TIntObjectHashMap<TowerStageRewardTemplate>();
	
    @XmlTransient
    private Map<Integer, TowerStageRewardTemplate> templatesMap = new HashMap<Integer, TowerStageRewardTemplate>();
	
    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (TowerStageRewardTemplate template : TowerReward) {
            templates.put(template.getFloor(), template);
            templatesMap.put(template.getFloor(), template);
        }
        TowerReward.clear();
        TowerReward = null;
    }
	
    public int size() {
        return templates.size();
    }
	
    public TowerStageRewardTemplate getTowerReward(int towerId) {
        return templates.get(towerId);
    }
	
    public Map<Integer, TowerStageRewardTemplate> getAll() {
        return templatesMap;
    }
}