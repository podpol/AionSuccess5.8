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
import com.aionemu.gameserver.model.instance.playerreward.HarmonyGroupReward;
import com.aionemu.gameserver.world.WorldMapInstance;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@InstanceID(300450000)
public class ArenaOfHarmonyInstance extends HarmonyArenaInstance
{
	@Override
	public void onInstanceCreate(WorldMapInstance instance) {
		killBonus = 1000;
		deathFine = -150;
		super.onInstanceCreate(instance);
	}
	
	@Override
	protected void reward() {
		float totalScoreAP = (1.0f * 3) * 100;
		float totalScoreGP = (1.0f * 3) * 100;
		float totalScoreCourage = (1.0f * 3) * 100;
		float totalScoreInfinity = (1.0f * 3) * 100;
		int totalPoints = instanceReward.getTotalPoints();
		for (HarmonyGroupReward group : instanceReward.getGroups()) {
			int score = group.getPoints();
			int rank = instanceReward.getRank(score);
			float percent = group.getParticipation();
			float scoreRate = ((float) score / (float) totalPoints);
			int basicAP = 100;
			int rankingAP = 0;
			basicAP *= percent;
			int basicGP = 100;
			int rankingGP = 0;
			basicGP *= percent;
			int basicCoI = 0;
			int rankingCoI = 0;
			basicCoI *= percent;
			int basicCiI = 0;
			int rankingCiI = 0;
			basicCiI *= percent;
			int scoreAP = (int) (totalScoreAP * scoreRate);
			int scoreGP = (int) (totalScoreGP * scoreRate);
			switch (rank) {
				case 0:
					rankingAP = 681;
					rankingGP = 481;
					rankingCoI = 49;
					rankingCiI = 49;
					group.setGloryTicket(1);
				break;
				case 1:
					rankingAP = 487;
					rankingGP = 287;
					rankingCoI = 20;
					rankingCiI = 20;
				break;
				case 2:
					rankingAP = 251;
					rankingGP = 151;
					rankingCoI = 1;
					rankingCiI = 1;
				break;
			}
			rankingAP *= percent;
			rankingGP *= percent;
			rankingCoI *= percent;
			rankingCiI *= percent;
			int scoreCoI = (int) (totalScoreCourage * scoreRate);
			int scoreCiI = (int) (totalScoreInfinity * scoreRate);
			group.setBasicAP(basicAP);
			group.setRankingAP(rankingAP);
			group.setScoreAP(scoreAP);
			group.setBasicGP((int)(basicGP * 0.1));
			group.setRankingGP((int) (rankingGP * 0.1));
			group.setScoreGP((int)(scoreGP * 0.1));
			group.setBasicCourage(basicCoI);
			group.setRankingCourage(rankingCoI);
			group.setScoreCourage(scoreCoI);
			group.setBasicInfinity(basicCiI);
			group.setRankingInfinity(rankingCiI);
			group.setScoreInfinity(scoreCiI);
		}
		super.reward();
	}
}