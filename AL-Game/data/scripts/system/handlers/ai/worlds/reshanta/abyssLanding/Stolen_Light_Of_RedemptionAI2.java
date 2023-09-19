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
package ai.worlds.reshanta.abyssLanding;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.AbyssLandingService;
import com.aionemu.gameserver.skillengine.SkillEngine;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("stolen_light_of_redemption")
public class Stolen_Light_Of_RedemptionAI2 extends NpcAI2
{
	@Override
	protected void handleSpawned() {
		super.handleSpawned();
		SkillEngine.getInstance().getSkill(getOwner(), 22781, 1, getOwner()).useNoAnimationSkill(); //Guardian Sanctuary Icon.
		SkillEngine.getInstance().getSkill(getOwner(), 22783, 1, getOwner()).useNoAnimationSkill(); //Guardian Sanctuary Field.
	}
	
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			case 884038: //Stolen Light Of Redemption <6,000 Points>
			case 884039: //Stolen Light Of Redemption <6,000 Points>
			case 884040: //Stolen Light Of Redemption <6,000 Points>
				updateRedemptionLanding1();
			break;
			case 884041: //Stolen Light Of Redemption <10,000 Points>
			case 884042: //Stolen Light Of Redemption <10,000 Points>
				updateRedemptionLanding2();
			break;
			case 884043: //Stolen Light Of Redemption <16,000 Points>
			case 884044: //Stolen Light Of Redemption <16,000 Points>
				updateRedemptionLanding3();
			break;
			case 884045: //Stolen Light Of Redemption <30,000 Points>
				updateRedemptionLanding4();
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	private void updateRedemptionLanding1() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ASMODIANS, 6000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ELYOS, 6000);
                    }
                }
			}
		});
	}
	private void updateRedemptionLanding2() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ASMODIANS, 10000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ELYOS, 10000);
                    }
                }
			}
		});
	}
	private void updateRedemptionLanding3() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ASMODIANS, 16000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ELYOS, 16000);
                    }
                }
			}
		});
	}
	private void updateRedemptionLanding4() {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ASMODIANS, 30000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onRewardFacility(Race.ELYOS, 30000);
                    }
                }
			}
		});
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}