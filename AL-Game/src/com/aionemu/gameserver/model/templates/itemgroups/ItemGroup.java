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
package com.aionemu.gameserver.model.templates.itemgroups;

import com.aionemu.gameserver.model.templates.rewards.BonusType;
import com.aionemu.gameserver.model.templates.rewards.IdReward;

import javax.xml.bind.annotation.*;

/**
 * @author Rolandas
 *
 */

/**
 * <p>
 * Java class for ItemGroup complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="bonusType" use="required" type="{}BonusType" />
 *       &lt;attribute name="chance" type="{http://www.w3.org/2001/XMLSchema}float" default="0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemGroup")
@XmlSeeAlso({ CraftItemGroup.class, CraftRecipeGroup.class, ManastoneGroup.class, FoodGroup.class, MedicineGroup.class,
	OreGroup.class, GatherGroup.class, EnchantGroup.class, BossGroup.class })
public abstract class ItemGroup {

	@XmlAttribute(name = "bonusType", required = true)
	protected BonusType bonusType;

	@XmlAttribute(name = "chance")
	protected Float chance;

	/**
	 * Gets the value of the bonusType property.
	 * 
	 * @return possible object is {@link BonusType }
	 */
	public BonusType getBonusType() {
		return bonusType;
	}

	/**
	 * Gets the value of the chance property.
	 * 
	 * @return possible object is {@link Float }
	 */
	public float getChance() {
		if (chance == null) {
			return 0.0F;
		}
		else {
			return chance;
		}
	}
	
	public abstract IdReward[] getRewards();

}
