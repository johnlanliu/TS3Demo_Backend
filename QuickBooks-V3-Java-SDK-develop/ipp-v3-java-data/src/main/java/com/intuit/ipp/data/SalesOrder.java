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
 * 				Product: QBW
 * 				Description: A sales order is a
 * 				financial transaction that represents a request received from a
 * 				customer to purchase products or services. Sales orders help you
 * 				manage the sale of products and services your customers order. For
 * 				example, a sales order tracks inventory that is on back order for a
 * 				customer. Sales Orders are supported only in QuickBooks Premier
 * 				(desktop) and above. However, if you are accessing a company file
 * 				created in Premier and above from a lesser edition of QuickBooks
 * 				(such as Pro), you can do queries against SalesOrders. Using sales
 * 				orders is optional.
 * 				Endpoint: services.intuit.com
 * 				Business Rules:
 * 				[li]A sales order must have at least one line that describes the
 * 				item. [/li][li]A sales order must have a reference to a customer in
 * 				the [/li][li]If you submit a query with the filter
 * 				IncludeDiscountLineDetails, the system retrieves either
 * 				DiscountAmount or DiscountRatePercent with associated values[/li]
 * 			
 * 
 * <p>Java class for SalesOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOrder">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schema.intuit.com/finance/v3}SalesTransaction">
 *       &lt;sequence>
 *         &lt;element name="ManuallyClosed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SalesOrderEx" type="{http://schema.intuit.com/finance/v3}IntuitAnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOrder", propOrder = {
    "manuallyClosed",
    "salesOrderEx"
})
public class SalesOrder
    extends SalesTransaction
    implements Serializable, Equals2, HashCode2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "ManuallyClosed")
    protected Boolean manuallyClosed;
    @XmlElement(name = "SalesOrderEx")
    protected IntuitAnyType salesOrderEx;

    /**
     * Gets the value of the manuallyClosed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isManuallyClosed() {
        return manuallyClosed;
    }

    /**
     * Sets the value of the manuallyClosed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setManuallyClosed(Boolean value) {
        this.manuallyClosed = value;
    }

    /**
     * Gets the value of the salesOrderEx property.
     * 
     * @return
     *     possible object is
     *     {@link IntuitAnyType }
     *     
     */
    public IntuitAnyType getSalesOrderEx() {
        return salesOrderEx;
    }

    /**
     * Sets the value of the salesOrderEx property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntuitAnyType }
     *     
     */
    public void setSalesOrderEx(IntuitAnyType value) {
        this.salesOrderEx = value;
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
        final SalesOrder that = ((SalesOrder) object);
        {
            Boolean lhsManuallyClosed;
            lhsManuallyClosed = this.isManuallyClosed();
            Boolean rhsManuallyClosed;
            rhsManuallyClosed = that.isManuallyClosed();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "manuallyClosed", lhsManuallyClosed), LocatorUtils.property(thatLocator, "manuallyClosed", rhsManuallyClosed), lhsManuallyClosed, rhsManuallyClosed, (this.manuallyClosed!= null), (that.manuallyClosed!= null))) {
                return false;
            }
        }
        {
            IntuitAnyType lhsSalesOrderEx;
            lhsSalesOrderEx = this.getSalesOrderEx();
            IntuitAnyType rhsSalesOrderEx;
            rhsSalesOrderEx = that.getSalesOrderEx();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesOrderEx", lhsSalesOrderEx), LocatorUtils.property(thatLocator, "salesOrderEx", rhsSalesOrderEx), lhsSalesOrderEx, rhsSalesOrderEx, (this.salesOrderEx!= null), (that.salesOrderEx!= null))) {
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
            Boolean theManuallyClosed;
            theManuallyClosed = this.isManuallyClosed();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "manuallyClosed", theManuallyClosed), currentHashCode, theManuallyClosed, (this.manuallyClosed!= null));
        }
        {
            IntuitAnyType theSalesOrderEx;
            theSalesOrderEx = this.getSalesOrderEx();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "salesOrderEx", theSalesOrderEx), currentHashCode, theSalesOrderEx, (this.salesOrderEx!= null));
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy2 strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

}