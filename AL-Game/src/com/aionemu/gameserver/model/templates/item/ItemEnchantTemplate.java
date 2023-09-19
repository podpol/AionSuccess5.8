package com.aionemu.gameserver.model.templates.item;

import com.aionemu.gameserver.model.stats.calc.functions.StatFunction;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ItemEnchantTemplate")
public class ItemEnchantTemplate
{
	@XmlAttribute(name = "id")
    private int id;
	
    @XmlAttribute(name = "type")
    private EnchantType type;
	
    @XmlElement(name = "item_enchant", required = false)
    private List<ItemEnchantBonus> item_enchant;
	
    @SuppressWarnings({"rawtypes", "unchecked"})
    @XmlTransient
    private TIntObjectHashMap<List<StatFunction>> enchants = new TIntObjectHashMap();
	
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<StatFunction> getStats(int level) {
        if (this.enchants.contains(level)) {
            return (List) this.enchants.get(level);
        }
        return null;
    }
	
    public List<ItemEnchantBonus> getItemEnchant() {
        return this.item_enchant;
    }
	
    public int getId() {
        return this.id;
    }
	
    public EnchantType getEnchantType() {
        return this.type;
    }
}