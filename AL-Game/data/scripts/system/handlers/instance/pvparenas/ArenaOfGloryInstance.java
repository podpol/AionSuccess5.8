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
package instance.pvparenas;

import com.aionemu.gameserver.instance.handlers.InstanceID;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.instance.playerreward.InstancePlayerReward;
import com.aionemu.gameserver.model.instance.playerreward.PvPArenaPlayerReward;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@InstanceID(300550000)
public class ArenaOfGloryInstance extends PvPArenaInstance
{
	@Override
	public void onInstanceCreate(WorldMapInstance instance) {
		killBonus = 1000;
		deathFine = -200;
		super.onInstanceCreate(instance);
	}
	
	@Override
	protected void reward() {
		int totalPoints = instanceReward.getTotalPoints();
		int size = instanceReward.getInstanceRewards().size();
		float totalScoreAP = (1.0f * size) * 100;
		float totalScoreGP = (1.0f * size) * 100;
		float rankingRate = 0;
		if (size > 1) {
			rankingRate = (0.077f * (4 - size));
		}
		float totalRankingAP = 30800 - 30800 * rankingRate;
		float totalRankingGP = 800 - 800 * rankingRate;
		for (InstancePlayerReward playerReward : instanceReward.getInstanceRewards()) {
			PvPArenaPlayerReward reward = (PvPArenaPlayerReward) playerReward;
			if (!reward.isRewarded()) {
				float playerRate = 1;
				Player player = instance.getPlayer(playerReward.getOwner());
				if (player != null) {
					playerRate = player.getRates().getGloryRewardRate();
				}
				int score = reward.getScorePoints();
				float scoreRate = ((float) score / (float) totalPoints);
				int rank = instanceReward.getRank(score);
				float percent = reward.getParticipation();
				float generalRate = 0.167f + rank * 0.227f;
				int basicAP = 100;
				int basicGP = 100;
				float rankingAP = totalRankingAP;
				float rankingGP = totalRankingGP;
				if (rank > 0) {
					rankingAP = rankingAP - rankingAP * generalRate;
					rankingGP = rankingGP - rankingGP * generalRate;
				}
				int scoreAP = (int) (totalScoreAP * scoreRate);
				int scoreGP = (int) (totalScoreGP * scoreRate);
				//<Abyss Points>
				basicAP *= percent;
				rankingAP *= percent;
				rankingAP *= playerRate;
				reward.setBasicAP(basicAP);
				reward.setRankingAP((int) rankingAP);
				reward.setScoreAP(scoreAP);
				//<Glory Points>
				basicGP *= percent;
				rankingGP *= percent;
				rankingGP *= playerRate;
				reward.setBasicGP((int)(basicGP * 0.1));
				reward.setRankingGP((int) (rankingGP * 0.1));
				reward.setScoreGP((int)(scoreGP * 0.1));
				switch (rank) {
					case 0:
						reward.setGloriousInsignia(1);
						reward.setMithrilMedal(5);
					break;
					case 1:
						reward.setGloriousInsignia(1);
						reward.setPlatinumMedal(3);
					break;
					case 2:
						reward.setPlatinumMedal(3);
					break;
					case 3:
						reward.setLifeSerum(1);
					break;
				}
			}
		}
		super.reward();
	}
}