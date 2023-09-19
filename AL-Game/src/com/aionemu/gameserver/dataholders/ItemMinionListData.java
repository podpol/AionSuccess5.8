package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.item.ItemMinionList;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Wnkrz on 09/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="minions_list")
public class ItemMinionListData {

    @XmlElement(name = "minion_list", required = true)
    protected List<ItemMinionList> minionlist;

    @XmlTransient
    private TIntObjectHashMap<ItemMinionList> custom = new TIntObjectHashMap<ItemMinionList>();

    public ItemMinionList getMinionList(int id) {
        return custom.get(id);
    }

    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (ItemMinionList it : minionlist) {
            getCustomMap().put(it.getId(), it);
        }
    }

    private TIntObjectHashMap<ItemMinionList> getCustomMap() {
        return custom;
    }

    public int size() {
        return custom.size();
    }
}