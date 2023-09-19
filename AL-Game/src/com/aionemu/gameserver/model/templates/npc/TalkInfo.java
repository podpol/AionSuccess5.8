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
package com.aionemu.gameserver.model.templates.npc;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Ghostfur (Aion-Unique)
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TalkInfo")
public class TalkInfo {

	@XmlAttribute(name = "distance")
	private int talkDistance = 2;
	@XmlAttribute(name = "delay")
	private int talkDelay;
	@XmlAttribute(name = "is_dialog")
	private boolean hasDialog;
	@XmlAttribute(name = "func_dialogs")
	private List<Integer> funcDialogIds;
	@XmlAttribute(name = "subdialog_type")
	private String subDialogType;

	/**
	 * @return the talkDistance
	 */
	public int getDistance() {
		return talkDistance;
	}

	/**
	 * @return the talk_delay
	 */
	public int getDelay() {
		return talkDelay;
	}

	/**
	 * @return the hasDialog
	 */
	public boolean isDialogNpc() {
		return hasDialog;
	}

	public List<Integer> getFuncDialogIds() {
		return funcDialogIds;
	}

	public String getSubDialogType() {
		return subDialogType;
	}
}