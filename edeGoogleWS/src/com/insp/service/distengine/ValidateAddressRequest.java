
package com.insp.service.distengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}address"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}extEngineAuxInputList"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}edeParams"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "address",
    "extEngineAuxInputList",
    "edeParams"
})
@XmlRootElement(name = "validateAddressRequest")
public class ValidateAddressRequest {

    @XmlElement(required = true)
    protected ExtEngineAddress address;
    @XmlElement(required = true)
    protected ExtEngineAuxInputList extEngineAuxInputList;
    @XmlElement(required = true)
    protected EdeParams edeParams;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link ExtEngineAddress }
     *     
     */
    public ExtEngineAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtEngineAddress }
     *     
     */
    public void setAddress(ExtEngineAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the extEngineAuxInputList property.
     * 
     * @return
     *     possible object is
     *     {@link ExtEngineAuxInputList }
     *     
     */
    public ExtEngineAuxInputList getExtEngineAuxInputList() {
        return extEngineAuxInputList;
    }

    /**
     * Sets the value of the extEngineAuxInputList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtEngineAuxInputList }
     *     
     */
    public void setExtEngineAuxInputList(ExtEngineAuxInputList value) {
        this.extEngineAuxInputList = value;
    }

    /**
     * Gets the value of the edeParams property.
     * 
     * @return
     *     possible object is
     *     {@link EdeParams }
     *     
     */
    public EdeParams getEdeParams() {
        return edeParams;
    }

    /**
     * Sets the value of the edeParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link EdeParams }
     *     
     */
    public void setEdeParams(EdeParams value) {
        this.edeParams = value;
    }

}
