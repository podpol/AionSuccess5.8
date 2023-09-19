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
package com.aionemu.gameserver.model.shield;

import com.aionemu.gameserver.controllers.ShieldController;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.templates.shield.ShieldTemplate;
import com.aionemu.gameserver.utils.idfactory.IDFactory;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldPosition;
import com.aionemu.gameserver.world.knownlist.SphereKnownList;

/**
 * @author Wakizashi
 */
public class Shield extends VisibleObject {

	private ShieldTemplate template = null;
	private String name = null;
	private int id = 0;

	public Shield(ShieldTemplate template) {
		super(IDFactory.getInstance().nextId(), new ShieldController(), null, null, null);

		((ShieldController) getController()).setOwner(this);
		this.template = template;
		this.name = (template.getName() == null) ? "SHIELD" : template.getName();
		this.id = template.getId();
		setKnownlist(new SphereKnownList(this, template.getRadius() * 2));
	}

	public ShieldTemplate getTemplate() {
		return template;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void spawn() {
		World w = World.getInstance();
		WorldPosition position = w.createPosition(template.getMap(), template.getCenter().getX(), template.getCenter().getY(), template
			.getCenter().getZ(), (byte) 0, 0);
		this.setPosition(position);
		w.storeObject(this);
		w.spawn(this);
	}
}
