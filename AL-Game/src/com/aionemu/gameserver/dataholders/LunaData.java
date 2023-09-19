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

import java.util.List;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.aionemu.gameserver.model.templates.recipe.LunaTemplate;

import gnu.trove.map.hash.TIntObjectHashMap;
import javolution.util.FastList;

/**
 * Made by Ghostfur (Aion-Unique)
 */
@XmlRootElement(name = "luna_templates")
@XmlAccessorType(XmlAccessType.FIELD)
public class LunaData {

	@XmlElement(name = "luna_template")
	protected List<LunaTemplate> list;

	private TIntObjectHashMap<LunaTemplate> lunaData;

	private FastList<LunaTemplate> elyos, asmos, any;

	void afterUnmarshal(Unmarshaller u, Object parent) {
		lunaData = new TIntObjectHashMap<LunaTemplate>();
		elyos = FastList.newInstance();
		asmos = FastList.newInstance();
		any = FastList.newInstance();
		for (LunaTemplate lt : list) {
			lunaData.put(lt.getId(), lt);
			switch (lt.getRace()) {
				case ASMODIANS:
					asmos.add(lt);
					break;
				case ELYOS:
					elyos.add(lt);
					break;
				case PC_ALL:
					any.add(lt);
					break;
				default:
					break;
			}
		}
		list = null;
	}

	public FastList<LunaTemplate> getLunaTemplatesAny() {
		return any;
	}

	public LunaTemplate getLunaTemplateById(int id) {
		return lunaData.get(id);
	}

	public TIntObjectHashMap<LunaTemplate> getLunaTemplates() {
		return lunaData;
	}

	public int size() {
		return lunaData.size();
	}
}