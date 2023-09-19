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
package com.aionemu.gameserver.taskmanager.tasks;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.taskmanager.AbstractFIFOPeriodicTaskManager;

/**
 * @author lord_rex and MrPoke
 */
public final class PacketBroadcaster extends AbstractFIFOPeriodicTaskManager<Creature> {

	private static final class SingletonHolder {

		private static final PacketBroadcaster INSTANCE = new PacketBroadcaster();
	}

	public static PacketBroadcaster getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private PacketBroadcaster() {
		super(200);
	}

	public static enum BroadcastMode {
		UPDATE_STATS {

			@Override
			public void sendPacket(Creature creature) {
				creature.getGameStats().updateStatInfo();
			}
		},

		UPDATE_SPEED {

			@Override
			public void sendPacket(Creature creature) {
				creature.getGameStats().updateSpeedInfo();
			}
		},

		UPDATE_PLAYER_HP_STAT {

			@Override
			public void sendPacket(Creature creature) {
				((Player) creature).getLifeStats().sendHpPacketUpdateImpl();
			}
		},
		UPDATE_PLAYER_MP_STAT {

			@Override
			public void sendPacket(Creature creature) {
				((Player) creature).getLifeStats().sendMpPacketUpdateImpl();
			}
		},
		UPDATE_PLAYER_EFFECT_ICONS {

			@Override
			public void sendPacket(Creature creature) {
				creature.getEffectController().updatePlayerEffectIconsImpl();
			}
		},

		UPDATE_PLAYER_FLY_TIME {

			@Override
			public void sendPacket(Creature creature) {
				((Player) creature).getLifeStats().sendFpPacketUpdateImpl();
			}
		},

		BROAD_CAST_EFFECTS {

			@Override
			public void sendPacket(Creature creature) {
				creature.getEffectController().broadCastEffectsImp();
			}
		};

		private final byte MASK;

		private BroadcastMode() {
			MASK = (byte) (1 << ordinal());
		}

		public byte mask() {
			return MASK;
		}

		protected abstract void sendPacket(Creature creature);

		protected final void trySendPacket(final Creature creature, byte mask) {
			if ((mask & mask()) == mask()) {
				sendPacket(creature);
				creature.removePacketBroadcastMask(this);
			}
		}
	}

	private static final BroadcastMode[] VALUES = BroadcastMode.values();

	@Override
	protected void callTask(Creature creature) {
		for (byte mask; (mask = creature.getPacketBroadcastMask()) != 0;) {
			for (BroadcastMode mode : VALUES) {
				mode.trySendPacket(creature, mask);
			}
		}
	}

	@Override
	protected String getCalledMethodName() {
		return "packetBroadcast()";
	}
}
