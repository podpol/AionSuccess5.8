package com.aionemu.gameserver.model.templates.minion;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="MinionAttribute", propOrder={"physicalAttr"})
public class MinionAttribute {
    @XmlElement(name="physical_attr")
    protected List<MinionAttr> physicalAttr;
    @XmlElement(name="magical_attr")
    protected List<MinionAttr> magicalAttr;
  
    public List<MinionAttr> getPyhsicalAttr() {
        if (physicalAttr == null) {
            physicalAttr = new ArrayList<MinionAttr>();
        }
        
        return physicalAttr;
    }
  
    public List<MinionAttr> getMagicalAttr() {
        if (magicalAttr == null) {
            magicalAttr = new ArrayList<MinionAttr>();
        }
        return magicalAttr;
    }
}