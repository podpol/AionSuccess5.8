package com.aionemu.gameserver.model.templates.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

import com.aionemu.gameserver.model.AttendType;
import com.aionemu.gameserver.utils.gametime.DateTimeUtil;
import org.joda.time.DateTime;

/**
 * @author Ghostfur (Aion-Unique)
 */
@XmlRootElement(name = "atreian_passport")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtreianPassport {

	@XmlAttribute(name = "id", required = true)
	private int id;

	@XmlAttribute(name = "name")
	private String name = "";

	@XmlAttribute(name = "active", required = true)
	private int active;

	@XmlAttribute(name = "attend_type", required = true)
	private AttendType attendType;

	@XmlAttribute(name="attend_num")
	private int attendNum;

	@XmlAttribute(name="period_start", required=true)
	@XmlSchemaType(name="dateTime")
	protected XMLGregorianCalendar pStart;

	@XmlAttribute(name="period_end", required=true)
	@XmlSchemaType(name="dateTime")
	protected XMLGregorianCalendar pEnd;

	protected List<AtreianPassportRewards> atreian_passport_reward;

	public int getActive() {
		return active;
	}

	public AttendType getAttendType() {
		return attendType;
	}

	public int getAttendNum() {
		return attendNum;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DateTime getPeriodStart() {
		return DateTimeUtil.getDateTime(pStart.toGregorianCalendar());
	}

	public DateTime getPeriodEnd() {
		return DateTimeUtil.getDateTime(pEnd.toGregorianCalendar());
	}

	public List<AtreianPassportRewards> getAtreianPassportRewards() {
		if (atreian_passport_reward == null) {
			atreian_passport_reward = new ArrayList<AtreianPassportRewards>();
		}
		return atreian_passport_reward;
	}
}
