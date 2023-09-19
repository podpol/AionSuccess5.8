/*
 * This file is part of Encom. **ENCOM FUCK OTHER SVN**
 *
 *  Encom is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Encom is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with Encom. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.instance.dredgionDefense;

import com.aionemu.gameserver.ai2.AI2Actions;
import com.aionemu.gameserver.ai2.AIName;
import com.aionemu.gameserver.ai2.NpcAI2;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import com.aionemu.gameserver.utils.PacketSendUtility;

/****/
/** Author Ghostfur & Unknown (Aion-Unique)
/****/

@AIName("Korunerk")
public class KorunerkAI2 extends NpcAI2
{
	@Override
	protected void handleDialogStart(Player player) {
		//엘리시움 방어 �?�탑�?� 움�?�?�기 위해선 특별한 �?�력�?�?� 필요하당.
        //몹시 구하기가 힘들고 어려웠지만 우리가 누구냥? �?�만 주면 �?든... 아, 아니 �?�움�?� 필요하면 누구든 �?�와주는 착한 슈고 아니냥 ?
        //전�?�?� 위험�?� 무릅쓰고 어렵게 확보한 �?�력�?�?� 수송 전차�? 실어놨당. �?�제 �?�것�?� 옮기는 건 �?�바들�?� 몫�?�당.
        //용족으로부터 수송 전차를 보호하며 엘리시움 방어 �?�탑까지 �?��?�해랑. 그럼 �?�탑�?� �?�용할 수 있�?� 거당, 갸릉.
        //아참, 침공�?� �??난 후 �?절한 대가를 지불하기로 했�?� 것 잊으면 안�?�다냥!
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 1011));
	}
	
	@Override
	public boolean onDialogSelect(Player player, int dialogId, int questId, int extendedRewardIndex) {
		//수송 전차를 �?�탑으로 �?��?�시킨다.
		if (dialogId == 10000) {
			switch (getNpcId()) {
				case 834305: //Korunerk.
					spawn(220820, 1390.711f, 1692.9382f, 573.28613f, (byte) 105); //Sanctum Tank A.
				break;
			}
		}
		AI2Actions.deleteOwner(this);
		PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(getObjectId(), 0));
		return true;
	}
}