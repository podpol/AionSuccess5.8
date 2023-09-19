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
package com.aionemu.gameserver.model.outpost;

import com.aionemu.gameserver.model.Race;
import com.aionemu.gameserver.model.templates.outpost.OutpostTemplate;

/**
 * Created by Wnkrz on 27/08/2017.
 */

public class OutpostLocation
{
    protected OutpostTemplate template;
    protected Race race = Race.NPC;
	
    public OutpostLocation() {
    }
	
    public OutpostLocation(OutpostTemplate template) {
        this.template = template;
    }
	
    public int getId() {
        return template.getId();
    }
	
    public int getWorldId() {
        return template.getWorldId();
    }
	
    public String getName() {
        return template.getName();
    }
	
    public Race getRace() {
        return race;
    }
	
    public void setRace(Race race) {
        this.race = race;
    }
	
	public int getArtifactId() {
        return template.getArtifactId();
    }
}