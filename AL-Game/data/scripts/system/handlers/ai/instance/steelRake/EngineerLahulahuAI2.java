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
package ai.instance.steelRake;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("engineerlahulahu")
public class EngineerLahulahuAI2 extends AggressiveNpcAI2
{
	private boolean isStart = false;
	private boolean isUsedSkill = false;
	private int skill = 18153;
	private Npc npc;
	private Npc npc1;
	private Npc npc2;
	private Npc npc3;
	private Npc npc4;
	private Npc npc5;
	private Npc npc6;
	private Npc npc7;
	private Npc npc8;
	private Npc npc9;
	private Npc npc10;
	private Npc npc11;
	
	private void registerNpcs() {
		WorldMapInstance instance = getPosition().getWorldMapInstance();
		npc = instance.getNpc(281111);
		npc1 = instance.getNpc(281325);
		npc2 = instance.getNpc(281323);
		npc3 = instance.getNpc(281322);
		npc4 = instance.getNpc(281326);
		npc5 = instance.getNpc(281113);
		npc6 = instance.getNpc(281324);
		npc7 = instance.getNpc(281109);
		npc8 = instance.getNpc(281112);
		npc9 = instance.getNpc(281114);
		npc10 = instance.getNpc(281108);
		npc11 = instance.getNpc(281110);
	}
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		checkPercentage(getLifeStats().getHpPercentage());
	}
	
	private void checkPercentage(int hpPercentage) {
		if (hpPercentage <= 95 && !isStart) {
			registerNpcs();
			isStart = true;
			AI2Actions.useSkill(this, 18131);
			useSkills();
		} if (hpPercentage <= 25 && !isUsedSkill) {
			isUsedSkill = true;
			getEffectController().removeEffect(18131);
			AI2Actions.useSkill(this, 18132);
		}
	}
	
	@Override
	protected void handleBackHome() {
		isStart = false;
		isUsedSkill = false;
		super.handleBackHome();
	}
	
	private void doSchedule() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				useSkills();
			}
		}, 10000);
	}
	
	private void useSkills() {
		if (getPosition().isSpawned() && !isAlreadyDead() && isStart) {
			switch (Rnd.get(1, 8)) {
				case 1:
					if (npc != null) {
						npc.setTarget(npc);
						npc.getController().useSkill(skill);
					} if (npc1 != null) {
						npc1.setTarget(npc1);
						npc1.getController().useSkill(skill);
					}
				break;
				case 2:
					if (npc2 != null) {
						npc2.setTarget(npc2);
						npc2.getController().useSkill(skill);
					} if (npc3 != null) {
						npc3.setTarget(npc3);
						npc3.getController().useSkill(skill);
					}
				break;
				case 3:
					if (npc4 != null) {
						npc4.setTarget(npc4);
						npc4.getController().useSkill(skill);
					} if (npc5 != null) {
						npc5.setTarget(npc5);
						npc5.getController().useSkill(skill);
					}
				break;
				case 4:
					if (npc6 != null) {
						npc6.setTarget(npc6);
						npc6.getController().useSkill(skill);
					} if (npc7 != null) {
						npc7.setTarget(npc7);
						npc7.getController().useSkill(skill);
					}
				break;
				case 5:
					if (npc8 != null) {
						npc8.setTarget(npc8);
						npc8.getController().useSkill(skill);
					}
				break;
				case 6:
					if (npc9 != null) {
						npc9.setTarget(npc9);
						npc9.getController().useSkill(skill);
					}
				break;
				case 7:
					if (npc10 != null) {
						npc10.setTarget(npc10);
						npc10.getController().useSkill(skill);
					}
				break;
				case 8:
					if (npc11 != null) {
						npc11.setTarget(npc11);
						npc11.getController().useSkill(skill);
					}
				break;
			}
			doSchedule();
		}
	}
}