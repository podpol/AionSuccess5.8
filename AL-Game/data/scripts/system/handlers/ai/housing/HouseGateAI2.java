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

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AI2Request;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.TeleportAnimation;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.templates.housing.BuildingType;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUESTION_WINDOW;
import com.aionemu.gameserver.services.HousingService;
import com.aionemu.gameserver.services.instance.InstanceService;
import com.aionemu.gameserver.services.teleport.TeleportService2;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("housegate")
public class HouseGateAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		final int creatorId = getCreatorId();
		if (!player.getObjectId().equals(creatorId)) {
			if (player.getCurrentGroup() == null || !player.getCurrentGroup().hasMember(creatorId))
				return;
		}
		House house = HousingService.getInstance().getPlayerStudio(creatorId);
		if (house == null) {
			int address = HousingService.getInstance().getPlayerAddress(creatorId);
			house = HousingService.getInstance().getHouseByAddress(address);
		}
		if (house == null)
			return;
		AI2Actions.addRequest(this, player, SM_QUESTION_WINDOW.STR_ASK_GROUP_GATE_DO_YOU_ACCEPT_MOVE, 0, 9,
		new AI2Request() {
			private boolean decided = false;
			@Override
			public void acceptRequest(Creature requester, Player responder) {
				if (decided)
					return;
				House house = HousingService.getInstance().getPlayerStudio(creatorId);
				if (house == null) {
					int address = HousingService.getInstance().getPlayerAddress(creatorId);
					house = HousingService.getInstance().getHouseByAddress(address);
				}
				int instanceOwnerId = responder.getPosition().getWorldMapInstance().getOwnerId();
				int exitMapId = 0;
				float x = 0, y = 0, z = 0;
				byte heading = 0;
				int instanceId = 0;
				if (instanceOwnerId > 0) {
					house = HousingService.getInstance().getPlayerStudio(instanceOwnerId);
					exitMapId = house.getAddress().getExitMapId();
					instanceId = World.getInstance().getWorldMap(exitMapId).getMainWorldMapInstance().getInstanceId();
					x = house.getAddress().getExitX();
					y = house.getAddress().getExitY();
					z = house.getAddress().getExitZ();
				} else {
					exitMapId = house.getAddress().getMapId();
					if (house.getBuilding().getType() == BuildingType.PERSONAL_INS) {
						WorldMapInstance instance = InstanceService.getPersonalInstance(exitMapId, creatorId);
						if (instance == null) {
							instance = InstanceService.getNextAvailableInstance(exitMapId, creatorId);
							InstanceService.registerPlayerWithInstance(instance, responder);
						}
						instanceId = instance.getInstanceId();
					} else {
						instanceId = house.getInstanceId();
					}
					x = house.getAddress().getX();
					y = house.getAddress().getY();
					z = house.getAddress().getZ();
					if (exitMapId == 710010000) {
						heading = 36;
					}
				}
				TeleportService2.teleportTo(responder, exitMapId, instanceId, x, y, z, heading, TeleportAnimation.BEAM_ANIMATION);
				decided = true;
			}
			
			@Override
			public void denyRequest(Creature requester, Player responder) {
			    decided = true;
			}
		});
	}
}