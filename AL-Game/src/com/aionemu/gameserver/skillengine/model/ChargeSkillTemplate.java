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
package com.aionemu.gameserver.skillengine.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Dr.Nism
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "charge_skill")
public class ChargeSkillTemplate
{
	@XmlAttribute(name = "id")
    private int id;
	
	@XmlAttribute(name = "charge_set_name")
    private String charge_set_name;
    
	@XmlAttribute(name = "first")
    private int first;
    
	@XmlAttribute(name = "second")
    private int second;
    
	@XmlAttribute(name = "third")
    private int third;
    
	@XmlAttribute(name = "min_charge")
    private int min_charge;
	
    @XmlElement(name = "charge")
    private List<ChargeTemplate> charges;
	
	@XmlAttribute(name = "bonus_type", required = true)
	protected BonusChargeType type = BonusChargeType.NONE;
    
	public int getId() {
        return id;
    }
	
	public String getChargeSetName() {
		return charge_set_name;
	}
	
    public int getFirstId() {
        return first;
    }
    
    public int getSecondId() {
        return second;
    }
    
    public int getThirdId() {
        return third;
    }
    
    /**
     * @return the MinCharge
     */
    public int getMinCharge() {
        return min_charge;
    }
    
    /**
     * @return the Charges
     */
    public List<ChargeTemplate> getCharges() {
        return charges;
    }
	
	/**
     * @return the BonusChargeType
     */
	public BonusChargeType getBonusChargeType() {
		return type;
	}
}