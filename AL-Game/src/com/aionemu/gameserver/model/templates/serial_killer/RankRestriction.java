package com.aionemu.gameserver.model.templates.serial_killer;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RankRestriction", propOrder = {"penaltyAttr"})
public class RankRestriction
{
    @XmlElement(name = "penalty_attr")
    protected List<RankPenaltyAttr> penaltyAttr;
	
    @XmlAttribute(name = "rank_num", required = true)
    protected int rankNum;
	
	public List<RankPenaltyAttr> getPenaltyAttr() {
        if (penaltyAttr == null) {
            penaltyAttr = new ArrayList<RankPenaltyAttr>();
		}
		return this.penaltyAttr;
    }
	
    public int getRankNum() {
        return rankNum;
    }
	
    public void setRankNum(int value) {
        this.rankNum = value;
    }
}