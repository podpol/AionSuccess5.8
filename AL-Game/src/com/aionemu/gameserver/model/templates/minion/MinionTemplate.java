/**
 * This file is part of Aion-Lightning <aion-lightning.org>.
 *
 *  Aion-Lightning is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Aion-Lightning is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details. *
 *  You should have received a copy of the GNU General Public License
 *  along with Aion-Lightning.
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.model.templates.minion;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.aionemu.gameserver.model.stats.calc.functions.StatFunction;
import com.aionemu.gameserver.model.templates.BoundRadius;
import com.aionemu.gameserver.model.templates.stats.ModifiersTemplate;
/**
 * @author Falke_34
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "MinionTemplate")
public class MinionTemplate {
    @XmlElement(name="physical_attr")
    protected List<MinionAttr> physicalAttr;
    @XmlElement(name="magical_attr")
    protected List<MinionAttr> magicalAttr;
    @XmlAttribute(name = "id", required = true)
    private int id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "nameid")
    private int name_id;
    @XmlAttribute(name="group_set", required=true)
    private String groupSet;
    @XmlAttribute(name = "grade")
    private String grade;
    @XmlAttribute(name = "level")
    private int level;
    @XmlAttribute(name="skill01", required=true)
    private int skill1;
    @XmlAttribute(name="skill01_energy", required=true)
    private int skill1Energy;
    @XmlAttribute(name="skill02", required=true)
    private int skill2;
    @XmlAttribute(name="skill02_energy", required=true)
    private int skill2Energy;
    @XmlAttribute(name = "growthPoints")
    private int growthPoints;
    @XmlAttribute(name = "growthMax")
    private int growthMax;
    @XmlAttribute(name = "growthCost")
    private int growthCost;
    @XmlElement(name = "modifiers", required = false)
    private ModifiersTemplate modifiers;
    @XmlElement(name = "minionstats")
    private MinionStatsTemplate statsTemplate;
    @XmlElement(name = "bound")
    private BoundRadius bound;
    @XmlAttribute(name = "itemId")
    private int itemId;
    @XmlAttribute(name = "evolvedNum")
    private int evolvedNum;
    @XmlAttribute(name = "evolvedCost")
    private int evolvedCost;
    public List<MinionAttr> getPhysicalAttr() {
        if (this.physicalAttr == null) {
            this.physicalAttr = new ArrayList<MinionAttr>();
        }
        return this.physicalAttr;
    }
    public List<MinionAttr> getMagicalAttr() {
        if (this.magicalAttr == null) {
            this.magicalAttr = new ArrayList<MinionAttr>();
        }
        return this.magicalAttr;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGroupSet() {
        return this.groupSet;
    }
    public String getGrade() {
        return this.grade;
    }
    public int getLevel() {
        return this.level;
    }
    public int getSkill1() {
      return this.skill1;
    }
    public int getSkill1Energy() {
      return this.skill1Energy;
    }
    public int getSkill2() {
      return this.skill2;
    }
    public int getSkill2Energy() {
      return this.skill2Energy;
    }
    public int getGrowthPt() {
        return growthPoints;
    }
    public int getMaxGrowthValue() {
        return growthMax;
    }
    public int getGrowthCost() {
        return growthCost;
    }
    public List<StatFunction> getModifiers() {
        if (this.modifiers != null) {
            return this.modifiers.getModifiers();
        }
        return null;
    }
    public MinionStatsTemplate getStatsTemplate() {
        return this.statsTemplate;
    }
    public BoundRadius getBoundRadius() {
        return this.bound;
    }
    public int getItemId() {
        return this.itemId;
    }
    public int getEvolvedNum() {
        return this.evolvedNum;
    }
    public int getEvolvedCost() {
        return this.evolvedCost;
    }
}