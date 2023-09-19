package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_STATS_INFO;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.PacketSendUtility;

import javax.xml.bind.annotation.XmlAttribute;

public class AbsoluteAPPointHealInstantEffect extends EffectTemplate
{
	@XmlAttribute(required = true)
	protected int points;
	
	public void applyEffect(Effect effect) {
		if ((effect.getEffected() instanceof Player)) {
			Player player = (Player) effect.getEffected();
			player.getCommonData().addAbyssFavor(points);
			PacketSendUtility.sendPacket(player, new SM_STATS_INFO(player));
		}
	}
}