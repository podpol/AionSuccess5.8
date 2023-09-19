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

import com.aionemu.gameserver.model.templates.gather.GatherableTemplate;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.List;

/**
 * @author ATracer
 */
@XmlRootElement(name = "gatherable_templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class GatherableData {

	@XmlElement(name = "gatherable_template")
	private List<GatherableTemplate> gatherables;

	/** A map containing all npc templates */
	private TIntObjectHashMap<GatherableTemplate> gatherableData = new TIntObjectHashMap<GatherableTemplate>();

	void afterUnmarshal(Unmarshaller u, Object parent) {
		for (GatherableTemplate gatherable : gatherables) {
			if (gatherable.getMaterials() != null)
				Collections.sort(gatherable.getMaterials().getMaterial());
			if (gatherable.getExtraMaterials() != null)
				Collections.sort(gatherable.getExtraMaterials().getMaterial());
			gatherableData.put(gatherable.getTemplateId(), gatherable);
		}
		gatherables = null;
	}

	public int size() {
		return gatherableData.size();
	}

	/**
	 * /** Returns an {@link GatherableTemplate} object with given id.
	 * 
	 * @param id
	 *          id of GatherableTemplate
	 * @return GatherableTemplate object containing data about Gatherable with that id.
	 */
	public GatherableTemplate getGatherableTemplate(int id) {
		return gatherableData.get(id);
	}
}
