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
package com.aionemu.gameserver.model.team.legion;

import com.aionemu.gameserver.configs.main.LegionConfig;
import com.aionemu.gameserver.model.bonus_service.ServiceBuff;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.world.World;
import javolution.util.FastMap;

import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Simple
 */
public class Legion {

	/** Legion Information **/
	private ServiceBuff serviceBuff;
	private int legionId = 0;
	private String legionName = "";
	private int legionLevel = 1;
	private int legionRank = 0;
	private long contributionPoints = 0;
	private List<Integer> legionMembers = new ArrayList<Integer>();
	private short deputyPermission = 0x1E0C;
	private short centurionPermission = 0x1C08;
	private short legionaryPermission = 0x1800;
	private short volunteerPermission = 0x800;
	private int disbandTime;
	private TreeMap<Timestamp, String> announcementList = new TreeMap<Timestamp, String>();
	private LegionEmblem legionEmblem = new LegionEmblem();
	private LegionWarehouse legionWarehouse;
	private SortedSet<LegionHistory> legionHistory;
	private AtomicBoolean hasBonus = new AtomicBoolean(false);
	private FastMap<Integer, LegionJoinRequest> joinRequestMap = new FastMap<Integer, LegionJoinRequest>();
	private String description = "";
	private int minJoinLevel = 0;
	private int joinType = 0;
	private LegionTerritory territory;

	/**
	 * Only called when a legion is created!
	 *
	 * @param legionId
	 * @param legionName
	 */
	public Legion(int legionId, String legionName) {
		this();
		this.legionId = legionId;
		this.legionName = legionName;
	}

	/**
	 * Only called when a legion is loaded!
	 */
	public Legion() {
		this.legionWarehouse = new LegionWarehouse(this);
		this.legionHistory = new TreeSet<LegionHistory>(new Comparator<LegionHistory>() {

			@Override
			public int compare(LegionHistory o1, LegionHistory o2) {
				return o1.getTime().getTime() < o2.getTime().getTime() ? 1 : -1;
			}

		});
	}

	/**
	 * @param legionId
	 *          the legionId to set
	 */
	public void setLegionId(int legionId) {
		this.legionId = legionId;
	}

	/**
	 * @return the legionId
	 */
	public int getLegionId() {
		return legionId;
	}

	/**
	 * @param legionName
	 *          the legionName to set
	 */
	public void setLegionName(String legionName) {
		this.legionName = legionName;
	}

	/**
	 * @return the legionName
	 */
	public String getLegionName() {
		return legionName;
	}

	/**
	 * @param legionMembers
	 *          the legionMembers to set
	 */
	public void setLegionMembers(ArrayList<Integer> legionMembers) {
		this.legionMembers = legionMembers;
	}

	/**
	 * @return the legionMembers
	 */
	public List<Integer> getLegionMembers() {
		return legionMembers;
	}

	/**
	 * @return the online legionMembers
	 */
	public ArrayList<Player> getOnlineLegionMembers() {
		ArrayList<Player> onlineLegionMembers = new ArrayList<Player>();
		for (int legionMemberObjId : legionMembers) {
			Player onlineLegionMember = World.getInstance().findPlayer(legionMemberObjId);
			if (onlineLegionMember != null)
				onlineLegionMembers.add(onlineLegionMember);
		}
		return onlineLegionMembers;
	}

	/**
	 * Add a legionMember to the legionMembers list
	 *
	 * @param legionMember
	 */
	public boolean addLegionMember(int playerObjId) {
		if (canAddMember()) {
			legionMembers.add(playerObjId);
			return true;
		}
		return false;
	}

	/**
	 * Delete a legionMember from the legionMembers list
	 *
	 * @param playerObjId
	 */
	public void deleteLegionMember(int playerObjId) {
		legionMembers.remove(new Integer(playerObjId));
	}

	/**
	 * This method will set the permissions
	 *
	 * @param legionarPermission2
	 * @param centurionPermission1
	 * @param centurionPermission2
	 * @return true or false
	 */
	public boolean setLegionPermissions(short deputyPermission,	short centurionPermission,	short legionaryPermission,	short volunteerPermission) {
		this.deputyPermission = deputyPermission;
		this.centurionPermission = centurionPermission;
		this.legionaryPermission = legionaryPermission;
		this.volunteerPermission = volunteerPermission;
		return true;
	}

