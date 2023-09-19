package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.skillengine.model.Effect;

import java.util.Collection;

public class SM_ABNORMAL_EFFECT extends AionServerPacket
{
	private int effectedId;
	private int effectType = 1;
	private int abnormals;
	private Collection<Effect> filtered;
	
	public SM_ABNORMAL_EFFECT(Creature effected, int abnormals, Collection<Effect> effects) {
		this.abnormals = abnormals;
		this.effectedId = effected.getObjectId();
		this.filtered = effects;
		if (effected instanceof Player) {
			effectType = 2;
		}
	}
	
	@Override
	protected void writeImpl(AionConnection con) {
		writeD(effectedId);
		writeC(effectType); //unk
		writeD(0); // unk
		writeD(abnormals); // unk
		writeD(0); // unk
		writeC(0x7F);//unk 5.3
		writeH(filtered.size()); // effects size
		for (Effect effect : filtered) {
			switch(effectType) {
				case 2:
					writeD(effect.getEffectorId());
					writeH(effect.getSkillId());
					writeC(effect.getSkillLevel());
					writeC(effect.getTargetSlot());
					writeD(effect.getRemainingTime());
					writeH(0x00);//unk 5.3
				break;
				case 1:
					writeH(effect.getSkillId());
					writeC(effect.getSkillLevel());
					writeC(effect.getTargetSlot());
					writeD(effect.getRemainingTime());
					writeH(0x00);//unk 5.3
				break;
				default:
					writeH(effect.getSkillId());
					writeC(effect.getSkillLevel());
			}
		}
	}
}