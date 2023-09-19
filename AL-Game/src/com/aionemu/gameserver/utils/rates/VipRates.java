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
package com.aionemu.gameserver.utils.rates;

import com.aionemu.gameserver.configs.main.CraftConfig;
import com.aionemu.gameserver.configs.main.RateConfig;

public class VipRates extends Rates
{
	@Override
	public float getXpRate() {
		return RateConfig.VIP_XP_RATE;
	}
	
	@Override
	public float getGroupXpRate() {
		return RateConfig.VIP_GROUPXP_RATE;
	}
	
	@Override
	public float getBookXpRate() {
		return RateConfig.VIP_BOOK_RATE;
	}
	
	@Override
	public float getQuestXpRate() {
		return RateConfig.VIP_QUEST_XP_RATE;
	}
	
	@Override
	public float getGatheringXPRate() {
		return RateConfig.VIP_GATHERING_XP_RATE;
	}
	
	@Override
	public int getGatheringCountRate() {
        return RateConfig.VIP_GATHERING_COUNT_RATE;
	}
	
	@Override
	public float getCraftingXPRate() {
        return RateConfig.VIP_CRAFTING_XP_RATE;
	}
	
	@Override
	public float getDropRate() {
		return RateConfig.VIP_DROP_RATE;
	}
	
	@Override
	public float getQuestKinahRate() {
		return RateConfig.VIP_QUEST_KINAH_RATE;
	}
	
	@Override
	public float getQuestApRate() {
		return RateConfig.VIP_QUEST_AP_RATE;
	}

	@Override
	public float getGpPlayerLossRate() { return RateConfig.VIP_GP_PLAYER_LOSS_RATE; }

	@Override
	public float getQuestGpRate() {
		return RateConfig.VIP_QUEST_GP_RATE;
	}
	
	@Override
	public float getQuestAbyssOpRate() {
		return RateConfig.VIP_QUEST_ABYSS_OP_RATE;
	}
	
	@Override
	public float getQuestExpBoostRate() {
		return RateConfig.VIP_QUEST_EXP_BOOST_RATE;
	}
	
	@Override
	public float getApPlayerGainRate() {
		return RateConfig.VIP_AP_PLAYER_GAIN_RATE;
	}
	
	@Override
	public float getXpPlayerGainRate() {
		return RateConfig.VIP_XP_PLAYER_GAIN_RATE;
	}
	
	@Override
	public float getApPlayerLossRate() {
		return RateConfig.VIP_AP_PLAYER_LOSS_RATE;
	}
	
	@Override
	public float getApNpcRate() {
		return RateConfig.VIP_AP_NPC_RATE;
	}
	
	@Override
	public float getDpNpcRate() {
		return RateConfig.VIP_DP_NPC_RATE;
	}
	
	@Override
	public float getDpPlayerRate() {
		return RateConfig.VIP_DP_PLAYER_RATE;
	}
	
	@Override
	public int getCraftCritRate() {
        return CraftConfig.VIP_CRAFT_CRIT_RATE;
	}
	
	@Override
	public int getComboCritRate() {
	    return CraftConfig.VIP_CRAFT_COMBO_RATE;
	}
	
	@Override
	public float getDisciplineRewardRate() {
		return RateConfig.VIP_PVP_ARENA_DISCIPLINE_REWARD_RATE;
	}
	
	@Override
	public float getChaosRewardRate() {
		return RateConfig.VIP_PVP_ARENA_CHAOS_REWARD_RATE;
	}
	
	@Override
	public float getHarmonyRewardRate() {
		return RateConfig.VIP_PVP_ARENA_HARMONY_REWARD_RATE;
	}
	
	@Override
	public float getGloryRewardRate() {
		return RateConfig.VIP_PVP_ARENA_GLORY_REWARD_RATE;
	}
	
	@Override
	public float getTollRewardRate() {
	    return RateConfig.VIP_TOLL_REWARD_RATE;
	}
	
	@Override
	public float getGlobalDropRate() {
		return RateConfig.VIP_GLOBAL_DROP_RATE;
	}
	
	@Override
	public float getGpPlayerGainRate() {
		return RateConfig.VIP_GP_PLAYER_GAIN_RATE;
	}
}