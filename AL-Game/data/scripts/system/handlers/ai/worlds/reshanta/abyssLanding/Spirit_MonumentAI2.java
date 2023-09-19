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
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("spirit_monument")
public class Spirit_MonumentAI2 extends NpcAI2
{
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			//Elyos Monument.
			case 883922: //Krotan Guardian Spirit's Monument <20,000 Points>
				updateGuardianLanding(1);
			break;
			case 883923: //Miren Guardian Spirit's Monument <20,000 Points>
				updateGuardianLanding(2);
			break;
			case 883924: //Kysis Guardian Spirit's Monument <20,000 Points>
				updateGuardianLanding(3);
			break;
			//Asmodians Monument.
			case 883941: //Krotan Guardian Spirit's Monument <20,000 Points>
				updateGuardianLanding(13);
			break;
			case 883942: //Miren Guardian Spirit's Monument <20,000 Points>
				updateGuardianLanding(14);
			break;
			case 883943: //Kysis Guardian Spirit's Monument <20,000 Points>
				updateGuardianLanding(15);
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	private void updateGuardianLanding(final int id) {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onDieMonuments(Race.ASMODIANS, id, 20000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onDieMonuments(Race.ELYOS, id, 20000);
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