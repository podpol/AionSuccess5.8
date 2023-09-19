package com.aionemu.gameserver.model.templates.atreian_bestiary;

import javax.xml.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Ranastic
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AtreianBestiaryTemplate")
public class AtreianBestiaryTemplate {

	@XmlAttribute(name = "id")
	private int id;
	
	@XmlAttribute(name = "level")
	private int level;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "npc_ids")
	private List<Integer> npc_ids;
	
	@XmlAttribute(name = "type")
	private BookType type;
	
	@XmlElement(name = "achievement")
	private List<AtreianBestiaryAchievementTemplate> achievement;
	
	public int getId() {
		return id;
	}
	
	public int getLevel() {
		return level;
	}
	
	public BookType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}

	public List<Integer> getNpcIds() {
        if (npc_ids == null) {
        	npc_ids = Collections.emptyList();
        }
        return this.npc_ids;
    }
	
	public List<AtreianBestiaryAchievementTemplate> getAtreianBestiaryAchievementTemplate() {
		return achievement;
	}
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "AtreianBestiaryAchievementTemplate")
	public static class AtreianBestiaryAchievementTemplate {
	
		@XmlAttribute(name = "condition")
		private int condition;
		
		@XmlAttribute(name = "exp")
		private int exp;
		
		public int getKillCondition() {
			return condition;
		}
		
		public int getRewardExp() {
			return exp;
		}
	}
	
	@XmlType(name = "BookType")
	@XmlEnum
	public enum BookType
	{
		NORMAL(),
		HERO();
	}
}
