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

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.world.knownlist.Visitor;

/****/
/** Author Rinzler (Encom)
/****/

public class MessagerAddition
{
	protected void DEEPINSIDE() {
	}
	
	public static void announce(Player player, String msg) {
		PacketSendUtility.sendBrightYellowMessageOnCenter(player, msg);
	}
	
	public static void message(Player player, String msg) {
		PacketSendUtility.sendMessage(player, msg);
	}
	
	public static void whiteMsg(Player player , String msg) {
		PacketSendUtility.sendWhiteMessage(player, msg);
	}
	
	public static void whiteMsgOnCtr(Player player, String msg) {
		PacketSendUtility.sendWhiteMessageOnCenter(player, msg);
	}
	
	public static void yellowMsg(Player player, String msg) {
		PacketSendUtility.sendYellowMessage(player, msg);
	}
	
	public static void yellowMsgOnCtr(Player player, String msg) {
		PacketSendUtility.sendYellowMessageOnCenter(player, msg);
	}
	
	public static void announceAll(final String msg, int delay) {
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player sender) {
							PacketSendUtility.sendBrightYellowMessageOnCenter(sender, msg);
							return;
						}
					});
				}
			}, delay);
		} else {
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player sender) {
					PacketSendUtility.sendBrightYellowMessageOnCenter(sender, msg);
					return;
				}
			});
		}
	}
	
	public static void messageToAll(final String msg, int delay) {
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player sender) {
							PacketSendUtility.sendMessage(sender, msg);
							return;
						}
					});
				}
			}, delay);
		} else {
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player sender) {
					PacketSendUtility.sendMessage(sender, msg);
					return;
				}
			});
		}
	}
	
	public static void whiteMsgToAll(final String msg, int delay) {
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player sender) {
							PacketSendUtility.sendWhiteMessage(sender, msg);
							return;
						}
					});
				}
			}, delay);
		} else {
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player sender) {
					PacketSendUtility.sendWhiteMessage(sender, msg);
					return;
				}
			});
		}
	}
	
	public static void whiteAnnounceToAll(final String msg, int delay) {
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player sender) {
							PacketSendUtility.sendWhiteMessageOnCenter(sender, msg);
							return;
						}
					});
				}
			}, delay);
		} else {
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player sender) {
					PacketSendUtility.sendWhiteMessageOnCenter(sender, msg);
					return;
				}
			});
		}
	}
	
	public static void yellowMsgToAll(final String msg, int delay) {
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player sender) {
							PacketSendUtility.sendYellowMessage(sender, msg);
							return;
						}
					});
				}
			}, delay);
		} else {
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player sender) {
					PacketSendUtility.sendYellowMessage(sender, msg);
					return;
				}
			});
		}
	}
	
	public static void yellowAnnounceToAll(final String msg, int delay) {
		if (delay > 0) {
			ThreadPoolManager.getInstance().schedule(new Runnable() {
				@Override
				public void run() {
					World.getInstance().doOnAllPlayers(new Visitor<Player>() {
						@Override
						public void visit(Player sender) {
							PacketSendUtility.sendYellowMessageOnCenter(sender, msg);
							return;
						}
					});
				}
			}, delay);
		} else {
			World.getInstance().doOnAllPlayers(new Visitor<Player>() {
				@Override
				public void visit(Player sender) {
					PacketSendUtility.sendYellowMessageOnCenter(sender, msg);
					return;
				}
			});
		}
	}
	
	public static void global(final String msg) {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player sender) {
				PacketSendUtility.sendBrightYellowMessageOnCenter(sender, "[Global]:"+ msg);
				return;
			}
		});
	}
	
	public static void attention(final String msg) {
		World.getInstance().doOnAllPlayers(new Visitor<Player>() {
			@Override
			public void visit(Player sender) {
				PacketSendUtility.sendBrightYellowMessageOnCenter(sender, "[Attention]:"+ msg);
				return;
			}
		});
	}
}