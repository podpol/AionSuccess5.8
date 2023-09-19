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

import com.aionemu.gameserver.model.autogroup.AutoGroup;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "autoGroup"
})
@XmlRootElement(name = "auto_groups")
public class AutoGroupData {

    @XmlElement(name = "auto_group")
    protected List<AutoGroup> autoGroup;
    @XmlTransient
    private TIntObjectHashMap<AutoGroup> autoGroupByInstanceId = new TIntObjectHashMap<AutoGroup>();
    @XmlTransient
    private TIntObjectHashMap<AutoGroup> autoGroupByNpcId = new TIntObjectHashMap<AutoGroup>();
    
    void afterUnmarshal(Unmarshaller unmarshaller, Object parent){
    	for (AutoGroup ag : autoGroup){
    		autoGroupByInstanceId.put(ag.getId(), ag);
    		
    		if (!ag.getNpcIds().isEmpty()){
    			for (int npcId : ag.getNpcIds()){
    				autoGroupByNpcId.put(npcId, ag);
    			}
    		}
    	}
    	autoGroup.clear();
    	autoGroup = null;
    }

    public AutoGroup getTemplateByInstaceMaskId(int maskId) {
		return autoGroupByInstanceId.get(maskId);
	}

    public int size(){
    	return autoGroupByInstanceId.size();
    }
}
