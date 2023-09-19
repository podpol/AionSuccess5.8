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
package com.aionemu.gameserver.model.stats.calc.functions;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.items.ItemSlot;
import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.skillengine.model.Effect;

/**
 * @author ATracer
 */
public class StatDualWeaponMasteryFunction extends StatFunctionProxy {

	public StatDualWeaponMasteryFunction(Effect effect, IStatFunction statFunction) {
		super(effect, statFunction);
	}

	@Override
	public void apply(Stat2 stat) {
		Player player = (Player) stat.getOwner();
		if (player.getEquipment().hasDualWeaponEquipped(ItemSlot.SUB_HAND)) {
			super.apply(stat);
		}
	}
}