	/**
	 * @return the deputyPermission
	 */
	public short getDeputyPermission() {
		return deputyPermission;
	}

	/**
	 * @return the centurionPermission
	 */
	public short getCenturionPermission() {
		return centurionPermission;
	}


	/**
	 * @return the legionarPermission
	 */
	public short getLegionaryPermission() {
		return legionaryPermission;
	}


	/**
	 * @return the volunteerPermission
	 */
	public short getVolunteerPermission() {
		return volunteerPermission;
	}

	/**
	 * @return the legionLevel
	 */
	public int getLegionLevel() {
		return legionLevel;
	}

	/**
	 * @param legionLevel
	 */
	public void setLegionLevel(int legionLevel) {
		this.legionLevel = legionLevel;
	}

	/**
	 * @param legionRank
	 *          the legionRank to set
	 */
	public void setLegionRank(int legionRank) {
		this.legionRank = legionRank;
	}

	/**
	 * @return the legionRank
	 */
	public int getLegionRank() {
		return legionRank;
	}

	/**
	 * @param contributionPoints
	 *          the contributionPoints to set
	 */
	public void addContributionPoints(long contributionPoints) {
		this.contributionPoints += contributionPoints;
	}

	/**
	 * @param newPoints
	 */
	public void setContributionPoints(long contributionPoints) {
		this.contributionPoints = contributionPoints;
	}

	/**
	 * @return the contributionPoints
	 */
	public long getContributionPoints() {
		return contributionPoints;
	}

	/**
	 * This method will check whether a legion has enough members to level up
	 *
	 * @return true or false
	 */
	public boolean hasRequiredMembers() {
		int memberSize = getLegionMembers().size();
		switch (getLegionLevel()) {
			case 1:
				return memberSize >= LegionConfig.LEGION_LEVEL2_REQUIRED_MEMBERS;
			case 2:
				return memberSize >= LegionConfig.LEGION_LEVEL3_REQUIRED_MEMBERS;
			case 3:
				return memberSize >= LegionConfig.LEGION_LEVEL4_REQUIRED_MEMBERS;
			case 4:
				return memberSize >= LegionConfig.LEGION_LEVEL5_REQUIRED_MEMBERS;
			case 5:
				return memberSize >= LegionConfig.LEGION_LEVEL6_REQUIRED_MEMBERS;
			case 6:
				return memberSize >= LegionConfig.LEGION_LEVEL7_REQUIRED_MEMBERS;
			case 7:
				return memberSize >= LegionConfig.LEGION_LEVEL8_REQUIRED_MEMBERS;
		}
		return false;
	}

	/**
	 * This method will return the kinah price required to level up
	 *
	 * @return int
	 */
	public int getKinahPrice() {
		switch (getLegionLevel()) {
			case 1:
				return LegionConfig.LEGION_LEVEL2_REQUIRED_KINAH;
			case 2:
				return LegionConfig.LEGION_LEVEL3_REQUIRED_KINAH;
			case 3:
				return LegionConfig.LEGION_LEVEL4_REQUIRED_KINAH;
			case 4:
				return LegionConfig.LEGION_LEVEL5_REQUIRED_KINAH;
			case 5:
				return LegionConfig.LEGION_LEVEL6_REQUIRED_KINAH;
			case 6:
				return LegionConfig.LEGION_LEVEL7_REQUIRED_KINAH;
			case 7:
				return LegionConfig.LEGION_LEVEL8_REQUIRED_KINAH;
		}
		return 0;
	}

	/**
	 * This method will return the contribution points required to level up
	 *
	 * @return int
	 */
	public int getContributionPrice() {
		switch (getLegionLevel()) {
			case 1:
				return LegionConfig.LEGION_LEVEL2_REQUIRED_CONTRIBUTION;
			case 2:
				return LegionConfig.LEGION_LEVEL3_REQUIRED_CONTRIBUTION;
			case 3:
				return LegionConfig.LEGION_LEVEL4_REQUIRED_CONTRIBUTION;
			case 4:
				return LegionConfig.LEGION_LEVEL5_REQUIRED_CONTRIBUTION;
			case 5:
				return LegionConfig.LEGION_LEVEL6_REQUIRED_CONTRIBUTION;
			case 6:
				return LegionConfig.LEGION_LEVEL7_REQUIRED_CONTRIBUTION;
			case 7:
				return LegionConfig.LEGION_LEVEL8_REQUIRED_CONTRIBUTION;
		}
		return 0;
	}

