package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;

public class CM_RECIPE_DELETE extends AionClientPacket {

	int recipeId;

	public CM_RECIPE_DELETE(int opcode, State state, State... restStates) {
		super(opcode, state, restStates);
	}

	protected void readImpl() {
		recipeId = readD();
	}

	protected void runImpl() {
		Player player = getConnection().getActivePlayer();
		player.getRecipeList().deleteRecipe(player, recipeId);
	}
}
