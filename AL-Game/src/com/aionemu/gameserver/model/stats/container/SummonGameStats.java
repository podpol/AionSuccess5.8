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
package com.aionemu.gameserver.model.stats.container;

import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.Summon;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.stats.calc.Stat2;
import com.aionemu.gameserver.model.templates.stats.SummonStatsTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SUMMON_UPDATE;
import com.aionemu.gameserver.taskmanager.tasks.PacketBroadcaster.BroadcastMode;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author ATracer
 */
public class SummonGameStats extends CreatureGameStats<Summon> {

	private int cachedSpeed;
	private final SummonStatsTemplate statsTemplate;

	/**
	 * @param owner
	 * @param statsTemplate
	 */
	public SummonGameStats(Summon owner, SummonStatsTemplate statsTemplate) {
		super(owner);
		this.statsTemplate = statsTemplate;
	}

	@Override
	protected void onStatsChange() {
		updateStatsAndSpeedVisually();
	}

	public void updateStatsAndSpeedVisually() {
		updateStatsVisually();
		checkSpeedStats();
	}

	public void updateStatsVisually() {
		owner.addPacketBroadcastMask(BroadcastMode.UPDATE_STATS);
	}

	private void checkSpeedStats() {
		int current = getMovementSpeed().getCurrent();
		if (current != cachedSpeed) {
			owner.addPacketBroadcastMask(BroadcastMode.UPDATE_SPEED);
		}
		cachedSpeed = current;
	}

	@Override
    public Stat2 getAllSpeed() {
        return getStat(StatEnum.ALLSPEED, 7500);
    }

	@Override
	public Stat2 getStat(StatEnum statEnum, int base) {
		Stat2 stat = super.getStat(statEnum, base);
		if (owner.getMaster() == null)
			return stat;
		switch (statEnum) {
			case MAXHP:
				stat.setBonusRate(0.5f);
				return owner.getMaster().getGameStats().getItemStatBoost(statEnum, stat);
			case BOOST_MAGICAL_SKILL:
			case MAGIC_SKILL_BOOST_RESIST:
				stat.setBonusRate(0.8f);
				return owner.getMaster().getGameStats().getItemStatBoost(statEnum, stat);
			case PHYSICAL_ATTACK:
				stat.setBonusRate(0.3f);
				return owner.getMaster().getGameStats().getItemStatBoost(statEnum, stat);
			case PHYSICAL_DEFENSE:
			case EVASION:
			case MAGICAL_ACCURACY:
			case MAGICAL_RESIST:
				stat.setBonusRate(0.5f);
				return owner.getMaster().getGameStats().getItemStatBoost(statEnum, stat);
			case PHYSICAL_ACCURACY:
				stat.setBonusRate(0.5f);
				owner.getMaster().getGameStats().getItemStatBoost(StatEnum.MAIN_HAND_ACCURACY, stat);
				return owner.getMaster().getGameStats().getItemStatBoost(statEnum, stat);
			case PHYSICAL_CRITICAL:
				stat.setBonusRate(0.5f);
				owner.getMaster().getGameStats().getItemStatBoost(StatEnum.MAIN_HAND_CRITICAL, stat);
				return owner.getMaster().getGameStats().getItemStatBoost(statEnum, stat);

		}
		return stat;
	}

	@Override
	public Stat2 getMaxHp() {
		return getStat(StatEnum.MAXHP, statsTemplate.getMaxHp());
	}
	@Override
	public Stat2 getMaxMp() {
		return getStat(StatEnum.MAXHP, statsTemplate.getMaxMp());
	}
	@Override
    public Stat2 getStrikeResist() {
        return getStat(StatEnum.PHYSICAL_CRITICAL_RESIST, 0);
    }
	@Override
    public Stat2 getStrikeFort() {
        return getStat(StatEnum.PHYSICAL_CRITICAL_DAMAGE_REDUCE, 0);
    }
	@Override
    public Stat2 getSpellResist() {
        return getStat(StatEnum.MAGICAL_CRITICAL_RESIST, 0);
    }
	@Override
    public Stat2 getSpellFort() {
        return getStat(StatEnum.MAGICAL_CRITICAL_DAMAGE_REDUCE, 0);
    }
	@Override
    public Stat2 getBCastingTime() {
        return getStat(StatEnum.BOOST_CASTING_TIME, 1000);
    }
	@Override
	public Stat2 getConcentration() {
        return getStat(StatEnum.CONCENTRATION, 0);
    }
	@Override
	public Stat2 getRootResistance() {
        return getStat(StatEnum.ROOT_RESISTANCE, 0);
    }
	@Override
	public Stat2 getSnareResistance() {
        return getStat(StatEnum.SNARE_RESISTANCE, 0);
    }
	@Override
	public Stat2 getBindResistance() {
        return getStat(StatEnum.BIND_RESISTANCE, 0);
    }
	@Override
	public Stat2 getFearResistance() {
        return getStat(StatEnum.FEAR_RESISTANCE, 0);
    }
	@Override
	public Stat2 getSleepResistance() {
        return getStat(StatEnum.SLEEP_RESISTANCE, 0);
    }
	@Override
	public Stat2 getAttackSpeed() {
		return getStat(StatEnum.ATTACK_SPEED, owner.getObjectTemplate().getAttackDelay());
	}

