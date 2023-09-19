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
package com.aionemu.gameserver.model.templates.event;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;

import com.aionemu.gameserver.utils.gametime.DateTimeUtil;

/**
 * @author Ghostfur (Aion-Unique)
 */
@XmlRootElement(name = "atreian_passport")
@XmlAccessorType(value = XmlAccessType.NONE)
public class EventsWindow {

	@XmlAttribute(name = "id", required = true)
	private int id;

	@XmlAttribute(name = "item", required = true)
	private int item;

	@XmlAttribute(name = "count", required = true)
	private long count;

	@XmlAttribute(name = "period_start", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar pStart;

	@XmlAttribute(name = "period_end", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar pEnd;

	@XmlAttribute(name = "remaining_time", required = true)
	private int remaining_time;

	@XmlAttribute(name = "min_level", required = true)
	private int min_level;

	@XmlAttribute(name = "max_level", required = true)
	private int max_level;

	@XmlAttribute(name = "dailyMaxCount", required = true)
	private int dailyMaxCount;

	private Timestamp lastStamp;

	public int getId() {
		return id;
	}

	public int getItemId() {
		return item;
	}

	public long getCount() {
		return count;
	}

	public int getMaxCountOfDay() {
		return dailyMaxCount;
	}

	public DateTime getPeriodStart() {
		return DateTimeUtil.getDateTime(pStart.toGregorianCalendar());
	}

	public DateTime getPeriodEnd() {
		return DateTimeUtil.getDateTime(pEnd.toGregorianCalendar());
	}

	public int getRemainingTime() {
		return remaining_time;
	}

	public int getMinLevel() {
		return min_level;
	}

	public int getMaxLevel() {
		return max_level;
	}

	public Timestamp getLastStamp() {
		return lastStamp;
	}

	public void setLastStamp(Timestamp timestamp) {
		lastStamp = timestamp;
	}
}