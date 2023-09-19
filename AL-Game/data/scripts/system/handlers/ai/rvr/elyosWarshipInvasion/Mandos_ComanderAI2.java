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
package ai.rvr.elyosWarshipInvasion;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.services.RvrService;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("mandos_comander")
public class Mandos_ComanderAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature instanceof Player) {
			final Player player = (Player) creature;
			if (MathUtil.getDistance(getOwner(), player) <= 15) {
				if (startedEvent.compareAndSet(false, true)) {
					//Victory is a certainty for us Elyos, yet you continue your futile struggles.
				    sendMsg(1501537, getObjectId(), false, 3000);
					//You're no fools. Haven't you yet realized that this isn't a battle you can win?
				    sendMsg(1501538, getObjectId(), false, 9000);
					//Are there really this many Asmodians ignorant of their fate…?
					//Then I'll teach you here and now.
					//That today is your last day alive!
				    sendMsg(1501539, getObjectId(), false, 15000);
					//Lord Ariel! Please show your power to that Asmodian!
				    sendMsg(1501541, getObjectId(), false, 21000);
				}
			}
		}
	}
	
	@Override
	protected void handleDied() {
		RvrService.getInstance().stopRvr(4);
		spawn(833766, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Dimensional Vortex.
		super.handleDied();
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}