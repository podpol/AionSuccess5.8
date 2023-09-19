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
package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.mail.Mails;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.model.templates.revive_start_points.*;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ae_static_data")
@XmlAccessorType(XmlAccessType.NONE)
public class StaticData
{
	@XmlElement(name = "world_maps")
	public WorldMapsData worldMapsData;
	@XmlElement(name = "npc_trade_list")
	public TradeListData tradeListData;
	@XmlElement(name = "npc_teleporter")
	public TeleporterData teleporterData;
	@XmlElement(name = "teleport_location")
	public TeleLocationData teleLocationData;
	@XmlElement(name = "bind_points")
	public BindPointData bindPointData;
	@XmlElement(name = "quests")
	public QuestsData questData;
	@XmlElement(name = "quest_scripts")
	public XMLQuests questsScriptData;
	@XmlElement(name = "player_experience_table")
	public PlayerExperienceTable playerExperienceTable;
	@XmlElement(name = "player_stats_templates")
	public PlayerStatsData playerStatsData;
	@XmlElement(name = "summon_stats_templates")
	public SummonStatsData summonStatsData;
	@XmlElement(name = "item_templates")
	public ItemData itemData;
	@XmlElement(name = "random_bonuses")
	public ItemRandomBonusData itemRandomBonuses;
	@XmlElement(name = "npc_templates")
	public NpcData npcData;
	@XmlElement(name = "npc_shouts")
	public NpcShoutData npcShoutData;
	@XmlElement(name = "player_initial_data")
	public PlayerInitialData playerInitialData;
	@XmlElement(name = "skill_data")
	public SkillData skillData;
	@XmlElement(name = "motion_times")
	public MotionData motionData;
	@XmlElement(name = "skill_tree")
	public SkillTreeData skillTreeData;
	@XmlElement(name = "cube_expander")
	public CubeExpandData cubeExpandData;
	@XmlElement(name = "warehouse_expander")
	public WarehouseExpandData warehouseExpandData;
	@XmlElement(name = "player_titles")
	public TitleData titleData;
	@XmlElement(name = "gatherable_templates")
	public GatherableData gatherableData;
	@XmlElement(name = "npc_walker")
	public WalkerData walkerData;
	@XmlElement(name = "zones")
	public ZoneData zoneData;
	@XmlElement(name = "goodslists")
	public GoodsListData goodsListData;
	@XmlElement(name = "tribe_relations")
	public TribeRelationsData tribeRelationsData;
	@XmlElement(name = "recipe_templates")
	public RecipeData recipeData;
	@XmlElement(name = "luna_templates")
	public LunaData lunaData;
	@XmlElement(name = "chest_templates")
	public ChestData chestData;
	@XmlElement(name = "staticdoor_templates")
	public StaticDoorData staticDoorData;
	@XmlElement(name = "item_sets")
	public ItemSetData itemSetData;
	@XmlElement(name = "npc_factions")
	public NpcFactionsData npcFactionsData;
	@XmlElement(name = "npc_skill_templates")
	public NpcSkillData npcSkillData;
	@XmlElement(name = "pet_skill_templates")
	public PetSkillData petSkillData;
	@XmlElement(name = "siege_locations")
	public SiegeLocationData siegeLocationData;
	@XmlElement(name = "fly_rings")
	public FlyRingData flyRingData;
	@XmlElement(name = "shields")
	public ShieldData shieldData;
	@XmlElement(name = "pets")
	public PetData petData;
	@XmlElement(name = "pet_feed")
	public PetFeedData petFeedData;
	@XmlElement(name = "dopings")
	public PetDopingData petDopingData;
	@XmlElement(name = "merchands")
	public PetMerchandData petMerchandData;
	@XmlElement(name = "guides")
	public GuideHtmlData guideData;
	@XmlElement(name = "roads")
	public RoadData roadData;
	@XmlElement(name = "instance_cooltimes")
	public InstanceCooltimeData instanceCooltimeData;
	@XmlElement(name = "decomposable_items")
	public DecomposableItemsData decomposableItemsData;
	@XmlElement(name = "ai_templates")
	public AIData aiData;
	@XmlElement(name = "flypath_template")
	public FlyPathData flyPath;
	@XmlElement(name = "windstreams")
	public WindstreamData windstreamsData;
	@XmlElement(name = "item_restriction_cleanups")
	public ItemRestrictionCleanupData itemCleanup;
	@XmlElement(name = "assembled_npcs")
	public AssembledNpcsData assembledNpcData;
	@XmlElement(name = "cosmetic_items")
	public CosmeticItemsData cosmeticItemsData;
	@XmlElement(name = "npc_drops")
	public NpcDropData npcDropData;
	@XmlElement(name = "auto_groups")
	public AutoGroupData autoGroupData;
	@XmlElement(name = "events_config")
	public EventData eventData;
	@XmlElement(name = "spawns")
	public SpawnsData2 spawnsData2;
	@XmlElement(name = "item_groups")
	public ItemGroupsData itemGroupsData;
	@XmlElement(name = "polymorph_panels")
	public PanelSkillsData panelSkillsData;
	@XmlElement(name = "instance_bonusattrs")
	public InstanceBuffData instanceBuffData;
	@XmlElement(name = "housing_objects")
	public HousingObjectData housingObjectData;
	@XmlElement(name = "rides")
	public RideData rideData;
	@XmlElement(name = "instance_exits")
	public InstanceExitData instanceExitData;
	@XmlElement(name = "portal_locs")
	PortalLocData portalLocData;
	@XmlElement(name = "portal_templates2")
	Portal2Data portalTemplate2;
	@XmlElement(name = "house_lands")
	public HouseData houseData;
	@XmlElement(name = "buildings")
	public HouseBuildingData houseBuildingData;
	@XmlElement(name = "house_parts")
	public HousePartsData housePartsData;
	@XmlElement(name = "curing_objects")
	public CuringObjectsData curingObjectsData;
	@XmlElement(name = "house_npcs")
	public HouseNpcsData houseNpcsData;
	@XmlElement(name = "assembly_items")
	public AssemblyItemsData assemblyItemData;
	@XmlElement(name = "multi_returns")
    public MultiReturnItemData multiReturnItemData;
	@XmlElement(name = "lboxes")
	public HouseScriptData houseScriptData;
	@XmlElement(name = "mails")
	public Mails systemMailTemplates;
	@XmlElement(name = "challenge_tasks")
	public ChallengeData challengeData;
	@XmlElement(name = "town_spawns_data")
	public TownSpawnsData townSpawnsData;
	@XmlElement(name = "charge_skills")
    public ChargeSkillData chargeSkillData;
	@XmlElement(name = "spring_objects")
	public SpringObjectsData springObjectsData;
	@XmlElement(name = "robots")
	public RobotData robotData;
	@XmlElement(name = "abyss_bonusattrs")
	public AbyssBuffData abyssBuffData;
	@XmlElement(name = "abyss_groupattrs")
	public AbyssGroupData abyssGroupData;
	@XmlElement(name = "absolute_stats")
	public AbsoluteStatsData absoluteStatsData;
	@XmlElement(name = "base_locations")
	public BaseData baseData;
	@XmlElement(name = "material_templates")
	public MaterialData materiaData;
	@XmlElement(name = "weather")
	public MapWeatherData mapWeatherData;
	@XmlElement(name = "dimensional_vortex")
	public VortexData vortexData;
	@XmlElement(name = "beritra_invasion")
	public BeritraData beritraData;
	@XmlElement(name = "agent_fight")
	public AgentData agentData;
	@XmlElement(name = "svs")
	public SvsData svsData;
	@XmlElement(name = "rvr")
	public RvrData rvrData;
	@XmlElement(name = "moltenus")
	public MoltenusData moltenusData;
	@XmlElement(name = "dynamic_rift")
	public DynamicRiftData dynamicRiftData;
	@XmlElement(name = "instance_rift")
	public InstanceRiftData instanceRiftData;
	@XmlElement(name = "nightmare_circus")
	public NightmareCircusData nightmareCircusData;
	@XmlElement(name = "zorshiv_dredgion")
	public ZorshivDredgionData zorshivDredgionData;
	@XmlElement(name = "dominion_locations")
	public LegionDominionData legionDominionData;
	@XmlElement(name = "idian_depths")
	public IdianDepthsData idianDepthsData;
	@XmlElement(name = "anoha")
	public AnohaData anohaData;
	@XmlElement(name = "iu")
	public IuData iuData;
	@XmlElement(name = "conquest")
	public ConquestData conquestData;
	@XmlElement(name = "serial_guards")
	public SerialGuardData serialGuardData;
	@XmlElement(name = "serial_killers")
	public SerialKillerData serialKillerData;
	@XmlElement(name = "rift_locations")
	public RiftData riftData;
	@XmlElement(name = "service_bonusattrs")
	public ServiceBuffData serviceBuffData;
	@XmlElement(name = "players_service_bonusattrs")
	public PlayersBonusData playersBonusData;
	@XmlElement(name = "enchant_templates")
   	public ItemEnchantData itemEnchantData;
	@XmlElement(name = "hotspot_location")
	public HotspotLocationData hotspotLocationData;
	@XmlElement(name = "item_upgrades")
  	public ItemUpgradeData itemUpgradeData;
	@XmlElement(name = "atreian_passports")
	public AtreianPassportData atreianPassportData;
	@XmlElement(name = "game_experience_items")
    public GameExperienceData gameExperienceData;
	@XmlElement(name = "abyss_ops")
    public AbyssOpData abyssOpData;
	@XmlElement(name = "panel_cps")
    public PanelCpData panelCpData;
	@XmlElement(name="decomposable_select_items")
    public DecomposableSelectItemsData decomposableSelectItemsData;
	@XmlElement(name = "pet_bonusattrs")
	public PetBuffData petBuffData;
	@XmlElement(name = "landing")
	public LandingData landingLocationData;
	@XmlElement(name="landing_special")
	public LandingSpecialData landingSpecialLocationData;
	@XmlElement(name="luna_consume_rewards")
	public LunaConsumeRewardsData lunaConsumeRewardsData;
	@XmlElement(name="item_custom_sets")
	public ItemCustomSetData itemCustomSet;
	@XmlElement(name="minions")
	public MinionData minionData;
	@XmlElement(name="f2p_bonus")
	public F2PBonusData f2pBonus;
	@XmlElement(name = "arcadelist")
	public ArcadeUpgradeData arcadeUpgradeData;
	@XmlElement(name = "global_rules")
	public GlobalDropData globalDropData;
	@XmlElement(name = "item_skill_enhances")
	public ItemSkillEnhanceData itemSkillEnhance;
	@XmlElement(name = "boost_events")
	public BoostEventdata boostEvents;
	@XmlElement(name = "monster_books")
	public AtreianBestiaryData atreianBestiary;
	@XmlElement(name = "events_window")
	public EventsWindowData eventsWindow;
	@XmlElement(name = "reward_mail_templates")
	public MailRewardData mailReward;
	@XmlElement(name = "luna_dice")
	public LunaDiceData lunaDice;
	@XmlElement(name = "minions_list")
	public ItemMinionListData itemMinionList;
	@XmlElement(name = "revive_world_start_points")
	public ReviveWorldStartPointsData reviveWorldStartPoints;
	@XmlElement(name = "tower_of_eternity")
	public TowerOfEternityData towerOfEternity;
	@XmlElement(name = "instance_revive_start_points")
	public ReviveInstanceStartPointsData reviveInstanceStartPoints;
	@XmlElement(name = "outpost_locations")
	public OutpostData outpostLocation;
	@XmlElement(name = "stones_cp")
	public StoneCpData stoneCp;
	@XmlElement(name = "tower_reward_templates")
	public TowerRewardData towerReward;
	@XmlElement(name = "shugo_sweeps")
	public ShugoSweepRewardData shugoSweepsRewardData;
	@XmlElement(name="skill_skins")
	public SkillSkinData skillSkinData;
	
