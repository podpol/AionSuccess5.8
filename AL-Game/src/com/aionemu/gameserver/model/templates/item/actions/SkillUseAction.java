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
package com.aionemu.gameserver.model.templates.item.actions;

import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.player.PlayerCommonData;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.skillengine.effect.EffectTemplate;
import com.aionemu.gameserver.skillengine.effect.SummonEffect;
import com.aionemu.gameserver.skillengine.effect.TransformEffect;
import com.aionemu.gameserver.skillengine.model.Skill;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SkillUseAction")
public class SkillUseAction extends AbstractItemAction
{
	@XmlAttribute
	protected int skillid;
	
	@XmlAttribute
	protected int level;
	
	@XmlAttribute(required = false)
    private Integer mapid;
	
	public int getSkillid() {
		return skillid;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public boolean canAct(Player player, Item parentItem, Item targetItem) {
		Skill skill = SkillEngine.getInstance().getSkill(player, skillid, level, player.getTarget(), parentItem.getItemTemplate());
        if (skill == null) {
            return false;
        }
		int nameId = parentItem.getItemTemplate().getNameId();
        byte levelRestrict = parentItem.getItemTemplate().getMaxLevelRestrict(player);
        if (levelRestrict != 0) {
            PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CANNOT_USE_ITEM_TOO_LOW_LEVEL_MUST_BE_THIS_LEVEL(levelRestrict, nameId));
            return false;
        } if (player.isTransformed()) {
			for (EffectTemplate template : skill.getSkillTemplate().getEffects().getEffects()) {
				if (template instanceof TransformEffect) {
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_CANT_USE_ITEM(new DescriptionId(nameId)));
					return false;
				}
			}
		} if (player.getSummon() != null) {
			for (EffectTemplate template: skill.getSkillTemplate().getEffects().getEffects()) {
				if (template instanceof SummonEffect) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1300072));
					return false;
				}
			}
		}
		if(skill.getSkillId() == 11072){ //add Golden Star Energy
			PlayerCommonData pcd = player.getCommonData();
			if(pcd.getBerdinStar() == pcd.getMaxBerdinStar()){
				PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1402615));
				return false;
			}
		}
		return skill.canUseSkill();
	}
	
	@Override
    public void act(Player player, Item parentItem, Item targetItem) {
        Skill skill = SkillEngine.getInstance().getSkill(player, skillid, level, player.getTarget(), parentItem.getItemTemplate());
        if (skill != null) {
            player.getController().cancelUseItem();
            skill.setItemObjectId(parentItem.getObjectId());
            skill.useSkill();
			QuestEnv env = new QuestEnv(player.getTarget(), player, 0, 0);
			QuestEngine.getInstance().onUseSkill(env, skillid);
        }
    }
	
	public int getMapid() {
        if (mapid == null) {
            return 0;
        }
        return mapid;
    }
}