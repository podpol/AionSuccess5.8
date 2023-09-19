package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.gameobjects.Gatherable;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.templates.spawns.*;
import com.aionemu.gameserver.model.templates.spawns.agentspawns.AgentSpawn;
import com.aionemu.gameserver.model.templates.spawns.anohaspawns.AnohaSpawn;
import com.aionemu.gameserver.model.templates.spawns.basespawns.BaseSpawn;
import com.aionemu.gameserver.model.templates.spawns.beritraspawns.BeritraSpawn;
import com.aionemu.gameserver.model.templates.spawns.conquestspawns.ConquestSpawn;
import com.aionemu.gameserver.model.templates.spawns.dynamicriftspawns.DynamicRiftSpawn;
import com.aionemu.gameserver.model.templates.spawns.idiandepthsspawns.IdianDepthsSpawn;
import com.aionemu.gameserver.model.templates.spawns.instanceriftspawns.InstanceRiftSpawn;
import com.aionemu.gameserver.model.templates.spawns.iuspawns.IuSpawn;
import com.aionemu.gameserver.model.templates.spawns.landingspawns.LandingSpawn;
import com.aionemu.gameserver.model.templates.spawns.landingspecialspawns.LandingSpecialSpawn;
import com.aionemu.gameserver.model.templates.spawns.legiondominionspawns.LegionDominionSpawn;
import com.aionemu.gameserver.model.templates.spawns.moltenusspawns.MoltenusSpawn;
import com.aionemu.gameserver.model.templates.spawns.nightmarecircusspawns.NightmareCircusSpawn;
import com.aionemu.gameserver.model.templates.spawns.outpostspawns.OutpostSpawn;
import com.aionemu.gameserver.model.templates.spawns.riftspawns.RiftSpawn;
import com.aionemu.gameserver.model.templates.spawns.rvrspawns.RvrSpawn;
import com.aionemu.gameserver.model.templates.spawns.siegespawns.SiegeSpawn;
import com.aionemu.gameserver.model.templates.spawns.svsspawns.SvsSpawn;
import com.aionemu.gameserver.model.templates.spawns.towerofeternityspawns.TowerOfEternitySpawn;
import com.aionemu.gameserver.model.templates.spawns.vortexspawns.VortexSpawn;
import com.aionemu.gameserver.model.templates.spawns.zorshivdredgionspawns.ZorshivDredgionSpawn;
import com.aionemu.gameserver.model.templates.world.WorldMapTemplate;
import com.aionemu.gameserver.spawnengine.SpawnHandlerType;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.WorldMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import javolution.util.FastMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ch.lambdaj.Lambda.*;

@XmlRootElement(name = "spawns")
@XmlType(namespace = "", name = "SpawnsData2")
@XmlAccessorType(XmlAccessType.NONE)
public class SpawnsData2
{
	private static final Logger log = LoggerFactory.getLogger(SpawnsData2.class);
	
	@XmlElement(name = "spawn_map", type = SpawnMap.class)
	protected List<SpawnMap> templates;
	
