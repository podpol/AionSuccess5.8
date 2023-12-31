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
package ai.instance.archivesOfEternity;

import ai.GeneralNpcAI2;
import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.manager.WalkManager;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.services.NpcShoutsService;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("leibo")
public class Leibo_StewardAI2 extends GeneralNpcAI2
{
    private boolean canThink = true;
    private AtomicBoolean startedEvent = new AtomicBoolean(false);
	
    @Override
    public boolean canThink() {
        return canThink;
    }
	
    @Override
    protected void handleCreatureMoved(Creature creature) {
        super.handleCreatureMoved(creature);
        if (getNpcId() == 857833 && isInRange(creature, 15) && creature instanceof Player) {
        	if (startedEvent.compareAndSet(false, true)) {
        		canThink = false;
				//Intruder!
        		sendMsg(1501514, getObjectId(), false, 0);
				//Guards! An unwelcome Daeva!
				sendMsg(1501513, getObjectId(), false, 2000);
        		getOwner().getMoveController().moveToPoint(643.78705f, 431.82138f, 468.96872f);
        		WalkManager.startWalking(this);
        		getOwner().setState(1);
            	PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
                ThreadPoolManager.getInstance().schedule(new Runnable() {
                    @Override
                    public void run() {
                        if (!isAlreadyDead()) {
                            despawn();
                        }
                    }
                }, 6000);
            }
        } else if (getNpcId() == 857834 && isInRange(creature, 15) && creature instanceof Player) {
        	if (startedEvent.compareAndSet(false, true)) {
        		canThink = false;
            	//Here, already ?
        		sendMsg(1501515, getObjectId(), false, 0);
        		getOwner().getMoveController().moveToPoint(519.9612f, 352.6099f, 469.05872f);
        		WalkManager.startWalking(this);
        		getOwner().setState(1);
            	PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
                ThreadPoolManager.getInstance().schedule(new Runnable() {
                    @Override
                    public void run() {
                        if (!isAlreadyDead()) {
                            despawn();
                        }
                    }
                }, 6000);
            }
        } else if (getNpcId() == 857835 && isInRange(creature, 15) && creature instanceof Player) {
        	if (startedEvent.compareAndSet(false, true)) {
        		canThink = false;
            	//Stop following me!
        		sendMsg(1501515, getObjectId(), false, 0);
        		getOwner().getMoveController().moveToPoint(477.04803f, 380.22256f, 468.9887f);
        		WalkManager.startWalking(this);
        		getOwner().setState(1);
            	PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
                ThreadPoolManager.getInstance().schedule(new Runnable() {
                    @Override
                    public void run() {
                        if (!isAlreadyDead()) {
                            despawn();
                        }
                    }
                }, 6000);
            }
        } else if (getNpcId() == 857836 && isInRange(creature, 15) && creature instanceof Player) {
        	if (startedEvent.compareAndSet(false, true)) {
        		canThink = false;
       			//Guards! Apprehend the intruder!
        		sendMsg(1501528, getObjectId(), false, 0);
           		getOwner().getMoveController().moveToPoint(536.2993f, 541.13513f, 468.9487f);
           		WalkManager.startWalking(this);
           		getOwner().setState(1);
        		PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
                ThreadPoolManager.getInstance().schedule(new Runnable() {
                    @Override
                    public void run() {
                        if (!isAlreadyDead()) {
                            despawn();
                        }
                    }
                }, 6000);
            }
        } else if (getNpcId() == 857904 && isInRange(creature, 15) && creature instanceof Player) {
        	if (startedEvent.compareAndSet(false, true)) {
        		canThink = false;
        		//Guards, take the intruder!
        		sendMsg(1501529, getObjectId(), false, 0);
	       		getOwner().getMoveController().moveToPoint(486.2936f, 627.62415f, 468.9487f);
	       		WalkManager.startWalking(this);
	       		getOwner().setState(1);
        		PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
        		ThreadPoolManager.getInstance().schedule(new Runnable() {
        			@Override
        			public void run() {
        				if (!isAlreadyDead()) {
        					despawn();
        				}
        			}
        		}, 6000);
        	}
        } else if (getNpcId() == 857905 && isInRange(creature, 15) && creature instanceof Player) {
        	if (startedEvent.compareAndSet(false, true)) {
        		canThink = false;
        		//Leave me alone!
        		sendMsg(1501530, getObjectId(), false, 0);
	       		getOwner().getMoveController().moveToPoint(398.56595f, 564.1358f, 468.9787f);
	       		WalkManager.startWalking(this);
	       		getOwner().setState(1);
        		PacketSendUtility.broadcastPacket(getOwner(), new SM_EMOTION(getOwner(), EmotionType.START_EMOTE2, 0, getObjectId()));
        		ThreadPoolManager.getInstance().schedule(new Runnable() {
        			@Override
        			public void run() {
        				if (!isAlreadyDead()) {
        					despawn();
        				}
        			}
        		}, 6000);
        	}
        }
    }
	
	private void despawn() {
    	AI2Actions.deleteOwner(this);
    }
	
	private void sendMsg(int msg, int Obj, boolean isShout, int time) {
		NpcShoutsService.getInstance().sendMsg(getPosition().getWorldMapInstance(), msg, Obj, isShout, 0, time);
	}
}