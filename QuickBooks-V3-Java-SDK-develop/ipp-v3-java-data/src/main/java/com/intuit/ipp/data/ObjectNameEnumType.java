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
 * <p>Java class for objectNameEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="objectNameEnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Account"/>
 *     &lt;enumeration value="All"/>
 *     &lt;enumeration value="Attachable"/>
 *     &lt;enumeration value="Bill"/>
 *     &lt;enumeration value="BillPayment"/>
 *     &lt;enumeration value="BOMComponent"/>
 *     &lt;enumeration value="ChargeCredit"/>
 *     &lt;enumeration value="Class"/>
 *     &lt;enumeration value="Company"/>
 *     &lt;enumeration value="CompanyInfo"/>
 *     &lt;enumeration value="CreditMemo"/>
 *     &lt;enumeration value="Customer"/>
 *     &lt;enumeration value="CustomerType"/>
 *     &lt;enumeration value="Discount"/>
 *     &lt;enumeration value="Department"/>
 *     &lt;enumeration value="Deposit"/>
 *     &lt;enumeration value="Employee"/>
 *     &lt;enumeration value="Estimate"/>
 *     &lt;enumeration value="FixedAsset"/>
 *     &lt;enumeration value="InventoryAdjustment"/>
 *     &lt;enumeration value="InventorySite"/>
 *     &lt;enumeration value="Invoice"/>
 *     &lt;enumeration value="Item"/>
 *     &lt;enumeration value="ItemReceipt"/>
 *     &lt;enumeration value="JournalEntry"/>
 *     &lt;enumeration value="ListObjectType"/>
 *     &lt;enumeration value="Names"/>
 *     &lt;enumeration value="OtherName"/>
 *     &lt;enumeration value="Payment"/>
 *     &lt;enumeration value="PaymentMethod"/>
 *     &lt;enumeration value="Preferences"/>
 *     &lt;enumeration value="PriceLevel"/>
 *     &lt;enumeration value="Purchase"/>
 *     &lt;enumeration value="PurchaseOrder"/>
 *     &lt;enumeration value="RefundReceipt"/>
 *     &lt;enumeration value="ReimburseCharge"/>
 *     &lt;enumeration value="Report"/>
 *     &lt;enumeration value="SalesOrder"/>
 *     &lt;enumeration value="SalesReceipt"/>
 *     &lt;enumeration value="SalesRep"/>
 *     &lt;enumeration value="ShipMethod"/>
 *     &lt;enumeration value="StatementCharge"/>
 *     &lt;enumeration value="TaxCode"/>
 *     &lt;enumeration value="TaxClassification"/>
 *     &lt;enumeration value="TaxRate"/>
 *     &lt;enumeration value="TaxReturn"/>
 *     &lt;enumeration value="Term"/>
 *     &lt;enumeration value="TimeActivity"/>
 *     &lt;enumeration value="Transfer"/>
 *     &lt;enumeration value="Transaction"/>
 *     &lt;enumeration value="TxnLocation"/>
 *     &lt;enumeration value="UOM"/>
 *     &lt;enumeration value="Vendor"/>
 *     &lt;enumeration value="VendorCredit"/>
 *     &lt;enumeration value="CustomFieldDefinition"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "objectNameEnumType")
@XmlEnum
public enum ObjectNameEnumType {

