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

import com.aionemu.gameserver.model.templates.panel_cp.PanelCp;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ghostfur (Aion-Unique)
 */
@XmlRootElement(name = "panel_cps")
@XmlAccessorType(XmlAccessType.FIELD)
public class PanelCpData {

	@XmlElement(name = "panel_cp")
	private List<PanelCp> pclist;

	@XmlTransient
	private TIntObjectHashMap<PanelCp> cpData = new TIntObjectHashMap<PanelCp>();

	@XmlTransient
	private Map<Integer, PanelCp> cpDataMap = new HashMap<Integer, PanelCp>(1);

	void afterUnmarshal(Unmarshaller paramUnmarshaller, Object paramObject) {
		for (PanelCp panelCp : pclist) {
			cpData.put(panelCp.getId(), panelCp);
			cpDataMap.put(panelCp.getId(), panelCp);
		}
	}

	public int size() {
		return cpData.size();
	}

	public PanelCp getPanelCpId(int id) {
		return cpData.get(id);
	}

	public Map<Integer, PanelCp> getAll() {
		return cpDataMap;
	}
}