	@Override
    public Stat2 getMovementSpeed() {
        int bonusSpeed = 0;
        Player master = owner.getMaster();
        if (master != null && (master.isInFlyingState() || master.isInState(CreatureState.GLIDING))) {
            bonusSpeed += 3000;
        }
        return getStat(StatEnum.SPEED, Math.round(statsTemplate.getRunSpeed() * 1000) + bonusSpeed);
    }

	@Override
	public Stat2 getAttackRange() {
		return getStat(StatEnum.ATTACK_RANGE, owner.getObjectTemplate().getAttackRange() * 1500);
	}

	@Override
	public Stat2 getPDef() {
		return getStat(StatEnum.PHYSICAL_DEFENSE, statsTemplate.getPdefense());
	}

    @Override
	public Stat2 getMDef() {
		return getStat(StatEnum.MAGICAL_DEFEND, 0);
	}

	@Override
	public Stat2 getMResist() {
		return getStat(StatEnum.MAGICAL_RESIST, statsTemplate.getMresist());
	}

    @Override
	public Stat2 getMBResist() {
		int base = 0;
		return getStat(StatEnum.MAGIC_SKILL_BOOST_RESIST, base);
	}

	@Override
	public Stat2 getPower() {
		return getStat(StatEnum.POWER, 100);
	}

	@Override
	public Stat2 getHealth() {
		return getStat(StatEnum.HEALTH, 100);
	}

	@Override
	public Stat2 getAccuracy() {
		return getStat(StatEnum.PHYSICAL_ACCURACY, 100);
	}

	@Override
	public Stat2 getAgility() {
		return getStat(StatEnum.AGILITY, 100);
	}

	@Override
	public Stat2 getKnowledge() {
		return getStat(StatEnum.KNOWLEDGE, 100);
	}

	@Override
	public Stat2 getWill() {
		return getStat(StatEnum.WILL, 100);
	}

	@Override
	public Stat2 getEvasion() {
		return getStat(StatEnum.EVASION, statsTemplate.getEvasion());
	}

	@Override
	public Stat2 getParry() {
		return getStat(StatEnum.PARRY, statsTemplate.getParry());
	}

	@Override
	public Stat2 getBlock() {
		return getStat(StatEnum.BLOCK, statsTemplate.getBlock());
	}

	@Override
	public Stat2 getMainHandPAttack() {
		return getStat(StatEnum.PHYSICAL_ATTACK, statsTemplate.getMainHandAttack());
	}

	@Override
	public Stat2 getMainHandPCritical() {
		return getStat(StatEnum.PHYSICAL_CRITICAL, statsTemplate.getMainHandCritRate());
	}

	@Override
	public Stat2 getMainHandPAccuracy() {
		return getStat(StatEnum.PHYSICAL_ACCURACY, statsTemplate.getMainHandAccuracy());
	}

	@Override
	public Stat2 getMAttack() {
		return getStat(StatEnum.MAGICAL_ATTACK, 100);
	}
	
	@Override
	public Stat2 getMainHandMAttack() {
		return getStat(StatEnum.MAGICAL_ATTACK, 100);
	}

	@Override
	public Stat2 getOffHandMAttack(){
		return getStat(StatEnum.MAGICAL_ATTACK,0);
	}

	@Override
	public Stat2 getMBoost() {
        return getStat(StatEnum.BOOST_MAGICAL_SKILL, 0);
	}

	@Override
	public Stat2 getMAccuracy() {
		return getStat(StatEnum.MAGICAL_ACCURACY, statsTemplate.getMagicAccuracy());
	}

	@Override
	public Stat2 getMCritical() {
		return getStat(StatEnum.MAGICAL_CRITICAL, statsTemplate.getMcrit());
	}

	@Override
	public Stat2 getHpRegenRate() {
		int base = (int) (owner.getLifeStats().getMaxHp() * owner.getMode().getId() == 2 ? 0.05f : 0.025f);
		return getStat(StatEnum.REGEN_HP, base);
	}

	@Override
	public Stat2 getMpRegenRate() {
		throw new IllegalStateException("No mp regen for Summon");
	}

	@Override
	public void updateStatInfo() {
		Player master = owner.getMaster();
		if (master != null) {
			PacketSendUtility.sendPacket(master, new SM_SUMMON_UPDATE(owner));
		}
	}

	@Override
	public void updateSpeedInfo() {
		PacketSendUtility.broadcastPacket(owner, new SM_EMOTION(owner, EmotionType.START_EMOTE2, 0, 0));
	}
}
