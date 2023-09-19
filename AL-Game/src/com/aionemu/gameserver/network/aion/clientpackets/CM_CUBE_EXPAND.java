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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.CubeExpandService;
import com.aionemu.gameserver.services.player.LunaShopService;

/**
 * @author Ranastic (Encom)
 */

public class CM_CUBE_EXPAND extends AionClientPacket
{
	int type;
	
	public CM_CUBE_EXPAND(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		type = readC();
	}
	
	private boolean canLunaExpand(Player player, int needLuna) {
		boolean canExpand = false;
		long playerLuna = player.getLunaAccount();
		if (playerLuna >= needLuna) {
			try {
				LunaShopService.getInstance().lunaPointController(player, (int) (player.getLunaAccount() - needLuna));
				canExpand = true;
			} catch (Exception e) {
			}
		}
		return canExpand;
	}
	
	@Override
	protected void runImpl() {
		Player activePlayer = getConnection().getActivePlayer();
		if (type == 0) { //Kinah
			if (activePlayer.getNpcExpands() < 15) {
				if (activePlayer.getNpcExpands() == 0) {
					if (activePlayer.getInventory().tryDecreaseKinah(1000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 1) {
					if (activePlayer.getInventory().tryDecreaseKinah(10000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 2) {
					if (activePlayer.getInventory().tryDecreaseKinah(50000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 3) {
					if (activePlayer.getInventory().tryDecreaseKinah(150000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 4) {
					if (activePlayer.getInventory().tryDecreaseKinah(300000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 5) {
					if (activePlayer.getInventory().tryDecreaseKinah(3000000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 6) {
					if (activePlayer.getInventory().tryDecreaseKinah(6000000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 7) {
					if (activePlayer.getInventory().tryDecreaseKinah(12000000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 8) {
					if (activePlayer.getInventory().tryDecreaseKinah(24000000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				} else if (activePlayer.getNpcExpands() == 9) {
					if (activePlayer.getInventory().tryDecreaseKinah(48000000)) {
						CubeExpandService.expand(activePlayer, true);
					}
				}
			}
		}
		//Cube Expansion Key.
		else if (type == 1) {
			if (activePlayer.getNpcExpands() < 10) {
				if (activePlayer.getInventory().decreaseByItemId(186000419, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000440, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000444, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000445, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 11) {
				if (activePlayer.getInventory().decreaseByItemId(186000419, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000440, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000444, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000445, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 12) {
				if (activePlayer.getInventory().decreaseByItemId(186000419, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000440, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000444, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000445, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 13) {
				if (activePlayer.getInventory().decreaseByItemId(186000419, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000440, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000444, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000445, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 14) {
				if (activePlayer.getInventory().decreaseByItemId(186000419, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000440, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000444, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000445, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 15) {
				if (activePlayer.getInventory().decreaseByItemId(186000419, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000440, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000444, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				} else if (activePlayer.getInventory().decreaseByItemId(186000445, 1)) { //Cube Expansion Key.
					CubeExpandService.expand(activePlayer, true);
				}
			}
			//Luna.
			else if (activePlayer.getNpcExpands() < 10) {
				if (canLunaExpand(activePlayer, 15)) {
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 11) {
				if (canLunaExpand(activePlayer, 15)) {
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 12) {
				if (canLunaExpand(activePlayer, 15)) {
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 13) {
				if (canLunaExpand(activePlayer, 15)) {
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 14) {
				if (canLunaExpand(activePlayer, 15)) {
					CubeExpandService.expand(activePlayer, true);
				}
			} else if (activePlayer.getNpcExpands() < 15) {
				if (canLunaExpand(activePlayer, 15)) {
					CubeExpandService.expand(activePlayer, true);
				}
			}
		}
	}
}