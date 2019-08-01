//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 02:36:16 PM PDT 
//


package com.intuit.ipp.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UOMBaseTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UOMBaseTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Area"/>
 *     &lt;enumeration value="Count"/>
 *     &lt;enumeration value="Length"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Time"/>
 *     &lt;enumeration value="Volume"/>
 *     &lt;enumeration value="Weight"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UOMBaseTypeEnum")
@XmlEnum
public enum UOMBaseTypeEnum {

    @XmlEnumValue("Area")
    AREA("Area"),
    @XmlEnumValue("Count")
    COUNT("Count"),
    @XmlEnumValue("Length")
    LENGTH("Length"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Time")
    TIME("Time"),
    @XmlEnumValue("Volume")
    VOLUME("Volume"),
    @XmlEnumValue("Weight")
    WEIGHT("Weight");
    private final String value;

    UOMBaseTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UOMBaseTypeEnum fromValue(String v) {
        for (UOMBaseTypeEnum c: UOMBaseTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}