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
package com.aionemu.gameserver.model.team2.group;

import com.aionemu.gameserver.model.gameobjects.Pet;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.pet.PetFunctionType;
import com.google.common.base.Predicate;

/**
 * @author ATracer
 */
public class PlayerFilters {

    public static final Predicate<Player> ONLINE = new Predicate<Player>() {
        @Override
        public boolean apply(Player member) {
            return member.isOnline();
        }
    };

    public static final class MentorSuiteFilter implements Predicate<Player> {

        private final Player player;

        public MentorSuiteFilter(Player player) {
            this.player = player;
        }

        @Override
        public boolean apply(Player member) {
            return member.getLevel() + 9 < player.getLevel();
        }
    }

    public static final class SameInstanceFilter implements Predicate<Player> {

        private final Player player;

        public SameInstanceFilter(Player player) {
            this.player = player;
        }

        @Override
        public boolean apply(Player member) {
            return member.getInstanceId() == player.getInstanceId();
        }
    }

    public static final Predicate<Player> HAS_LOOT_PET = new Predicate<Player>() {
        @Override
        public boolean apply(Player member) {
            Pet pet = member.getPet();
            if (pet == null) {
                return false;
            }
            return pet.getPetTemplate().getPetFunction(PetFunctionType.LOOT) != null;
        }
    };

    public static final class ExcludePlayerFilter implements Predicate<Player> {

        private final Player player;

        public ExcludePlayerFilter(Player player) {
            this.player = player;
        }

        @Override
        public boolean apply(Player member) {
            return !player.getObjectId().equals(member.getObjectId());
        }
    }
}
