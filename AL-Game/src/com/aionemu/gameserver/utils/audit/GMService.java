package com.aionemu.gameserver.utils.audit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javolution.util.FastMap;

import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.commons.database.dao.DAOManager;
import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.configs.main.MembershipConfig;
import com.aionemu.gameserver.configs.main.WeddingsConfig;
import com.aionemu.gameserver.dao.PlayerDAO;
import com.aionemu.gameserver.model.ChatType;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_MESSAGE;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;

/**
 * @author Waii
 * @editor Ghostfur
 */
public class GMService {

	public static final GMService getInstance() {
		return SingletonHolder.instance;
	}

	private Map<Integer, Player> gms = new FastMap<Integer, Player>();
	private boolean announceAny = false;
	private List<Byte> announceList;

	private GMService() {

		announceList = new ArrayList<Byte>();
		announceAny = AdminConfig.ANNOUNCE_LEVEL_LIST.equals("*");
		if (!announceAny) {
			try {
				for (String level : AdminConfig.ANNOUNCE_LEVEL_LIST.split(",")) {
					announceList.add(Byte.parseByte(level));
				}
			} catch (Exception e) {
				announceAny = true;
			}
		}
	}
	public void onPlayerLogin(Player player) {
		if (player.isGM()) {
			gms.put(player.getObjectId(), player);
		}
	}

	/*
	public void onPlayerLogin(Player player){
		if (player.isGM()){
			gms.put(player.getObjectId(), player);
			if (announceAny)
				PacketSendUtility.broadcastPacket(player, new SM_MESSAGE(player, "Announce: " + player.getCustomTag(true) + player.getName() + " appear !!", ChatType.BRIGHT_YELLOW_CENTER), true);
				else if (announceList.contains(Byte.valueOf(player.getAccessLevel())))
				PacketSendUtility.broadcastPacket(player, new SM_MESSAGE(player, "Announce: " + player.getCustomTag(true) + player.getName() + " appear !!", ChatType.BRIGHT_YELLOW_CENTER), true);
		}
	}
	*/

	public void onPlayerLogedOut(Player player) {
		if (player.isGM()) {
			gms.remove(player.getObjectId());
		}
	}

	public Collection<Player> getGMs() {
		return gms.values();
	}

	public void onPlayerAvailable(Player player) {
		if (player.isGM()) {
			gms.put(player.getObjectId(), player);
			String adminTag = "%s";
			StringBuilder sb = new StringBuilder(adminTag);

			if (player.getClientConnection() != null) {

				// * = Premium & VIP Membership
				if (MembershipConfig.PREMIUM_TAG_DISPLAY) {
					switch (player.getClientConnection().getAccount().getMembership()) {
						case 1:
							adminTag = sb.insert(0, MembershipConfig.TAG_PREMIUM.substring(0, 2)).toString();
							break;
						case 2:
							adminTag = sb.insert(0, MembershipConfig.TAG_VIP.substring(0, 2)).toString();
							break;
					}
				}
				// * = Wedding
				if (player.isMarried()) {
					String partnerName = DAOManager.getDAO(PlayerDAO.class).getPlayerNameByObjId(player.getPartnerId());
					adminTag += "\uE020"+ partnerName;
				}
				if (AdminConfig.CUSTOMTAG_ENABLE) {
					if (player.getAccessLevel() == 1) {
						adminTag = AdminConfig.ADMIN_TAG_1.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 2) {
						adminTag = AdminConfig.ADMIN_TAG_2.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 3) {
						adminTag = AdminConfig.ADMIN_TAG_3.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 4) {
						adminTag = AdminConfig.ADMIN_TAG_4.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 5) {
						adminTag = AdminConfig.ADMIN_TAG_5.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 6) {
						adminTag = AdminConfig.ADMIN_TAG_6.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 7) {
						adminTag = AdminConfig.ADMIN_TAG_7.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 8) {
						adminTag = AdminConfig.ADMIN_TAG_8.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 9) {
						adminTag = AdminConfig.ADMIN_TAG_9.replace("%s", sb.toString());
					} else if (player.getAccessLevel() == 10) {
						adminTag = AdminConfig.ADMIN_TAG_10.replace("%s", sb.toString());
					}
				}

				Iterator<Player> iter = World.getInstance().getPlayersIterator();
				while (iter.hasNext()) {
					PacketSendUtility.sendBrightYellowMessageOnCenter(iter.next(), "Information : " + String.format(adminTag, player.getName()) + "Is Now Available For Support!!");
				}
			}
		}
	}
	public void onPlayerUnavailable(Player player) {
		gms.remove(player.getObjectId());
		String adminTag = "%s";
		StringBuilder sb = new StringBuilder(adminTag);


		// * = Premium & VIP Membership
		if (MembershipConfig.PREMIUM_TAG_DISPLAY) {
			switch (player.getClientConnection().getAccount().getMembership()) {
				case 1:
					adminTag = sb.insert(0, MembershipConfig.TAG_PREMIUM.substring(0, 2)).toString();
					break;
				case 2:
					adminTag = sb.insert(0, MembershipConfig.TAG_VIP.substring(0, 2)).toString();
					break;
			}
		}
		// * = Wedding
		if (player.isMarried()) {
			String partnerName = DAOManager.getDAO(PlayerDAO.class).getPlayerNameByObjId(player.getPartnerId());
			adminTag += "\uE020"+ partnerName;
		}

		if (AdminConfig.CUSTOMTAG_ENABLE) {
			if (player.getAccessLevel() == 1) {
				adminTag = AdminConfig.ADMIN_TAG_1.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 2) {
				adminTag = AdminConfig.ADMIN_TAG_2.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 3) {
				adminTag = AdminConfig.ADMIN_TAG_3.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 4) {
				adminTag = AdminConfig.ADMIN_TAG_4.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 5) {
				adminTag = AdminConfig.ADMIN_TAG_5.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 6) {
				adminTag = AdminConfig.ADMIN_TAG_6.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 7) {
				adminTag = AdminConfig.ADMIN_TAG_7.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 8) {
				adminTag = AdminConfig.ADMIN_TAG_8.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 9) {
				adminTag = AdminConfig.ADMIN_TAG_9.replace("%s", sb.toString());
			} else if (player.getAccessLevel() == 10) {
				adminTag = AdminConfig.ADMIN_TAG_10.replace("%s", sb.toString());
			}
		}

		Iterator<Player> iter = World.getInstance().getPlayersIterator();
		while (iter.hasNext()) {
			PacketSendUtility.sendBrightYellowMessageOnCenter(iter.next(), "Information : " + String.format(adminTag, player.getName()) + "Is Now Unavailable For Support!!");
		}
	}

	public void broadcastMesage(String message) {
		SM_MESSAGE packet = new SM_MESSAGE(0, null, message, ChatType.YELLOW);
		for (Player player : gms.values()) {
			PacketSendUtility.sendPacket(player, packet);
		}
	}

	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder {

		protected static final GMService instance = new GMService();
	}
}