    @XmlEnumValue("Account")
    ACCOUNT("Account"),
    @XmlEnumValue("All")
    ALL("All"),
    @XmlEnumValue("Attachable")
    ATTACHABLE("Attachable"),
    @XmlEnumValue("Bill")
    BILL("Bill"),
    @XmlEnumValue("BillPayment")
    BILL_PAYMENT("BillPayment"),
    @XmlEnumValue("BOMComponent")
    BOM_COMPONENT("BOMComponent"),
    @XmlEnumValue("ChargeCredit")
    CHARGE_CREDIT("ChargeCredit"),
    @XmlEnumValue("Class")
    CLASS("Class"),
    @XmlEnumValue("Company")
    COMPANY("Company"),
    @XmlEnumValue("CompanyInfo")
    COMPANY_INFO("CompanyInfo"),
    @XmlEnumValue("CreditMemo")
    CREDIT_MEMO("CreditMemo"),
    @XmlEnumValue("Customer")
    CUSTOMER("Customer"),
    @XmlEnumValue("CustomerType")
    CUSTOMER_TYPE("CustomerType"),
    @XmlEnumValue("Discount")
    DISCOUNT("Discount"),
    @XmlEnumValue("Department")
    DEPARTMENT("Department"),
    @XmlEnumValue("Deposit")
    DEPOSIT("Deposit"),
    @XmlEnumValue("Employee")
    EMPLOYEE("Employee"),
    @XmlEnumValue("Estimate")
    ESTIMATE("Estimate"),
    @XmlEnumValue("FixedAsset")
    FIXED_ASSET("FixedAsset"),
    @XmlEnumValue("InventoryAdjustment")
    INVENTORY_ADJUSTMENT("InventoryAdjustment"),
    @XmlEnumValue("InventorySite")
    INVENTORY_SITE("InventorySite"),
    @XmlEnumValue("Invoice")
    INVOICE("Invoice"),
    @XmlEnumValue("Item")
    ITEM("Item"),
    @XmlEnumValue("ItemReceipt")
    ITEM_RECEIPT("ItemReceipt"),
    @XmlEnumValue("JournalEntry")
    JOURNAL_ENTRY("JournalEntry"),
    @XmlEnumValue("ListObjectType")
    LIST_OBJECT_TYPE("ListObjectType"),
    @XmlEnumValue("Names")
    NAMES("Names"),
    @XmlEnumValue("OtherName")
    OTHER_NAME("OtherName"),
    @XmlEnumValue("Payment")
    PAYMENT("Payment"),
    @XmlEnumValue("PaymentMethod")
    PAYMENT_METHOD("PaymentMethod"),
    @XmlEnumValue("Preferences")
    PREFERENCES("Preferences"),
    @XmlEnumValue("PriceLevel")
    PRICE_LEVEL("PriceLevel"),
    @XmlEnumValue("Purchase")
    PURCHASE("Purchase"),
    @XmlEnumValue("PurchaseOrder")
    PURCHASE_ORDER("PurchaseOrder"),
    @XmlEnumValue("RefundReceipt")
    REFUND_RECEIPT("RefundReceipt"),
    @XmlEnumValue("ReimburseCharge")
    REIMBURSE_CHARGE("ReimburseCharge"),
    @XmlEnumValue("Report")
    REPORT("Report"),
    @XmlEnumValue("SalesOrder")
    SALES_ORDER("SalesOrder"),
    @XmlEnumValue("SalesReceipt")
    SALES_RECEIPT("SalesReceipt"),
    @XmlEnumValue("SalesRep")
    SALES_REP("SalesRep"),
    @XmlEnumValue("ShipMethod")
    SHIP_METHOD("ShipMethod"),
    @XmlEnumValue("StatementCharge")
    STATEMENT_CHARGE("StatementCharge"),
    @XmlEnumValue("TaxCode")
    TAX_CODE("TaxCode"),
    @XmlEnumValue("TaxClassification")
    TAX_CLASSIFICATION("TaxClassification"),
    @XmlEnumValue("TaxRate")
    TAX_RATE("TaxRate"),
    @XmlEnumValue("TaxReturn")
    TAX_RETURN("TaxReturn"),
    @XmlEnumValue("Term")
    TERM("Term"),
    @XmlEnumValue("TimeActivity")
    TIME_ACTIVITY("TimeActivity"),
    @XmlEnumValue("Transfer")
    TRANSFER("Transfer"),
    @XmlEnumValue("Transaction")
    TRANSACTION("Transaction"),
    @XmlEnumValue("TxnLocation")
    TXN_LOCATION("TxnLocation"),
    UOM("UOM"),
    @XmlEnumValue("Vendor")
    VENDOR("Vendor"),
    @XmlEnumValue("VendorCredit")
    VENDOR_CREDIT("VendorCredit"),
    @XmlEnumValue("CustomFieldDefinition")
    CUSTOM_FIELD_DEFINITION("CustomFieldDefinition");
    private final String value;

    ObjectNameEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectNameEnumType fromValue(String v) {
        for (ObjectNameEnumType c: ObjectNameEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
