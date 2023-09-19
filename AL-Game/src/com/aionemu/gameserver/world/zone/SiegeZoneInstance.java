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
package com.aionemu.gameserver.world.zone;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.zone.ZoneInfo;
import com.aionemu.gameserver.world.knownlist.Visitor;
import javolution.util.FastMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author MrPoke
 *
 */
public class SiegeZoneInstance extends ZoneInstance{

	private static final Logger log = LoggerFactory.getLogger(SiegeZoneInstance.class);
	
	private FastMap<Integer, Player> players = new FastMap<Integer, Player>();
	
	/**
	 * @param mapId
	 * @param template
	 * @param handler
	 */
	public SiegeZoneInstance(int mapId, ZoneInfo template) {
		super(mapId, template);
	}
	
	@Override
	public boolean onEnter(Creature creature) {
		if (super.onEnter(creature)){
			if (creature instanceof Player)
				players.put(creature.getObjectId(), (Player)creature);
			return true;
		}
		return false;
	}

	@Override
	public synchronized boolean onLeave(Creature creature) {
		if (super.onLeave(creature)){
			if (creature instanceof Player)
				players.remove(creature.getObjectId());
			return true;
		}
		return false;
	}
	
	public void doOnAllPlayers(Visitor<Player> visitor) {
		try {
			for (FastMap.Entry<Integer, Player> e = players.head(), mapEnd = players.tail(); (e = e.getNext()) != mapEnd;) {
				Player player = e.getValue();
				if (player != null) {
					visitor.visit(player);
				}
			}
		}
		catch (Exception ex) {
			log.error("Exception when running visitor on all players" + ex);
		}
	}
}
