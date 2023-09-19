package com.aionemu.gameserver.model.templates.panel_cp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Wnkrz on 29/08/2017.
 */

@XmlType(name = "stone_cp")
@XmlAccessorType(XmlAccessType.NONE)
public class StoneCP
{
    @XmlAttribute
    protected int id;
	
    @XmlAttribute
    protected String name;
	
    @XmlAttribute
    protected int cp;
	
    public int getId() {
        return this.id;
    }
	
    public String getName() {
        return name;
    }
	
    public int getCP() {
        return cp;
    }
}