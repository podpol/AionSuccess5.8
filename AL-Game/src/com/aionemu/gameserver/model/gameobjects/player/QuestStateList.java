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
package com.aionemu.gameserver.model.gameobjects.player;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.dataholders.QuestsData;
import com.aionemu.gameserver.model.templates.quest.QuestCategory;
import com.aionemu.gameserver.questEngine.model.QuestState;
import com.aionemu.gameserver.questEngine.model.QuestStatus;
import javolution.util.FastList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author MrPoke
 */
public class QuestStateList {

	private static final Logger log = LoggerFactory.getLogger(QuestStateList.class);

	private final SortedMap<Integer, QuestState> _quests;
	private QuestsData _questData = DataManager.QUEST_DATA;

	/**
	 * Creates an empty quests list
	 */
	public QuestStateList() {
		_quests = new TreeMap<Integer, QuestState>();
	}

	public synchronized boolean addQuest(int questId, QuestState questState) {
		if (_quests.containsKey(questId)) {
			log.warn("Duplicate quest. ");
			return false;
		}
		_quests.put(questId, questState);
		return true;
	}

	public synchronized boolean removeQuest(int questId) {
		if (_quests.containsKey(questId)) {
			_quests.remove(questId);
			return true;
		}
		return false;
	}

	public QuestState getQuestState(int questId) {
		return _quests.get(questId);
	}

	public Collection<QuestState> getAllQuestState() {
		return _quests.values();
	}

	public FastList<QuestState> getAllFinishedQuests() {
		FastList<QuestState> completeQuestList = FastList.newInstance();
		for (QuestState qs : _quests.values()) {
			if (qs.getStatus() == QuestStatus.COMPLETE) {
				completeQuestList.add(qs);
			}
		}
		return completeQuestList;
	}

	/*
	 * Issue #13 fix Used by the QuestService to check the amount of normal quests in the player's list
	 * @author vlog
	 */
	public int getNormalQuestListSize() {
		return this.getNormalQuests().size();
	}

	/*
	 * Issue #13 fix Returns the list of normal quests
	 * @author vlog
	 */
	public Collection<QuestState> getNormalQuests() {
		Collection<QuestState> l = new ArrayList<QuestState>();

		for (QuestState qs : this.getAllQuestState()) {
			QuestCategory qc = _questData.getQuestById(qs.getQuestId()).getCategory();
			QuestStatus s = qs.getStatus();

			if (s != QuestStatus.COMPLETE && s != QuestStatus.LOCKED && s != QuestStatus.NONE && qc == QuestCategory.QUEST) {
				l.add(qs);
			}
		}
		return l;
	}

	/*
	 * Returns true if there is a quest in the list with this id Used by the QuestService
	 * @author vlog
	 */
	public boolean hasQuest(int questId) {
		return _quests.containsKey(questId);
	}

	/*
	 * Change the old value of the quest status to the new one Used by the QuestService
	 * @author vlog
	 */
	public void changeQuestStatus(Integer key, QuestStatus newStatus) {
		_quests.get(key).setStatus(newStatus);
	}
	
	public int size() {
		return this._quests.size();
	}
	
	public SortedMap<Integer, QuestState> getQuests() {
		return this._quests;
	}
}
