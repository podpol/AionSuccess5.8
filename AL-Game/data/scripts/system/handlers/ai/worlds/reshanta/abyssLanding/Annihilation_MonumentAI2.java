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

@AIName("annihilation_monument")
public class Annihilation_MonumentAI2 extends NpcAI2
{
	@Override
	protected void handleDied() {
		switch (getNpcId()) {
			case 883925: //Ide Cannon Tumon 1 Annihilation Monument <10,000 Points>
				updateAnnihilationLanding1(7);
			break;
			case 883926: //Ide Cannon Tumon 2 Annihilation Monument <10,000 Points>
				updateAnnihilationLanding1(8);
			break;
			case 883927: //Artillery Tumon 1 Annihilation Monument <10,000 Points>
				updateAnnihilationLanding1(9);
			break;
			case 883928: //Artillery Tumon 2 Annihilation Monument <10,000 Points>
				updateAnnihilationLanding1(10);
			break;
			case 883929: //Wurg The Glacier Annihilation Monument <15,000 Points>
				updateAnnihilationLanding2(11);
			break;
			case 883930: //Terracrusher Annihilation Monument <15,000 Points>
				updateAnnihilationLanding2(12);
			break;
		}
		super.handleDied();
		AI2Actions.deleteOwner(this);
	}
	
	private void updateAnnihilationLanding1(final int id) {
        World.getInstance().doOnAllPlayers(new Visitor<Player>() {
            @Override
            public void visit(Player player) {
                if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onDieMonuments(Race.ASMODIANS, id, 10000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onDieMonuments(Race.ELYOS, id, 10000);
                    }
                }
            }
        });
    }
	private void updateAnnihilationLanding2(final int id) {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player player) {
				if (MathUtil.isIn3dRange(getOwner().getAggroList().getMostHated(), getOwner(), 20)) {
                    if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ASMODIANS) {
                        AbyssLandingService.getInstance().onDieMonuments(Race.ASMODIANS, id, 15000);
                    } else if (getOwner().getAggroList().getPlayerWinnerRace() == Race.ELYOS) {
                        AbyssLandingService.getInstance().onDieMonuments(Race.ELYOS, id, 15000);
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