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
package com.aionemu.gameserver.geoEngine.scene.mesh;

import java.nio.Buffer;
import java.nio.ShortBuffer;

/**
 * @author lex
 */
public class IndexShortBuffer extends IndexBuffer {

	private ShortBuffer buf;

	public IndexShortBuffer(ShortBuffer buffer) {
		this.buf = buffer;
	}

	@Override
	public int get(int i) {
		return buf.get(i) & 0x0000FFFF;
	}

	@Override
	public void put(int i, int value) {
		buf.put(i, (short) value);
	}

	@Override
	public int size() {
		return buf.limit();
	}

	@Override
	public Buffer getBuffer() {
		return buf;
	}
}
