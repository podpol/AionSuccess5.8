package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.stats.container.StatEnum;
import com.aionemu.gameserver.skillengine.model.Effect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SleepEffect")
public class SleepEffect extends EffectTemplate
{
	@Override
	public void applyEffect(Effect effect) {
		effect.addToEffectedController();
	}
	
	@Override
	public void calculate(Effect effect) {
		super.calculate(effect, StatEnum.SLEEP_RESISTANCE, null);
	}
	
	@Override
	public void startEffect(final Effect effect) {
		final Creature effected = effect.getEffected();
		if (effected.isInState(CreatureState.RESTING)) {
        	effected.unsetState(CreatureState.RESTING);
		}
		effected.getMoveController().abortMove();
		effected.getController().cancelCurrentSkill();
		effect.setAbnormal(AbnormalState.SLEEP.getId());
		effected.getEffectController().setAbnormal(AbnormalState.SLEEP.getId());
		effect.setCancelOnDmg(true);
	}
	
	@Override
	public void endEffect(Effect effect) {
		effect.getEffected().getEffectController().unsetAbnormal(AbnormalState.SLEEP.getId());
	}
}