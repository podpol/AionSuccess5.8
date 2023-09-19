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

@AIName("IDF5TDWar_chief_d")
public class IDF5TDWar_Chief_DAI2 extends AggressiveNpcAI2
{
	private AtomicBoolean isAggred = new AtomicBoolean(false);
	
	@Override
	protected void handleAttack(Creature creature) {
		super.handleAttack(creature);
		if (isAggred.compareAndSet(false, true)) {
			switch (getNpcId()) {
				case 233498: //Asmodians Supply Base Beta Officer.
				    announceIDF5TDWarDV01();
				break;
				case 233499: //Asmodians Military Supply Base Officer.
				    announceIDF5TDWarDV02();
				break;
				case 233500: //Asmodians Supply Base Alpha Officer.
					announceIDF5TDWarDV03();
				break;
				case 233501: //Asmodians Artillery Base Officer.
					announceIDF5TDWarDV04();
				break;
				case 233502: //Asmodians Sentry Post Alpha Officer.
					announceIDF5TDWarDV05();
				break;
				case 233503: //Asmodians Sentry Post Beta Officer.
					announceIDF5TDWarDV06();
				break;
				case 233504: //Asmodians Holy Grounds Officer.
					announceIDF5TDWarDV07();
				break;
				case 233505: //Asmodians Command Center Officer.
					announceIDF5TDWarDV08();
				break;
				case 233506: //Asmodians Headquarters Alpha Officer.
					announceIDF5TDWarDV09();
				break;
				case 233507: //Asmodians Headquarters Beta Officer.
					announceIDF5TDWarDV10();
				break;
			}
		}
	}
	
	private void announceIDF5TDWarDV01() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_01);
				}
			}
		});
	}
	private void announceIDF5TDWarDV02() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_02);
				}
			}
		});
	}
	private void announceIDF5TDWarDV03() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_03);
				}
			}
		});
	}
	private void announceIDF5TDWarDV04() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_04);
				}
			}
		});
	}
	private void announceIDF5TDWarDV05() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_05);
				}
			}
		});
	}
	private void announceIDF5TDWarDV06() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_06);
				}
			}
		});
	}
	private void announceIDF5TDWarDV07() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_07);
				}
			}
		});
	}
	private void announceIDF5TDWarDV08() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_08);
				}
			}
		});
	}
	private void announceIDF5TDWarDV09() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_09);
				}
			}
		});
	}
	private void announceIDF5TDWarDV10() {
		getPosition().getWorldMapInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (player.isOnline()) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_IDF5_TD_War_Officer_Da_10);
				}
			}
		});
	}
}