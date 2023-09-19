package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.globaldrops.GlobalRule;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanke on 19/02/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlobalDropData", propOrder = { "globalDropRules" })
@XmlRootElement(name = "global_rules")
public class GlobalDropData
{
    @XmlElementWrapper(name = "gd_rules")
    @XmlElement(name = "gd_rule")
    protected List<GlobalRule> globalDropRules;
	
    public List<GlobalRule> getAllRules() {
        if (globalDropRules == null) {
            globalDropRules = new ArrayList<GlobalRule>();
        }
        return this.globalDropRules;
    }
	
    public int size() {
        return globalDropRules.size();
    }
}