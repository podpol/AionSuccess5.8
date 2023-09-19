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
package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.controllers.observer.ActionObserver;
import com.aionemu.gameserver.controllers.observer.ObserverType;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.item.EquipType;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.model.templates.robot.RobotInfo;
import com.aionemu.gameserver.network.aion.serverpackets.SM_USE_ROBOT;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RideRobotEffect")
public class RideRobotEffect extends EffectTemplate
{
	@Override
	public void applyEffect(final Effect effect) {
		effect.addToEffectedController();
		Creature effected = effect.getEffected();
		Player player = (Player)effected;
		player.setUseRobot(true);
		PacketSendUtility.broadcastPacketAndReceive(player, new SM_USE_ROBOT(player, getRobotInfo(player).getRobotId()));
		player.setRobotId(getRobotInfo(player).getRobotId());
		ActionObserver observer = new ActionObserver(ObserverType.UNEQUIP) {
            @Override
            public void unequip(Item item, Player owner) {
                if (item.getEquipmentType() == EquipType.WEAPON) {
                    effect.endEffect();
                }
            }
        };
        player.getObserveController().addObserver(observer);
        effect.setActionObserver(observer, position);
	}
	
	@Override
	public void endEffect(Effect effect) {
		super.endEffect(effect);
		Creature effected = effect.getEffected();
		Player player = (Player)effected;
		if (player.isUseRobot()) {
			PacketSendUtility.broadcastPacket(player, new SM_USE_ROBOT(player, 0), true);
			player.setUseRobot(false);
			player.setRobotId(0);
		}
		ActionObserver observer = effect.getActionObserver(position);
        if (observer != null) {
            effect.getEffected().getObserveController().removeObserver(observer);
        }
	}
	
	public RobotInfo getRobotInfo(Player player) {
		ItemTemplate template = player.getEquipment().getMainHandWeapon().getItemSkinTemplate();
		return DataManager.ROBOT_DATA.getRobotInfo(template.getRobotId());
	}
}