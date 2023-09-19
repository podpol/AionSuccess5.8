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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.abyss_op.AbyssOp;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name = "abyss_ops")
@XmlAccessorType(XmlAccessType.FIELD)
public class AbyssOpData
{
	@XmlElement(name="abyss_op")
	private List<AbyssOp> aolist;
	
	@XmlTransient
	private TIntObjectHashMap<AbyssOp> opData = new TIntObjectHashMap<AbyssOp>();
	
	@XmlTransient
	private Map<Integer, AbyssOp> opDataMap = new HashMap<Integer, AbyssOp>(1);
	
	void afterUnmarshal(Unmarshaller paramUnmarshaller, Object paramObject) {
		for (AbyssOp abyssOp: aolist) {
			opData.put(abyssOp.getId(), abyssOp);
			opDataMap.put(abyssOp.getId(), abyssOp);
		}
	}
	
	public int size() {
		return opData.size();
	}
	
	public AbyssOp getAbyssOpId(int id) {
		return opData.get(id);
	}
	
	public Map<Integer, AbyssOp> getAll() {
		return opDataMap;
	}
}