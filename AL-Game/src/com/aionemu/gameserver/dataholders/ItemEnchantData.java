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

import com.aionemu.gameserver.model.templates.item.EnchantType;
import com.aionemu.gameserver.model.templates.item.ItemEnchantTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @author Ranastic (Encom)
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="enchant_templates")
public class ItemEnchantData {

	@XmlElement(name = "enchant_template", required = true)
    protected List<ItemEnchantTemplate> enchantTemplates;


    @XmlTransient
    private TIntObjectHashMap<ItemEnchantTemplate> enchants = new TIntObjectHashMap<ItemEnchantTemplate>();

    @XmlTransient
    private TIntObjectHashMap<ItemEnchantTemplate> authorizes = new TIntObjectHashMap<ItemEnchantTemplate>();

    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (ItemEnchantTemplate it : enchantTemplates) {
            getEnchantMap(it.getEnchantType()).put(it.getId(), it);
        }
    }

    private TIntObjectHashMap<ItemEnchantTemplate> getEnchantMap(EnchantType type) {
        if (type == EnchantType.ENCHANT) {
            return enchants;
        }
        return authorizes;
    }

    public ItemEnchantTemplate getEnchantTemplate(EnchantType type, int id) {
        if (type == EnchantType.ENCHANT) {
            return enchants.get(id);
        }
        return authorizes.get(id);
    }

    public int size() {
        return enchants.size() + authorizes.size();
    }
}