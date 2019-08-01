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
 * <p>Java class for ETransactionStatusEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ETransactionStatusEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Sent"/>
 *     &lt;enumeration value="Viewed"/>
 *     &lt;enumeration value="Paid"/>
 *     &lt;enumeration value="Delivery Error"/>
 *     &lt;enumeration value="Updated"/>
 *     &lt;enumeration value="Error"/>
 *     &lt;enumeration value="Accepted"/>
 *     &lt;enumeration value="Rejected"/>
 *     &lt;enumeration value="Sent With ICN Error"/>
 *     &lt;enumeration value="Delivered"/>
 *     &lt;enumeration value="Disputed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ETransactionStatusEnum")
@XmlEnum
public enum ETransactionStatusEnum {

    @XmlEnumValue("Sent")
    SENT("Sent"),
    @XmlEnumValue("Viewed")
    VIEWED("Viewed"),
    @XmlEnumValue("Paid")
    PAID("Paid"),
    @XmlEnumValue("Delivery Error")
    DELIVERY_ERROR("Delivery Error"),
    @XmlEnumValue("Updated")
    UPDATED("Updated"),
    @XmlEnumValue("Error")
    ERROR("Error"),
    @XmlEnumValue("Accepted")
    ACCEPTED("Accepted"),
    @XmlEnumValue("Rejected")
    REJECTED("Rejected"),
    @XmlEnumValue("Sent With ICN Error")
    SENT_WITH_ICN_ERROR("Sent With ICN Error"),
    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Disputed")
    DISPUTED("Disputed");
    private final String value;

    ETransactionStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ETransactionStatusEnum fromValue(String v) {
        for (ETransactionStatusEnum c: ETransactionStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}