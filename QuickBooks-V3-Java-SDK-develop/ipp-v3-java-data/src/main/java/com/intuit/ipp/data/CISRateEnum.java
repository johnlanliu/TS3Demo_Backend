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
 * <p>Java class for CISRateEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CISRateEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CIS gross rate (0%)"/>
 *     &lt;enumeration value="CIS standard rate (20%)"/>
 *     &lt;enumeration value="CIS higher rate (30%)"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CISRateEnum")
@XmlEnum
public enum CISRateEnum {

    @XmlEnumValue("CIS gross rate (0%)")
    CIS_GROSS_RATE_0("CIS gross rate (0%)"),
    @XmlEnumValue("CIS standard rate (20%)")
    CIS_STANDARD_RATE_20("CIS standard rate (20%)"),
    @XmlEnumValue("CIS higher rate (30%)")
    CIS_HIGHER_RATE_30("CIS higher rate (30%)");
    private final String value;

    CISRateEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CISRateEnum fromValue(String v) {
        for (CISRateEnum c: CISRateEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
