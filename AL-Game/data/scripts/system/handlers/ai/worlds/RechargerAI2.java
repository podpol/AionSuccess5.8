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
package ai.worlds;

import ai.ActionItemNpcAI2;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ATTACK_STATUS;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SKILL_COOLDOWN;
import com.aionemu.gameserver.skillengine.model.SkillTargetSlot;
import com.aionemu.gameserver.utils.PacketSendUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ghostfur (Aion-Unique)
 */
@AIName("recharger") //730397
public class RechargerAI2 extends ActionItemNpcAI2 {

    @Override
    protected void handleUseItemFinish(Player player) {
        switch (getNpcId()){
            case 730397:
                reseter(player);
                super.handleUseItemFinish(player);
                break;
        }
    }

    @Override
    protected void handleDied() {
        AI2Actions.deleteOwner(this);
    }

    public void reseter(Player player){
        player.getLifeStats().increaseHp(SM_ATTACK_STATUS.TYPE.HP, player.getLifeStats().getMaxHp() + 1);
        player.getLifeStats().increaseMp(SM_ATTACK_STATUS.TYPE.MP, player.getLifeStats().getMaxMp() + 1);
        player.getEffectController().removeAbnormalEffectsByTargetSlot(SkillTargetSlot.SPEC2);

        List<Integer> delayIds = new ArrayList<Integer>();
        if (player.getSkillCoolDowns() != null) {
            long currentTime = System.currentTimeMillis();
            for (Map.Entry<Integer, Long> en : player.getSkillCoolDowns().entrySet()) {
                delayIds.add(en.getKey());
                if(delayIds.contains(11885) || delayIds.contains(11886) || delayIds.contains(11887) || delayIds.contains(11888) || delayIds.contains(11889) ||
                        delayIds.contains(11890) || delayIds.contains(11891) || delayIds.contains(11892) || delayIds.contains(11893) || delayIds.contains(11894)){
                    delayIds.remove(en.getKey());
                }
            }

            for (Integer delayId : delayIds) {
                player.setSkillCoolDown(delayId, currentTime);
            }

            delayIds.clear();
            PacketSendUtility.sendPacket(player, new SM_SKILL_COOLDOWN(player.getSkillCoolDowns()));
        }
    }
}
