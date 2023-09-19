package com.aionemu.gameserver.model.templates.serial_guard;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GuardTypeRestriction", propOrder = {"guardpenaltyAttr"})
public class GuardTypeRestriction
{
    @XmlElement(name = "guard_penalty_attr")
    protected List<GuardTypePenaltyAttr> guardpenaltyAttr;
	
    @XmlAttribute(name = "type_num", required = true)
    protected int typeNum;
	
	public List<GuardTypePenaltyAttr> getGuardPenaltyAttr() {
        if (guardpenaltyAttr == null) {
            guardpenaltyAttr = new ArrayList<GuardTypePenaltyAttr>();
		}
		return this.guardpenaltyAttr;
    }
	
    public int getTypeNum() {
        return typeNum;
    }
	
	public void setTypeNum(int value) {
        this.typeNum = value;
    }
}