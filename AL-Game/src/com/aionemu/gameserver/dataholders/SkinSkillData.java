package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.skillengine.model.SkinSkillTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Ranastic
 *
 */
@XmlRootElement(name = "skin_skills")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkinSkillData {

	@XmlElement(name="skin_skill")
	private List<SkinSkillTemplate> tlist;
	
	@XmlTransient
	private TIntObjectHashMap<SkinSkillTemplate> skinSkillData = new TIntObjectHashMap<SkinSkillTemplate>();
	
	private final Map<String, SkinSkillTemplate> string = new FastMap<String, SkinSkillTemplate>().shared();
	
	void afterUnmarshal(Unmarshaller paramUnmarshaller, Object paramObject) {
		for (SkinSkillTemplate skinSkill : tlist) {
			skinSkillData.put(skinSkill.getId(), skinSkill);
			string.put(skinSkill.getGroup().toUpperCase(), skinSkill);
		}
	}

	public int size() {
		return skinSkillData.size();
	}

	public SkinSkillTemplate getSkinSkillById(int id) {
		return skinSkillData.get(id);
	}
	
	public SkinSkillTemplate getSkinSkillByGroupName(String name) {
		return string.get(name);
	}
}
