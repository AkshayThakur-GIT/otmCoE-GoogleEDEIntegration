
package com.insp.service.distengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for extEngineAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="extEngineAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}accuracy"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}addressLines"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}city"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}countryCode"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}countyCode"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}latitude"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}locationName"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}longitude"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}postalCode"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}province"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}provinceCode"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}railSplc"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}railStationCode"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}seqNumber"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}shortPostalCode"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extEngineAddress", propOrder = {
    "accuracy",
    "addressLines",
    "city",
    "countryCode",
    "countyCode",
    "latitude",
    "locationName",
    "longitude",
    "postalCode",
    "province",
    "provinceCode",
    "railSplc",
    "railStationCode",
    "seqNumber",
    "shortPostalCode"
})
public class ExtEngineAddress {

    @XmlElement(required = true)
    protected String accuracy;
    @XmlElement(required = true)
    protected AddressLines addressLines;
    @XmlElement(required = true)
    protected String city;
    @XmlElement(required = true)
    protected String countryCode;
    @XmlElement(required = true)
    protected String countyCode;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double latitude;
    @XmlElement(required = true)
    protected String locationName;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double longitude;
    @XmlElement(required = true)
    protected String postalCode;
    @XmlElement(required = true)
    protected String province;
    @XmlElement(required = true)
    protected String provinceCode;
    @XmlElement(required = true)
    protected String railSplc;
    @XmlElement(required = true)
    protected String railStationCode;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer seqNumber;
    @XmlElement(required = true)
    protected String shortPostalCode;

    /**
     * Gets the value of the accuracy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccuracy() {
        return accuracy;
    }

    /**
     * Sets the value of the accuracy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccuracy(String value) {
        this.accuracy = value;
    }

    /**
     * Gets the value of the addressLines property.
     * 
     * @return
     *     possible object is
     *     {@link AddressLines }
     *     
     */
    public AddressLines getAddressLines() {
        return addressLines;
    }

    /**
     * Sets the value of the addressLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressLines }
     *     
     */
    public void setAddressLines(AddressLines value) {
        this.addressLines = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the countyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * Sets the value of the countyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountyCode(String value) {
        this.countyCode = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLatitude(Double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLongitude(Double value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvince(String value) {
        this.province = value;
    }

    /**
     * Gets the value of the provinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the value of the provinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCode(String value) {
        this.provinceCode = value;
    }

    /**
     * Gets the value of the railSplc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRailSplc() {
        return railSplc;
    }

    /**
     * Sets the value of the railSplc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRailSplc(String value) {
        this.railSplc = value;
    }

    /**
     * Gets the value of the railStationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRailStationCode() {
        return railStationCode;
    }

    /**
     * Sets the value of the railStationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRailStationCode(String value) {
        this.railStationCode = value;
    }

    /**
     * Gets the value of the seqNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeqNumber() {
        return seqNumber;
    }

    /**
     * Sets the value of the seqNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeqNumber(Integer value) {
        this.seqNumber = value;
    }

    /**
     * Gets the value of the shortPostalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortPostalCode() {
        return shortPostalCode;
    }

    /**
     * Sets the value of the shortPostalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortPostalCode(String value) {
        this.shortPostalCode = value;
    }

}
