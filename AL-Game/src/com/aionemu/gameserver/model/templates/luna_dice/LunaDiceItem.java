package com.aionemu.gameserver.model.templates.luna_dice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Wnkrz on 26/07/2017.
 */

@XmlType(name = "LunaDiceItem")
public class LunaDiceItem
{
    @XmlAttribute(name = "item_id")
    protected int item_id;
	
    @XmlAttribute(name = "count")
    protected int count;
	
    public final int getItemId() {
        return item_id;
    }
	
    public final int getCount() {
        return count;
    }
}