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

/**
 * @author MrPoke
 */
public class SafeMath {

	public static int addSafe(int source, int value) throws OverfowException {
		 long s = (long)source+(long)value;
     if (s < Integer.MIN_VALUE ||
         s > Integer.MAX_VALUE) {
         throw new OverfowException(source + " + " + value + " = " + ((long) source + (long) value));
     }
     return (int)s;
	}

	public static long addSafe(long source, long value) throws OverfowException {
		if ((source > 0 && value > Long.MAX_VALUE - source) || (source < 0 && value < Long.MIN_VALUE - source)) {
			throw new OverfowException(source + " + " + value + " = " + ((long) source + (long) value));
		}
		return source + value;
	}

	public static int multSafe(int source, int value) throws OverfowException {
		 long m = ((long)source)*((long)value);
     if (m < Integer.MIN_VALUE ||
         m > Integer.MAX_VALUE) {
         throw new OverfowException(source + " * " + value + " = " + ((long) source * (long) value));
     }
     return (int)m;
	}

	public static long multSafe(long a, long b) throws OverfowException {

		long ret;
		String msg = "overflow: multiply";
		if (a > b) {
			// use symmetry to reduce boundry cases
			ret = multSafe(b, a);
		}
		else {
			if (a < 0) {
				if (b < 0) {
					// check for positive overflow with negative a, negative b
					if (a >= Long.MAX_VALUE / b) {
						ret = a * b;
					}
					else {
						throw new OverfowException(msg);
					}
				}
				else if (b > 0) {
					// check for negative overflow with negative a, positive b
					if (Long.MIN_VALUE / b <= a) {
						ret = a * b;
					}
					else {
						throw new OverfowException(msg);

					}
				}
				else {
					ret = 0;
				}
			}
			else if (a > 0) {
				// check for positive overflow with positive a, positive b
				if (a <= Long.MAX_VALUE / b) {
					ret = a * b;
				}
				else {
					throw new OverfowException(msg);
				}
			}
			else {
				ret = 0;
			}
		}
		return ret;
	}
}
