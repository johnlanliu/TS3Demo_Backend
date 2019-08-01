//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.23 at 02:36:16 PM PDT 
//


package com.intuit.ipp.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * 				Description: Describes the base class of name entities (Customer, Employee, Vendor, OtherName)
 * 			
 * 
 * <p>Java class for NameBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NameBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.intuit.com/finance/v3}IntuitEntity">
 *       &lt;sequence>
 *         &lt;element name="IntuitId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Organization" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GivenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FamilyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Suffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FullyQualifiedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrintOnCheckName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PrimaryPhone" type="{http://schema.intuit.com/finance/v3}TelephoneNumber" minOccurs="0"/>
 *         &lt;element name="AlternatePhone" type="{http://schema.intuit.com/finance/v3}TelephoneNumber" minOccurs="0"/>
 *         &lt;element name="Mobile" type="{http://schema.intuit.com/finance/v3}TelephoneNumber" minOccurs="0"/>
 *         &lt;element name="Fax" type="{http://schema.intuit.com/finance/v3}TelephoneNumber" minOccurs="0"/>
 *         &lt;element name="PrimaryEmailAddr" type="{http://schema.intuit.com/finance/v3}EmailAddress" minOccurs="0"/>
 *         &lt;element name="WebAddr" type="{http://schema.intuit.com/finance/v3}WebSiteAddress" minOccurs="0"/>
 *         &lt;element name="OtherContactInfo" type="{http://schema.intuit.com/finance/v3}ContactInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DefaultTaxCodeRef" type="{http://schema.intuit.com/finance/v3}ReferenceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameBase", propOrder = {
    "intuitId",
    "organization",
    "title",
    "givenName",
    "middleName",
    "familyName",
    "suffix",
    "fullyQualifiedName",
    "companyName",
    "displayName",
    "printOnCheckName",
    "userId",
    "active",
    "primaryPhone",
    "alternatePhone",
    "mobile",
    "fax",
    "primaryEmailAddr",
    "webAddr",
    "otherContactInfo",
    "defaultTaxCodeRef"
})
@XmlSeeAlso({
    Customer.class,
    Employee.class,
    OtherName.class,
    Vendor.class
})
public class NameBase
    extends IntuitEntity
    implements Serializable, Equals2, HashCode2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "IntuitId")
    protected String intuitId;
    @XmlElement(name = "Organization")
    protected Boolean organization;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "GivenName")
    protected String givenName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "FamilyName")
    protected String familyName;
    @XmlElement(name = "Suffix")
    protected String suffix;
    @XmlElement(name = "FullyQualifiedName")
    protected String fullyQualifiedName;
    @XmlElement(name = "CompanyName")
    protected String companyName;
    @XmlElement(name = "DisplayName")
    protected String displayName;
    @XmlElement(name = "PrintOnCheckName")
    protected String printOnCheckName;
    @XmlElement(name = "UserId")
    protected String userId;
    @XmlElement(name = "Active")
    protected Boolean active;
    @XmlElement(name = "PrimaryPhone")
    protected TelephoneNumber primaryPhone;
    @XmlElement(name = "AlternatePhone")
    protected TelephoneNumber alternatePhone;
    @XmlElement(name = "Mobile")
    protected TelephoneNumber mobile;
    @XmlElement(name = "Fax")
    protected TelephoneNumber fax;
    @XmlElement(name = "PrimaryEmailAddr")
    protected EmailAddress primaryEmailAddr;
    @XmlElement(name = "WebAddr")
    protected WebSiteAddress webAddr;
    @XmlElement(name = "OtherContactInfo")
    protected List<ContactInfo> otherContactInfo;
    @XmlElement(name = "DefaultTaxCodeRef")
    protected ReferenceType defaultTaxCodeRef;

    /**
     * Gets the value of the intuitId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntuitId() {
        return intuitId;
    }

    /**
     * Sets the value of the intuitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntuitId(String value) {
        this.intuitId = value;
    }

    /**
     * Gets the value of the organization property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrganization() {
        return organization;
    }

    /**
     * Sets the value of the organization property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrganization(Boolean value) {
        this.organization = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the givenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * Gets the value of the suffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the value of the suffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * Gets the value of the fullyQualifiedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullyQualifiedName() {
        return fullyQualifiedName;
    }

    /**
     * Sets the value of the fullyQualifiedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullyQualifiedName(String value) {
        this.fullyQualifiedName = value;
    }

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the printOnCheckName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrintOnCheckName() {
        return printOnCheckName;
    }

    /**
     * Sets the value of the printOnCheckName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrintOnCheckName(String value) {
        this.printOnCheckName = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the active property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActive(Boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the primaryPhone property.
     * 
     * @return
     *     possible object is
     *     {@link TelephoneNumber }
     *     
     */
    public TelephoneNumber getPrimaryPhone() {
        return primaryPhone;
    }

    /**
     * Sets the value of the primaryPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelephoneNumber }
     *     
     */
    public void setPrimaryPhone(TelephoneNumber value) {
        this.primaryPhone = value;
    }

    /**
     * Gets the value of the alternatePhone property.
     * 
     * @return
     *     possible object is
     *     {@link TelephoneNumber }
     *     
     */
    public TelephoneNumber getAlternatePhone() {
        return alternatePhone;
    }

    /**
     * Sets the value of the alternatePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelephoneNumber }
     *     
     */
    public void setAlternatePhone(TelephoneNumber value) {
        this.alternatePhone = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link TelephoneNumber }
     *     
     */
    public TelephoneNumber getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelephoneNumber }
     *     
     */
    public void setMobile(TelephoneNumber value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link TelephoneNumber }
     *     
     */
    public TelephoneNumber getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelephoneNumber }
     *     
     */
    public void setFax(TelephoneNumber value) {
        this.fax = value;
    }

    /**
     * Gets the value of the primaryEmailAddr property.
     * 
     * @return
     *     possible object is
     *     {@link EmailAddress }
     *     
     */
    public EmailAddress getPrimaryEmailAddr() {
        return primaryEmailAddr;
    }

    /**
     * Sets the value of the primaryEmailAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailAddress }
     *     
     */
    public void setPrimaryEmailAddr(EmailAddress value) {
        this.primaryEmailAddr = value;
    }

    /**
     * Gets the value of the webAddr property.
     * 
     * @return
     *     possible object is
     *     {@link WebSiteAddress }
     *     
     */
    public WebSiteAddress getWebAddr() {
        return webAddr;
    }

    /**
     * Sets the value of the webAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebSiteAddress }
     *     
     */
    public void setWebAddr(WebSiteAddress value) {
        this.webAddr = value;
    }

    /**
     * Gets the value of the otherContactInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherContactInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherContactInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactInfo }
     * 
     * 
     */
    public List<ContactInfo> getOtherContactInfo() {
        if (otherContactInfo == null) {
            otherContactInfo = new ArrayList<ContactInfo>();
        }
        return this.otherContactInfo;
    }

    /**
     * Gets the value of the defaultTaxCodeRef property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getDefaultTaxCodeRef() {
        return defaultTaxCodeRef;
    }

    /**
     * Sets the value of the defaultTaxCodeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setDefaultTaxCodeRef(ReferenceType value) {
        this.defaultTaxCodeRef = value;
    }

    /**
     * Sets the value of the otherContactInfo property.
     * 
     * @param otherContactInfo
     *     allowed object is
     *     {@link ContactInfo }
     *     
     */
    public void setOtherContactInfo(List<ContactInfo> otherContactInfo) {
        this.otherContactInfo = otherContactInfo;
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
        final NameBase that = ((NameBase) object);
        {
            String lhsIntuitId;
            lhsIntuitId = this.getIntuitId();
            String rhsIntuitId;
            rhsIntuitId = that.getIntuitId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "intuitId", lhsIntuitId), LocatorUtils.property(thatLocator, "intuitId", rhsIntuitId), lhsIntuitId, rhsIntuitId, (this.intuitId!= null), (that.intuitId!= null))) {
                return false;
            }
        }
        {
            Boolean lhsOrganization;
            lhsOrganization = this.isOrganization();
            Boolean rhsOrganization;
            rhsOrganization = that.isOrganization();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "organization", lhsOrganization), LocatorUtils.property(thatLocator, "organization", rhsOrganization), lhsOrganization, rhsOrganization, (this.organization!= null), (that.organization!= null))) {
                return false;
            }
        }
        {
            String lhsTitle;
            lhsTitle = this.getTitle();
            String rhsTitle;
            rhsTitle = that.getTitle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "title", lhsTitle), LocatorUtils.property(thatLocator, "title", rhsTitle), lhsTitle, rhsTitle, (this.title!= null), (that.title!= null))) {
                return false;
            }
        }
        {
            String lhsGivenName;
            lhsGivenName = this.getGivenName();
            String rhsGivenName;
            rhsGivenName = that.getGivenName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "givenName", lhsGivenName), LocatorUtils.property(thatLocator, "givenName", rhsGivenName), lhsGivenName, rhsGivenName, (this.givenName!= null), (that.givenName!= null))) {
                return false;
            }
        }
        {
            String lhsMiddleName;
            lhsMiddleName = this.getMiddleName();
            String rhsMiddleName;
            rhsMiddleName = that.getMiddleName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "middleName", lhsMiddleName), LocatorUtils.property(thatLocator, "middleName", rhsMiddleName), lhsMiddleName, rhsMiddleName, (this.middleName!= null), (that.middleName!= null))) {
                return false;
            }
        }
        {
            String lhsFamilyName;
            lhsFamilyName = this.getFamilyName();
            String rhsFamilyName;
            rhsFamilyName = that.getFamilyName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "familyName", lhsFamilyName), LocatorUtils.property(thatLocator, "familyName", rhsFamilyName), lhsFamilyName, rhsFamilyName, (this.familyName!= null), (that.familyName!= null))) {
                return false;
            }
        }
        {
            String lhsSuffix;
            lhsSuffix = this.getSuffix();
            String rhsSuffix;
            rhsSuffix = that.getSuffix();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "suffix", lhsSuffix), LocatorUtils.property(thatLocator, "suffix", rhsSuffix), lhsSuffix, rhsSuffix, (this.suffix!= null), (that.suffix!= null))) {
                return false;
            }
        }
        {
            String lhsFullyQualifiedName;
            lhsFullyQualifiedName = this.getFullyQualifiedName();
            String rhsFullyQualifiedName;
            rhsFullyQualifiedName = that.getFullyQualifiedName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fullyQualifiedName", lhsFullyQualifiedName), LocatorUtils.property(thatLocator, "fullyQualifiedName", rhsFullyQualifiedName), lhsFullyQualifiedName, rhsFullyQualifiedName, (this.fullyQualifiedName!= null), (that.fullyQualifiedName!= null))) {
                return false;
            }
        }
        {
            String lhsCompanyName;
            lhsCompanyName = this.getCompanyName();
            String rhsCompanyName;
            rhsCompanyName = that.getCompanyName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "companyName", lhsCompanyName), LocatorUtils.property(thatLocator, "companyName", rhsCompanyName), lhsCompanyName, rhsCompanyName, (this.companyName!= null), (that.companyName!= null))) {
                return false;
            }
        }
        {
            String lhsDisplayName;
            lhsDisplayName = this.getDisplayName();
            String rhsDisplayName;
            rhsDisplayName = that.getDisplayName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "displayName", lhsDisplayName), LocatorUtils.property(thatLocator, "displayName", rhsDisplayName), lhsDisplayName, rhsDisplayName, (this.displayName!= null), (that.displayName!= null))) {
                return false;
            }
        }
        {
            String lhsPrintOnCheckName;
            lhsPrintOnCheckName = this.getPrintOnCheckName();
            String rhsPrintOnCheckName;
            rhsPrintOnCheckName = that.getPrintOnCheckName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printOnCheckName", lhsPrintOnCheckName), LocatorUtils.property(thatLocator, "printOnCheckName", rhsPrintOnCheckName), lhsPrintOnCheckName, rhsPrintOnCheckName, (this.printOnCheckName!= null), (that.printOnCheckName!= null))) {
                return false;
            }
        }
        {
            String lhsUserId;
            lhsUserId = this.getUserId();
            String rhsUserId;
            rhsUserId = that.getUserId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userId", lhsUserId), LocatorUtils.property(thatLocator, "userId", rhsUserId), lhsUserId, rhsUserId, (this.userId!= null), (that.userId!= null))) {
                return false;
            }
        }
        {
            Boolean lhsActive;
            lhsActive = this.isActive();
            Boolean rhsActive;
            rhsActive = that.isActive();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "active", lhsActive), LocatorUtils.property(thatLocator, "active", rhsActive), lhsActive, rhsActive, (this.active!= null), (that.active!= null))) {
                return false;
            }
        }
        {
            TelephoneNumber lhsPrimaryPhone;
            lhsPrimaryPhone = this.getPrimaryPhone();
            TelephoneNumber rhsPrimaryPhone;
            rhsPrimaryPhone = that.getPrimaryPhone();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "primaryPhone", lhsPrimaryPhone), LocatorUtils.property(thatLocator, "primaryPhone", rhsPrimaryPhone), lhsPrimaryPhone, rhsPrimaryPhone, (this.primaryPhone!= null), (that.primaryPhone!= null))) {
                return false;
            }
        }
        {
            TelephoneNumber lhsAlternatePhone;
            lhsAlternatePhone = this.getAlternatePhone();
            TelephoneNumber rhsAlternatePhone;
            rhsAlternatePhone = that.getAlternatePhone();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "alternatePhone", lhsAlternatePhone), LocatorUtils.property(thatLocator, "alternatePhone", rhsAlternatePhone), lhsAlternatePhone, rhsAlternatePhone, (this.alternatePhone!= null), (that.alternatePhone!= null))) {
                return false;
            }
        }
        {
            TelephoneNumber lhsMobile;
            lhsMobile = this.getMobile();
            TelephoneNumber rhsMobile;
            rhsMobile = that.getMobile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mobile", lhsMobile), LocatorUtils.property(thatLocator, "mobile", rhsMobile), lhsMobile, rhsMobile, (this.mobile!= null), (that.mobile!= null))) {
                return false;
            }
        }
        {
            TelephoneNumber lhsFax;
            lhsFax = this.getFax();
            TelephoneNumber rhsFax;
            rhsFax = that.getFax();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fax", lhsFax), LocatorUtils.property(thatLocator, "fax", rhsFax), lhsFax, rhsFax, (this.fax!= null), (that.fax!= null))) {
                return false;
            }
        }
        {
            EmailAddress lhsPrimaryEmailAddr;
            lhsPrimaryEmailAddr = this.getPrimaryEmailAddr();
            EmailAddress rhsPrimaryEmailAddr;
            rhsPrimaryEmailAddr = that.getPrimaryEmailAddr();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "primaryEmailAddr", lhsPrimaryEmailAddr), LocatorUtils.property(thatLocator, "primaryEmailAddr", rhsPrimaryEmailAddr), lhsPrimaryEmailAddr, rhsPrimaryEmailAddr, (this.primaryEmailAddr!= null), (that.primaryEmailAddr!= null))) {
                return false;
            }
        }
        {
            WebSiteAddress lhsWebAddr;
            lhsWebAddr = this.getWebAddr();
            WebSiteAddress rhsWebAddr;
            rhsWebAddr = that.getWebAddr();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "webAddr", lhsWebAddr), LocatorUtils.property(thatLocator, "webAddr", rhsWebAddr), lhsWebAddr, rhsWebAddr, (this.webAddr!= null), (that.webAddr!= null))) {
                return false;
            }
        }
        {
            List<ContactInfo> lhsOtherContactInfo;
            lhsOtherContactInfo = (((this.otherContactInfo!= null)&&(!this.otherContactInfo.isEmpty()))?this.getOtherContactInfo():null);
            List<ContactInfo> rhsOtherContactInfo;
            rhsOtherContactInfo = (((that.otherContactInfo!= null)&&(!that.otherContactInfo.isEmpty()))?that.getOtherContactInfo():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "otherContactInfo", lhsOtherContactInfo), LocatorUtils.property(thatLocator, "otherContactInfo", rhsOtherContactInfo), lhsOtherContactInfo, rhsOtherContactInfo, ((this.otherContactInfo!= null)&&(!this.otherContactInfo.isEmpty())), ((that.otherContactInfo!= null)&&(!that.otherContactInfo.isEmpty())))) {
                return false;
            }
        }
        {
            ReferenceType lhsDefaultTaxCodeRef;
            lhsDefaultTaxCodeRef = this.getDefaultTaxCodeRef();
            ReferenceType rhsDefaultTaxCodeRef;
            rhsDefaultTaxCodeRef = that.getDefaultTaxCodeRef();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultTaxCodeRef", lhsDefaultTaxCodeRef), LocatorUtils.property(thatLocator, "defaultTaxCodeRef", rhsDefaultTaxCodeRef), lhsDefaultTaxCodeRef, rhsDefaultTaxCodeRef, (this.defaultTaxCodeRef!= null), (that.defaultTaxCodeRef!= null))) {
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
            String theIntuitId;
            theIntuitId = this.getIntuitId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "intuitId", theIntuitId), currentHashCode, theIntuitId, (this.intuitId!= null));
        }
        {
            Boolean theOrganization;
            theOrganization = this.isOrganization();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "organization", theOrganization), currentHashCode, theOrganization, (this.organization!= null));
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "title", theTitle), currentHashCode, theTitle, (this.title!= null));
        }
        {
            String theGivenName;
            theGivenName = this.getGivenName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "givenName", theGivenName), currentHashCode, theGivenName, (this.givenName!= null));
        }
        {
            String theMiddleName;
            theMiddleName = this.getMiddleName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "middleName", theMiddleName), currentHashCode, theMiddleName, (this.middleName!= null));
        }
        {
            String theFamilyName;
            theFamilyName = this.getFamilyName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "familyName", theFamilyName), currentHashCode, theFamilyName, (this.familyName!= null));
        }
        {
            String theSuffix;
            theSuffix = this.getSuffix();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "suffix", theSuffix), currentHashCode, theSuffix, (this.suffix!= null));
        }
        {
            String theFullyQualifiedName;
            theFullyQualifiedName = this.getFullyQualifiedName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fullyQualifiedName", theFullyQualifiedName), currentHashCode, theFullyQualifiedName, (this.fullyQualifiedName!= null));
        }
        {
            String theCompanyName;
            theCompanyName = this.getCompanyName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "companyName", theCompanyName), currentHashCode, theCompanyName, (this.companyName!= null));
        }
        {
            String theDisplayName;
            theDisplayName = this.getDisplayName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "displayName", theDisplayName), currentHashCode, theDisplayName, (this.displayName!= null));
        }
        {
            String thePrintOnCheckName;
            thePrintOnCheckName = this.getPrintOnCheckName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "printOnCheckName", thePrintOnCheckName), currentHashCode, thePrintOnCheckName, (this.printOnCheckName!= null));
        }
        {
            String theUserId;
            theUserId = this.getUserId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "userId", theUserId), currentHashCode, theUserId, (this.userId!= null));
        }
        {
            Boolean theActive;
            theActive = this.isActive();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "active", theActive), currentHashCode, theActive, (this.active!= null));
        }
        {
            TelephoneNumber thePrimaryPhone;
            thePrimaryPhone = this.getPrimaryPhone();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "primaryPhone", thePrimaryPhone), currentHashCode, thePrimaryPhone, (this.primaryPhone!= null));
        }
        {
            TelephoneNumber theAlternatePhone;
            theAlternatePhone = this.getAlternatePhone();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "alternatePhone", theAlternatePhone), currentHashCode, theAlternatePhone, (this.alternatePhone!= null));
        }
        {
            TelephoneNumber theMobile;
            theMobile = this.getMobile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mobile", theMobile), currentHashCode, theMobile, (this.mobile!= null));
        }
        {
            TelephoneNumber theFax;
            theFax = this.getFax();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fax", theFax), currentHashCode, theFax, (this.fax!= null));
        }
        {
            EmailAddress thePrimaryEmailAddr;
            thePrimaryEmailAddr = this.getPrimaryEmailAddr();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "primaryEmailAddr", thePrimaryEmailAddr), currentHashCode, thePrimaryEmailAddr, (this.primaryEmailAddr!= null));
        }
        {
            WebSiteAddress theWebAddr;
            theWebAddr = this.getWebAddr();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "webAddr", theWebAddr), currentHashCode, theWebAddr, (this.webAddr!= null));
        }
        {
            List<ContactInfo> theOtherContactInfo;
            theOtherContactInfo = (((this.otherContactInfo!= null)&&(!this.otherContactInfo.isEmpty()))?this.getOtherContactInfo():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "otherContactInfo", theOtherContactInfo), currentHashCode, theOtherContactInfo, ((this.otherContactInfo!= null)&&(!this.otherContactInfo.isEmpty())));
        }
        {
            ReferenceType theDefaultTaxCodeRef;
            theDefaultTaxCodeRef = this.getDefaultTaxCodeRef();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "defaultTaxCodeRef", theDefaultTaxCodeRef), currentHashCode, theDefaultTaxCodeRef, (this.defaultTaxCodeRef!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}