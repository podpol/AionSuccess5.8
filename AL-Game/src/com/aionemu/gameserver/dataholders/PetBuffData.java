package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.model.templates.pet.PetBonusAttr;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"petBonusattr"})
@XmlRootElement(name = "pet_bonusattrs")
public class PetBuffData {

	@XmlElement(name = "pet_bonusattr")
	protected List<PetBonusAttr> petBonusattr;

	@XmlTransient
	private TIntObjectHashMap<PetBonusAttr> templates = new TIntObjectHashMap<PetBonusAttr>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (PetBonusAttr template : petBonusattr) {
			templates.put(template.getBuffId(), template);
			templates.put(template.getFoodCount(), template);
		}
		petBonusattr.clear();
		petBonusattr = null;
	}

	public int size() {
		return templates.size();
	}

	public PetBonusAttr getPetBonusattr(int buffId) {
		return templates.get(buffId);
	}
	
	public PetBonusAttr getFoodCount(int count) {
		return templates.get(count);
	}
}
