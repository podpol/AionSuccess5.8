package com.aionemu.gameserver.model.instance.instanceposition;

import com.aionemu.gameserver.model.gameobjects.player.Player;

/**
 * 
 * @author Ranastic
 *
 */
public class HallOfTenacityInstancePosition extends GenerealInstancePosition {
	
	@Override
	public void port(Player player, int zone, int position) {
		switch (position) {
		case 1:
			switch (zone) {
				case 0:
					teleport(player, 384.1f, 285.5f, 231.1f, (byte) 90);
				break;
				case 1:
					teleport(player, 385.0f, 226.6f, 231.1f, (byte) 35);
				break;
			}
		break;
		}
	}
}
