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
package com.aionemu.gameserver.model.siege;

import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Kisk;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.siegelocation.SiegeLegionReward;
import com.aionemu.gameserver.model.templates.siegelocation.SiegeLocationTemplate;
import com.aionemu.gameserver.model.templates.siegelocation.SiegeReward;
import com.aionemu.gameserver.model.templates.zone.ZoneType;
import com.aionemu.gameserver.world.zone.ZoneInstance;

import java.util.List;

/**
 * @author Source
 */
public class FortressLocation extends SiegeLocation {

	protected List<SiegeReward> siegeRewards;
	protected List<SiegeLegionReward> siegeLegionRewards;
	protected boolean isUnderShield;
    protected boolean isUnderAssault;
	protected boolean isCanTeleport;

	public FortressLocation() {
	}

	public FortressLocation(SiegeLocationTemplate template) {
		super(template);
		this.siegeRewards = template.getSiegeRewards() != null ? template.getSiegeRewards() : null;
		this.siegeLegionRewards = template.getSiegeLegionRewards() != null ? template.getSiegeLegionRewards() : null;
	}

	public List<SiegeReward> getReward() {
		return this.siegeRewards;
	}

	public List<SiegeLegionReward> getLegionReward() {
		return this.siegeLegionRewards;
	}


	/**
	 * @return isEnemy
	 */
	public boolean isEnemy(Creature creature) {
    return creature.getRace().getRaceId() != getRace().getRaceId();
  }

	/**
	 * @return isUnderShield
	 */
	@Override
	public boolean isUnderShield() {
		return this.isUnderShield;
	}

	/**
	 * @param value new undershield value
	 */
	@Override
	public void setUnderShield(boolean value) {
		this.isUnderShield = value;
	}

	/**
	 * @return isCanTeleport
	 */
	@Override
	public boolean isCanTeleport(Player player) {
	    if (player == null)
      return isCanTeleport;
		return isCanTeleport && player.getRace().getRaceId() == getRace().getRaceId();
	}

	/**
	 * @param status Teleportation status
	 */
	@Override
	public void setCanTeleport(boolean status) {
		this.isCanTeleport = status;
	}

	/**
	 * @return DescriptionId object with fortress name
	 */
	public DescriptionId getNameAsDescriptionId() {
		return new DescriptionId(template.getNameId());
	}

	public void onEnterZone(Creature creature, ZoneInstance zone) {
    super.onEnterZone(creature, zone);
    if (isVulnerable())
      creature.setInsideZoneType(ZoneType.SIEGE);
  }
  
	@Override
	public void onLeaveZone(Creature creature, ZoneInstance zone) {
		super.onLeaveZone(creature, zone);
		if (this.isVulnerable())
			creature.unsetInsideZoneType(ZoneType.SIEGE);
	}

	public void clearLocation() {
		for (Creature creature: getCreatures().values()) {
			if ((isEnemy(creature)) && ((creature instanceof Kisk))) {
				Kisk kisk = (Kisk)creature;
				kisk.getController().die();
			}
		}
    }
}