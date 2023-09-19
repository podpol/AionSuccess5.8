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
package instance;

import java.util.*;

import com.aionemu.gameserver.instance.handlers.GeneralInstanceHandler;
import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/** Source: https://www.youtube.com/watch?v=8Qt-ZODwhoA
/****/

@InstanceID(301580000)
public class SanctuaryDungeonInstance extends GeneralInstanceHandler
{
	private Race spawnRace;
	
	@Override
	public void onEnterInstance(Player player) {
		if (spawnRace == null) {
			spawnRace = player.getRace();
			spawnSanctuaryRace();
		}
	}
	
	private void spawnSanctuaryRace() {
		//Npc
		final int Feregran_Weatha = spawnRace == Race.ASMODIANS ? 806080 : 806076;
		spawn(Feregran_Weatha, 432.54724f, 479.6076f, 99.59915f, (byte) 31);
		//Tp
		final int Dungeon_Exit = spawnRace == Race.ASMODIANS ? 806190 : 806189;
		spawn(Dungeon_Exit, 432.7019f, 475.63489f, 99.471016f, (byte) 0, 20);
	}
}