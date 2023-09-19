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
package ai.instance.dragonLordRefuge;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.teleport.TeleportService2;

/****/
/** Author Ranastic (Encom)
/****/

@AIName("internal_passage_out")
public class Internal_Passage_OutAI2 extends ActionItemNpcAI2
{
	@Override
	protected void handleUseItemFinish(Player player) {
		switch (getNpcId()) {
		    case 730633: //Internal Passage Out 1
				switch (player.getWorldId()) {
					case 300520000:
						TeleportService2.teleportTo(player, 300520000, 530.0911f, 480.24875f, 417.40436f, (byte) 103);
			        break;
					case 300630000:
						TeleportService2.teleportTo(player, 300630000, 530.0911f, 480.24875f, 417.40436f, (byte) 103);
			        break;
				}
		    break;
			case 730634: //Internal Passage Out 2
				switch (player.getWorldId()) {
					case 300520000:
						TeleportService2.teleportTo(player, 300520000, 477.32306f, 549.42285f, 417.40436f, (byte) 43);
			        break;
					case 300630000:
						TeleportService2.teleportTo(player, 300630000, 477.32306f, 549.42285f, 417.40436f, (byte) 43);
			        break;
				}
		    break;
			case 730635: //Internal Passage Out 3
				switch (player.getWorldId()) {
					case 300520000:
						TeleportService2.teleportTo(player, 300520000, 530.8401f, 549.626f, 417.40436f, (byte) 17);
			        break;
					case 300630000:
						TeleportService2.teleportTo(player, 300630000, 530.8401f, 549.626f, 417.40436f, (byte) 17);
			        break;
				}
		    break;
			case 730636: //Internal Passage Out 4
				switch (player.getWorldId()) {
					case 300520000:
						TeleportService2.teleportTo(player, 300520000, 504.3792f, 520.4297f, 417.40436f, (byte) 61);
			        break;
					case 300630000:
						TeleportService2.teleportTo(player, 300630000, 504.3792f, 520.4297f, 417.40436f, (byte) 61);
			        break;
				}
		    break;
		}
	}
}