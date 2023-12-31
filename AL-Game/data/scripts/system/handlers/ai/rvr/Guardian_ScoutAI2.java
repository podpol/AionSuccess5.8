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
package ai.rvr;

import com.aionemu.commons.network.util.ThreadPoolManager;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.services.NpcShoutsService;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("guardian_scout")
public class Guardian_ScoutAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 240971: //Guardian Warrior.
			case 240972: //Guardian Mage.
			case 240973: //Guardian Scout.
			case 240974: //Guardian Marksman.
				startLifeTask();
			break;
        }
		//Victory is a certainty for us Elyos, yet you continue your futile struggles.
		sendMsg(1501537, getObjectId(), false, 5000);
		//You're no fools. Haven't you yet realized that this isn't a battle you can win ?
		sendMsg(1501538, getObjectId(), false, 8000);
		//Are there really this many Asmodians ignorant of their fate… ?
		//Then I'll teach you here and now. That today is your last day alive!
		sendMsg(1501539, getObjectId(), false, 11000);
		//Lord Ariel! Please show your power to that Asmodian!
		sendMsg(1501541, getObjectId(), false, 14000);
	}
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Guardian_ScoutAI2.this);
			}
		}, 300000); //5 Minutes.
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}