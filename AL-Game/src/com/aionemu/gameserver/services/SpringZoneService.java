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

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.springzone.SpringObject;
import com.aionemu.gameserver.model.templates.springzones.SpringTemplate;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.knownlist.Visitor;
import javolution.util.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/****/
/** Author Rinzler (Encom)
/****/

public class SpringZoneService
{
	Logger log = LoggerFactory.getLogger(SpringZoneService.class);
	private FastList<SpringObject> springObjects = new FastList<SpringObject>();
	
	private SpringZoneService() {
		for (SpringTemplate t : DataManager.SPRING_OBJECTS_DATA.getSpringObject()) {
			SpringObject obj = new SpringObject(t, 0);
			obj.spawn();
			springObjects.add(obj);
		}
		startSpring();
	}
	
	private void startSpring() {
		ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			public void run() {
				for (final SpringObject obj : springObjects)
				obj.getKnownList().doOnAllPlayers(new Visitor<Player>() {
					public void visit(Player player) {
						if ((MathUtil.isIn3dRange(obj, player, obj.getRange())) &&
						   (!player.getEffectController().hasAbnormalEffect(17560))) { //Bless Of Guardian Spring.
							SkillEngine.getInstance().getSkill(player, 17560, 1, player).useNoAnimationSkill();
						}
					}
				});
			}
		}, 1000, 1000);
	}
	
	public static final SpringZoneService getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		protected static final SpringZoneService instance = new SpringZoneService();
	}
}