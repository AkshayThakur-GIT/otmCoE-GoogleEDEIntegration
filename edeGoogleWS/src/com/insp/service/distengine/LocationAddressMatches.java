
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
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}possibleMatches"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}validFlag"/>
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
    "possibleMatches",
    "validFlag"
})
@XmlRootElement(name = "locationAddressMatches")
public class LocationAddressMatches {

    @XmlElement(required = true)
    protected PossibleMatches possibleMatches;
    @XmlElement(defaultValue = "false")
    protected boolean validFlag;

    /**
     * Gets the value of the possibleMatches property.
     * 
     * @return
     *     possible object is
     *     {@link PossibleMatches }
     *     
     */
    public PossibleMatches getPossibleMatches() {
        return possibleMatches;
    }

    /**
     * Sets the value of the possibleMatches property.
     * 
     * @param value
     *     allowed object is
     *     {@link PossibleMatches }
     *     
     */
    public void setPossibleMatches(PossibleMatches value) {
        this.possibleMatches = value;
    }

    /**
     * Gets the value of the validFlag property.
     * 
     */
    public boolean isValidFlag() {
        return validFlag;
    }

    /**
     * Sets the value of the validFlag property.
     * 
     */
    public void setValidFlag(boolean value) {
        this.validFlag = value;
    }

}
