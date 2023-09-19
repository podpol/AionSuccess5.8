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
package com.aionemu.gameserver.utils.gametime;


import com.aionemu.gameserver.configs.main.GSConfig;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.GregorianCalendar;

/**
 * @author Rolandas
 *
 */
public final class DateTimeUtil
{
	static Logger log = LoggerFactory.getLogger(DateTimeUtil.class);

	private static boolean canApplyZoneChange = false;
	
	public static void init()
	{
		try 
		{
			if (!GSConfig.TIME_ZONE_ID.isEmpty()) {
				// just check the validity on start (if invalid zone specified in the switch, default id used)
				DateTimeZone.forID(System.getProperty("Duser.timezone"));
				DateTimeZone.forID(GSConfig.TIME_ZONE_ID);
				canApplyZoneChange = true;
			}
		} 
		catch (Throwable e)
		{
			log.error("Invalid or not supported timezones specified!!!\n" + 
					 "Use both -Duser.timezone=\"timezone_id\" switch from command line\n" + 
					 "and add a valid value for GSConfig.TIME_ZONE_ID");
		}
	}
	
	// Get now date and time
	public static DateTime getDateTime()
	{
		DateTime dt = new DateTime();
		if (canApplyZoneChange)
		{
			return dt.withZoneRetainFields(DateTimeZone.forID(GSConfig.TIME_ZONE_ID));
		}
		return dt;
	}
	
	public static DateTime getDateTime(String isoDateTime)
	{
		DateTime dt = new DateTime(isoDateTime);
		if (canApplyZoneChange)
		{
			return dt.withZoneRetainFields(DateTimeZone.forID(GSConfig.TIME_ZONE_ID));
		}
		return dt;
	}
	
	public static DateTime getDateTime(GregorianCalendar calendar)
	{
		DateTime dt = new DateTime(calendar);
		if (canApplyZoneChange)
		{
			return dt.withZoneRetainFields(DateTimeZone.forID(GSConfig.TIME_ZONE_ID));
		}
		return dt;
	}
	
	public static DateTime getDateTime(long millisSinceSeventies)
	{
		DateTime dt = new DateTime(millisSinceSeventies);
		if (canApplyZoneChange)
		{
			return dt.withZoneRetainFields(DateTimeZone.forID(GSConfig.TIME_ZONE_ID));
		}
		return dt;
	}

	public static boolean canApplyZoneChange()
	{
		return canApplyZoneChange;
	}

}
