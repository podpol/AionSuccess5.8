package com.aionemu.gameserver.dataholders;

import com.aionemu.gameserver.skillengine.model.SkillTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;
import javolution.util.FastMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "skill_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class SkillData
{
	@XmlElement(name = "skill_template")
	private List<SkillTemplate> skillTemplates;
	
	private HashMap<Integer, ArrayList<Integer>> cooldownGroups;
	
	private TIntObjectHashMap<SkillTemplate> skillData = new TIntObjectHashMap<SkillTemplate>();
	
	private final Map<String, SkillTemplate> skillGroup = new FastMap<String, SkillTemplate>().shared();
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
        skillData.clear();
        for (SkillTemplate st: skillTemplates) {
            skillData.put(st.getSkillId(), st);
            skillGroup.put(st.getStack().replace("SKILL_", ""), st);
        }
    }
	
	public SkillTemplate getSkillTemplate(int skillId) {
        return skillData.get(skillId);
    }
	
	public int size() {
        return skillData.size();
    }
	
	public SkillTemplate getSkillTemplateByGroup(String name) {
        return skillGroup.get(name);
    }
	
	public int sizeOfGroup() {
        return skillGroup.size();
    }
	
	public List<SkillTemplate> getSkillTemplates() {
        return skillTemplates;
    }
	
	public void setSkillTemplates(List<SkillTemplate> skillTemplates) {
        this.skillTemplates = skillTemplates;
        afterUnmarshal(null, null);
    }
	
	public void initializeCooldownGroups() {
        cooldownGroups = new HashMap<Integer, ArrayList<Integer>>();
        for (SkillTemplate skillTemplate: skillTemplates) {
            int delayId = skillTemplate.getDelayId();
            if (!cooldownGroups.containsKey(delayId)) {
                cooldownGroups.put(delayId, new ArrayList<Integer>());
            }
            cooldownGroups.get(delayId).add(skillTemplate.getSkillId());
        }
    }
	
	public ArrayList<Integer> getSkillsForDelayId(int delayId) {
        if (cooldownGroups == null) {
            initializeCooldownGroups();
        }
        return cooldownGroups.get(delayId);
    }
	
	public TIntObjectHashMap<SkillTemplate> getSkillData() {
        return skillData;
    }
}