	@SuppressWarnings("unused")
	private void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
		DataManager.log.info("[DataManager] Loaded " + worldMapsData.size() + " Map");
		DataManager.log.info("[DataManager] Loaded " + playerExperienceTable.getMaxLevel() + " Level");
		DataManager.log.info("[DataManager] Loaded " + playerStatsData.size() + " Player Stats Template");
		DataManager.log.info("[DataManager] Loaded " + summonStatsData.size() + " Summon Stats Template");
		DataManager.log.info("[DataManager] Loaded " + itemCleanup.size() + " Item Cleanup");
		DataManager.log.info("[DataManager] Loaded " + itemData.size() + " Item Template");
		DataManager.log.info("[DataManager] Loaded " + itemRandomBonuses.size() + " Random Bonus Template");
		DataManager.log.info("[DataManager] Loaded " + itemGroupsData.bonusSize() + " Bonus Item Group Template");
		DataManager.log.info("[DataManager] Loaded " + itemGroupsData.petFoodSize() + " Pet Food Item");
		DataManager.log.info("[DataManager] Loaded " + npcData.size() + " Npc Templates");
		DataManager.log.info("[DataManager] Loaded " + systemMailTemplates.size() + " System Mail Template");
		DataManager.log.info("[DataManager] Loaded " + npcShoutData.size() + " Npc Shout Template");
		DataManager.log.info("[DataManager] Loaded " + petData.size() + " Pet Template");
		DataManager.log.info("[DataManager] Loaded " + petFeedData.size() + " Food Flavour");
		DataManager.log.info("[DataManager] Loaded " + petDopingData.size() + " Pet Doping Template");
		DataManager.log.info("[DataManager] Loaded " + petMerchandData.size() + " Pet Merchand Template");
		DataManager.log.info("[DataManager] Loaded " + playerInitialData.size() + " Initial Player Template");
		DataManager.log.info("[DataManager] Loaded " + goodsListData.size() + " Goods List");
		DataManager.log.info("[DataManager] Loaded " + tradeListData.size() + " Npc Trade List");
		DataManager.log.info("[DataManager] Loaded " + teleporterData.size() + " Npc Teleporter Template");
		DataManager.log.info("[DataManager] Loaded " + teleLocationData.size() + " Teleport Location");
		DataManager.log.info("[DataManager] Loaded " + hotspotLocationData.size() + " Hotspot Location");
		DataManager.log.info("[DataManager] Loaded " + skillData.size() + " Skill Templates");
		DataManager.log.info("[DataManager] Loaded " + motionData.size() + " Motion Times");
		DataManager.log.info("[DataManager] Loaded " + skillTreeData.size() + " Skill Learn");
		DataManager.log.info("[DataManager] Loaded " + cubeExpandData.size() + " Cube Expander");
		DataManager.log.info("[DataManager] Loaded " + warehouseExpandData.size() + " Warehouse Expander");
		DataManager.log.info("[DataManager] Loaded " + bindPointData.size() + " Bind Point");
		DataManager.log.info("[DataManager] Loaded " + questData.size() + " Quest Data");
		DataManager.log.info("[DataManager] Loaded " + gatherableData.size() + " Gatherable");
		DataManager.log.info("[DataManager] Loaded " + titleData.size() + " Title");
		DataManager.log.info("[DataManager] Loaded " + walkerData.size() + " Walker Road");
		DataManager.log.info("[DataManager] Loaded " + zoneData.size() + " Zone");
		DataManager.log.info("[DataManager] Loaded " + tribeRelationsData.size() + " Tribe Relation");
		DataManager.log.info("[DataManager] Loaded " + recipeData.size() + " Recipe Template");
		DataManager.log.info("[DataManager] Loaded " + lunaData.size() + " Luna Template");
		DataManager.log.info("[DataManager] Loaded " + chestData.size() + " Chest");
		DataManager.log.info("[DataManager] Loaded " + staticDoorData.size() + " Static Door");
		DataManager.log.info("[DataManager] Loaded " + itemSetData.size() + " Item Set");
		DataManager.log.info("[DataManager] Loaded " + npcFactionsData.size() + " Npc Faction");
		DataManager.log.info("[DataManager] Loaded " + npcSkillData.size() + " Npc Skill List");
		DataManager.log.info("[DataManager] Loaded " + petSkillData.size() + " Pet Skill List");
		DataManager.log.info("[DataManager] Loaded " + siegeLocationData.size() + " Siege Location");
		DataManager.log.info("[DataManager] Loaded " + flyRingData.size() + " Fly Ring");
		DataManager.log.info("[DataManager] Loaded " + shieldData.size() + " Shield");
		DataManager.log.info("[DataManager] Loaded " + petData.size() + " Pet");
		DataManager.log.info("[DataManager] Loaded " + guideData.size() + " Guide");
		DataManager.log.info("[DataManager] Loaded " + roadData.size() + " Road");
		DataManager.log.info("[DataManager] Loaded " + instanceCooltimeData.size() + " Instance Cooltime");
		DataManager.log.info("[DataManager] Loaded " + decomposableItemsData.size() + " Decomposable Item");
		DataManager.log.info("[DataManager] Loaded " + decomposableSelectItemsData.size() + " Decomposable Select Item");
		DataManager.log.info("[DataManager] Loaded " + aiData.size() + " Ai2 Template");
		DataManager.log.info("[DataManager] Loaded " + flyPath.size() + " Flypath Template");
		DataManager.log.info("[DataManager] Loaded " + windstreamsData.size() + " Windstream");
		DataManager.log.info("[DataManager] Loaded " + assembledNpcData.size() + " Assembled Npc");
		DataManager.log.info("[DataManager] Loaded " + cosmeticItemsData.size() + " Cosmetics Item");
		DataManager.log.info("[DataManager] Loaded " + npcDropData.size() + " Npc Drop");
		DataManager.log.info("[DataManager] Loaded " + autoGroupData.size() + " Auto Group");
		DataManager.log.info("[DataManager] Loaded " + spawnsData2.size() + " Spawn Map");
		DataManager.log.info("[DataManager] Loaded " + eventData.size() + " Event");
		DataManager.log.info("[DataManager] Loaded " + panelSkillsData.size() + " Polymorph Panel");
		DataManager.log.info("[DataManager] Loaded " + instanceBuffData.size() + " Instance Bonus");
		DataManager.log.info("[DataManager] Loaded " + housingObjectData.size() + " Housing Object");
		DataManager.log.info("[DataManager] Loaded " + rideData.size() + " Ride");
		DataManager.log.info("[DataManager] Loaded " + robotData.size() + " Aethertech Armor");
		DataManager.log.info("[DataManager] Loaded " + instanceExitData.size() + " Instance Exit");
		DataManager.log.info("[DataManager] Loaded " + portalLocData.size() + " Portal Location");
		DataManager.log.info("[DataManager] Loaded " + portalTemplate2.size() + " Portal Template");
		DataManager.log.info("[DataManager] Loaded " + houseData.size() + " Housing land");
		DataManager.log.info("[DataManager] Loaded " + houseBuildingData.size() + " Housing Building Style");
		DataManager.log.info("[DataManager] Loaded " + housePartsData.size() + " House Parts");
		DataManager.log.info("[DataManager] Loaded " + houseNpcsData.size() + " House Spawn");
		DataManager.log.info("[DataManager] Loaded " + houseScriptData.size() + " House Scripts");
		DataManager.log.info("[DataManager] Loaded " + curingObjectsData.size() + " Curing Object");
		DataManager.log.info("[DataManager] Loaded " + springObjectsData.size() + " Spring Object");
		DataManager.log.info("[DataManager] Loaded " + assemblyItemData.size() + " Assembly Item");
		DataManager.log.info("[DataManager] Loaded " + challengeData.size() + " Challenge Task");
		DataManager.log.info("[DataManager] Loaded " + townSpawnsData.getSpawnsCount() + " Town Location");
		DataManager.log.info("[DataManager] Loaded " + abyssBuffData.size() + " Abyss Bonus");
		DataManager.log.info("[DataManager] Loaded " + abyssGroupData.size() + " Abyss Group");
		DataManager.log.info("[DataManager] Loaded " + absoluteStatsData.size() + " Absolute Stats");
		DataManager.log.info("[DataManager] Loaded " + baseData.size() + " Bases Location");
		DataManager.log.info("[DataManager] Loaded " + agentData.size() + " Agent Fight");
		DataManager.log.info("[DataManager] Loaded " + beritraData.size() + " Eresukigal/Beritra Invasion");
		DataManager.log.info("[DataManager] Loaded " + svsData.size() + " S.v.S Location");
		DataManager.log.info("[DataManager] Loaded " + rvrData.size() + " R.v.R Location");
		DataManager.log.info("[DataManager] Loaded " + moltenusData.size() + " Moltenus Location");
		DataManager.log.info("[DataManager] Loaded " + dynamicRiftData.size() + " Dynamic Rift Location");
		DataManager.log.info("[DataManager] Loaded " + instanceRiftData.size() + " Instance Rift Location");
		DataManager.log.info("[DataManager] Loaded " + nightmareCircusData.size() + " Nightmare Cirus Location");
		DataManager.log.info("[DataManager] Loaded " + zorshivDredgionData.size() + " Zorshiv Dredgion Location");
		DataManager.log.info("[DataManager] Loaded " + legionDominionData.size() + " Legion Dominion Location");
		DataManager.log.info("[DataManager] Loaded " + anohaData.size() + " Anoha Location");
		DataManager.log.info("[DataManager] Loaded " + iuData.size() + " Concert location");
		DataManager.log.info("[DataManager] Loaded " + conquestData.size() + " Conquest/Offering Location");
		DataManager.log.info("[DataManager] Loaded " + idianDepthsData.size() + " Idian Depths Location");
		DataManager.log.info("[DataManager] Loaded " + materiaData.size() + " Materials");
		DataManager.log.info("[DataManager] Loaded " + mapWeatherData.size() + " Weather Map");
		DataManager.log.info("[DataManager] Loaded " + vortexData.size() + " Vortex");
		DataManager.log.info("[DataManager] Loaded " + serialGuardData.size() + " Serial Guard");
		DataManager.log.info("[DataManager] Loaded " + serialKillerData.size() + " Serial Killer");
		DataManager.log.info("[DataManager] Loaded " + riftData.size() + " Rift/Volatile/Chaos Rift");
		DataManager.log.info("[DataManager] Loaded " + serviceBuffData.size() + " Service Bonus");
		DataManager.log.info("[DataManager] Loaded " + playersBonusData.size() + " Player Bonus");
		DataManager.log.info("[DataManager] Loaded " + itemEnchantData.size() + " Item Enchant Table");
		DataManager.log.info("[DataManager] Loaded " + itemUpgradeData.size() + " Item Upgrade");
		DataManager.log.info("[DataManager] Loaded " + atreianPassportData.size() + " Atreian Passport");
		DataManager.log.info("[DataManager] Loaded " + gameExperienceData.size() + " Game Experience Item");
		DataManager.log.info("[DataManager] Loaded " + abyssOpData.size() + " Abyss Landing Table");
		DataManager.log.info("[DataManager] Loaded " + panelCpData.size() + " Panel Cp");
		DataManager.log.info("[DataManager] Loaded " + petBuffData.size() + " Pet Buff");
		DataManager.log.info("[DataManager] Loaded " + multiReturnItemData.size() + " Multi Return Item");
		DataManager.log.info("[DataManager] Loaded " + landingLocationData.size() + " Abyss Landing Location");
		DataManager.log.info("[DataManager] Loaded " + landingSpecialLocationData.size() + " Monument Location");
		DataManager.log.info("[DataManager] Loaded " + lunaConsumeRewardsData.size() + " Luna Consume Reward");
		DataManager.log.info("[DataManager] Loaded " + itemCustomSet.size() + " Item Custom Set");
		DataManager.log.info("[DataManager] Loaded " + minionData.size() + " Minions Template");
		DataManager.log.info("[DataManager] Loaded " + f2pBonus.size() + " F2p Bonus Pack");
		DataManager.log.info("[DataManager] Loaded " + arcadeUpgradeData.size() + " Upgrade Arcade");
		DataManager.log.info("[DataManager] Loaded " + globalDropData.size() + " Global Drops");
		DataManager.log.info("[DataManager] Loaded " + itemSkillEnhance.size() + " Skill Boost Reassignment");
		DataManager.log.info("[DataManager] Loaded " + boostEvents.size() + " Boost Events");
		DataManager.log.info("[DataManager] Loaded " + atreianBestiary.size() + " Atreian Bestiary");
		DataManager.log.info("[DataManager] Loaded " + skillData.sizeOfGroup() + " Skill Templates Group");
		DataManager.log.info("[DataManager] Loaded " + chargeSkillData.size() + " Charge Skill");
		DataManager.log.info("[DataManager] Loaded " + eventsWindow.size() + " Event Window");
		DataManager.log.info("[DataManager] Loaded " + mailReward.size() + " Reward Mail");
		DataManager.log.info("[DataManager] Loaded " + lunaDice.size() + " Luna Dice Reward");
		DataManager.log.info("[DataManager] Loaded " + itemMinionList.size() + " Item Minions List");
		DataManager.log.info("[DataManager] Loaded " + reviveWorldStartPoints.size() + " Revive World Start Points");
		DataManager.log.info("[DataManager] Loaded " + reviveInstanceStartPoints.size() + " Revive Instance Start Points");
		DataManager.log.info("[DataManager] Loaded " + outpostLocation.size() + " Outpost Location");
		DataManager.log.info("[DataManager] Loaded " + stoneCp.size() + " Estima Enchant");
		DataManager.log.info("[DataManager] Loaded " + towerReward.size() + " Tower Data Reward");
		DataManager.log.info("[DataManager] Loaded " + shugoSweepsRewardData.size() + " Shugo Sweep Reward");
		DataManager.log.info("[DataManager] Loaded " + skillSkinData.size() + " Skill Animation Entries");
	}
}