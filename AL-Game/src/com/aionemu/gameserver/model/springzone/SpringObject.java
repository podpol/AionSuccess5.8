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
package com.aionemu.gameserver.model.springzone;

import com.aionemu.gameserver.controllers.VisibleObjectController;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.templates.springzones.SpringTemplate;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.NpcKnownList;

/****/
/** Author Rinzler (Encom)
/****/

public class SpringObject extends VisibleObject
{
	private float range;
	private SpringTemplate template;
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public SpringObject(SpringTemplate template, int instanceId) {
		super(IDFactory.getInstance().nextId(), new VisibleObjectController() {
		}, null, null, World.getInstance().createPosition(template.getMapId(), template.getX(), template.getY(), template.getZ(), (byte) 0, instanceId));
		this.template = template;
		range = template.getRange();
		setKnownlist(new NpcKnownList(this));
	}
	
	public SpringTemplate getTemplate() {
		return template;
	}
	
	public String getName() {
		return "";
	}
	
	public float getRange() {
		return range;
	}
	
	public void spawn() {
		World w = World.getInstance();
		w.storeObject(this);
		w.spawn(this);
	}
}