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
package com.aionemu.gameserver.model.templates.outpost;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Wnkrz on 27/08/2017.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Outpost")
public class OutpostTemplate
{
    @XmlAttribute(name = "id")
    protected int id;
	
    @XmlAttribute(name = "world")
    protected int world;
	
    @XmlAttribute(name = "name")
    protected String nameId;
	
	@XmlAttribute(name = "artifact_id")
    protected int artifactId;
	
    public int getId() {
        return this.id;
    }
	
    public int getWorldId() {
        return this.world;
    }
	
    public String getName() {
        return nameId;
    }
	
	public int getArtifactId() {
		return artifactId;
    }
}