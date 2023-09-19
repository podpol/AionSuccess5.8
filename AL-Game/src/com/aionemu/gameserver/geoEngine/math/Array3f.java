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
package com.aionemu.gameserver.geoEngine.math;

import com.aionemu.gameserver.configs.main.GeoDataConfig;

import javolution.context.ObjectFactory;
import javolution.lang.Reusable;

/**
 * @author MrPoke
 */
public class Array3f implements Reusable {

	@SuppressWarnings("rawtypes")
	private static final ObjectFactory FACTORY = new ObjectFactory() {

		@Override
		public Object create() {
			return new Array3f();
		}
	};
	public float a = 0;
	public float b = 0;
	public float c = 0;

	@Override
	public void reset() {
		a = 0;
		b = 0;
		c = 0;
	}

	/**
	 * Returns a new, preallocated or {@link #recycle recycled} text builder (on the stack when executing in a {@link javolution.context.StackContext StackContext}).
	 *
	 * @return a new, preallocated or recycled text builder instance.
	 */
	public static Array3f newInstance() {
		if (GeoDataConfig.GEO_OBJECT_FACTORY_ENABLE) {
			return (Array3f) FACTORY.object();
		}
		else {
			return new Array3f();
		}
	}

	/**
	 * Recycles a text builder {@link #newInstance() instance} immediately (on the stack when executing in a {@link javolution.context.StackContext StackContext}).
	 */
	@SuppressWarnings("unchecked")
	public static void recycle(Array3f instance) {
		if (GeoDataConfig.GEO_OBJECT_FACTORY_ENABLE) {
			FACTORY.recycle(instance);
		}
		else {
			instance = null;
		}
	}
}
