package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.PlayerClass;
import com.aionemu.gameserver.model.templates.item.ItemSkillEnhance;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanke on 01/03/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="item_skill_enhances")
public class ItemSkillEnhanceData
{
    @XmlElement(name = "item_skill_enhance", required = true)
    protected List<ItemSkillEnhance> skillEnhances;
	
    @XmlTransient
    protected List<ItemSkillEnhance> enhanceSkillList = new ArrayList<ItemSkillEnhance>();
	
    public ItemSkillEnhance getSkillEnhance(int id) {
        for (ItemSkillEnhance enhance : enhanceSkillList) {
            if (enhance.getId() == id) {
                return enhance;
            }
        }
        return null;
    }
	
    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (ItemSkillEnhance enhance : skillEnhances) {
            enhanceSkillList.add(enhance);
        }
        skillEnhances.clear();
        skillEnhances = null;
    }
	
    public int size() {
        return enhanceSkillList.size();
    }
}