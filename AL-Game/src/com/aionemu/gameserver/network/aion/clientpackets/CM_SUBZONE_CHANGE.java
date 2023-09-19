package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.zone.ZoneClassName;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.zone.ZoneInstance;

import java.util.List;

public class CM_SUBZONE_CHANGE extends AionClientPacket
{
    private int unk;
	
    public CM_SUBZONE_CHANGE(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        unk = readC();
    }
	
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        player.revalidateZones();
        if (player.getAccessLevel() >= 5) {
            List<ZoneInstance> zones = player.getPosition().getMapRegion().getZones(player);
            int foundZones = 0;
            for (ZoneInstance zone: zones) {
                if (zone.getZoneTemplate().getZoneType() == ZoneClassName.DUMMY || zone.getZoneTemplate().getZoneType() == ZoneClassName.WEATHER)
                    continue;
                foundZones++;
                PacketSendUtility.sendMessage(player, "Passed zone: unk=" + unk + "; " + zone.getZoneTemplate().getZoneType() + " " + zone.getAreaTemplate().getZoneName().name());
            } if (foundZones == 0) {
                PacketSendUtility.sendMessage(player, "Passed unknown zone, unk=" + unk);
                return;
            }
        }
    }
}