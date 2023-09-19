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
package ai.instance.ironWallWarfront;

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.knownlist.Visitor;

import java.util.concurrent.atomic.AtomicBoolean;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDF5TDWar_chief_l")
public class IDF5TDWar_Chief_LAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 233518: //Elyos Supply Base Beta Officer.
				    announceIDF5TDWarLV01();
				break;
				case 233519: //Elyos Military Supply Base Officer.
				    announceIDF5TDWarLV02();
				break;
				case 233520: //Elyos Supply Base Alpha Officer.
					announceIDF5TDWarLV03();
				break;
				case 233521: //Elyos Artillery Base Officer.
					announceIDF5TDWarLV04();
				break;
				case 233522: //Elyos Sentry Post Alpha Officer.
					announceIDF5TDWarLV05();
				break;
				case 233523: //Elyos Sentry Post Beta Officer.
					announceIDF5TDWarLV06();
				break;
				case 233524: //Elyos Holy Grounds Officer.
					announceIDF5TDWarLV07();
				break;
				case 233525: //Elyos Command Center Officer.
					announceIDF5TDWarLV08();
				break;
				case 233526: //Elyos Headquarters Alpha Officer.
					announceIDF5TDWarLV09();
				break;
				case 233527: //Elyos Headquarters Beta Officer.
					announceIDF5TDWarLV10();
				break;
			}
		}
	}
	
	private void announceIDF5TDWarLV01() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_01);
				}
			}
		});
	}
	private void announceIDF5TDWarLV02() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_02);
				}
			}
		});
	}
	private void announceIDF5TDWarLV03() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_03);
				}
			}
		});
	}
	private void announceIDF5TDWarLV04() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_04);
				}
			}
		});
	}
	private void announceIDF5TDWarLV05() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_05);
				}
			}
		});
	}
	private void announceIDF5TDWarLV06() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_06);
				}
			}
		});
	}
	private void announceIDF5TDWarLV07() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_07);
				}
			}
		});
	}
	private void announceIDF5TDWarLV08() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_08);
				}
			}
		});
	}
	private void announceIDF5TDWarLV09() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_09);
				}
			}
		});
	}
	private void announceIDF5TDWarLV10() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Li_10);
				}
			}
		});
	}
}