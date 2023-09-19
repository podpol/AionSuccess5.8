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
package com.aionemu.gameserver.model;

/**
 * Chat types that are supported by Aion.
 *
 * @author SoulKeeper, Imaginary
 */
public enum ChatType
{
	NORMAL(0x00), // Normal chat (White)
	SHOUT(0x03), // Shout chat (Orange)
	WHISPER(0x04), // Whisper chat (Green)
	GROUP(0x05), // Group chat (Blue)
	ALLIANCE(0x06), // Alliance chat (Aqua)
	GROUP_LEADER(0x07), // Group Leader chat (???)
	LEAGUE(0x08), // League chat (Dark Blue)
	LEAGUE_ALERT(0x09), // League chat (Orange)
	LEGION(0x0A), // Legion chat (Green)
	SHOUT2(0x0C), // Shout chat (Orange) visible in "All" chat thumbnail only !
	COMMAND(0x1A), // Command chat (Yellow)
	COALITION(0x1B), // Command chat (Blue)
	COALITION_ALERT(0x1C), // Coalition chat (Orange)
	ANNOUNCE(0x37), //Announce.

	CH1(0x0E),
	CH2(0x0F),
	CH3(0x10),
	CH4(0x11),
	CH5(0x12),
	CH6(0x13),
	CH7(0x14),
	CH8(0x15),
	CH9(0x16),
	CH10(0x17),

	/**
	 * Global chat types
	 */
	GOLDEN_YELLOW(0x20, true), // Same with 0x21 System message (Dark Yellow), most commonly used, no "center" equivalent.

	YELLOW(0x22, true), // System message (Yellow), visible in "All" chat thumbnail only !
	WHITE(0x23, true), // System message (White), visible in "All" chat thumbnail only !
	BRIGHT_YELLOW(0x25, true), // System message (Light Yellow), visible in "All" chat thumbnail only !
	WHITE_CENTER(0x26, true), // Periodic Notice (White && Box on screen center)
	YELLOW_CENTER(0x27, true), // Periodic Announcement(Yellow && Box on screen center)
	BRIGHT_YELLOW_CENTER(0x28, true); // System Notice (Light Yellow && Box on screen center)


	private final int intValue;
	private boolean sysMsg;

	/**
	 * Constructor
	 *    client chat type integer representation
	 */
	private ChatType(int intValue) {
		this(intValue, false);
	}

	/**
	 * Converts ChatType value to integer representation
	 *
	 * @return chat type in client
	 */
	public int toInteger() {
		return intValue;
	}

	/**
	 * Returns ChatType by it's integer representation
	 *
	 * @param integerValue
	 *          integer value of chat type
	 * @return ChatType
	 * @throws IllegalArgumentException
	 *           if can't find suitable chat type
	 */
	public static ChatType getChatTypeByInt(int integerValue) throws IllegalArgumentException {
		for (ChatType ct : ChatType.values()) {
			if (ct.toInteger() == integerValue) {
				return ct;
			}
		}

		throw new IllegalArgumentException("Unsupported chat type: " + integerValue);
	}

	private ChatType(int intValue, boolean sysMsg) {
		this.intValue = intValue;
		this.sysMsg = sysMsg;
	}

	/**
	 * @return true if this is one of system message ( all races can read chat )
	 */
	public boolean isSysMsg() {
		return sysMsg;
	}
}
