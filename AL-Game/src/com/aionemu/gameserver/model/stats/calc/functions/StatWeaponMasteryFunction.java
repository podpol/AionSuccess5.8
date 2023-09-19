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
import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.model.stats.container.StatEnum;
import com.aionemu.gameserver.model.templates.item.WeaponType;

/**
 * @author ATracer (based on Mr.Poke WeaponMasteryModifier)
 */
public class StatWeaponMasteryFunction extends StatRateFunction {

	private final WeaponType weaponType;

	public StatWeaponMasteryFunction(WeaponType weaponType, StatEnum name, int value, boolean bonus) {
		super(name, value, bonus);
		this.weaponType = weaponType;
	}

	@Override
	public void apply(Stat2 stat) {
		Player player = (Player) stat.getOwner();
		switch (this.stat) {
			case MAIN_HAND_POWER:
				if (player.getEquipment().getMainHandWeaponType() == weaponType)
					super.apply(stat);
				break;
			case OFF_HAND_POWER:
				if (player.getEquipment().getOffHandWeaponType() == weaponType)
					super.apply(stat);
				break;
			default:
				if (player.getEquipment().getMainHandWeaponType() == weaponType)
					super.apply(stat);
		}

	}

}
