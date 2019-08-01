//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 02:36:16 PM PDT 
//


package com.intuit.ipp.data;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.HashCode2;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * 
 * 				Product: ALL
 * 				Description: Check payment details for
 * 				both payments to vendors and payments from customers.
 * 			
 * 
 * <p>Java class for CheckPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NameOnAcct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AcctNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CheckPaymentEx" type="{http://schema.intuit.com/finance/v3}IntuitAnyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckPayment", propOrder = {
    "checkNum",
    "status",
    "nameOnAcct",
    "acctNum",
    "bankName",
    "checkPaymentEx"
})
public class CheckPayment implements Serializable, Equals2, HashCode2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "CheckNum")
    protected String checkNum;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "NameOnAcct")
    protected String nameOnAcct;
    @XmlElement(name = "AcctNum")
    protected String acctNum;
    @XmlElement(name = "BankName")
    protected String bankName;
    @XmlElement(name = "CheckPaymentEx", required = true)
    protected IntuitAnyType checkPaymentEx;

    /**
     * Gets the value of the checkNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCheckNum() {
        return checkNum;
    }

    /**
     * Sets the value of the checkNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCheckNum(String value) {
        this.checkNum = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the nameOnAcct property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOnAcct() {
        return nameOnAcct;
    }

    /**
     * Sets the value of the nameOnAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOnAcct(String value) {
        this.nameOnAcct = value;
    }

    /**
     * Gets the value of the acctNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcctNum() {
        return acctNum;
    }

    /**
     * Sets the value of the acctNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcctNum(String value) {
        this.acctNum = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the checkPaymentEx property.
     * 
     * @return
     *     possible object is
     *     {@link IntuitAnyType }
     *     
     */
    public IntuitAnyType getCheckPaymentEx() {
        return checkPaymentEx;
    }

    /**
     * Sets the value of the checkPaymentEx property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntuitAnyType }
     *     
     */
    public void setCheckPaymentEx(IntuitAnyType value) {
        this.checkPaymentEx = value;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CheckPayment that = ((CheckPayment) object);
        {
            String lhsCheckNum;
            lhsCheckNum = this.getCheckNum();
            String rhsCheckNum;
            rhsCheckNum = that.getCheckNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "checkNum", lhsCheckNum), LocatorUtils.property(thatLocator, "checkNum", rhsCheckNum), lhsCheckNum, rhsCheckNum, (this.checkNum!= null), (that.checkNum!= null))) {
                return false;
            }
        }
        {
            String lhsStatus;
            lhsStatus = this.getStatus();
            String rhsStatus;
            rhsStatus = that.getStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "status", lhsStatus), LocatorUtils.property(thatLocator, "status", rhsStatus), lhsStatus, rhsStatus, (this.status!= null), (that.status!= null))) {
                return false;
            }
        }
        {
            String lhsNameOnAcct;
            lhsNameOnAcct = this.getNameOnAcct();
            String rhsNameOnAcct;
            rhsNameOnAcct = that.getNameOnAcct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nameOnAcct", lhsNameOnAcct), LocatorUtils.property(thatLocator, "nameOnAcct", rhsNameOnAcct), lhsNameOnAcct, rhsNameOnAcct, (this.nameOnAcct!= null), (that.nameOnAcct!= null))) {
                return false;
            }
        }
        {
            String lhsAcctNum;
            lhsAcctNum = this.getAcctNum();
            String rhsAcctNum;
            rhsAcctNum = that.getAcctNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "acctNum", lhsAcctNum), LocatorUtils.property(thatLocator, "acctNum", rhsAcctNum), lhsAcctNum, rhsAcctNum, (this.acctNum!= null), (that.acctNum!= null))) {
                return false;
            }
        }
        {
            String lhsBankName;
            lhsBankName = this.getBankName();
            String rhsBankName;
            rhsBankName = that.getBankName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bankName", lhsBankName), LocatorUtils.property(thatLocator, "bankName", rhsBankName), lhsBankName, rhsBankName, (this.bankName!= null), (that.bankName!= null))) {
                return false;
            }
        }
        {
            IntuitAnyType lhsCheckPaymentEx;
            lhsCheckPaymentEx = this.getCheckPaymentEx();
            IntuitAnyType rhsCheckPaymentEx;
            rhsCheckPaymentEx = that.getCheckPaymentEx();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "checkPaymentEx", lhsCheckPaymentEx), LocatorUtils.property(thatLocator, "checkPaymentEx", rhsCheckPaymentEx), lhsCheckPaymentEx, rhsCheckPaymentEx, (this.checkPaymentEx!= null), (that.checkPaymentEx!= null))) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy2 strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy2 strategy) {
        int currentHashCode = 1;
        {
            String theCheckNum;
            theCheckNum = this.getCheckNum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "checkNum", theCheckNum), currentHashCode, theCheckNum, (this.checkNum!= null));
        }
        {
            String theStatus;
            theStatus = this.getStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "status", theStatus), currentHashCode, theStatus, (this.status!= null));
        }
        {
            String theNameOnAcct;
            theNameOnAcct = this.getNameOnAcct();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nameOnAcct", theNameOnAcct), currentHashCode, theNameOnAcct, (this.nameOnAcct!= null));
        }
        {
            String theAcctNum;
            theAcctNum = this.getAcctNum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "acctNum", theAcctNum), currentHashCode, theAcctNum, (this.acctNum!= null));
        }
        {
            String theBankName;
            theBankName = this.getBankName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "bankName", theBankName), currentHashCode, theBankName, (this.bankName!= null));
        }
        {
            IntuitAnyType theCheckPaymentEx;
            theCheckPaymentEx = this.getCheckPaymentEx();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "checkPaymentEx", theCheckPaymentEx), currentHashCode, theCheckPaymentEx, (this.checkPaymentEx!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}