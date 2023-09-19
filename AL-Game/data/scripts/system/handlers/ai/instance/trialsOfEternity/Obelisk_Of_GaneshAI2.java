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
package ai.instance.trialsOfEternity;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.skillengine.SkillEngine;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDEternity_03_Def_Boss_Energy")
public class Obelisk_Of_GaneshAI2 extends NpcAI2
{
	@Override
	public void think() {
	}
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		SkillEngine.getInstance().getSkill(getOwner(), 17746, 60, getOwner()).useNoAnimationSkill();
	}
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			case 246421: //Obelisk Of Ganesh.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(246421, 408.66196f, 1013.1304f, 711.93115f, (byte) 0, 177); //Obelisk Of Ganesh.
					}
				}, 120000);
			break;
			case 246422: //Obelisk Of Ganesh.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(246422, 408.77426f, 1037.3873f, 711.90881f, (byte) 0, 179); //Obelisk Of Ganesh.
					}
				}, 120000);
			break;
			case 246423: //Obelisk Of Ganesh.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(246423, 386.68903f, 1037.3842f, 711.95770f, (byte) 0, 181); //Obelisk Of Ganesh.
					}
				}, 120000);
			break;
			case 246424: //Obelisk Of Ganesh.
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(246424, 386.68146f, 1013.2744f, 711.93091f, (byte) 0, 183); //Obelisk Of Ganesh.
					}
				}, 120000);
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}