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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.HouseDecoration;
import com.aionemu.gameserver.model.gameobjects.SummonedHouseNpc;
import com.aionemu.gameserver.model.house.House;
import com.aionemu.gameserver.model.team.legion.LegionEmblem;
import com.aionemu.gameserver.model.team.legion.LegionMember;
import com.aionemu.gameserver.model.templates.housing.BuildingType;
import com.aionemu.gameserver.model.templates.housing.PartType;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.services.LegionService;
import com.mysql.jdbc.StringUtils;

public class SM_HOUSE_UPDATE extends AionServerPacket
{
	private House house;
	
	public SM_HOUSE_UPDATE(House house) {
		this.house = house;
	}
	
	@Override
    protected void writeImpl(AionConnection con) {
    	writeH(1);
        writeH(0);
        writeH(1);
        writeD(0);
        writeD(house.getAddress().getId());
        int playerObjectId = house.getOwnerId();
        writeD(playerObjectId);
        writeD(house.getBuilding().getType().getId());
        writeC(1);
        writeD(house.getBuilding().getId());
        writeC(house.getHouseOwnerInfoFlags());
        writeC(house.getDoorState().getPacketValue());
        int dataSize = 52;
        if (house.getButler() != null) {
            SummonedHouseNpc butler = (SummonedHouseNpc) house.getButler();
            if (!StringUtils.isNullOrEmpty(butler.getMasterName())) {
                dataSize -= (butler.getMasterName().length() + 1) * 2;
                writeS(butler.getMasterName());
            }
        } for (int i = 0; i < dataSize; i++) {
            writeC(0);
        }
        LegionMember member = LegionService.getInstance().getLegionMember(playerObjectId);
        writeD(member == null ? 0 : member.getLegion().getLegionId());
        //Show/Hide Owner Name
        writeC(house.getNoticeState().getPacketValue());
        byte[] signNotice = house.getSignNotice();
        for (int i = 0; i < signNotice.length; i++) {
            writeC(signNotice[i]);
        } for (int i = signNotice.length; i < House.NOTICE_LENGTH; i++) {
            writeC(0);
        }
        writePartData(house, PartType.ROOF, 0, true);
        writePartData(house, PartType.OUTWALL, 0, true);
        writePartData(house, PartType.FRAME, 0, true);
        writePartData(house, PartType.DOOR, 0, true);
        writePartData(house, PartType.GARDEN, 0, true);
        writePartData(house, PartType.FENCE, 0, true);
        for (int floor = 0; floor < 6; floor++) {
            writePartData(house, PartType.INWALL_ANY, floor, floor > 0);
        } for (int floor = 0; floor < 6; floor++) {
            writePartData(house, PartType.INFLOOR_ANY, floor, floor > 0);
        }
        writePartData(house, PartType.ADDON, 0, true);
        writeD(0);
        writeD(0);
        writeC(0);
		
        //Emblem & Color
        if (member == null || member.getLegion().getLegionEmblem() == null) {
            writeC(0);
            writeC(0);
            writeD(0);
        } else {
            LegionEmblem emblem = member.getLegion().getLegionEmblem();
            writeC(emblem.getEmblemId());
            writeC(emblem.getEmblemType().getValue());
            writeC(emblem.isDefaultEmblem() ? 0x0 : 0xFF);
            writeC(emblem.getColor_r());
            writeC(emblem.getColor_g());
            writeC(emblem.getColor_b());
        }
    }
	
    private void writePartData(House house, PartType partType, int floor, boolean skipPersonal) {
        boolean isPersonal = house.getBuilding().getType() == BuildingType.PERSONAL_INS;
        HouseDecoration deco = house.getRenderPart(partType, floor);
        if (skipPersonal && isPersonal) {
            writeD(0);
        } else {
            writeD(deco != null ? deco.getTemplate().getId() : 0);
        }
    }
}