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
package ai.housing;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.HousingService;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("studioportal")
public class StudioPortalAI2 extends ActionItemNpcAI2
{
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		return true;
	}
	
	@Override
	protected void handleUseItemFinish(Player player) {
		int ownerId = player.getPosition().getWorldMapInstance().getOwnerId();
		House studio = HousingService.getInstance().getPlayerStudio(player.getObjectId());
		if (studio == null && ownerId == 0) {
			PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_HOUSING_ENTER_NEED_HOUSE);
			return;
		}
		int exitMapId = 0;
		float x = 0, y = 0, z = 0;
		byte heading = 0;
		int instanceId = 0;
		if (ownerId > 0) {
			studio = HousingService.getInstance().getPlayerStudio(ownerId);
			exitMapId = studio.getAddress().getExitMapId();
			instanceId = World.getInstance().getWorldMap(exitMapId).getMainWorldMapInstance().getInstanceId();
			x = studio.getAddress().getExitX();
			y = studio.getAddress().getExitY();
			z = studio.getAddress().getExitZ();
		} else if (studio == null) {
			return;
		} else {
			exitMapId = studio.getAddress().getMapId();
			WorldMapInstance instance = InstanceService.getPersonalInstance(exitMapId, player.getObjectId());
			if (instance == null) {
				instance = InstanceService.getNextAvailableInstance(exitMapId, player.getObjectId());
				InstanceService.registerPlayerWithInstance(instance, player);
			}
			instanceId = instance.getInstanceId();
			x = studio.getAddress().getX();
			y = studio.getAddress().getY();
			z = studio.getAddress().getZ();
			if (exitMapId == 710010000) {
				heading = 36;
			}
		}
		TeleportService2.teleportTo(player, exitMapId, instanceId, x, y, z, heading, TeleportAnimation.BEAM_ANIMATION);
	}
}