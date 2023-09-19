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

@AIName("archon_scout")
public class Archon_ScoutAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
			case 240887: //Archon Warrior.
			case 240888: //Archon Mage.
			case 240889: //Archon Scout.
			case 240890: //Archon Marksman.
				startLifeTask();
			break;
        }
		//Wretches!! Your resistance shall be futile.
		sendMsg(1501534, getObjectId(), false, 5000);
		//Let's show these cowardly Elyos the might of the Asmodians!
		sendMsg(1501535, getObjectId(), false, 8000);
		//Don't give up! The will of Empyrean Lord Azphel is with us.
		sendMsg(1501536, getObjectId(), false, 11000);
		//Empyrean Lord Azphel! Please give me strength.
		sendMsg(1501540, getObjectId(), false, 14000);
	}
	
	private void startLifeTask() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				AI2Actions.deleteOwner(Archon_ScoutAI2.this);
			}
		}, 300000); //5 Minutes.
	}
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}