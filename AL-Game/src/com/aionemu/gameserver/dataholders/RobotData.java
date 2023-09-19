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

import com.aionemu.gameserver.model.templates.robot.RobotInfo;
import gnu.trove.map.hash.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.util.List;

/****/
/** Author Rinzler (Encom)
/****/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"robots"})
@XmlRootElement(name = "robots")
public class RobotData
{
	@XmlElement(name = "robot_info")
	private List<RobotInfo> robots;
	
	@XmlTransient
	private TIntObjectHashMap<RobotInfo> robotInfos;
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		robotInfos = new TIntObjectHashMap<RobotInfo>();
		for (RobotInfo info : robots) {
			robotInfos.put(info.getRobotId(), info);
		}
		robots.clear();
		robots = null;
	}
	
	public RobotInfo getRobotInfo(int npcId) {
		return (RobotInfo) robotInfos.get(npcId);
	}
	
	public int size() {
		return robotInfos.size();
	}
}