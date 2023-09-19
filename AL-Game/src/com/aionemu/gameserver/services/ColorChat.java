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
package com.aionemu.gameserver.services;

/**
 * @author KorLightning (Encom)
 */

public class ColorChat
{
	/**
	 * @param message
	 * @param color
	 * @return
	 */
	public static String colorChat(String message, String color) {
		StringBuilder sb = new StringBuilder();
		int index = 0;
		int start = 0;
		for (char ch : message.toCharArray()) {
			if (index % 3 == 0) {
				if (start % 2 == 0) {
					if (start > 0) {
						sb.append(";" + color + "][color:");
					} else {
						sb.append("[color:");
					}
				} else if (start % 2 == 1) {
					if (index < message.length()) {
						sb.append(";" + color + "][color:");
					}
				}
				start++;
			}
			sb.append(String.valueOf(ch));
			index++;
		} if (start % 2 == 1) {
			sb.append(";" + color + "]");
		} if (sb.lastIndexOf("[color:") > sb.lastIndexOf(";" + color + "]")) {
			sb.append(";" + color + "]");
		}
		return sb.toString();
	}
}