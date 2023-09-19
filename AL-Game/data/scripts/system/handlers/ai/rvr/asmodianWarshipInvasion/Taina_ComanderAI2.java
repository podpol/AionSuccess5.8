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
package ai.rvr.asmodianWarshipInvasion;

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

@AIName("taina_comander")
public class Taina_ComanderAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		if (creature instanceof Player) {
			final Player player = (Player) creature;
			if (MathUtil.getDistance(getOwner(), player) <= 15) {
				if (startedEvent.compareAndSet(false, true)) {
					//Wretches!! Your resistance shall be futile.
				    sendMsg(1501534, getObjectId(), false, 3000);
					//Let's show these cowardly Elyos the might of the Asmodians!
				    sendMsg(1501535, getObjectId(), false, 9000);
					//Don't give up! The will of Empyrean Lord Azphel is with us.
				    sendMsg(1501536, getObjectId(), false, 15000);
					//Empyrean Lord Azphel! Please give me strength.
				    sendMsg(1501540, getObjectId(), false, 21000);
				}
			}
		}
	}
	
	@Override
	protected void handleDied() {
		RvrService.getInstance().stopRvr(3);
		spawn(833766, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Dimensional Vortex.
		super.handleDied();
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}