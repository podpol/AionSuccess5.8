package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.geoEngine.collision.CollisionIntention;
import com.aionemu.gameserver.geoEngine.math.Vector3f;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SKILL_COOLDOWN;
import com.aionemu.gameserver.network.aion.serverpackets.SM_TARGET_UPDATE;
import com.aionemu.gameserver.network.aion.serverpackets.SM_TRANSFORM;
import com.aionemu.gameserver.network.aion.serverpackets.SM_USE_ROBOT;
import com.aionemu.gameserver.skillengine.model.DashStatus;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.skillengine.model.SkillMoveType;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.geo.GeoService;

import java.util.*;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RandomMoveLocEffect")
public class RandomMoveLocEffect extends EffectTemplate
{
	@XmlAttribute(name = "distance")
	private float distance;
	
	@XmlAttribute(name = "direction")
	private float direction;
	
	@Override
	public void applyEffect(Effect effect) {
		final Player effector = (Player) effect.getEffector();
		PacketSendUtility.sendPacket(effector, new SM_TARGET_UPDATE(effector));
		Skill skill = effect.getSkill();
		World.getInstance().updatePosition(effector, skill.getX(), skill.getY(), skill.getZ(), skill.getH());
	}
	
	@Override
	public void calculate(Effect effect) {
		effect.addSucessEffect(this);
		if (((Player) effect.getEffector()).getRobotId() != 0) {
			if (effect.getSkill().getSkillId() == 2424 || effect.getSkill().getSkillId() == 2425) { //Hypergate Detonation.
				effect.setDashStatus(DashStatus.RANDOMMOVELOC);
			} else {
				effect.setDashStatus(DashStatus.RIDERMOVELOC);
			}
		} else {
			if (effect.getSkillId() == 4697) { //Mercurial Blast.
				effect.setDashStatus(DashStatus.RIDERMOVELOC);
			} else {
				effect.setDashStatus(DashStatus.RANDOMMOVELOC);
			}
			effect.setSkillMoveType(SkillMoveType.MOVEBEHIND);
		}
		final Player effector = (Player) effect.getEffector();
		if (effect.getSkill().getSkillId() == 2424 || effect.getSkill().getSkillId() == 2425) { //Hypergate Detonation.
			RemoveSkill((Player) effect.getEffector());
			PacketSendUtility.broadcastPacket(effector, new SM_USE_ROBOT(effector, 0), true);
			effector.setUseRobot(false);
			effector.setRobotId(0);
			//Remove Cooldown Skill.
			List<Integer> delayIds = new ArrayList<Integer>();
			if (effector.getSkillCoolDowns() != null) {
				long currentTime = System.currentTimeMillis();
				for (Entry<Integer, Long> en: effector.getSkillCoolDowns().entrySet()) {
					delayIds.add(en.getKey());
				} for (Integer delayId: delayIds) {
					effector.setSkillCoolDown(delayId, currentTime);
				}
				delayIds.clear();
				PacketSendUtility.sendPacket(effector, new SM_SKILL_COOLDOWN(effector.getSkillCoolDowns()));
			}
		}
		double radian = Math.toRadians(MathUtil.convertHeadingToDegree(effector.getHeading()));
		float x1 = (float) (Math.cos(Math.PI * direction + radian) * distance);
		float y1 = (float) (Math.sin(Math.PI * direction + radian) * distance);
		effector.getEffectController().updatePlayerEffectIcons();
		PacketSendUtility.broadcastPacketAndReceive(effector, new SM_TRANSFORM(effector, true));
		PacketSendUtility.broadcastPacketAndReceive(effector, new SM_TRANSFORM(effector, effector.getTransformedModelId(), true, effector.getTransformedItemId()));
		byte intentions = (byte) (CollisionIntention.PHYSICAL.getId() | CollisionIntention.DOOR.getId());
		Vector3f closestCollision = GeoService.getInstance().getClosestCollision(effector, effector.getX() + x1, effector.getY() + y1, effector.getZ(), false, intentions);
		effect.getSkill().setTargetPosition(closestCollision.getX(), closestCollision.getY(), closestCollision.getZ(), effector.getHeading());
	}
	
	private void RemoveSkill(Player player) {
		player.getEffectController().removeEffect(2767); //Embark I
		player.getEffectController().removeEffect(2768); //Embark II
		player.getEffectController().removeEffect(2769); //Embark III
		player.getEffectController().removeEffect(2770); //Embark VI
		player.getEffectController().removeEffect(2771); //Embark V
		player.getEffectController().removeEffect(2772); //Embark VI
		player.getEffectController().removeEffect(2773); //Embark VII
		player.getEffectController().removeEffect(2774); //Embark VIII
		player.getEffectController().removeEffect(2775); //Embark IX
		player.getEffectController().removeEffect(2776); //Embark X
		player.getEffectController().removeEffect(2777); //Embark XI
		player.getEffectController().removeEffect(2778); //Embark XII
		player.getEffectController().removeEffect(2421); //Mobility Thrusters I
		player.getEffectController().removeEffect(2422); //Mobility Thrusters II
		player.getEffectController().removeEffect(2440); //Kinetic Battery I
		player.getEffectController().removeEffect(2441); //Kinetic Battery II
		player.getEffectController().removeEffect(2442); //Kinetic Battery III
		player.getEffectController().removeEffect(2443); //Kinetic Battery VI
		player.getEffectController().removeEffect(2444); //Kinetic Battery V
		player.getEffectController().removeEffect(2445); //Kinetic Battery VI
		player.getEffectController().removeEffect(2446); //Kinetic Battery VII
		player.getEffectController().removeEffect(2447); //Kinetic Battery VIII
		player.getEffectController().removeEffect(2448); //Kinetic Battery IX
		player.getEffectController().removeEffect(2449); //Kinetic Battery X
		player.getEffectController().removeEffect(4796); //[ArchDaeva] Kinetic Battery 5.1
		player.getEffectController().removeEffect(2579); //Kinetic Bulwark I
		player.getEffectController().removeEffect(2580); //Kinetic Bulwark II
		player.getEffectController().removeEffect(2581); //Kinetic Bulwark III
		player.getEffectController().removeEffect(2736); //Stability Thrusters I
		player.getEffectController().removeEffect(2737); //Stability Thrusters II
		player.getEffectController().removeEffect(2738); //Stability Thrusters III
		player.getEffectController().removeEffect(2739); //Stability Thrusters IV
		player.getEffectController().removeEffect(2740); //Stability Thrusters V
		player.getEffectController().removeEffect(4794); //[ArchDaeva] Stability Thrusters 5.1
		player.getEffectController().removeEffect(2838); //Mounting Frustration I
		player.getEffectController().removeEffect(2839); //Mounting Frustration II
		player.getEffectController().removeEffect(2840); //Mounting Frustration III
		player.getEffectController().removeEffect(2841); //Mounting Frustration IV
		player.getEffectController().removeEffect(2842); //Mounting Frustration V
		player.getEffectController().removeEffect(2843); //Mounting Frustration VI
		player.getEffectController().removeEffect(2844); //Mounting Frustration VI
		player.getEffectController().removeEffect(2845); //Mounting Frustration VIII
		player.getEffectController().removeEffect(2846); //Mounting Frustration IX
		player.getEffectController().removeEffect(2847); //Mounting Frustration X
		player.getEffectController().removeEffect(2848); //Mounting Frustration XI
	}
}