	private TIntObjectHashMap<FastMap<Integer, SimpleEntry<SpawnGroup2, Spawn>>> allSpawnMaps = new TIntObjectHashMap<FastMap<Integer, SimpleEntry<SpawnGroup2, Spawn>>>();
	private TIntObjectHashMap<List<SpawnGroup2>> siegeSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> baseSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> vortexSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> riftSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> beritraSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> agentSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> anohaSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> rvrSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> svsSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> iuSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> dynamicRiftSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> instanceRiftSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> idianDepthsSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> nightmareCircusSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> legionDominionSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> zorshivDredgionSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> moltenusSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> conquestSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> landingSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> landingSpecialSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> towerOfEternitySpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<List<SpawnGroup2>> outpostSpawnMaps = new TIntObjectHashMap<List<SpawnGroup2>>();
	private TIntObjectHashMap<Spawn> customs = new TIntObjectHashMap<Spawn>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void afterUnmarshal(Unmarshaller u, Object parent) {
		if (templates != null) {
			for (SpawnMap spawnMap : templates) {
				int mapId = spawnMap.getMapId();
				if (!allSpawnMaps.containsKey(mapId)) {
					allSpawnMaps.put(mapId, new FastMap<Integer, SimpleEntry<SpawnGroup2, Spawn>>());
				}
				for (Spawn spawn : spawnMap.getSpawns()) {
					if (spawn.isCustom()) {
						if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
							allSpawnMaps.get(mapId).remove(spawn.getNpcId());
						}
						customs.put(spawn.getNpcId(), spawn);
					} else if (customs.containsKey(spawn.getNpcId())) {
						continue;
					}
					allSpawnMaps.get(mapId).put(spawn.getNpcId(), new SimpleEntry(new SpawnGroup2(mapId, spawn), spawn));
				} if (!allSpawnMaps.containsKey(mapId)) {
					allSpawnMaps.put(mapId, new FastMap<Integer, SimpleEntry<SpawnGroup2, Spawn>>());
				}
				for (SiegeSpawn SiegeSpawn : spawnMap.getSiegeSpawns()) {
					int siegeId = SiegeSpawn.getSiegeId();
					if (!siegeSpawnMaps.containsKey(siegeId)) {
						siegeSpawnMaps.put(siegeId, new ArrayList<SpawnGroup2>());
					}
					for (SiegeSpawn.SiegeRaceTemplate race : SiegeSpawn.getSiegeRaceTemplates()) {
						for (SiegeSpawn.SiegeRaceTemplate.SiegeModTemplate mod : race.getSiegeModTemplates()) {
							if (mod == null || mod.getSpawns() == null) {
								continue;
							}
							for (Spawn spawn : mod.getSpawns()) {
								if (spawn.isCustom()) {
									if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
										allSpawnMaps.get(mapId).remove(spawn.getNpcId());
									}
									customs.put(spawn.getNpcId(), spawn);
								} else if (customs.containsKey(spawn.getNpcId())) {
									continue;
								}
								SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, siegeId, race.getSiegeRace(), mod.getSiegeModType());
								allSpawnMaps.get(mapId).put(spawn.getNpcId(), new SimpleEntry(spawnGroup, spawn));
								siegeSpawnMaps.get(siegeId).add(spawnGroup);
							}
						}
					}
				}
				for (LegionDominionSpawn LegionDominionSpawn : spawnMap.getLegionDominionSpawns()) {
					int legionDominionId = LegionDominionSpawn.getLegionDominionId();
					if (!legionDominionSpawnMaps.containsKey(legionDominionId)) {
						legionDominionSpawnMaps.put(legionDominionId, new ArrayList<SpawnGroup2>());
					}
					for (LegionDominionSpawn.LegionDominionRaceTemplate race : LegionDominionSpawn.getLegionDominionRaceTemplates()) {
						for (LegionDominionSpawn.LegionDominionRaceTemplate.LegionDominionModTemplate mod : race.getLegionDominionModTemplates()) {
							if (mod == null || mod.getSpawns() == null) {
								continue;
							}
							for (Spawn spawn : mod.getSpawns()) {
								if (spawn.isCustom()) {
									if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
										allSpawnMaps.get(mapId).remove(spawn.getNpcId());
									}
									customs.put(spawn.getNpcId(), spawn);
								} else if (customs.containsKey(spawn.getNpcId())) {
									continue;
								}
								SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, legionDominionId, race.getLegionDominionRace(), mod.getLegionDominionModType());
								allSpawnMaps.get(mapId).put(spawn.getNpcId(), new SimpleEntry(spawnGroup, spawn));
								legionDominionSpawnMaps.get(legionDominionId).add(spawnGroup);
							}
						}
					}
				}
				for (BaseSpawn BaseSpawn : spawnMap.getBaseSpawns()) {
					int baseId = BaseSpawn.getId();
					if (!baseSpawnMaps.containsKey(baseId)) {
						baseSpawnMaps.put(baseId, new ArrayList<SpawnGroup2>());
					}
					for (BaseSpawn.SimpleRaceTemplate simpleRace : BaseSpawn.getBaseRaceTemplates()) {
						for (Spawn spawn : simpleRace.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, baseId, simpleRace.getBaseRace());
							allSpawnMaps.get(mapId).put(spawn.getNpcId(), new SimpleEntry(spawnGroup, spawn));
							baseSpawnMaps.get(baseId).add(spawnGroup);
						}
					}
				}
				for (OutpostSpawn OutpostSpawn : spawnMap.getOutpostSpawns()) {
					int outpostId = OutpostSpawn.getId();
					if (!outpostSpawnMaps.containsKey(outpostId)) {
						outpostSpawnMaps.put(outpostId, new ArrayList<SpawnGroup2>());
					}
					for (OutpostSpawn.SimpleRaceTemplate simpleRace : OutpostSpawn.getOutpostRaceTemplates()) {
						for (Spawn spawn : simpleRace.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, outpostId, simpleRace.getBaseRace(), 0);
							allSpawnMaps.get(mapId).put(spawn.getNpcId(), new SimpleEntry(spawnGroup, spawn));
							outpostSpawnMaps.get(outpostId).add(spawnGroup);
						}
					}
				}
				for (RiftSpawn rift : spawnMap.getRiftSpawns()) {
					int id = rift.getId();
					if (!riftSpawnMaps.containsKey(id)) {
						riftSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (Spawn spawn : rift.getSpawns()) {
						if (spawn.isCustom()) {
							if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
								allSpawnMaps.get(mapId).remove(spawn.getNpcId());
							}
							customs.put(spawn.getNpcId(), spawn);
						} else if (customs.containsKey(spawn.getNpcId())) {
							continue;
						}
						SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id);
						allSpawnMaps.get(mapId).put(spawn.getNpcId(), new SimpleEntry(spawnGroup, spawn));
						riftSpawnMaps.get(id).add(spawnGroup);
					}
				}
				for (VortexSpawn VortexSpawn : spawnMap.getVortexSpawns()) {
					int id = VortexSpawn.getId();
					if (!vortexSpawnMaps.containsKey(id)) {
						vortexSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (VortexSpawn.VortexStateTemplate type : VortexSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getStateType());
							vortexSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (BeritraSpawn BeritraSpawn : spawnMap.getBeritraSpawns()) {
					int id = BeritraSpawn.getId();
					if (!beritraSpawnMaps.containsKey(id)) {
						beritraSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (BeritraSpawn.BeritraStateTemplate type : BeritraSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getBeritraType());
							beritraSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (AgentSpawn AgentSpawn : spawnMap.getAgentSpawns()) {
					int id = AgentSpawn.getId();
					if (!agentSpawnMaps.containsKey(id)) {
						agentSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (AgentSpawn.AgentStateTemplate type : AgentSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getAgentType());
							agentSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (AnohaSpawn AnohaSpawn : spawnMap.getAnohaSpawns()) {
					int id = AnohaSpawn.getId();
					if (!anohaSpawnMaps.containsKey(id)) {
						anohaSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (AnohaSpawn.AnohaStateTemplate type : AnohaSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getAnohaType());
							anohaSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (ConquestSpawn ConquestSpawn : spawnMap.getConquestSpawns()) {
					int id = ConquestSpawn.getId();
					if (!conquestSpawnMaps.containsKey(id)) {
						conquestSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (ConquestSpawn.ConquestStateTemplate type : ConquestSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getConquestType());
							conquestSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (SvsSpawn SvsSpawn : spawnMap.getSvsSpawns()) {
					int id = SvsSpawn.getId();
					if (!svsSpawnMaps.containsKey(id)) {
						svsSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (SvsSpawn.SvsStateTemplate type : SvsSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getSvsType());
							svsSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (RvrSpawn RvrSpawn : spawnMap.getRvrSpawns()) {
					int id = RvrSpawn.getId();
					if (!rvrSpawnMaps.containsKey(id)) {
						rvrSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (RvrSpawn.RvrStateTemplate type : RvrSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getRvrType());
							rvrSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (IuSpawn IuSpawn : spawnMap.getIuSpawns()) {
					int id = IuSpawn.getId();
					if (!iuSpawnMaps.containsKey(id)) {
						iuSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (IuSpawn.IuStateTemplate type : IuSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getIuType());
							iuSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (DynamicRiftSpawn DynamicRiftSpawn : spawnMap.getDynamicRiftSpawns()) {
					int id = DynamicRiftSpawn.getId();
					if (!dynamicRiftSpawnMaps.containsKey(id)) {
						dynamicRiftSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (DynamicRiftSpawn.DynamicRiftStateTemplate type : DynamicRiftSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getDynamicRiftType());
							dynamicRiftSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (InstanceRiftSpawn InstanceRiftSpawn : spawnMap.getInstanceRiftSpawns()) {
					int id = InstanceRiftSpawn.getId();
					if (!instanceRiftSpawnMaps.containsKey(id)) {
						instanceRiftSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (InstanceRiftSpawn.InstanceRiftStateTemplate type : InstanceRiftSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getInstanceRiftType());
							instanceRiftSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (NightmareCircusSpawn NightmareCircusSpawn : spawnMap.getNightmareCircusSpawns()) {
					int id = NightmareCircusSpawn.getId();
					if (!nightmareCircusSpawnMaps.containsKey(id)) {
						nightmareCircusSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (NightmareCircusSpawn.NightmareCircusStateTemplate type : NightmareCircusSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getNightmareCircusType());
							nightmareCircusSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (IdianDepthsSpawn IdianDepthsSpawn : spawnMap.getIdianDepthsSpawns()) {
					int id = IdianDepthsSpawn.getId();
					if (!idianDepthsSpawnMaps.containsKey(id)) {
						idianDepthsSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (IdianDepthsSpawn.IdianDepthsStateTemplate type : IdianDepthsSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getIdianDepthsType());
							idianDepthsSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (ZorshivDredgionSpawn ZorshivDredgionSpawn : spawnMap.getZorshivDredgionSpawns()) {
					int id = ZorshivDredgionSpawn.getId();
					if (!zorshivDredgionSpawnMaps.containsKey(id)) {
						zorshivDredgionSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (ZorshivDredgionSpawn.ZorshivDredgionStateTemplate type : ZorshivDredgionSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getZorshivDredgionType());
							zorshivDredgionSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (MoltenusSpawn MoltenusSpawn : spawnMap.getMoltenusSpawns()) {
					int id = MoltenusSpawn.getId();
					if (!moltenusSpawnMaps.containsKey(id)) {
						moltenusSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (MoltenusSpawn.MoltenusStateTemplate type : MoltenusSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getMoltenusType());
							moltenusSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
                for (LandingSpawn LandingSpawn : spawnMap.getLandingSpawns()) {
                    int id = LandingSpawn.getId();
                    if (!landingSpawnMaps.containsKey(id)) {
                        landingSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
                    }
                    for (LandingSpawn.LandingStateTemplate type : LandingSpawn.getSiegeModTemplates()) {
                        if (type == null || type.getSpawns() == null) {
                            continue;
                        }
                        for (Spawn spawn : type.getSpawns()) {
                            if (spawn.isCustom()) {
                                if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
                                    allSpawnMaps.get(mapId).remove(spawn.getNpcId());
                                }
                                customs.put(spawn.getNpcId(), spawn);
                            } else if (customs.containsKey(spawn.getNpcId())) {
                                continue;
                            }
                            SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getLandingType());
                            landingSpawnMaps.get(id).add(spawnGroup);
                        }
                    }
                }
				for (LandingSpecialSpawn LandingSpecialSpawn : spawnMap.getLandingSpecialSpawns()) {
					int id = LandingSpecialSpawn.getId();
					if (!landingSpecialSpawnMaps.containsKey(id)) {
						landingSpecialSpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (LandingSpecialSpawn.LandingSpStateTemplate type : LandingSpecialSpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getLandingSpecialType());
							landingSpecialSpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
				for (TowerOfEternitySpawn TowerOfEternitySpawn : spawnMap.getTowerOfEternitySpawns()) {
					int id = TowerOfEternitySpawn.getId();
					if (!towerOfEternitySpawnMaps.containsKey(id)) {
						towerOfEternitySpawnMaps.put(id, new ArrayList<SpawnGroup2>());
					}
					for (TowerOfEternitySpawn.TowerOfEternityStateTemplate type : TowerOfEternitySpawn.getSiegeModTemplates()) {
						if (type == null || type.getSpawns() == null) {
							continue;
						}
						for (Spawn spawn : type.getSpawns()) {
							if (spawn.isCustom()) {
								if (allSpawnMaps.get(mapId).containsKey(spawn.getNpcId())) {
									allSpawnMaps.get(mapId).remove(spawn.getNpcId());
								}
								customs.put(spawn.getNpcId(), spawn);
							} else if (customs.containsKey(spawn.getNpcId())) {
								continue;
							}
							SpawnGroup2 spawnGroup = new SpawnGroup2(mapId, spawn, id, type.getTowerOfEternityType());
							towerOfEternitySpawnMaps.get(id).add(spawnGroup);
						}
					}
				}
                customs.clear();
            }
        }
	}

	public void clearTemplates() {
		if (templates != null) {
			templates.clear();
			templates = null;
		}
	}

	public List<SpawnGroup2> getSpawnsByWorldId(int worldId) {
		if (!allSpawnMaps.containsKey(worldId))
			return Collections.emptyList();
		return flatten(extractIterator(allSpawnMaps.get(worldId).values(), on(SimpleEntry.class).getKey()));
	}

	public Spawn getSpawnsForNpc(int worldId, int npcId) {
		if (!allSpawnMaps.containsKey(worldId) || !allSpawnMaps.get(worldId).containsKey(npcId))
			return null;
		return allSpawnMaps.get(worldId).get(npcId).getValue();
	}
	
	public List<SpawnGroup2> getSiegeSpawnsByLocId(int siegeId) {
		return siegeSpawnMaps.get(siegeId);
	}
	
	public List<SpawnGroup2> getLegionDominionSpawnsByLocId(int legionDominionId) {
		return legionDominionSpawnMaps.get(legionDominionId);
	}
	
	public List<SpawnGroup2> getBaseSpawnsByLocId(int id) {
		return baseSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getOutpostSpawnsByLocId(int id) {
		return outpostSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getRiftSpawnsByLocId(int id) {
		return riftSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getVortexSpawnsByLocId(int id) {
		return vortexSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getBeritraSpawnsByLocId(int id) {
		return beritraSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getAgentSpawnsByLocId(int id) {
		return agentSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getAnohaSpawnsByLocId(int id) {
		return anohaSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getConquestSpawnsByLocId(int id) {
		return conquestSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getSvsSpawnsByLocId(int id) {
		return svsSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getRvrSpawnsByLocId(int id) {
		return rvrSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getIuSpawnsByLocId(int id) {
		return iuSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getDynamicRiftSpawnsByLocId(int id) {
		return dynamicRiftSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getInstanceRiftSpawnsByLocId(int id) {
		return instanceRiftSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getNightmareCircusSpawnsByLocId(int id) {
		return nightmareCircusSpawnMaps.get(id);
	}

	public List<SpawnGroup2> getIdianDepthsSpawnsByLocId(int id) {
		return idianDepthsSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getZorshivDredgionSpawnsByLocId(int id) {
		return zorshivDredgionSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getMoltenusSpawnsByLocId(int id) {
		return moltenusSpawnMaps.get(id);
	}
	
    public List<SpawnGroup2> getLandingSpawnsByLocId(int id) {
        return landingSpawnMaps.get(id);
    }
	
	public List<SpawnGroup2> getLandingSpecialSpawnsByLocId(int id) {
		return landingSpecialSpawnMaps.get(id);
	}
	
	public List<SpawnGroup2> getTowerOfEternitySpawnsByLocId(int id) {
		return towerOfEternitySpawnMaps.get(id);
	}
	
    public synchronized boolean saveSpawn(Player admin, VisibleObject visibleObject, boolean delete) throws IOException {
        SpawnTemplate spawn = visibleObject.getSpawn();
		Spawn oldGroup = DataManager.SPAWNS_DATA2.getSpawnsForNpc(visibleObject.getWorldId(), spawn.getNpcId());

		File xml = new File("./data/static_data/spawns/" + getRelativePath(visibleObject));
		SpawnsData2 data = null;
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		JAXBContext jc = null;
		boolean addGroup = false;

		try {
			schema = sf.newSchema(new File("./data/static_data/spawns/spawns.xsd"));
			jc = JAXBContext.newInstance(SpawnsData2.class);
		}
		catch (Exception e) {
			// ignore, if schemas are wrong then we even could not call the command;
		}

		FileInputStream fin = null;
		if (xml.exists()) {
			try {
				fin = new FileInputStream(xml);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				unmarshaller.setSchema(schema);
				data = (SpawnsData2) unmarshaller.unmarshal(fin);
			}
			catch (Exception e) {
				log.error(e.getMessage());
				PacketSendUtility.sendMessage(admin, "Could not load old XML file!");
				return false;
			}
			finally {
				if (fin != null)
					fin.close();
			}
		}

		if (oldGroup == null || oldGroup.isCustom()) {
			if (data == null)
				data = new SpawnsData2();

			oldGroup = data.getSpawnsForNpc(visibleObject.getWorldId(), spawn.getNpcId());
			if (oldGroup == null) {
				oldGroup = new Spawn(spawn.getNpcId(), spawn.getRespawnTime(), spawn.getHandlerType());
				addGroup = true;
			}
		}
		else {
			if (data == null)
				data = DataManager.SPAWNS_DATA2;
			// only remove from memory, will be added back later
			allSpawnMaps.get(visibleObject.getWorldId()).remove(spawn.getNpcId());
			addGroup = true;
		}

		SpawnSpotTemplate spot = new SpawnSpotTemplate(visibleObject.getX(), visibleObject.getY(), visibleObject.getZ(),
			visibleObject.getHeading(), visibleObject.getSpawn().getRandomWalk(), visibleObject.getSpawn().getWalkerId(),
			visibleObject.getSpawn().getWalkerIndex());
		boolean changeX = visibleObject.getX() != spawn.getX();
		boolean changeY = visibleObject.getY() != spawn.getY();
		boolean changeZ = visibleObject.getZ() != spawn.getZ();
		boolean changeH = visibleObject.getHeading() != spawn.getHeading();
		if (changeH && visibleObject instanceof Npc) {
			Npc npc = (Npc)visibleObject;
			if (!npc.isAtSpawnLocation() || !npc.isInState(CreatureState.NPC_IDLE) || changeX || changeY || changeZ) {
				// if H changed, XSD validation fails, because it may be negative; thus, reset it back
				visibleObject.setXYZH(null, null, null, spawn.getHeading());
				changeH = false;
			}
		}

		SpawnSpotTemplate oldSpot = null;
		for (SpawnSpotTemplate s : oldGroup.getSpawnSpotTemplates()) {
			if (s.getX() == spot.getX() && s.getY() == spot.getY() && s.getZ() == spot.getZ()
				&& s.getHeading() == spot.getHeading()) {
				if (delete || !StringUtils.equals(s.getWalkerId(), spot.getWalkerId())) {
					oldSpot = s;
					break;
				}
				else
					return false; // nothing to change
			}
			else if (changeX && s.getY() == spot.getY() && s.getZ() == spot.getZ() && s.getHeading() == spot.getHeading()
				|| changeY && s.getX() == spot.getX() && s.getZ() == spot.getZ() && s.getHeading() == spot.getHeading()
				|| changeZ && s.getX() == spot.getX() && s.getY() == spot.getY() && s.getHeading() == spot.getHeading()
				|| changeH && s.getX() == spot.getX() && s.getY() == spot.getY() && s.getZ() == spot.getZ()) {
				oldSpot = s;
				break;
			}
		}

		if (oldSpot != null)
			oldGroup.getSpawnSpotTemplates().remove(oldSpot);
		if (!delete)
			oldGroup.addSpawnSpot(spot);
		oldGroup.setCustom(true);

		SpawnMap map = null;
		if (data.templates == null) {
			data.templates = new ArrayList<SpawnMap>();
			map = new SpawnMap(spawn.getWorldId());
			data.templates.add(map);
		}
		else {
			map = data.templates.get(0);
		}

		if (addGroup)
			map.addSpawns(oldGroup);

		FileOutputStream fos = null;
		try {
			xml.getParentFile().mkdir();
			fos = new FileOutputStream(xml);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setSchema(schema);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(data, fos);
			DataManager.SPAWNS_DATA2.templates = data.templates;
			DataManager.SPAWNS_DATA2.afterUnmarshal(null, null);
			DataManager.SPAWNS_DATA2.clearTemplates();
			data.clearTemplates();
		}
		catch (Exception e) {
			log.error(e.getMessage());
			PacketSendUtility.sendMessage(admin, "Could not save XML file!");
			return false;
		}
		finally {
			if (fos != null)
				fos.close();
		}
		return true;
	}

	String getRelativePath(VisibleObject visibleObject) {
		String path;
		WorldMap map = World.getInstance().getWorldMap(visibleObject.getWorldId());
		if (visibleObject.getSpawn().getHandlerType() == SpawnHandlerType.RIFT)
			path = "Rifts";
		if (visibleObject.getSpawn().getHandlerType() == SpawnHandlerType.VOLATILE_RIFT)
			path = "Volatile Rifts";
		
		else if (visibleObject instanceof Gatherable)
			path = "Gather";
		else if (map.isInstanceType())
			path = "Instances";
		else
			path = "Npcs";
		return path + "/New/" + visibleObject.getWorldId() + "_" + map.getName().replace(' ', '_') + ".xml";
	}

	public int size() {
		return allSpawnMaps.size();
	}

	/**
	 * @param worldId
	 *          Optional. If provided, searches in this world first
	 * @param npcId
	 * @return template for the spot
	 */
	public SpawnSearchResult getFirstSpawnByNpcId(int worldId, int npcId) {
		Spawn spawns = DataManager.SPAWNS_DATA2.getSpawnsForNpc(worldId, npcId);

		if (spawns == null) {
			for (WorldMapTemplate template : DataManager.WORLD_MAPS_DATA) {
				if (template.getMapId() == worldId)
					continue;
				spawns = DataManager.SPAWNS_DATA2.getSpawnsForNpc(template.getMapId(), npcId);
				if (spawns != null) {
					worldId = template.getMapId();
					break;
				}
			}
			if (spawns == null)
				return null;
		}
		return new SpawnSearchResult(worldId, spawns.getSpawnSpotTemplates().get(0));
	}

	/**
	 * Used by Event Service to add additional spawns
	 * 
	 * @param spawnMap
	 *          templates to add
	 */
	public void addNewSpawnMap(SpawnMap spawnMap) {
		if (templates == null)
			templates = new ArrayList<SpawnMap>();
		templates.add(spawnMap);
	}

	public void removeEventSpawnObjects(List<VisibleObject> objects) {
		for (VisibleObject visObj : objects) {
			if (!allSpawnMaps.contains(visObj.getWorldId()))
				continue;
			SimpleEntry<SpawnGroup2, Spawn> entry = allSpawnMaps.get(visObj.getWorldId()).get(
				visObj.getObjectTemplate().getTemplateId());
			if (!entry.getValue().isEventSpawn())
				continue;
			if (entry.getValue().getEventTemplate().equals(visObj.getSpawn().getEventTemplate()))
				allSpawnMaps.get(visObj.getWorldId()).remove(entry);
		}
	}

	public List<SpawnMap> getTemplates() {
		return templates;
	}
}
