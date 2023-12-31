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
package ai.instance.fissureOfOblivion;

import com.aionemu.commons.network.util.ThreadPoolManager;
import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.MathUtil;

import java.util.List;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("IDTransform_TransRoom_04")
public class IDTransformTransRoom04AI2 extends NpcAI2
{
	@Override
    protected void handleCreatureSee(Creature creature) {
        checkDistance(this, creature);
    }
	
    @Override
    protected void handleCreatureMoved(Creature creature) {
        checkDistance(this, creature);
    }
	
	private void checkDistance(NpcAI2 ai, Creature creature) {
        if (creature instanceof Player && !creature.getLifeStats().isAlreadyDead()) {
        	if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 66) {
        		IDTransformTransRoom04_66();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 67) {
        		IDTransformTransRoom04_67();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 68) {
        		IDTransformTransRoom04_68();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 69) {
        		IDTransformTransRoom04_69();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 70) {
        		IDTransformTransRoom04_70();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 71) {
        		IDTransformTransRoom04_71();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 72) {
        		IDTransformTransRoom04_72();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 73) {
        		IDTransformTransRoom04_73();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 74) {
        		IDTransformTransRoom04_74();
        	} else if (MathUtil.isIn3dRange(getOwner(), creature, 15) && creature.getLevel() == 75) {
        		IDTransformTransRoom04_75();
        	}
        }
    }
	
	//Player Lvl 66
	private void IDTransformTransRoom04_66() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244490, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 67
	private void IDTransformTransRoom04_67() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244531, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 68
	private void IDTransformTransRoom04_68() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244572, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 69
	private void IDTransformTransRoom04_69() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244613, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 70
	private void IDTransformTransRoom04_70() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244654, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 71
	private void IDTransformTransRoom04_71() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244695, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 72
	private void IDTransformTransRoom04_72() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244736, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 73
	private void IDTransformTransRoom04_73() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244777, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 74
	private void IDTransformTransRoom04_74() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244818, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	//Player Lvl 75
	private void IDTransformTransRoom04_75() {
    	//Shadow D.
		despawnNpc(244854);
		despawnNpc(244855);
		despawnNpc(244856);
		despawnNpc(244857);
		despawnNpc(245885);
		despawnNpc(245886);
		despawnNpc(245887);
		despawnNpc(245888);
		spawn(245405, 301.12494f, 513.34650f, 352.99631f, (byte) 0, 33);
		AI2Actions.deleteOwner(IDTransformTransRoom04AI2.this);
		ThreadPoolManager.getInstance().schedule(new Runnable() {
			@Override
			public void run() {
				spawn(244859, 301.1525f, 512.97736f, 350.8281f, (byte) 0);
			}
		}, 2000);
    }
	
	private void despawnNpc(int npcId) {
		if (getPosition().getWorldMapInstance().getNpcs(npcId) != null) {
			List<Npc> npcs = getPosition().getWorldMapInstance().getNpcs(npcId);
			for (Npc npc: npcs) {
				npc.getController().onDelete();
			}
		}
	}
	
	@Override
	public boolean isMoveSupported() {
		return false;
	}
}