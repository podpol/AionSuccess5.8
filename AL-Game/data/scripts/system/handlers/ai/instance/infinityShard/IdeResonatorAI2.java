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
package ai.instance.infinityShard;

import ai.AggressiveNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.NpcType;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.WorldMapInstance;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.List;
import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("ide_resonator")
public class IdeResonatorAI2 extends AggressiveNpcAI2
{
	private Future<?> attackBoostTask;
	
	@Override
	public void think() {
	}
	
	@Override
	protected void handleSpawned() {
		attackBoost();
		startIdeInvulnerable();
		super.handleSpawned();
	}
	
	private void startIdeInvulnerable() {
		final Npc IdeResonator = getPosition().getWorldMapInstance().getNpc(276519); //Ide Resonator.
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				IdeResonator.setTarget(getOwner());
				IdeResonator.setNpcType(NpcType.INVULNERABLE);
				WorldMapInstance instance = getPosition().getWorldMapInstance();
				for (Player player: instance.getPlayersInside()) {
					if (MathUtil.isIn3dRange(player, IdeResonator, 10)) {
						infinityShardFail();
						player.clearKnownlist();
						player.updateKnownlist();
					}
				}
			}
		}, 60000);
	}
	
	private void infinityShardFail() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendSys3Message(player, "\uE005", "You fail <Hyperion> !!!");
				}
			}
		});
		despawnNpc(231073); //Hyperion.
		spawn(730842, 124.669853f, 137.840668f, 113.942917f, (byte) 0); //Infinity Shard Exit.
	}
	
	private void attackBoost() {
		attackBoostTask = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				AI2Actions.targetCreature(IdeResonatorAI2.this, getPosition().getWorldMapInstance().getNpc(231073)); //Hyperion.
				AI2Actions.useSkill(IdeResonatorAI2.this, 21257);
			}
		}, 3000, 8000);
	}
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			case 276519: //Ide Resonator.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(276519, 108.55013f, 138.96940f, 132.60164f, (byte) 0);
					}
				}, 300000);
			break;
			case 231093: //Ide Resonator.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(231093, 126.54710f, 154.47961f, 131.47116f, (byte) 0);
					}
				}, 300000);
			break;
			case 231094: //Ide Resonator.
			    ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(231094, 146.72450f, 139.12267f, 132.68515f, (byte) 0);
					}
				}, 300000);
			break;
			case 231095: //Ide Resonator.
				ThreadPoolManager.getInstance().schedule(new Runnable() {
					@Override
					public void run() {
						spawn(231095, 129.41306f, 121.34766f, 131.47110f, (byte) 0);
					}
				}, 300000);
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	private void despawnNpc(int npcId) {
		if (getPosition().getWorldMapInstance().getNpcs(npcId) != null) {
			List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
			for (Npc npc: npcs) {
				npc.getController().onDelete();
			}
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}