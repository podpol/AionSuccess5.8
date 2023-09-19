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
package com.aionemu.gameserver.utils;

import java.nio.ByteBuffer;

import com.aionemu.gameserver.configs.main.NameConfig;

public class Util {

	/**
	 * @param s
	 */
	public static void printSection(String s) {
		if (!s.isEmpty())
			s = "[ " + s + " ]";

		while (s.length() < 79) {
			s = "=" + s + "=";
		}

		System.out.println("");
		System.out.println(s);
		System.out.println("");
	}

	public static void printSsSection(String s) {
		s = "( " + s + " )";

		while (s.length() < 79) {
			s = "-" + s + "-";
		}

		System.out.println("");
		System.out.println(s);
		System.out.println("");
	}

	public static void printProgressBarHeader(int size) {
		StringBuilder header = new StringBuilder("0%[");
		for (int i = 0; i < size; i++) {
			header.append("-");
		}
		header.append("]100%");
		System.out.println(header);
		System.out.print("   ");
	}

	public static void printCurrentProgress() {
		System.out.print("+");
	}

	public static void printEndProgress() {
		System.out.print(" Done. \n");
	}

	public static void printRotatingBarHeader(int dataSize) {
		String anim = "|/-\\";
		System.out.print("\r" + anim.charAt(Math.round(dataSize / 50) % anim.length()) + " Processing data : " + dataSize + " data" + (dataSize <= 1 ? "." : "s.                 "));
		System.out.print("\r");
	}

	/**
	 * Convert data from given ByteBuffer to hex
	 *
	 * @param data
	 * @return hex
	 */
	public static String toHex(ByteBuffer data) {
		StringBuilder result = new StringBuilder();
		int counter = 0;
		int b;
		while (data.hasRemaining()) {
			if (counter % 16 == 0) {
				result.append(String.format("%04X: ", counter));
			}

			b = data.get() & 0xff;
			result.append(String.format("%02X ", b));

			counter++;
			if (counter % 16 == 0) {
				result.append("  ");
				toText(data, result, 16);
				result.append("\n");
			}
		}
		int rest = counter % 16;
		if (rest > 0) {
			for (int i = 0; i < 17 - rest; i++) {
				result.append("   ");
			}
			toText(data, result, rest);
		}
		return result.toString();
	}

	/**
	 * Convert data from given ByteBuffer to hex
	 *
	 * @param data
	 * @return hex
	 */
	public static String toHexStream(ByteBuffer data) {
		StringBuilder result = new StringBuilder();
		int counter = 0;
		int b;
		while (data.hasRemaining()) {
			b = data.get() & 0xff;
			result.append(String.format("%02X ", b));

			counter++;
			if (counter % 16 == 0) {
				result.append("\n");
			}
		}
		return result.toString();
	}

	/**
	 * Gets last <tt>cnt</tt> read bytes from the <tt>data</tt> buffer and puts into <tt>result</tt> buffer in special format:
	 * <ul>
	 * <li>if byte represents char from partition 0x1F to 0x80 (which are normal ascii chars) then it's put into buffer as it is</li>
	 * <li>otherwise dot is put into buffer</li>
	 * </ul>
	 *
	 * @param data
	 * @param result
	 * @param cnt
	 */
	private static void toText(ByteBuffer data, StringBuilder result, int cnt) {
		int charPos = data.position() - cnt;
		for (int a = 0; a < cnt; a++) {
			int c = data.get(charPos++);
			if (c > 0x1f && c < 0x80) {
				result.append((char) c);
			}
			else {
				result.append('.');
			}
		}
	}

	/**
	 * Converts name to valid pattern For example : "atracer" -> "Atracer"
	 *
	 * @param name
	 * @return String
	 */
	public static String convertName(String name) {
		if (!name.isEmpty()) {
			if (NameConfig.ALLOW_CUSTOM_NAMES) {
				return name;
			}
			else {
				return name.substring(0, 1).toUpperCase() + name.toLowerCase().substring(1);
			}
		}
		else {
			return "";
		}
	}
}
