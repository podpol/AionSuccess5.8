/*
 * This file is part of aion-lightning <aion-lightning.org>.
 *
 *  aion-lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-lightning.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.skillengine.effect;

import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.NpcObjectType;
import com.aionemu.gameserver.model.gameobjects.Servant;
import com.aionemu.gameserver.skillengine.model.Effect;
import com.aionemu.gameserver.utils.ThreadPoolManager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.concurrent.Future;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SummonSkillAreaEffect")
public class SummonSkillAreaEffect extends SummonServantEffect
{
	@Override
	public void applyEffect(Effect effect) {
		if (effect.getEffector().getTarget() == null) {
			effect.getEffector().setTarget(effect.getEffector());
		}
		float x = effect.getX();
		float y = effect.getY();
		float z = effect.getZ();
		if (x == 0 && y == 0) {
			Creature effected = effect.getEffected();
			x = effected.getX();
			y = effected.getY();
			z = effected.getZ();
		}
		int useTime = time;
		switch (effect.getSkillId()) {
			//Ice Sheet 4.8
			case 1308:
			case 1309:
			case 1310:
			case 1311:
			case 1312:
			case 1313:
			case 1314:
			case 1315:
			case 1316:
			case 1317:
			case 1318:
			case 1319:
			case 1320:
			case 1321:
			case 1322:
			case 1323:
			    useTime = 15;
			break;
			//Mounting Explosion 4.8
			case 1431:
			case 1432:
			   useTime = 30;
			break;
			//Manifest Tornado 4.8
			case 1460:
			case 1461:
			case 1462:
			case 1463:
			case 1464:
			case 1465:
			case 1466:
			case 1467:
			case 1468:
			case 1469:
			case 1470:
			case 1471:
			case 1472:
			case 1473:
			case 1474:
			case 1475:
				useTime = 3;
			break;
			//Battle Call 4.8
			case 3036:
			case 3037:
			    useTime = 11;
			break;
			//Field Of Lightning 5.1
			case 4770:
			case 4771:
			case 4826:
			    useTime = 9;
			break;
		}
		final Servant servant = spawnServant(effect, useTime, NpcObjectType.SKILLAREA, x, y, z);
		final int finalSkillId = servant.getSkillList() != null ? servant.getSkillList().getRandomSkill().getSkillId() : 0;
		Future<?> task = ThreadPoolManager.getInstance().scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				servant.getController().useSkill(finalSkillId);
			}
		}, 0, 3000);
		servant.getController().addTask(TaskId.SKILL_USE, task);
	}
}