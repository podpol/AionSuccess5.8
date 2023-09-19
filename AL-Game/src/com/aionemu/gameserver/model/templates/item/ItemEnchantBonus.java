package com.aionemu.gameserver.model.templates.item;

import com.aionemu.gameserver.model.templates.stats.ModifiersTemplate;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ItemEnchantBouns")
public class ItemEnchantBonus
{
	@XmlElement(name="modifiers", required=false)
	private ModifiersTemplate modifiers;
	@XmlAttribute(name="level")
	private int level;

	public ItemEnchantBonus() {}

	public ModifiersTemplate getModifiers()
	{
		return modifiers;
	}

	public int getLevel()
	{
		return level;
	}
}