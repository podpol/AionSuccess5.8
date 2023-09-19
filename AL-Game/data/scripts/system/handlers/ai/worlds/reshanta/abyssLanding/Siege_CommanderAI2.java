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

import ai.AggressiveNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.AbyssLandingService;
import com.aionemu.gameserver.utils.MathUtil;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("siege_commander")
public class Siege_CommanderAI2 extends AggressiveNpcAI2
{
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			//Elyos Commander.
			case 883635: //Krotan Commander <15,000 Points>
				updateCommanderLanding1(4);
			break;
			case 883665: //Miren Commander <15,000 Points>
				updateCommanderLanding1(5);
			break;
			case 883666: //Kysis Commander <20,000 Points>
				updateCommanderLanding2(6);
			break;
			//Asmodians Commander.
			case 883636: //Krotan Commander <15,000 Points>
				updateCommanderLanding1(16);
			break;
			case 883667: //Miren Commander <15,000 Points>
				updateCommanderLanding1(17);
			break;
			case 883668: //Kysis Commander <20,000 Points>
				updateCommanderLanding2(18);
			break;
		}
		
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	private void updateCommanderLanding1(final int id) {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onDieCommander(Race.ASMODIANS, id, 15000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onDieCommander(Race.ELYOS, id, 15000);
                    }
                }
			}
		});
	}
	private void updateCommanderLanding2(final int id) {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onDieCommander(Race.ASMODIANS, id, 20000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onDieCommander(Race.ELYOS, id, 20000);
                    }
                }
			}
		});
	}
}