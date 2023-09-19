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

import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDEternity01Teleporter")
public class IDEternity_01_A_Save_PointAI2 extends NpcAI2
{
	@Override
    protected void handleCreatureSee(Creature creature) {
        checkDistance(this, creature);
    }
	
	@Override
	protected void handleCreatureMoved(Creature creature) {
		checkDistance(this, creature);
	}
	
	private void checkDistance(NpcAI2 ai, Creature creature) {
		if (creature instanceof Player && !creature.getLifeStats().isAlreadyDead()) {
        	if (MathUtil.isIn3dRange(getOwner(), creature, 10)) {
        		IDEternity01ASavePoint();
        	}
        }
    }
	
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		switch (getNpcId()) {
            case 731809:
                announceTeleporter01();
            break;
			case 731810:
                announceTeleporter02();
            break;
			case 731811:
                announceTeleporter03();
            break;
			case 731812:
                announceTeleporter04();
            break;
        }
	}
	
	private void IDEternity01ASavePoint() {
		AI2Actions.deleteOwner(IDEternity_01_A_Save_PointAI2.this);
		switch (Rnd.get(1, 4)) {
			case 1:
				spawn(281446, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(731809, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //IDEternity_01_Teleporter_01.
			break;
			case 2:
				spawn(281446, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
			    spawn(731810, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //IDEternity_01_Teleporter_02.
			break;
			case 3:
				spawn(281446, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(731811, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //IDEternity_01_Teleporter_03.
			break;
			case 4:
				spawn(281446, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading());
				spawn(731812, getOwner().getX(), getOwner().getY(), getOwner().getZ(), (byte) getOwner().getHeading()); //IDEternity_01_Teleporter_04.
			break;
		}
    }
	
	private void announceTeleporter01() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//주신�?� 기�?보관소 �?��?�장치가 활성화 �?�었습니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_Teleport_MSG_01);
				}
			}
		});
	}
	private void announceTeleporter02() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//�?�간�?� 기�?보관소 �?��?�장치가 활성화 �?�었습니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_Teleport_MSG_02);
				}
			}
		});
	}
	private void announceTeleporter03() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//아트레�?�아�?� 기�?보관소 �?��?�장치가 활성화 �?�었습니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_Teleport_MSG_03);
				}
			}
		});
	}
	private void announceTeleporter04() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					//근�?�?� 기�?보관소 �?��?�장치가 활성화 �?�었습니다.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDEternity_Teleport_MSG_04);
				}
			}
		});
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}