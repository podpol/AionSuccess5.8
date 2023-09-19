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
package ai.instance.darkPoeta;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.WorldMapInstance;

import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("drana_lump")
public class Drana_LumpAI2 extends AggressiveNpcAI2
{
	private Future<?> dranaBreakTask;
	
	@Override
	public void think() {
	}
	
	@Override
	protected void handleSpawned() {
		startDranaBreak();
		super.handleSpawned();
	}
	
	private void startDranaBreak() {
		final Npc spallerEchtra = getPosition().getWorldMapInstance().getNpc(214880); //Spaller Echtra.
		final Npc spallerRakanatra = getPosition().getWorldMapInstance().getNpc(215388); //Spaller Rakanatra.
		final Npc spallerDhatra = getPosition().getWorldMapInstance().getNpc(215389); //Spaller Dhatra.
		WorldMapInstance instance = getPosition().getWorldMapInstance();
		for (Player player: instance.getPlayersInside()) {
			if (MathUtil.isIn3dRange(player, spallerEchtra, 8)) {
				dranaBreak();
			} if (MathUtil.isIn3dRange(player, spallerRakanatra, 8)) {
				dranaBreak();
			} if (MathUtil.isIn3dRange(player, spallerDhatra, 8)) {
				dranaBreak();
			}
		}
	}
	
	private void dranaBreak() {
		dranaBreakTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AI2Actions.targetCreature(Drana_LumpAI2.this, getPosition().getWorldMapInstance().getNpc(214880)); //Spaller Echtra.
				AI2Actions.targetCreature(Drana_LumpAI2.this, getPosition().getWorldMapInstance().getNpc(215388)); //Spaller Rakanatra.
				AI2Actions.targetCreature(Drana_LumpAI2.this, getPosition().getWorldMapInstance().getNpc(215389)); //Spaller Dhatra.
				AI2Actions.useSkill(Drana_LumpAI2.this, 18536); //Drana Break.
			}
		}, 1000, 6000);
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}