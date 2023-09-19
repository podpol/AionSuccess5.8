package com.aionemu.gameserver.model.templates.serial_guard;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuardRankRestriction", propOrder = {"guardpenaltyAttr"})
public class GuardRankRestriction
{
    @XmlElement(name = "guard_penalty_attr")
    protected List<GuardRankPenaltyAttr> guardpenaltyAttr;
	
    @XmlAttribute(name = "rank_num", required = true)
    protected int rankNum;
	
	public List<GuardRankPenaltyAttr> getGuardPenaltyAttr() {
        if (guardpenaltyAttr == null) {
            guardpenaltyAttr = new ArrayList<GuardRankPenaltyAttr>();
		}
		return this.guardpenaltyAttr;
    }
	
    public int getRankNum() {
        return rankNum;
    }
	
	public void setRankNum(int value) {
        this.rankNum = value;
    }
}