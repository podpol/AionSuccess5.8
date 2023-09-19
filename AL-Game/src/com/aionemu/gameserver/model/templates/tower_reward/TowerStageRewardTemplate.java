package com.aionemu.gameserver.model.templates.tower_reward;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Wnkrz on 17/10/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TowerStageReward")
public class TowerStageRewardTemplate
{
    @XmlAttribute(name = "floor")
    protected int floor;
	
    @XmlAttribute(name = "name")
    protected String name;
	
    @XmlAttribute(name = "item_id")
    protected int itemId;
	
    @XmlAttribute(name = "item_count")
    protected int itemCount;
	
    @XmlAttribute(name = "item_id2")
    protected int itemId2;
	
    @XmlAttribute(name = "item_count2")
    protected int itemCount2;
	
    @XmlAttribute(name = "ap_count")
    protected int apCount;
	
    @XmlAttribute(name = "gp_count")
    protected int gpCount;
	
    @XmlAttribute(name = "kinah_count")
    protected int kinahCount;
	
    @XmlAttribute(name = "exp_count")
    protected int expCount;
	
    public int getFloor() {
        return this.floor;
    }
	
    public String getName() {
        return this.name;
    }
	
    public int getItemId() {
        return this.itemId;
    }
	
    public int getItemId2() {
        return this.itemId2;
    }
	
    public int getItemCount() {
        return this.itemCount;
    }
	
    public int getItemCount2() {
        return this.itemCount2;
    }
	
    public int getApCount() {
        return this.apCount;
    }
	
    public int getGpCount() {
        return this.gpCount;
    }
	
    public int getKinahCount() {
        return this.kinahCount;
    }
	
    public int getExpCount() {
        return this.expCount;
    }
}