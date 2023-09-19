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
package com.aionemu.gameserver.model.autogroup;

import com.aionemu.gameserver.dataholders.DataManager;

import java.util.List;

/**
 * @author Rinzler (Encom)
 */

public enum AutoGroupType
{
	//DREDGION.
	BARANATH_DREDGION(1, 600000, 4) { @Override AutoInstance newAutoInstance() { return new AutoDredgionInstance(); } },
	CHANTRA_DREDGION(2, 600000, 4) { @Override AutoInstance newAutoInstance() { return new AutoDredgionInstance(); } },
	TERATH_DREDGION(3, 600000, 4) { @Override AutoInstance newAutoInstance() { return new AutoDredgionInstance(); } },
	ASHUNATAL_DREDGION(121, 600000, 4) { @Override AutoInstance newAutoInstance() { return new AutoDredgionInstance(); } },
	
	//ARENA PVP 46-60
	ARENA_OF_CHAOS_46_60_1(21, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_CHAOS_46_60_2(22, 110000, 2, 2) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_CHAOS_46_60_3(23, 110000, 2, 3) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_DISCIPLINE_46_60_1(24, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_DISCIPLINE_46_60_2(25, 110000, 2, 2) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_DISCIPLINE_46_60_3(26, 110000, 2, 3) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	CHAOS_TRAINING_GROUNDS_46_60_1(27, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	CHAOS_TRAINING_GROUNDS_46_60_2(28, 110000, 2, 2) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	CHAOS_TRAINING_GROUNDS_46_60_3(29, 110000, 2, 3) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	DISCIPLINE_TRAINING_GROUNDS_46_60_1(30, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	DISCIPLINE_TRAINING_GROUNDS_46_60_2(31, 110000, 2, 2) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	DISCIPLINE_TRAINING_GROUNDS_46_60_3(32, 110000, 2, 3) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_HARMONY_46_60_1(33, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	ARENA_OF_HARMONY_46_60_2(34, 110000, 4, 2) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	ARENA_OF_HARMONY_46_60_3(35, 110000, 4, 3) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	ARENA_OF_GLORY_46_60_1(38, 110000, 4) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	HARMONY_TRAINING_GROUNDS_46_60_1(101, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	HARMONY_TRAINING_GROUNDS_46_60_2(102, 110000, 4, 2) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	HARMONY_TRAINING_GROUNDS_46_60_3(103, 110000, 4, 3) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	UNITY_TRAINING_GROUNDS_46_60_1(104, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	UNITY_TRAINING_GROUNDS_46_60_2(105, 110000, 4, 2) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	UNITY_TRAINING_GROUNDS_46_60_3(106, 110000, 4, 3) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	
	//ARENA PVP 61-65
	ARENA_OF_CHAOS_61_65_1(39, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_DISCIPLINE_61_65_1(40, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_HARMONY_61_65_1(41, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	ARENA_OF_GLORY_61_65_1(42, 110000, 4) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	CHAOS_TRAINING_GROUNDS_61_65_1(43, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	DISCIPLINE_TRAINING_GROUNDS_61_65_1(44, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	HARMONY_TRAINING_GROUNDS_61_65_1(45, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	UNITY_TRAINING_GROUNDS_61_65_1(46, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	
	//ARENA PVP 66-83
	ARENA_OF_CHAOS_66_83_1(113, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_DISCIPLINE_66_83_1(114, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	ARENA_OF_HARMONY_66_83_1(115, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	ARENA_OF_GLORY_66_83_1(116, 110000, 4) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	CHAOS_TRAINING_GROUNDS_66_83_1(117, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	DISCIPLINE_TRAINING_GROUNDS_66_83_1(118, 110000, 2, 1) { @Override AutoInstance newAutoInstance() { return new AutoPvPFFAInstance(); } },
	HARMONY_TRAINING_GROUNDS_66_83_1(119, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	UNITY_TRAINING_GROUNDS_66_83_1(120, 110000, 4, 1) { @Override AutoInstance newAutoInstance() { return new AutoHarmonyInstance(); } },
	
	//BATTLEFIELD.
	KAMAR_BATTLEFIELD(107, 600000, 2) { @Override AutoInstance newAutoInstance() { return new AutoKamarBattlefieldInstance(); } },
	ENGULFED_OPHIDAN_BRIDGE(108, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoEngulfedOphidanBridgeInstance(); } },
	IRON_WALL_WARFRONT(109, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoIronWallWarfrontInstance(); } },
	IDGEL_DOME(111, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoIdgelDomeInstance(); } },
	OPHIDAN_WARPATH(122, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoEngulfedOphidanBridgeInstance(); } },
	IDGEL_DOME_LANDMARK(123, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoIdgelDomeInstance(); } },
	HALL_OF_TENACITY(125, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoHallOfTenacityInstance(); } },
	//5.6
	IDTM_LOBBY_P_01(127, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	IDTM_LOBBY_P_02(128, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	IDTM_LOBBY_E_01(129, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	//IDTM_LOBBY_P_01(130, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	//5.8
	IDRUN(131, 600000, 2)  { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	
	//INSTANCE.
	FIRE_TEMPLE(302, 300000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	NOCHSANA_TRAINING_CAMP(303, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DARK_POETA(304, 1200000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	THEOBOMOS_LAB(305, 1200000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ADMA_STRONGHOLD(306, 1200000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DRAUPNIR_CAVE(307, 1200000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	STEEL_RAKE(308, 1200000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	UDAS_TEMPLE(309, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	LOWER_UDAS_TEMPLE(310, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	EMPYREAN_CRUCIBLE(311, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	RENTUS_BASE(313, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	OPHIDAN_BRIDGE(314, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	INDRATU_FORTRESS(315, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DANUAR_RELIQUARY(316, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	SAURO_SUPPLY_BASE(317, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	AETHEROGENETICS_LAB(318, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DRAGON_LORD_REFUGE(322, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ALQUIMIA_RESEARCH_CENTER(323, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	INFINITY_SHARD(324, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	NIGHTMARE_CIRCUS(330, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	BESHMUNDIR_TEMPLE_NORMAL(331, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	BESHMUNDIR_TEMPLE_HARD(332, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	TIAMAT_STRONGHOLD(333, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	AZOTURAN_FORTRESS(334, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ETERNAL_BASTION(335, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	SEIZED_DANUAR_SANCTUARY(336, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	RUKIBUKI_CIRCUS_TROUPE_CAMP(337, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ILLUMINARY_OBELISK(338, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	SHUGO_IMPERIAL_TOMB(339, 600000, 3) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	LUCKY_OPHIDAN_BRIDGE(340, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	LUCKY_DANUAR_RELIQUARY(341, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	INFERNAL_ILLUMINARY_OBELISK(342, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	INFERNAL_DANUAR_RELIQUARY(345, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DANUAR_SANCTUARY(346, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DRAKENSPIRE_DEPTHS(347, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	THE_SHUGO_EMPEROR_VAULT(348, 600000, 3) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	OCCUPIED_RENTUS_BASE(349, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ARCHIVES_OF_ETERNITY(350, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	CRADLE_OF_ETERNITY(351, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	TRIALS_OF_ETERNITY(352, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	EMPEROR_TRILLIRUNERK_SAFE(353, 600000, 3) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ADMA_FALL(354, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	THEOBOMOS_TEST_CHAMBER(355, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DRAKENSEER_LAIR(356, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	FALLEN_POETA(357, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ESOTERRACE(358, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	BASTION_OF_SOULS(359, 600000, 12) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	PADMARASHKA_CAVE(360, 600000, 12) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	IDAB1_HEROES_L(419, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	IDAB1_HEROES_D(421, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	
	//INSTANCE INTER PARTY MATCH
	STEEL_RAKE_INTER_PARTY_MATCH(401, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	UDAS_TEMPLE_INTER_PARTY_MATCH(402, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	LOWER_UDAS_TEMPLE_INTER_PARTY_MATCH(403, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	INDRATU_FORTRESS_INTER_PARTY_MATCH(404, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	AZOTURAN_FORTRESS_INTER_PARTY_MATCH(405, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	AETHEROGENETICS_LAB_INTER_PARTY_MATCH(406, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ALQUIMIA_RESEARCH_CENTER_INTER_PARTY_MATCH(407, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ANGUISHED_DRAGON_LORD_REFUGE_INTER_PARTY_MATCH(408, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	FIRE_TEMPLE_OF_MEMORIES(409, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	RENTUS_BASE_INTER_PARTY_MATCH(410, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DRAGON_LORD_REFUGE_INTER_PARTY_MATCH(411, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DANUAR_SANCTUARY_INTER_PARTY_MATCH(412, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	TIAMAT_STRONGHOLD_INTER_PARTY_MATCH(413, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	OPHIDAN_BRIDGE_INTER_PARTY_MATCH(414, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DRAKENSPIRE_DEPTHS_INTER_PARTY_MATCH(415, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	
	//PANESTERRA 4.7
	BELUS(10001, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ASPIDA(10005, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	ATANATOS(10007, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } },
	DISILLON(10010, 600000, 6) { @Override AutoInstance newAutoInstance() { return new AutoGeneralInstance(); } };
	
	private int instanceMaskId;
	private int time;
	private byte playerSize;
	private byte difficultId;
	private AutoGroup template;
	
	private AutoGroupType(int instanceMaskId, int time, int playerSize, int difficultId) {
        this(instanceMaskId, time, playerSize);
        this.difficultId = (byte) difficultId;
    }
	
	private AutoGroupType(int instanceMaskId, int time, int playerSize) {
        this.instanceMaskId = instanceMaskId;
        this.time = time;
        this.playerSize = (byte) playerSize;
        template = DataManager.AUTO_GROUP.getTemplateByInstaceMaskId(this.instanceMaskId);
    }
	
	public int getInstanceMapId() {
		return template.getInstanceId();
	}
	
	public byte getPlayerSize() {
		return playerSize;
	}
	
	public int getInstanceMaskId() {
		return instanceMaskId;
	}
	
	public int getNameId() {
		return template.getNameId();
	}
	
	public int getTitleId() {
		return template.getTitleId();
	}
	
	public int getTime() {
		return time;
	}
	
	public int getMinLevel() {
		return template.getMinLvl();
	}
	
	public int getMaxLevel() {
		return template.getMaxLvl();
	}
	
	public boolean hasRegisterGroup() {
		return template.hasRegisterGroup();
	}
	
	public boolean hasRegisterFast() {
		return template.hasRegisterFast();
	}
	
	public boolean hasSpecialPurpose() {
		return template.hasSpecialPurpose();
	}
	
	public boolean hasRegisterNew() {
		return template.hasRegisterNew();
	}
	
	public boolean containNpcId(int npcId) {
		return template.getNpcIds().contains(npcId);
	}
	
	public List<Integer> getNpcIds() {
		return template.getNpcIds();
	}
	
	public boolean isDredgion() {
		switch (this) {
			case BARANATH_DREDGION:
			case CHANTRA_DREDGION:
			case TERATH_DREDGION:
				return true;
		}
		return false;
	}
	public boolean isAsyunatar() {
		switch (this) {
			case ASHUNATAL_DREDGION:
				return true;
		}
		return false;
	}
	
	public boolean isKamar() {
		switch (this) {
			case KAMAR_BATTLEFIELD:
				return true;
		}
		return false;
	}
	
	public boolean isOphidan() {
		switch (this) {
			case ENGULFED_OPHIDAN_BRIDGE:
				return true;
		}
		return false;
	}
	public boolean isSuspiciousOphidan() {
		switch (this) {
			case OPHIDAN_WARPATH:
				return true;
		}
		return false;
	}
	
	public boolean isBastion() {
		switch (this) {
			case IRON_WALL_WARFRONT:
				return true;
		}
		return false;
	}
	
	public boolean isIdgelDome() {
		switch (this) {
			case IDGEL_DOME:
				return true;
		}
		return false;
	}
	public boolean isIdgelDomeLandmark() {
		switch (this) {
			case IDGEL_DOME_LANDMARK:
				return true;
		}
		return false;
	}
	
	public boolean isHallOfTenacity() {
		switch (this) {
			case HALL_OF_TENACITY:
				return true;
		}
		return false;
	}
	
	public boolean isGrandArenaTrainingCamp() {
		switch (this) {
			case IDTM_LOBBY_P_01:
				return true;
		}
		return false;
	}
	
	public boolean isIDRun() {
		switch (this) {
			case IDRUN:
				return true;
		}
		return false;
	}
	
	public static AutoGroupType getAGTByMaskId(int instanceMaskId) {
		for (AutoGroupType autoGroupsType : values()) {
			if (autoGroupsType.getInstanceMaskId() == instanceMaskId) {
				return autoGroupsType;
			}
		}
		return null;
	}
	
	public static AutoGroupType getAutoGroup(int level, int npcId) {
		for (AutoGroupType agt : values()) {
			if (agt.hasLevelPermit(level) && agt.containNpcId(npcId)) {
				return agt;
			}
		}
		return null;
	}
	
	public static AutoGroupType getAutoGroupByWorld(int level, int worldId) {
		for (AutoGroupType agt : values()) {
			if (agt.getInstanceMapId() == worldId && agt.hasLevelPermit(level)) {
				return agt;
			}
		}
		return null;
	}
	
	public static AutoGroupType getAutoGroup(int npcId) {
		for (AutoGroupType agt : values()) {
			if (agt.containNpcId(npcId)) {
				return agt;
			}
		}
		return null;
	}
	
	public boolean isPvPSoloArena() {
		switch (this) {
			case ARENA_OF_DISCIPLINE_46_60_1:
			case ARENA_OF_DISCIPLINE_46_60_2:
			case ARENA_OF_DISCIPLINE_46_60_3:
			case ARENA_OF_DISCIPLINE_61_65_1:
			case ARENA_OF_DISCIPLINE_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isTrainingPvPSoloArena() {
		switch (this) {
			case DISCIPLINE_TRAINING_GROUNDS_46_60_1:
			case DISCIPLINE_TRAINING_GROUNDS_46_60_2:
			case DISCIPLINE_TRAINING_GROUNDS_46_60_3:
			case DISCIPLINE_TRAINING_GROUNDS_61_65_1:
			case DISCIPLINE_TRAINING_GROUNDS_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isPvPFFAArena() {
		switch (this) {
			case ARENA_OF_CHAOS_46_60_1:
			case ARENA_OF_CHAOS_46_60_2:
			case ARENA_OF_CHAOS_46_60_3:
			case ARENA_OF_CHAOS_61_65_1:
			case ARENA_OF_CHAOS_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isTrainingPvPFFAArena() {
		switch (this) {
			case CHAOS_TRAINING_GROUNDS_46_60_1:
			case CHAOS_TRAINING_GROUNDS_46_60_2:
			case CHAOS_TRAINING_GROUNDS_46_60_3:
			case CHAOS_TRAINING_GROUNDS_61_65_1:
			case CHAOS_TRAINING_GROUNDS_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isTrainingHarmonyArena() {
		switch (this) {
			case HARMONY_TRAINING_GROUNDS_46_60_1:
			case HARMONY_TRAINING_GROUNDS_46_60_2:
			case HARMONY_TRAINING_GROUNDS_46_60_3:
			case HARMONY_TRAINING_GROUNDS_61_65_1:
			case HARMONY_TRAINING_GROUNDS_66_83_1:
			case UNITY_TRAINING_GROUNDS_46_60_1:
			case UNITY_TRAINING_GROUNDS_46_60_2:
			case UNITY_TRAINING_GROUNDS_46_60_3:
			case UNITY_TRAINING_GROUNDS_61_65_1:
			case UNITY_TRAINING_GROUNDS_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isHarmonyArena() {
		switch (this) {
			case ARENA_OF_HARMONY_46_60_1:
			case ARENA_OF_HARMONY_46_60_2:
			case ARENA_OF_HARMONY_46_60_3:
			case ARENA_OF_HARMONY_61_65_1:
			case ARENA_OF_HARMONY_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isGloryArena() {
		switch (this) {
			case ARENA_OF_GLORY_46_60_1:
			case ARENA_OF_GLORY_61_65_1:
			case ARENA_OF_GLORY_66_83_1:
				return true;
		}
		return false;
	}
	
	public boolean isPvpArena() {
		return isHarmonyArena() || isTrainingHarmonyArena() || isTrainingPvPFFAArena() || isPvPFFAArena() || isTrainingPvPSoloArena() || isPvPSoloArena();
	}
	
	public boolean hasLevelPermit(int level) {
		return level >= getMinLevel() && level <= getMaxLevel();
	}
	
	public byte getDifficultId() {
		return difficultId;
	}
	
	public AutoInstance getAutoInstance() {
		return newAutoInstance();
	}
	
	abstract AutoInstance newAutoInstance();
}