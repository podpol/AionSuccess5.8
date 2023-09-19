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
package instance.danuarReliquary;

import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.instance.handlers.GeneralInstanceHandler;
import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.drop.DropItem;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.services.drop.DropRegistrationService;
import com.aionemu.gameserver.services.player.PlayerReviveService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.Set;
import java.util.concurrent.Future;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@InstanceID(301330000)
public class Lucky_DanuarReliquaryInstance extends GeneralInstanceHandler
{
	private int ideanKilled;
	private int cloneModorKilled;
	private Future<?> luckyReliquaryTask;
	protected boolean isInstanceDestroyed = false;
	
	@Override
    public void onDropRegistered(Npc npc) {
        Set<DropItem> dropItems = DropRegistrationService.getInstance().getCurrentDropMap().get(npc.getObjectId());
		int npcId = npc.getNpcId();
		int index = dropItems.size() + 1;
        switch (npcId) {
            case 701795: //Lucky Danuar Reliquary Box.
                for (Player player: instance.getPlayersInside()) {
                    if (player.isOnline()) {
						dropItems.add(DropRegistrationService.getInstance().regDropItem(index++, player.getObjectId(), npcId, 188053789, 1)); //Major Stigma Support Bundle.
						dropItems.add(DropRegistrationService.getInstance().regDropItem(index++, player.getObjectId(), npcId, 188052388, 1)); //Modor's Equipment Box.
						dropItems.add(DropRegistrationService.getInstance().regDropItem(index++, player.getObjectId(), npcId, 188053083, 1)); //Tempering Solution Chest.
						dropItems.add(DropRegistrationService.getInstance().regDropItem(index++, player.getObjectId(), npcId, 188053353, 1)); //Lucky Danuar Reliquary Bundle.
                    }
                }
            break;
        }
    }
	
   /**
	* Modor activated the Danuar Bomb of grudge.
	*/
	private void startLuckyReliquaryTimer() {
		//Modor activated the Danuar Bomb of grudge. You have 15 minutes to defeat her.
		sendMsgByRace(1401676, Race.PC_ALL, 5000);
		this.sendMessage(1401677, 10 * 60 * 1000); //10 minutes elapsed.
		this.sendMessage(1401678, 15 * 60 * 1000); //The bomb has detonated.
		instance.doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
				    luckyReliquaryTask = ThreadPoolManager.getInstance().schedule(new Runnable() {
						@Override
						public void run() {
							instance.doOnAllPlayers(new Visitor<Player>() {
								@Override
								public void visit(Player player) {
									onExitInstance(player);
								}
							});
							onInstanceDestroy();
						}
					}, 900000); //15 Minutes.
				}
			}
		});
    }
	
	@Override
	public void onDie(Npc npc) {
		Player player = npc.getAggroList().getMostPlayerDamage();
		switch (npc.getObjectTemplate().getTemplateId()) {
			case 284380:
			case 284381:
			case 284382:
			case 284659:
			case 284660:
			case 284662:
			case 284663:
			case 284664:
			    despawnNpc(npc);
			break;
			case 284377: //Danuar Reliquary Novun.
			case 284378: //Idean Lapilima.
			case 284379: //Idean Obscura.
				ideanKilled ++;
				if (ideanKilled == 1) {
				} else if (ideanKilled == 2) {
				} else if (ideanKilled == 3) {
				    spawn(231304, 256.45197f, 257.91986f, 241.78688f, (byte) 90); //Cursed Queen's Modor.
					instance.doOnAllPlayers(new Visitor<Player>() {
					    @Override
					    public void visit(Player player) {
						    if (player.isOnline()) {
							    startLuckyReliquaryTimer();
							    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 900)); //15 Minutes.
						    }
					    }
				    });
				}
				despawnNpc(npc);
			break;
			case 284383: //Clone's Modor.
				cloneModorKilled ++;
				if (cloneModorKilled == 1) {
				} else if (cloneModorKilled == 2) {
				} else if (cloneModorKilled == 3) {
				} else if (cloneModorKilled == 4) {
				} else if (cloneModorKilled == 5) {
				    spawn(231305, 256.45197f, 257.91986f, 241.78688f, (byte) 90); //Enraged Queen's Modor.
				}
				despawnNpc(npc);
			break;
			case 231305: //Enraged Queen's Modor.
				luckyReliquaryTask.cancel(true);
				sendMsg("[SUCCES]: You have finished <[Lucky] Danuar Reliquary>");
				spawn(730907, 256.45197f, 257.91986f, 241.78688f, (byte) 90); //Lucky Danuar Reliquary Exit.
				spawn(701795, 256.39725f, 255.52034f, 241.78006f, (byte) 90); //Lucky Danuar Reliquary Box.
				instance.doOnAllPlayers(new Visitor<Player>() {
			        @Override
			        public void visit(Player player) {
				        if (player.isOnline()) {
						    PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 0));
					    }
				    }
			    });
			break;
		}
	}
	
	private void sendMsg(final String str) {
		instance.doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				PacketSendUtility.sendWhiteMessageOnCenter(player, str);
			}
		});
	}
	
	protected void sendMsgByRace(final int msg, final Race race, int time) {
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				instance.doOnAllPlayers(new Visitor<Player>() {
					@Override
					public void visit(Player player) {
						if (player.getRace().equals(race) || race.equals(Race.PC_ALL)) {
							PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(msg));
						}
					}
				});
			}
		}, time);
	}
	
	private void sendMessage(final int msgId, long delay) {
        if (delay == 0) {
            this.sendMsg(msgId);
        } else {
            ThreadPoolManager.getInstance().schedule(new Runnable() {
                public void run() {
                    sendMsg(msgId);
                }
            }, delay);
        }
    }
	
	@Override
	public void onInstanceDestroy() {
		isInstanceDestroyed = true;
	}
	
	private void despawnNpc(Npc npc) {
		if (npc != null) {
			npc.getController().onDelete();
		}
	}
	
	public void onExitInstance(Player player) {
		TeleportService2.moveToInstanceExit(player, mapId, player.getRace());
	}
}