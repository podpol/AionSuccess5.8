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
package ai.worlds.norsvold;

import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("df6_field_attraction")
public class DF6_FieldAttractionAI2 extends NpcAI2
{
	@Override
    protected void handleDialogStart(Player player) {
        PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
    }
	
    @Override
    public boolean onDialogSelect(final Player player, int dialogId, int questId, int extendedRewardIndex) {
        if (dialogId == 10000) {
            switch (getNpcId()) {
                case 805765: //DF6_A1_F1_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(329001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 329001, 0));
                break;
                case 805766: //DF6_F1_A1_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(330001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 330001, 0));
                break;
				case 805767: //DF6_A1_D_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(327001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 327001, 0));
                break;
                case 805768: //DF6_D_A1_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(328001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 328001, 0));
                break;
				case 805769: //DF6_A2_B1_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(333001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 333001, 0));
                break;
				case 805770: //DF6_B1_A2_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(334001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 334001, 0));
                break;
				case 805771: //DF6_A2_H_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(331001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 331001, 0));
                break;
				case 805772: //DF6_H_A2_Attraction_D.
                    player.setState(CreatureState.FLIGHT_TELEPORT);
					player.unsetState(CreatureState.ACTIVE);
					player.setFlightTeleportId(332001);
					PacketSendUtility.sendPacket(player, new SM_EMOTION(player, EmotionType.START_FLYTELEPORT, 332001, 0));
                break;
            }
        }
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
        return true;
    }
}