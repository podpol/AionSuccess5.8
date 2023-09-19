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
package ai.housing;

import ai.ActionItemNpcAI2;
import com.aionemu.commons.utils.Rnd;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.services.item.ItemService;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("dyeplant")
public class DyePlantAI2 extends ActionItemNpcAI2
{
    @Override
    protected void handleUseItemFinish(Player player) {
		switch (Rnd.get(1, 21)) {
		    case 1:
				ItemService.addItem(player, 171110041, 1); //Purple Plain Wallpaper.
			break;
			case 2:
				ItemService.addItem(player, 182006982, 2); //Flower's Essence.
			break;
			case 3:
				ItemService.addItem(player, 169120000, 1); //Paint: Red.
			break;
			case 4:
				ItemService.addItem(player, 169120001, 1); //Paint: Deep Red.
			break;
			case 5:
				ItemService.addItem(player, 169120002, 1); //Paint: Pink.
			break;
			case 6:
				ItemService.addItem(player, 169120003, 1); //Paint: Blue.
			break;
			case 7:
				ItemService.addItem(player, 169120004, 1); //Paint: Purple.
			break;
			case 8:
				ItemService.addItem(player, 169120005, 1); //Paint: Orange.
			break;
			case 9:
				ItemService.addItem(player, 169120006, 1); //Paint: Mustard.
			break;
			case 10:
				ItemService.addItem(player, 169120035, 1); //Paint: Pale Pink.
			break;
			case 11:
				ItemService.addItem(player, 169120036, 1); //Paint: Wine.
			break;
			case 12:
				ItemService.addItem(player, 169120037, 1); //Paint: Scarlet Red.
			break;
			case 13:
				ItemService.addItem(player, 169120038, 1); //Paint: Mint.
			break;
			case 14:
				ItemService.addItem(player, 169120039, 1); //Paint: Scarlet Red.
			break;
			case 15:
				ItemService.addItem(player, 169120040, 1); //Paint: Green.
			break;
			case 16:
				ItemService.addItem(player, 169120041, 1); //Paint: Sky Blue.
			break;
			case 17:
				ItemService.addItem(player, 169120042, 1); //Paint: Steel Blue.
			break;
			case 18:
				ItemService.addItem(player, 169120043, 1); //Paint: Imperial Blue.
			break;
			case 19:
				ItemService.addItem(player, 169120044, 1); //Paint: Beige.
			break;
			case 20:
				ItemService.addItem(player, 169120045, 1); //Paint: Espresso.
			break;
			case 21:
				ItemService.addItem(player, 169120046, 1); //Paint: Lemon.
			break;
		}
        AI2Actions.deleteOwner(this);
		AI2Actions.scheduleRespawn(this);
    }
}