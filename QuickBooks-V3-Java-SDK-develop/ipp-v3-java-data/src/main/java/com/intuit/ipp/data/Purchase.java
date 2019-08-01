//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 02:36:16 PM PDT 
//


package com.intuit.ipp.data;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Financial Transaction information that pertains to
 * 				the entire Check.
 * 
 * <p>Java class for Purchase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Purchase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.intuit.com/finance/v3}Transaction">
 *       &lt;sequence>
 *         &lt;element name="AccountRef" type="{http://schema.intuit.com/finance/v3}ReferenceType" minOccurs="0"/>
 *         &lt;element name="PaymentMethodRef" type="{http://schema.intuit.com/finance/v3}ReferenceType" minOccurs="0"/>
 *         &lt;element name="PaymentRefNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentType" type="{http://schema.intuit.com/finance/v3}PaymentTypeEnum" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="CheckPayment" type="{http://schema.intuit.com/finance/v3}CheckPayment" minOccurs="0"/>
 *           &lt;element name="CreditCardPayment" type="{http://schema.intuit.com/finance/v3}CreditCardPayment" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="EntityRef" type="{http://schema.intuit.com/finance/v3}ReferenceType" minOccurs="0"/>
 *         &lt;element name="Credit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RemitToAddr" type="{http://schema.intuit.com/finance/v3}PhysicalAddress" minOccurs="0"/>
 *         &lt;element name="TotalAmt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;group ref="{http://schema.intuit.com/finance/v3}TxnReferenceGroup" minOccurs="0"/>
 *         &lt;element name="Memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrintStatus" type="{http://schema.intuit.com/finance/v3}PrintStatusEnum" minOccurs="0"/>
 *         &lt;element name="GlobalTaxCalculation" type="{http://schema.intuit.com/finance/v3}GlobalTaxCalculationEnum" minOccurs="0"/>
 *         &lt;element name="PurchaseEx" type="{http://schema.intuit.com/finance/v3}IntuitAnyType" minOccurs="0"/>
 *         &lt;element name="LessCIS" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="IncludeInAnnualTPAR" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Purchase", propOrder = {
    "accountRef",
    "paymentMethodRef",
    "paymentRefNum",
    "paymentType",
    "checkPayment",
    "creditCardPayment",
    "entityRef",
    "credit",
    "remitToAddr",
    "totalAmt",
    "txnId",
    "txnNum",
    "memo",
    "printStatus",
    "globalTaxCalculation",
    "purchaseEx",
    "lessCIS",
    "includeInAnnualTPAR"
})
public class Purchase
    extends Transaction
    implements Serializable, Equals2, HashCode2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "AccountRef")
    protected ReferenceType accountRef;
    @XmlElement(name = "PaymentMethodRef")
    protected ReferenceType paymentMethodRef;
    @XmlElement(name = "PaymentRefNum")
    protected String paymentRefNum;
    @XmlElement(name = "PaymentType")
    protected PaymentTypeEnum paymentType;
    @XmlElement(name = "CheckPayment")
    protected CheckPayment checkPayment;
    @XmlElement(name = "CreditCardPayment")
    protected CreditCardPayment creditCardPayment;
    @XmlElement(name = "EntityRef")
    protected ReferenceType entityRef;
    @XmlElement(name = "Credit")
    protected Boolean credit;
    @XmlElement(name = "RemitToAddr")
    protected PhysicalAddress remitToAddr;
    @XmlElement(name = "TotalAmt")
    protected BigDecimal totalAmt;
    @XmlElement(name = "TxnId")
    protected String txnId;
    @XmlElement(name = "TxnNum")
    protected String txnNum;
    @XmlElement(name = "Memo")
    protected String memo;
    @XmlElement(name = "PrintStatus")
    protected PrintStatusEnum printStatus;
    @XmlElement(name = "GlobalTaxCalculation")
    protected GlobalTaxCalculationEnum globalTaxCalculation;
    @XmlElement(name = "PurchaseEx")
    protected IntuitAnyType purchaseEx;
    @XmlElement(name = "LessCIS")
    protected BigDecimal lessCIS;
    @XmlElement(name = "IncludeInAnnualTPAR")
    protected Boolean includeInAnnualTPAR;

    /**
     * Gets the value of the accountRef property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getAccountRef() {
        return accountRef;
    }

    /**
     * Sets the value of the accountRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setAccountRef(ReferenceType value) {
        this.accountRef = value;
    }

    /**
     * Gets the value of the paymentMethodRef property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getPaymentMethodRef() {
        return paymentMethodRef;
    }

    /**
     * Sets the value of the paymentMethodRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setPaymentMethodRef(ReferenceType value) {
        this.paymentMethodRef = value;
    }

    /**
     * Gets the value of the paymentRefNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentRefNum() {
        return paymentRefNum;
    }

    /**
     * Sets the value of the paymentRefNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentRefNum(String value) {
        this.paymentRefNum = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTypeEnum }
     *     
     */
    public PaymentTypeEnum getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTypeEnum }
     *     
     */
    public void setPaymentType(PaymentTypeEnum value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the checkPayment property.
     * 
     * @return
     *     possible object is
     *     {@link CheckPayment }
     *     
     */
    public CheckPayment getCheckPayment() {
        return checkPayment;
    }

    /**
     * Sets the value of the checkPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckPayment }
     *     
     */
    public void setCheckPayment(CheckPayment value) {
        this.checkPayment = value;
    }

    /**
     * Gets the value of the creditCardPayment property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCardPayment }
     *     
     */
    public CreditCardPayment getCreditCardPayment() {
        return creditCardPayment;
    }

    /**
     * Sets the value of the creditCardPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCardPayment }
     *     
     */
    public void setCreditCardPayment(CreditCardPayment value) {
        this.creditCardPayment = value;
    }

    /**
     * Gets the value of the entityRef property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getEntityRef() {
        return entityRef;
    }

    /**
     * Sets the value of the entityRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setEntityRef(ReferenceType value) {
        this.entityRef = value;
    }

    /**
     * Gets the value of the credit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCredit() {
        return credit;
    }

    /**
     * Sets the value of the credit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCredit(Boolean value) {
        this.credit = value;
    }

    /**
     * Gets the value of the remitToAddr property.
     * 
     * @return
     *     possible object is
     *     {@link PhysicalAddress }
     *     
     */
    public PhysicalAddress getRemitToAddr() {
        return remitToAddr;
    }

    /**
     * Sets the value of the remitToAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhysicalAddress }
     *     
     */
    public void setRemitToAddr(PhysicalAddress value) {
        this.remitToAddr = value;
    }

    /**
     * Gets the value of the totalAmt property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * Sets the value of the totalAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmt(BigDecimal value) {
        this.totalAmt = value;
    }

    /**
     * Gets the value of the txnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnId() {
        return txnId;
    }

    /**
     * Sets the value of the txnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnId(String value) {
        this.txnId = value;
    }

    /**
     * Gets the value of the txnNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTxnNum() {
        return txnNum;
    }

    /**
     * Sets the value of the txnNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTxnNum(String value) {
        this.txnNum = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the printStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PrintStatusEnum }
     *     
     */
    public PrintStatusEnum getPrintStatus() {
        return printStatus;
    }

    /**
     * Sets the value of the printStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrintStatusEnum }
     *     
     */
    public void setPrintStatus(PrintStatusEnum value) {
        this.printStatus = value;
    }

    /**
     * Gets the value of the globalTaxCalculation property.
     * 
     * @return
     *     possible object is
     *     {@link GlobalTaxCalculationEnum }
     *     
     */
    public GlobalTaxCalculationEnum getGlobalTaxCalculation() {
        return globalTaxCalculation;
    }

    /**
     * Sets the value of the globalTaxCalculation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GlobalTaxCalculationEnum }
     *     
     */
    public void setGlobalTaxCalculation(GlobalTaxCalculationEnum value) {
        this.globalTaxCalculation = value;
    }

    /**
     * Gets the value of the purchaseEx property.
     * 
     * @return
     *     possible object is
     *     {@link IntuitAnyType }
     *     
     */
    public IntuitAnyType getPurchaseEx() {
        return purchaseEx;
    }

    /**
     * Sets the value of the purchaseEx property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntuitAnyType }
     *     
     */
    public void setPurchaseEx(IntuitAnyType value) {
        this.purchaseEx = value;
    }

    /**
     * Gets the value of the lessCIS property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLessCIS() {
        return lessCIS;
    }

    /**
     * Sets the value of the lessCIS property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLessCIS(BigDecimal value) {
        this.lessCIS = value;
    }

    /**
     * Gets the value of the includeInAnnualTPAR property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeInAnnualTPAR() {
        return includeInAnnualTPAR;
    }

    /**
     * Sets the value of the includeInAnnualTPAR property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeInAnnualTPAR(Boolean value) {
        this.includeInAnnualTPAR = value;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Purchase that = ((Purchase) object);
        {
            ReferenceType lhsAccountRef;
            lhsAccountRef = this.getAccountRef();
            ReferenceType rhsAccountRef;
            rhsAccountRef = that.getAccountRef();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountRef", lhsAccountRef), LocatorUtils.property(thatLocator, "accountRef", rhsAccountRef), lhsAccountRef, rhsAccountRef, (this.accountRef!= null), (that.accountRef!= null))) {
                return false;
            }
        }
        {
            ReferenceType lhsPaymentMethodRef;
            lhsPaymentMethodRef = this.getPaymentMethodRef();
            ReferenceType rhsPaymentMethodRef;
            rhsPaymentMethodRef = that.getPaymentMethodRef();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentMethodRef", lhsPaymentMethodRef), LocatorUtils.property(thatLocator, "paymentMethodRef", rhsPaymentMethodRef), lhsPaymentMethodRef, rhsPaymentMethodRef, (this.paymentMethodRef!= null), (that.paymentMethodRef!= null))) {
                return false;
            }
        }
        {
            String lhsPaymentRefNum;
            lhsPaymentRefNum = this.getPaymentRefNum();
            String rhsPaymentRefNum;
            rhsPaymentRefNum = that.getPaymentRefNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentRefNum", lhsPaymentRefNum), LocatorUtils.property(thatLocator, "paymentRefNum", rhsPaymentRefNum), lhsPaymentRefNum, rhsPaymentRefNum, (this.paymentRefNum!= null), (that.paymentRefNum!= null))) {
                return false;
            }
        }
        {
            PaymentTypeEnum lhsPaymentType;
            lhsPaymentType = this.getPaymentType();
            PaymentTypeEnum rhsPaymentType;
            rhsPaymentType = that.getPaymentType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentType", lhsPaymentType), LocatorUtils.property(thatLocator, "paymentType", rhsPaymentType), lhsPaymentType, rhsPaymentType, (this.paymentType!= null), (that.paymentType!= null))) {
                return false;
            }
        }
        {
            CheckPayment lhsCheckPayment;
            lhsCheckPayment = this.getCheckPayment();
            CheckPayment rhsCheckPayment;
            rhsCheckPayment = that.getCheckPayment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "checkPayment", lhsCheckPayment), LocatorUtils.property(thatLocator, "checkPayment", rhsCheckPayment), lhsCheckPayment, rhsCheckPayment, (this.checkPayment!= null), (that.checkPayment!= null))) {
                return false;
            }
        }
        {
            CreditCardPayment lhsCreditCardPayment;
            lhsCreditCardPayment = this.getCreditCardPayment();
            CreditCardPayment rhsCreditCardPayment;
            rhsCreditCardPayment = that.getCreditCardPayment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCardPayment", lhsCreditCardPayment), LocatorUtils.property(thatLocator, "creditCardPayment", rhsCreditCardPayment), lhsCreditCardPayment, rhsCreditCardPayment, (this.creditCardPayment!= null), (that.creditCardPayment!= null))) {
                return false;
            }
        }
        {
            ReferenceType lhsEntityRef;
            lhsEntityRef = this.getEntityRef();
            ReferenceType rhsEntityRef;
            rhsEntityRef = that.getEntityRef();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entityRef", lhsEntityRef), LocatorUtils.property(thatLocator, "entityRef", rhsEntityRef), lhsEntityRef, rhsEntityRef, (this.entityRef!= null), (that.entityRef!= null))) {
                return false;
            }
        }
        {
            Boolean lhsCredit;
            lhsCredit = this.isCredit();
            Boolean rhsCredit;
            rhsCredit = that.isCredit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "credit", lhsCredit), LocatorUtils.property(thatLocator, "credit", rhsCredit), lhsCredit, rhsCredit, (this.credit!= null), (that.credit!= null))) {
                return false;
            }
        }
        {
            PhysicalAddress lhsRemitToAddr;
            lhsRemitToAddr = this.getRemitToAddr();
            PhysicalAddress rhsRemitToAddr;
            rhsRemitToAddr = that.getRemitToAddr();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "remitToAddr", lhsRemitToAddr), LocatorUtils.property(thatLocator, "remitToAddr", rhsRemitToAddr), lhsRemitToAddr, rhsRemitToAddr, (this.remitToAddr!= null), (that.remitToAddr!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsTotalAmt;
            lhsTotalAmt = this.getTotalAmt();
            BigDecimal rhsTotalAmt;
            rhsTotalAmt = that.getTotalAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalAmt", lhsTotalAmt), LocatorUtils.property(thatLocator, "totalAmt", rhsTotalAmt), lhsTotalAmt, rhsTotalAmt, (this.totalAmt!= null), (that.totalAmt!= null))) {
                return false;
            }
        }
        {
            String lhsTxnId;
            lhsTxnId = this.getTxnId();
            String rhsTxnId;
            rhsTxnId = that.getTxnId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "txnId", lhsTxnId), LocatorUtils.property(thatLocator, "txnId", rhsTxnId), lhsTxnId, rhsTxnId, (this.txnId!= null), (that.txnId!= null))) {
                return false;
            }
        }
        {
            String lhsTxnNum;
            lhsTxnNum = this.getTxnNum();
            String rhsTxnNum;
            rhsTxnNum = that.getTxnNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "txnNum", lhsTxnNum), LocatorUtils.property(thatLocator, "txnNum", rhsTxnNum), lhsTxnNum, rhsTxnNum, (this.txnNum!= null), (that.txnNum!= null))) {
                return false;
            }
        }
        {
            String lhsMemo;
            lhsMemo = this.getMemo();
            String rhsMemo;
            rhsMemo = that.getMemo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "memo", lhsMemo), LocatorUtils.property(thatLocator, "memo", rhsMemo), lhsMemo, rhsMemo, (this.memo!= null), (that.memo!= null))) {
                return false;
            }
        }
        {
            PrintStatusEnum lhsPrintStatus;
            lhsPrintStatus = this.getPrintStatus();
            PrintStatusEnum rhsPrintStatus;
            rhsPrintStatus = that.getPrintStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printStatus", lhsPrintStatus), LocatorUtils.property(thatLocator, "printStatus", rhsPrintStatus), lhsPrintStatus, rhsPrintStatus, (this.printStatus!= null), (that.printStatus!= null))) {
                return false;
            }
        }
        {
            GlobalTaxCalculationEnum lhsGlobalTaxCalculation;
            lhsGlobalTaxCalculation = this.getGlobalTaxCalculation();
            GlobalTaxCalculationEnum rhsGlobalTaxCalculation;
            rhsGlobalTaxCalculation = that.getGlobalTaxCalculation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "globalTaxCalculation", lhsGlobalTaxCalculation), LocatorUtils.property(thatLocator, "globalTaxCalculation", rhsGlobalTaxCalculation), lhsGlobalTaxCalculation, rhsGlobalTaxCalculation, (this.globalTaxCalculation!= null), (that.globalTaxCalculation!= null))) {
                return false;
            }
        }
        {
            IntuitAnyType lhsPurchaseEx;
            lhsPurchaseEx = this.getPurchaseEx();
            IntuitAnyType rhsPurchaseEx;
            rhsPurchaseEx = that.getPurchaseEx();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseEx", lhsPurchaseEx), LocatorUtils.property(thatLocator, "purchaseEx", rhsPurchaseEx), lhsPurchaseEx, rhsPurchaseEx, (this.purchaseEx!= null), (that.purchaseEx!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsLessCIS;
            lhsLessCIS = this.getLessCIS();
            BigDecimal rhsLessCIS;
            rhsLessCIS = that.getLessCIS();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lessCIS", lhsLessCIS), LocatorUtils.property(thatLocator, "lessCIS", rhsLessCIS), lhsLessCIS, rhsLessCIS, (this.lessCIS!= null), (that.lessCIS!= null))) {
                return false;
            }
        }
        {
            Boolean lhsIncludeInAnnualTPAR;
            lhsIncludeInAnnualTPAR = this.isIncludeInAnnualTPAR();
            Boolean rhsIncludeInAnnualTPAR;
            rhsIncludeInAnnualTPAR = that.isIncludeInAnnualTPAR();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includeInAnnualTPAR", lhsIncludeInAnnualTPAR), LocatorUtils.property(thatLocator, "includeInAnnualTPAR", rhsIncludeInAnnualTPAR), lhsIncludeInAnnualTPAR, rhsIncludeInAnnualTPAR, (this.includeInAnnualTPAR!= null), (that.includeInAnnualTPAR!= null))) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            ReferenceType theAccountRef;
            theAccountRef = this.getAccountRef();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountRef", theAccountRef), currentHashCode, theAccountRef, (this.accountRef!= null));
        }
        {
            ReferenceType thePaymentMethodRef;
            thePaymentMethodRef = this.getPaymentMethodRef();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "paymentMethodRef", thePaymentMethodRef), currentHashCode, thePaymentMethodRef, (this.paymentMethodRef!= null));
        }
        {
            String thePaymentRefNum;
            thePaymentRefNum = this.getPaymentRefNum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "paymentRefNum", thePaymentRefNum), currentHashCode, thePaymentRefNum, (this.paymentRefNum!= null));
        }
        {
            PaymentTypeEnum thePaymentType;
            thePaymentType = this.getPaymentType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "paymentType", thePaymentType), currentHashCode, thePaymentType, (this.paymentType!= null));
        }
        {
            CheckPayment theCheckPayment;
            theCheckPayment = this.getCheckPayment();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "checkPayment", theCheckPayment), currentHashCode, theCheckPayment, (this.checkPayment!= null));
        }
        {
            CreditCardPayment theCreditCardPayment;
            theCreditCardPayment = this.getCreditCardPayment();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "creditCardPayment", theCreditCardPayment), currentHashCode, theCreditCardPayment, (this.creditCardPayment!= null));
        }
        {
            ReferenceType theEntityRef;
            theEntityRef = this.getEntityRef();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "entityRef", theEntityRef), currentHashCode, theEntityRef, (this.entityRef!= null));
        }
        {
            Boolean theCredit;
            theCredit = this.isCredit();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "credit", theCredit), currentHashCode, theCredit, (this.credit!= null));
        }
        {
            PhysicalAddress theRemitToAddr;
            theRemitToAddr = this.getRemitToAddr();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "remitToAddr", theRemitToAddr), currentHashCode, theRemitToAddr, (this.remitToAddr!= null));
        }
        {
            BigDecimal theTotalAmt;
            theTotalAmt = this.getTotalAmt();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "totalAmt", theTotalAmt), currentHashCode, theTotalAmt, (this.totalAmt!= null));
        }
        {
            String theTxnId;
            theTxnId = this.getTxnId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "txnId", theTxnId), currentHashCode, theTxnId, (this.txnId!= null));
        }
        {
            String theTxnNum;
            theTxnNum = this.getTxnNum();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "txnNum", theTxnNum), currentHashCode, theTxnNum, (this.txnNum!= null));
        }
        {
            String theMemo;
            theMemo = this.getMemo();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "memo", theMemo), currentHashCode, theMemo, (this.memo!= null));
        }
        {
            PrintStatusEnum thePrintStatus;
            thePrintStatus = this.getPrintStatus();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printStatus", thePrintStatus), currentHashCode, thePrintStatus, (this.printStatus!= null));
        }
        {
            GlobalTaxCalculationEnum theGlobalTaxCalculation;
            theGlobalTaxCalculation = this.getGlobalTaxCalculation();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "globalTaxCalculation", theGlobalTaxCalculation), currentHashCode, theGlobalTaxCalculation, (this.globalTaxCalculation!= null));
        }
        {
            IntuitAnyType thePurchaseEx;
            thePurchaseEx = this.getPurchaseEx();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "purchaseEx", thePurchaseEx), currentHashCode, thePurchaseEx, (this.purchaseEx!= null));
        }
        {
            BigDecimal theLessCIS;
            theLessCIS = this.getLessCIS();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lessCIS", theLessCIS), currentHashCode, theLessCIS, (this.lessCIS!= null));
        }
        {
            Boolean theIncludeInAnnualTPAR;
            theIncludeInAnnualTPAR = this.isIncludeInAnnualTPAR();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "includeInAnnualTPAR", theIncludeInAnnualTPAR), currentHashCode, theIncludeInAnnualTPAR, (this.includeInAnnualTPAR!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}