	/**
	 * This method will return true if a legion is able to add a member
	 *
	 * @return
	 */
	private boolean canAddMember() {
		int memberSize = getLegionMembers().size();
		switch (getLegionLevel()) {
			case 1:
				return memberSize < LegionConfig.LEGION_LEVEL1_MAX_MEMBERS;
			case 2:
				return memberSize < LegionConfig.LEGION_LEVEL2_MAX_MEMBERS;
			case 3:
				return memberSize < LegionConfig.LEGION_LEVEL3_MAX_MEMBERS;
			case 4:
				return memberSize < LegionConfig.LEGION_LEVEL4_MAX_MEMBERS;
			case 5:
				return memberSize < LegionConfig.LEGION_LEVEL5_MAX_MEMBERS;
			case 6:
				return memberSize < LegionConfig.LEGION_LEVEL6_MAX_MEMBERS;
			case 7:
				return memberSize < LegionConfig.LEGION_LEVEL7_MAX_MEMBERS;
			case 8:
				return memberSize < LegionConfig.LEGION_LEVEL8_MAX_MEMBERS;
		}
		return false;
	}

	/**
	 * @param announcementList
	 *          the announcementList to set
	 */
	public void setAnnouncementList(TreeMap<Timestamp, String> announcementList) {
		this.announcementList = announcementList;
	}

	/**
	 * This method will add a new announcement to the list
	 */
	public void addAnnouncementToList(Timestamp unixTime, String announcement) {
		this.announcementList.put(unixTime, announcement);
	}

	/**
	 * This method removes the first entry
	 */
	public void removeFirstEntry() {
		this.announcementList.remove(this.announcementList.firstEntry().getKey());
	}

	/**
	 * @return the announcementList
	 */
	public TreeMap<Timestamp, String> getAnnouncementList() {
		return this.announcementList;
	}

	/**
	 * @return the currentAnnouncement
	 */
	public Entry<Timestamp, String> getCurrentAnnouncement() {
		if (this.announcementList.size() > 0)
			return this.announcementList.lastEntry();
		return null;
	}

	/**
	 * @param disbandTime
	 *          the disbandTime to set
	 */
	public void setDisbandTime(int disbandTime) {
		this.disbandTime = disbandTime;
	}

	/**
	 * @return the disbandTime
	 */
	public int getDisbandTime() {
		return disbandTime;
	}

	/**
	 * @return true if currently disbanding
	 */
	public boolean isDisbanding() {
		return disbandTime > 0;
	}

	/**
	 * This function checks if object id is in list
	 *
	 * @param playerObjId
	 * @return true if ID is found in the list
	 */
	public boolean isMember(int playerObjId) {
		return legionMembers.contains(playerObjId);
	}

	/**
	 * @param legionEmblem
	 *          the legionEmblem to set
	 */
	public void setLegionEmblem(LegionEmblem legionEmblem) {
		this.legionEmblem = legionEmblem;
	}

	/**
	 * @return the legionEmblem
	 */
	public LegionEmblem getLegionEmblem() {
		return legionEmblem;
	}

	/**
	 * @param legionWarehouse
	 *          the legionWarehouse to set
	 */
	public void setLegionWarehouse(LegionWarehouse legionWarehouse) {
		this.legionWarehouse = legionWarehouse;
	}

	/**
	 * @return the legionWarehouse
	 */
	public LegionWarehouse getLegionWarehouse() {
		return legionWarehouse;
	}

	/**
	 * Get warehouse slots
	 *
	 * @return warehouse slots
	 */
	public int getWarehouseSlots() {
		switch (getLegionLevel()) {
			case 1:
				return LegionConfig.LWH_LEVEL1_SLOTS;
			case 2:
				return LegionConfig.LWH_LEVEL2_SLOTS;
			case 3:
				return LegionConfig.LWH_LEVEL3_SLOTS;
			case 4:
				return LegionConfig.LWH_LEVEL4_SLOTS;
			case 5:
				return LegionConfig.LWH_LEVEL5_SLOTS;
			case 6:
				return LegionConfig.LWH_LEVEL6_SLOTS;
			case 7:
				return LegionConfig.LWH_LEVEL7_SLOTS;
			case 8:
				return LegionConfig.LWH_LEVEL8_SLOTS;
		}
		return LegionConfig.LWH_LEVEL1_SLOTS;
	}

