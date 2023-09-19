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
package com.aionemu.gameserver.configs.schedule;

import com.aionemu.commons.utils.xml.JAXBUtil;
import com.aionemu.gameserver.model.templates.rift.OpenRift;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.List;

/**
 * @author Rinzler (Encom)
 */

@XmlRootElement(name = "rift_schedule")
@XmlAccessorType(XmlAccessType.FIELD)
public class RiftSchedule
{
	@XmlElement(name = "rift", required = true)
    private List<Rift> riftsList;
	
    public List<Rift> getRiftsList() {
        return riftsList;
    }
	
	public void setRiftsList(List<Rift> fortressList) {
        this.riftsList = fortressList;
    }
	
	public static RiftSchedule load() {
        RiftSchedule rs;
        try {
            String xml = FileUtils.readFileToString(new File("./config/schedule/rift_schedule.xml"));
            rs = JAXBUtil.deserialize(xml, RiftSchedule.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Rifts", e);
        }
        return rs;
    }
	
	@XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "rift")
    public static class Rift {
        @XmlAttribute(required = true)
        private int id;
        @XmlElement(name = "open")
        private List<OpenRift> openRift;
		
        public int getWorldId() {
            return id;
        }
		
        public List<OpenRift> getRift() {
            return openRift;
        }
    }
}