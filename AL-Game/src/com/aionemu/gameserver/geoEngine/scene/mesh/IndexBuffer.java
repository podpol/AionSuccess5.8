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

/**
 * <code>IndexBuffer</code> is an abstraction for integer index buffers, it is used to retrieve indices without knowing in which format they are stored (ushort or uint).
 *
 * @author lex
 */
public abstract class IndexBuffer {

	public abstract int get(int i);

	public abstract void put(int i, int value);

	public abstract int size();

	public abstract Buffer getBuffer();
}
