package com.aionemu.gameserver.model.templates.item;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wnkrz on 09/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ItemMinionList")
public class ItemMinionList
{
    @XmlAttribute(name="id")
    protected int id;
	
    @XmlAttribute(name="minion_id")
    protected List<Integer> minionsId;
	
    public int getId() {
        return this.id;
    }
	
    public List<Integer> getMinionId() {
        if (minionsId == null) {
            minionsId = new ArrayList<Integer>();
        }
        return minionsId;
    }
}