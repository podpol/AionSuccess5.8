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
package ai.event.conquestOffering;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.HTMLService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldPosition;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("conquest_gelkmaros")
public class Conquest_Gelkmaros_BossAI2 extends AggressiveNpcAI2
{
	@Override
    protected void handleSpawned() {
        super.handleSpawned();
		boostDefense();
    }
	
	@Override
	protected void handleDied() {
		final WorldPosition p = getPosition();
		if (p != null) {
			sendGuide();
		} switch (Rnd.get(1, 2)) {
			case 1:
			    spawnSecretPortal();
			break;
			case 2:
			break;
		}
		super.handleDied();
	}
	
    private void spawnSecretPortal() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(833021, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Secret Portal.
			}
		}, 15000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				despawnNpc(833021); //Secret Portal.
			}
		}, 300000); //5 Minutes.
    }
	
    private void spawnQuestionablePortal() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(833022, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Questionable Portal.
			}
		}, 15000);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				despawnNpc(833022); //Secret Portal.
			}
		}, 300000); //5 Minutes.
    }
	
    private void spawnConquestNpcBuff() {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				switch (Rnd.get(1, 4)) {
					case 1:
						spawn(856175, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Pawrunerk.
					break;
					case 2:
						spawn(856176, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Chitrunerk.
					break;
					case 3:
						spawn(856177, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Rapirunerk.
					break;
					case 4:
						spawn(856178, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) 0); //Dandrunerk.
					break;
				}
			}
		}, 15000);
	}
	
	private void boostDefense() {
		SkillEngine.getInstance().getSkill(getOwner(), 21923, 1, getOwner()).useNoAnimationSkill(); //Boost Defense.
	}
	
	private void sendGuide() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(player, getOwner(), 15)) {
					HTMLService.sendGuideHtml(player, "Conquest_Offering");
				}
			}
		});
	}
	
	private void despawnNpc(int npcId) {
		if (getPosition().getWorldMapInstance().getNpcs(npcId) != null) {
			List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
			for (Npc npc: npcs) {
				npc.getController().onDelete();
			}
		}
	}
}