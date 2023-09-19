package com.aionemu.gameserver.model.bonus_service;

import com.aionemu.gameserver.model.stats.calc.StatOwner;

/**
 * Created by wanke on 02/03/2017.
 */

public class BoostEventBonus implements StatOwner
{
   /*
   private static final Logger log = LoggerFactory.getLogger(BoostEventBonus.class);
    private List<IStatFunction> functions = new ArrayList<IStatFunction>();
    private BoostEvents boostBonusattr;
	
    public BoostEventBonus(int buffId) {
        boostBonusattr = DataManager.BOOST_EVENT_DATA.getInstanceBonusattr(buffId);
    }
	
    public void applyEffect(Player player, int buffId) {
        if (boostBonusattr == null) {
            return;
        } for (BonusPenaltyAttr BonusPenaltyAttr: boostBonusattr.getPenaltyAttr()) {
            if (BonusPenaltyAttr.getFunc().equals(Func.PERCENT)) {
                functions.add(new StatRateFunction(BonusPenaltyAttr.getStat(), BonusPenaltyAttr.getValue(), true));
            } else {
                functions.add(new StatAddFunction(BonusPenaltyAttr.getStat(), BonusPenaltyAttr.getValue(), true));
            }
        }
        player.getGameStats().addEffect(this, functions);
    }
	
    public void endEffect(Player player, int buffId) {
        functions.clear();
        player.getGameStats().endEffect(this);
    }*/
}