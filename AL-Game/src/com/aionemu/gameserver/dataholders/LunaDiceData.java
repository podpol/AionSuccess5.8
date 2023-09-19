package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.luna_dice.LunaDiceItem;
import com.aionemu.gameserver.model.templates.luna_dice.LunaDiceTable;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Wnkrz on 26/07/2017.
 */

@XmlRootElement(name = "luna_dice")
@XmlAccessorType(XmlAccessType.FIELD)
public class LunaDiceData {

    @XmlElement(name = "table")
    private List<LunaDiceTable> lunaDiceTabTemplate;
    private TIntObjectHashMap<List<LunaDiceItem>> diceItemList = new TIntObjectHashMap<>();

    void afterUnmarshal(Unmarshaller u, Object parent) {
        diceItemList.clear();
        for (LunaDiceTable template : lunaDiceTabTemplate) {
            diceItemList.put(template.getId(), template.getLunaDiceTabItems());
        }
    }

    public int size() {
        return diceItemList.size();
    }

    public List<LunaDiceItem> getLunaDiceTabById(int id) {
        return diceItemList.get(id);
    }

    public List<LunaDiceTable> getLunaDiceTabs() {
        return lunaDiceTabTemplate;
    }
}