	public int getWarehouseLevel() {
		return getLegionLevel() - 1;
	}

	/**
	 * @return the legionHistory
	 */
	public Collection<LegionHistory> getLegionHistory() {
		return legionHistory;
	}

	public Collection<LegionHistory> getLegionHistoryByTabId(int tabType) {
		if (legionHistory.isEmpty()) {
			return legionHistory;
		}
		return select(legionHistory, having(on(LegionHistory.class).getTabId(), equalTo(tabType)));
	}

	/**
	 * @param history
	 */
	public void addHistory(LegionHistory history) {
		this.legionHistory.add(history);
	}

	public void addBonus() {
		ArrayList<Player> members = getOnlineLegionMembers();
		//레기온 창고란 같은 레기온원들 끼리 공동으로 사용하는 창고의 개념이다.
		//즉, 다른 레기온원이 이곳에 물건을 넣으면, 자신 외에 다른 권한을 가진 레기온원이 꺼내서 사용할 수 있다는 것.
		//아직 영혼 각인하지 않은 무기, 방어구라든지 각종 소비 아이템, 심지어 키나까지도 레기온원들 끼리 공동으로 사용할 수 있게 해주는 아주 편리한 시스템이다.
		//레기온 창고는 레기온을 설립하면 바로 이용이 가능하며, 레기온 창고의 이용 방식은 일반 창고와 동일하다.
		//레기온의 레벨이 올라갈 수록 레기온 창고의 슬롯 수를 늘릴 수 있다.
		if (members.size() >= 2 && members.size() <= 9) {
			if (hasBonus.compareAndSet(false, true)) {
				for (Player member: members) {
					serviceBuff = new ServiceBuff(1);
					serviceBuff.applyEffect(member, 1);
				}
			}
		} else if (members.size() >= 10 && members.size() <= 240) {
			if (hasBonus.compareAndSet(false, true)) {
				for (Player member: members) {
					serviceBuff = new ServiceBuff(6);
					serviceBuff.applyEffect(member, 6);
					serviceBuff.endEffect(member, 1);
				}
			}
		}
	}

	public void removeBonus() {
		ArrayList<Player> members = getOnlineLegionMembers();
		if (members.size() < 2) {
			if (hasBonus.compareAndSet(true, false)) {
				for (Player member: members) {
					serviceBuff = new ServiceBuff(1);
					serviceBuff.endEffect(member, 1);
				}
			}
		} else if (members.size() < 10) {
			if (hasBonus.compareAndSet(true, false)) {
				for (Player member: members) {
					serviceBuff = new ServiceBuff(6);
					serviceBuff.endEffect(member, 6);
					serviceBuff.applyEffect(member, 1);
				}
			}
		}
	}

	public boolean hasBonus() {
		return hasBonus.get();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Legion legion = (Legion) o;
		return legionId == legion.legionId;
	}

	@Override
	public int hashCode() {
		return legionId;
	}

	public String getLegionDescription() {
		return description;
	}

	public int getLegionJoinType() {
		return joinType;
	}

	public int getMinLevel() {
		return minJoinLevel;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMinJoinLevel(int minJoinLevel) {
		this.minJoinLevel = minJoinLevel;
	}

	public void setJoinType(int joinType) {
		this.joinType = joinType;
	}

	public FastMap<Integer, LegionJoinRequest> getJoinRequestMap() {
		return joinRequestMap;
	}

	public LegionJoinRequest getJoinRequestByPlayerId(int playerId) {
		return joinRequestMap.get(playerId);
	}

	public void deleteJoinRequest(int playerId) {
		joinRequestMap.remove(playerId);
	}

	public void addJoinRequest(LegionJoinRequest joinRequest) {
		if (!joinRequestMap.containsKey(joinRequest.getPlayerId())) {
			this.joinRequestMap.put(joinRequest.getPlayerId(), joinRequest);
		}
	}

	public void clearTerritory() {
		setTerritory(new LegionTerritory(0));
	}

	public boolean ownsTerretory() {
		return getTerritory().getId() > 0;
	}

	public LegionTerritory getTerritory() {
		return territory;
	}

	public void setTerritory(LegionTerritory territory) {
		this.territory = territory;
	}
}