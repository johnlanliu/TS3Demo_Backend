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
import com.intuit.ipp.core.IEntity;
import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.HashCode2;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Describes OLBAccount details
 * 
 * <p>Java class for OLBAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OLBAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountId" type="{http://schema.intuit.com/finance/v3}ReferenceType" minOccurs="0"/>
 *         &lt;element name="AccountDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubscribedToApp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AppVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastBankBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OLBAccount", propOrder = {
    "accountId",
    "accountDetails",
    "subscribedToApp",
    "appVersion",
    "lastBankBalance"
})
public class OLBAccount implements Serializable, IEntity, Equals2, HashCode2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "AccountId")
    protected ReferenceType accountId;
    @XmlElement(name = "AccountDetails")
    protected String accountDetails;
    @XmlElement(name = "SubscribedToApp")
    protected Boolean subscribedToApp;
    @XmlElement(name = "AppVersion")
    protected String appVersion;
    @XmlElement(name = "LastBankBalance")
    protected BigDecimal lastBankBalance;

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setAccountId(ReferenceType value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the accountDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDetails() {
        return accountDetails;
    }

    /**
     * Sets the value of the accountDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDetails(String value) {
        this.accountDetails = value;
    }

    /**
     * Gets the value of the subscribedToApp property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSubscribedToApp() {
        return subscribedToApp;
    }

    /**
     * Sets the value of the subscribedToApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSubscribedToApp(Boolean value) {
        this.subscribedToApp = value;
    }

    /**
     * Gets the value of the appVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * Sets the value of the appVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppVersion(String value) {
        this.appVersion = value;
    }

    /**
     * Gets the value of the lastBankBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLastBankBalance() {
        return lastBankBalance;
    }

    /**
     * Sets the value of the lastBankBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLastBankBalance(BigDecimal value) {
        this.lastBankBalance = value;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy2 strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OLBAccount that = ((OLBAccount) object);
        {
            ReferenceType lhsAccountId;
            lhsAccountId = this.getAccountId();
            ReferenceType rhsAccountId;
            rhsAccountId = that.getAccountId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountId", lhsAccountId), LocatorUtils.property(thatLocator, "accountId", rhsAccountId), lhsAccountId, rhsAccountId, (this.accountId!= null), (that.accountId!= null))) {
                return false;
            }
        }
        {
            String lhsAccountDetails;
            lhsAccountDetails = this.getAccountDetails();
            String rhsAccountDetails;
            rhsAccountDetails = that.getAccountDetails();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountDetails", lhsAccountDetails), LocatorUtils.property(thatLocator, "accountDetails", rhsAccountDetails), lhsAccountDetails, rhsAccountDetails, (this.accountDetails!= null), (that.accountDetails!= null))) {
                return false;
            }
        }
        {
            Boolean lhsSubscribedToApp;
            lhsSubscribedToApp = this.isSubscribedToApp();
            Boolean rhsSubscribedToApp;
            rhsSubscribedToApp = that.isSubscribedToApp();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscribedToApp", lhsSubscribedToApp), LocatorUtils.property(thatLocator, "subscribedToApp", rhsSubscribedToApp), lhsSubscribedToApp, rhsSubscribedToApp, (this.subscribedToApp!= null), (that.subscribedToApp!= null))) {
                return false;
            }
        }
        {
            String lhsAppVersion;
            lhsAppVersion = this.getAppVersion();
            String rhsAppVersion;
            rhsAppVersion = that.getAppVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appVersion", lhsAppVersion), LocatorUtils.property(thatLocator, "appVersion", rhsAppVersion), lhsAppVersion, rhsAppVersion, (this.appVersion!= null), (that.appVersion!= null))) {
                return false;
            }
        }
        {
            BigDecimal lhsLastBankBalance;
            lhsLastBankBalance = this.getLastBankBalance();
            BigDecimal rhsLastBankBalance;
            rhsLastBankBalance = that.getLastBankBalance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastBankBalance", lhsLastBankBalance), LocatorUtils.property(thatLocator, "lastBankBalance", rhsLastBankBalance), lhsLastBankBalance, rhsLastBankBalance, (this.lastBankBalance!= null), (that.lastBankBalance!= null))) {
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
            ReferenceType theAccountId;
            theAccountId = this.getAccountId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountId", theAccountId), currentHashCode, theAccountId, (this.accountId!= null));
        }
        {
            String theAccountDetails;
            theAccountDetails = this.getAccountDetails();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "accountDetails", theAccountDetails), currentHashCode, theAccountDetails, (this.accountDetails!= null));
        }
        {
            Boolean theSubscribedToApp;
            theSubscribedToApp = this.isSubscribedToApp();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "subscribedToApp", theSubscribedToApp), currentHashCode, theSubscribedToApp, (this.subscribedToApp!= null));
        }
        {
            String theAppVersion;
            theAppVersion = this.getAppVersion();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "appVersion", theAppVersion), currentHashCode, theAppVersion, (this.appVersion!= null));
        }
        {
            BigDecimal theLastBankBalance;
            theLastBankBalance = this.getLastBankBalance();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "lastBankBalance", theLastBankBalance), currentHashCode, theLastBankBalance, (this.lastBankBalance!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}