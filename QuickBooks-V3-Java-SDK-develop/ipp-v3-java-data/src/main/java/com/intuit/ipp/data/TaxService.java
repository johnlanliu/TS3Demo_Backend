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
import javax.xml.bind.annotation.XmlType;
import com.intuit.ipp.core.IEntity;
import com.intuit.ipp.core.Response;
import org.jvnet.jaxb2_commons.lang.Equals2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy2;
import org.jvnet.jaxb2_commons.lang.HashCode2;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy2;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Describes SalesTax details
 * 
 * <p>Java class for TaxService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxService">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.intuit.com/finance/v3}IntuitEntity">
 *       &lt;sequence>
 *         &lt;element name="TaxCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxCodeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TaxRateDetails" type="{http://schema.intuit.com/finance/v3}TaxRateDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Fault" type="{http://schema.intuit.com/finance/v3}Fault" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxService", propOrder = {
    "taxCode",
    "taxCodeId",
    "taxRateDetails",
    "fault"
})
public class TaxService
    extends IntuitEntity
    implements Serializable, IEntity, Response, Equals2, HashCode2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "TaxCode")
    protected String taxCode;
    @XmlElement(name = "TaxCodeId")
    protected String taxCodeId;
    @XmlElement(name = "TaxRateDetails")
    protected List<TaxRateDetails> taxRateDetails;
    @XmlElement(name = "Fault")
    protected Fault fault;

    /**
     * Gets the value of the taxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the taxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
    }

    /**
     * Gets the value of the taxCodeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCodeId() {
        return taxCodeId;
    }

    /**
     * Sets the value of the taxCodeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCodeId(String value) {
        this.taxCodeId = value;
    }

    /**
     * Gets the value of the taxRateDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taxRateDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaxRateDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaxRateDetails }
     * 
     * 
     */
    public List<TaxRateDetails> getTaxRateDetails() {
        if (taxRateDetails == null) {
            taxRateDetails = new ArrayList<TaxRateDetails>();
        }
        return this.taxRateDetails;
    }

    /**
     * Gets the value of the fault property.
     * 
     * @return
     *     possible object is
     *     {@link Fault }
     *     
     */
    public Fault getFault() {
        return fault;
    }

    /**
     * Sets the value of the fault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fault }
     *     
     */
    public void setFault(Fault value) {
        this.fault = value;
    }

    /**
     * Sets the value of the taxRateDetails property.
     * 
     * @param taxRateDetails
     *     allowed object is
     *     {@link TaxRateDetails }
     *     
     */
    public void setTaxRateDetails(List<TaxRateDetails> taxRateDetails) {
        this.taxRateDetails = taxRateDetails;
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
        final TaxService that = ((TaxService) object);
        {
            String lhsTaxCode;
            lhsTaxCode = this.getTaxCode();
            String rhsTaxCode;
            rhsTaxCode = that.getTaxCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxCode", lhsTaxCode), LocatorUtils.property(thatLocator, "taxCode", rhsTaxCode), lhsTaxCode, rhsTaxCode, (this.taxCode!= null), (that.taxCode!= null))) {
                return false;
            }
        }
        {
            String lhsTaxCodeId;
            lhsTaxCodeId = this.getTaxCodeId();
            String rhsTaxCodeId;
            rhsTaxCodeId = that.getTaxCodeId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxCodeId", lhsTaxCodeId), LocatorUtils.property(thatLocator, "taxCodeId", rhsTaxCodeId), lhsTaxCodeId, rhsTaxCodeId, (this.taxCodeId!= null), (that.taxCodeId!= null))) {
                return false;
            }
        }
        {
            List<TaxRateDetails> lhsTaxRateDetails;
            lhsTaxRateDetails = (((this.taxRateDetails!= null)&&(!this.taxRateDetails.isEmpty()))?this.getTaxRateDetails():null);
            List<TaxRateDetails> rhsTaxRateDetails;
            rhsTaxRateDetails = (((that.taxRateDetails!= null)&&(!that.taxRateDetails.isEmpty()))?that.getTaxRateDetails():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxRateDetails", lhsTaxRateDetails), LocatorUtils.property(thatLocator, "taxRateDetails", rhsTaxRateDetails), lhsTaxRateDetails, rhsTaxRateDetails, ((this.taxRateDetails!= null)&&(!this.taxRateDetails.isEmpty())), ((that.taxRateDetails!= null)&&(!that.taxRateDetails.isEmpty())))) {
                return false;
            }
        }
        {
            Fault lhsFault;
            lhsFault = this.getFault();
            Fault rhsFault;
            rhsFault = that.getFault();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fault", lhsFault), LocatorUtils.property(thatLocator, "fault", rhsFault), lhsFault, rhsFault, (this.fault!= null), (that.fault!= null))) {
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
            String theTaxCode;
            theTaxCode = this.getTaxCode();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxCode", theTaxCode), currentHashCode, theTaxCode, (this.taxCode!= null));
        }
        {
            String theTaxCodeId;
            theTaxCodeId = this.getTaxCodeId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxCodeId", theTaxCodeId), currentHashCode, theTaxCodeId, (this.taxCodeId!= null));
        }
        {
            List<TaxRateDetails> theTaxRateDetails;
            theTaxRateDetails = (((this.taxRateDetails!= null)&&(!this.taxRateDetails.isEmpty()))?this.getTaxRateDetails():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "taxRateDetails", theTaxRateDetails), currentHashCode, theTaxRateDetails, ((this.taxRateDetails!= null)&&(!this.taxRateDetails.isEmpty())));
        }
        {
            Fault theFault;
            theFault = this.getFault();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fault", theFault), currentHashCode, theFault, (this.fault!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}