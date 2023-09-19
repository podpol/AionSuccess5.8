package com.aionemu.gameserver.model.templates.luna_dice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by Wnkrz on 26/07/2017.
 */

@XmlType(name = "LunaDiceTable")
public class LunaDiceTable
{
    @XmlAttribute(name = "id")
    private int id;
	
    @XmlElement(name = "reward")
    private List<LunaDiceItem> lunaDiceTabItem;
	
    public int getId() {
        return id;
    }
	
    public List<LunaDiceItem> getLunaDiceTabItems() {
        return lunaDiceTabItem;
    }
}