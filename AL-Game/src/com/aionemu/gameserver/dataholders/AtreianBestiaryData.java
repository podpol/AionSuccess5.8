package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.atreian_bestiary.AtreianBestiaryTemplate;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Ranastic
 *
 */
@XmlRootElement(name = "monster_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtreianBestiaryData {

	@XmlElement(name = "monster_book", type = AtreianBestiaryTemplate.class)
	private List<AtreianBestiaryTemplate> templates;
	
	private final Map<Integer, AtreianBestiaryTemplate> idsHolder = new FastMap<Integer, AtreianBestiaryTemplate>().shared();
	private final Map<Integer, AtreianBestiaryTemplate> npcIdsHolder = new FastMap<Integer, AtreianBestiaryTemplate>().shared();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (AtreianBestiaryTemplate template : templates) {
			idsHolder.put(template.getId(), template);
			if (!template.getNpcIds().isEmpty()) {
				for (int npcId : template.getNpcIds()) {
					npcIdsHolder.put(npcId, template);
    			}
			}
		}
		templates.clear();
		templates = null;
	}

	public int size() {
		return idsHolder.size();
	}

	public AtreianBestiaryTemplate getAtreianBestiaryTemplate(int id) {
		return idsHolder.get(id);
	}
	
	public int sizeByNpcId() {
		return npcIdsHolder.size();
	}

	public AtreianBestiaryTemplate getAtreianBestiaryTemplateByNpcId(int id) {
		return npcIdsHolder.get(id);
	}
}
