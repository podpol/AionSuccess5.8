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
package com.aionemu.gameserver.utils;

import com.aionemu.commons.utils.AEInfos;
import com.aionemu.commons.versionning.Version;
import com.aionemu.gameserver.GameServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lord_rex
 */
public class AEVersions {

	private static final Logger log = LoggerFactory.getLogger(AEVersions.class);
	private static final Version commons = new Version(AEInfos.class);
	private static final Version gameserver = new Version(GameServer.class);

	private static String getRevisionInfo(Version version) {
		return String.format("%-6s", version.getRevision());
	}

	private static String getBranchInfo(Version version) {
		return String.format("%-6s", version.getBranch());
	}

	private static String getBranchCommitTimeInfo(Version version) {
		return String.format("%-6s", version.getCommitTime());
	}

	private static String getDateInfo(Version version) {
		return String.format("[ %4s ]", version.getDate());
	}

	public static String[] getFullVersionInfo() {
		return new String[] { "Commons Revision: " + getRevisionInfo(commons),
			"Commons Build Date: " + getDateInfo(commons), "GS Revision: " + getRevisionInfo(gameserver),
			"GS Branch: " + getBranchInfo(gameserver), "GS Branch Commit Date: " + getBranchCommitTimeInfo(gameserver),
			"GS Build Date: " + getDateInfo(gameserver), "..................................................",
			".................................................." };
	}

	public static void printFullVersionInfo() {
		for (String line : getFullVersionInfo())
			log.info(line);
	}
}
