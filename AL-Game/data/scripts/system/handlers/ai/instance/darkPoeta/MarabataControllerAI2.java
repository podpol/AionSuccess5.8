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

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Npc;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("marabatacontroller")
public class MarabataControllerAI2 extends NpcAI2
{
	private Npc getBoss() {
		Npc npc = null;
		switch (getNpcId()) {
			case 700443:
			case 700444:
			case 700442:
				npc = getPosition().getWorldMapInstance().getNpc(214850);
			break;
			case 700446:
			case 700447:
			case 700445:
				npc = getPosition().getWorldMapInstance().getNpc(214851);
			break;
			case 700440:
			case 700441:
			case 700439:
				npc = getPosition().getWorldMapInstance().getNpc(214849);
			break;
		}
		return npc;
	}
	
	private void applyEffect(boolean remove) {
		Npc boss = getBoss();
		if (boss != null && !boss.getLifeStats().isAlreadyDead()) {
			switch (getNpcId()) {
				case 700443:
				case 700446:
				case 700440:
				    if (remove) {
					    boss.getEffectController().removeEffect(18556);
				    } else {
					    boss.getController().useSkill(18556);
				    }
				break;
				case 700444:
				case 700447:
				case 700441:
				case 700442:
				case 700445:
				case 700439:
				    if (remove) {
					    boss.getEffectController().removeEffect(18110);
				    } else {
					    boss.getController().useSkill(18110);
				    }
				break;
			}
		}
	}
	
	@Override
	protected void handleDied() {
		super.handleDied();
		applyEffect(true);
		AI2Actions.deleteOwner(this);
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		applyEffect(false);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				useSkill();
			}
		}, 2000);
	}
	
	private void useSkill() {
		if (isAlreadyDead()) {
			return;
		}
		AI2Actions.targetSelf(this);
		int skill = 0;
		switch (getNpcId()) {
			case 700443:
			case 700446:
			case 700440:
				skill = 18554;
			break;
			case 700444:
			case 700447:
			case 700441:
				skill = 18555;
			break;
			case 700442:
			case 700445:
			case 700439:
				skill = 18553;
			break;
		}
		AI2Actions.useSkill(this, skill);
	}
}