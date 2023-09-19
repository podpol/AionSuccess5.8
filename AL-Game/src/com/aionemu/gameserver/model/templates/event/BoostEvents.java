package com.aionemu.gameserver.model.templates.event;

import com.aionemu.gameserver.utils.gametime.DateTimeUtil;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by wanke on 02/03/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoostEvents")
public class BoostEvents
{
    @XmlAttribute(name = "id", required = true)
    protected int id;
	
    @XmlAttribute(name = "name", required = true)
    protected String name;
	
    @XmlAttribute(name = "buff_id", required = true)
    protected int buffId;
	
    @XmlAttribute(name = "buff_value", required = true)
    protected int buffValue;
	
    @XmlAttribute(name = "start", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
	
    @XmlAttribute(name = "end", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
	
    public int getId() {
        return id;
    }
	
    public String getName() {
        return name;
    }
	
    public int getBuffId() {
        return buffId;
    }
	
    public int getBuffValue() {
        return buffValue;
    }
	
    public DateTime getStartDate() {
        return DateTimeUtil.getDateTime(startDate.toGregorianCalendar());
    }
	
    public DateTime getEndDate() {
        return DateTimeUtil.getDateTime(endDate.toGregorianCalendar());
    }
	
    public boolean isActive() {
        return getStartDate().isBeforeNow() && getEndDate().isAfterNow();
    }
	
    public boolean isExpired() {
        return !isActive();
    }
}