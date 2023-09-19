package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.controllers.observer.ItemUseObserver;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.DescriptionId;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.decomposable.SelectItem;
import com.aionemu.gameserver.model.templates.decomposable.SelectItems;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.serverpackets.SM_ITEM_USAGE_ANIMATION;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SELECT_ITEM_ADD;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;
import com.aionemu.gameserver.services.item.ItemService;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/**
 * Rework LightNing (ENCOM)
 */

public class CM_SELECT_ITEM extends AionClientPacket
{
	private int uniqueItemId;
	private int index;
	
	@SuppressWarnings("unused")
	private int unk;
	public CM_SELECT_ITEM(int opcode, AionConnection.State state, AionConnection.State... restStates) {
		super(opcode, state, restStates);
	}
	
	@Override
	protected void readImpl() {
		this.uniqueItemId = readD();
		this.unk = readD();
		this.index = readC();
	}
	
	@Override
	protected void runImpl() {
		final Player player = getConnection().getActivePlayer();
		final Item item = player.getInventory().getItemByObjId(uniqueItemId);
		if (item == null) {
			return;
		}
		final int nameId = item.getNameId();
		sendPacket(new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), player.getObjectId(), uniqueItemId, item.getItemId(), 1000, 0, 0));
		final ItemUseObserver observer = new ItemUseObserver() {
			@Override
			public void abort() {
				player.getController().cancelTask(TaskId.ITEM_USE);
				PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400453, new DescriptionId(nameId)));
				player.getObserveController().removeObserver(this);
				sendPacket(new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), player.getObjectId(), uniqueItemId, item.getItemId(), 0, 2, 2));
			}
		};
		player.getObserveController().attach(observer);
		player.getController().addTask(TaskId.ITEM_USE, ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				player.getObserveController().removeObserver(observer);
				sendPacket(new SM_ITEM_USAGE_ANIMATION(player.getObjectId(), player.getObjectId(), uniqueItemId, item.getItemId(), 0, 1, 1));
				boolean delete = player.getInventory().decreaseByObjectId(uniqueItemId, 1L);
				if (delete) {
					SelectItems selectitem = DataManager.DECOMPOSABLE_SELECT_ITEM_DATA.getSelectItem(player.getPlayerClass(), player.getRace(), item.getItemId());
					SelectItem st = selectitem.getItems().get(index);
					ItemService.addItem(player, st.getSelectItemId(), st.getCount());
					sendPacket(new SM_SELECT_ITEM_ADD(uniqueItemId, index));
				}
			}
		}, 1000));
	}
}