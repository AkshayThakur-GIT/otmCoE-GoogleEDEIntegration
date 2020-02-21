
package com.insp.service.distengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.insp.service.distengine package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExtEngineAuxInput_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "extEngineAuxInput");
    private final static QName _Address_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "address");
    private final static QName _DestinationAddress_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "destinationAddress");
    private final static QName _ExtEngineAuxValueList_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "extEngineAuxValueList");
    private final static QName _Accuracy_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "accuracy");
    private final static QName _RailSplc_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "railSplc");
    private final static QName _Latitude_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "latitude");
    private final static QName _ShortPostalCode_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "shortPostalCode");
    private final static QName _ExtEngineException_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "ExtEngineException");
    private final static QName _CountyCode_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "countyCode");
    private final static QName _EdeParam_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "edeParam");
    private final static QName _PossibleMatch_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "possibleMatch");
    private final static QName _SeqNumber_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "seqNumber");
    private final static QName _CountryCode_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "countryCode");
    private final static QName _Uom_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "uom");
    private final static QName _Amount_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "amount");
    private final static QName _AddressLine_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "addressLine");
    private final static QName _PostalCode_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "postalCode");
    private final static QName _SourceAddress_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "sourceAddress");
    private final static QName _RailStationCode_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "railStationCode");
    private final static QName _Province_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "province");
    private final static QName _Value_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "value");
    private final static QName _Longitude_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "longitude");
    private final static QName _ProvinceCode_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "provinceCode");
    private final static QName _ValidFlag_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "validFlag");
    private final static QName _City_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "city");
    private final static QName _LocationName_QNAME = new QName("http://xmlns.oracle.com/apps/otm/distanceengine", "locationName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.insp.service.distengine
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LocationAddressMatches }
     * 
     */
    public LocationAddressMatches createLocationAddressMatches() {
        return new LocationAddressMatches();
    }

    /**
     * Create an instance of {@link PossibleMatches }
     * 
     */
    public PossibleMatches createPossibleMatches() {
        return new PossibleMatches();
    }

    /**
     * Create an instance of {@link ExtEngineAddress }
     * 
     */
    public ExtEngineAddress createExtEngineAddress() {
        return new ExtEngineAddress();
    }

    /**
     * Create an instance of {@link Values }
     * 
     */
    public Values createValues() {
        return new Values();
    }

    /**
     * Create an instance of {@link ExtEngineAuxInputList }
     * 
     */
    public ExtEngineAuxInputList createExtEngineAuxInputList() {
        return new ExtEngineAuxInputList();
    }

    /**
     * Create an instance of {@link KeyValue }
     * 
     */
    public KeyValue createKeyValue() {
        return new KeyValue();
    }

    /**
     * Create an instance of {@link KeyValues }
     * 
     */
    public KeyValues createKeyValues() {
        return new KeyValues();
    }

    /**
     * Create an instance of {@link ExtEngineDistance }
     * 
     */
    public ExtEngineDistance createExtEngineDistance() {
        return new ExtEngineDistance();
    }

    /**
     * Create an instance of {@link AddressLines }
     * 
     */
    public AddressLines createAddressLines() {
        return new AddressLines();
    }

    /**
     * Create an instance of {@link EdeParams }
     * 
     */
    public EdeParams createEdeParams() {
        return new EdeParams();
    }

    /**
     * Create an instance of {@link ValidateAddressRequest }
     * 
     */
    public ValidateAddressRequest createValidateAddressRequest() {
        return new ValidateAddressRequest();
    }

    /**
     * Create an instance of {@link LookupDistanceRequest }
     * 
     */
    public LookupDistanceRequest createLookupDistanceRequest() {
        return new LookupDistanceRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "extEngineAuxInput")
    public JAXBElement<KeyValue> createExtEngineAuxInput(KeyValue value) {
        return new JAXBElement<KeyValue>(_ExtEngineAuxInput_QNAME, KeyValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtEngineAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "address")
    public JAXBElement<ExtEngineAddress> createAddress(ExtEngineAddress value) {
        return new JAXBElement<ExtEngineAddress>(_Address_QNAME, ExtEngineAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtEngineAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "destinationAddress")
    public JAXBElement<ExtEngineAddress> createDestinationAddress(ExtEngineAddress value) {
        return new JAXBElement<ExtEngineAddress>(_DestinationAddress_QNAME, ExtEngineAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyValues }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "extEngineAuxValueList")
    public JAXBElement<KeyValues> createExtEngineAuxValueList(KeyValues value) {
        return new JAXBElement<KeyValues>(_ExtEngineAuxValueList_QNAME, KeyValues.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "accuracy")
    public JAXBElement<String> createAccuracy(String value) {
        return new JAXBElement<String>(_Accuracy_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "railSplc")
    public JAXBElement<String> createRailSplc(String value) {
        return new JAXBElement<String>(_RailSplc_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "latitude")
    public JAXBElement<Double> createLatitude(Double value) {
        return new JAXBElement<Double>(_Latitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "shortPostalCode")
    public JAXBElement<String> createShortPostalCode(String value) {
        return new JAXBElement<String>(_ShortPostalCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "ExtEngineException")
    public JAXBElement<String> createExtEngineException(String value) {
        return new JAXBElement<String>(_ExtEngineException_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "countyCode")
    public JAXBElement<String> createCountyCode(String value) {
        return new JAXBElement<String>(_CountyCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyValue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "edeParam")
    public JAXBElement<KeyValue> createEdeParam(KeyValue value) {
        return new JAXBElement<KeyValue>(_EdeParam_QNAME, KeyValue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtEngineAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "possibleMatch")
    public JAXBElement<ExtEngineAddress> createPossibleMatch(ExtEngineAddress value) {
        return new JAXBElement<ExtEngineAddress>(_PossibleMatch_QNAME, ExtEngineAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "seqNumber")
    public JAXBElement<Integer> createSeqNumber(Integer value) {
        return new JAXBElement<Integer>(_SeqNumber_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "countryCode")
    public JAXBElement<String> createCountryCode(String value) {
        return new JAXBElement<String>(_CountryCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "uom")
    public JAXBElement<String> createUom(String value) {
        return new JAXBElement<String>(_Uom_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "amount")
    public JAXBElement<Double> createAmount(Double value) {
        return new JAXBElement<Double>(_Amount_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "addressLine")
    public JAXBElement<String> createAddressLine(String value) {
        return new JAXBElement<String>(_AddressLine_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "postalCode")
    public JAXBElement<String> createPostalCode(String value) {
        return new JAXBElement<String>(_PostalCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExtEngineAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "sourceAddress")
    public JAXBElement<ExtEngineAddress> createSourceAddress(ExtEngineAddress value) {
        return new JAXBElement<ExtEngineAddress>(_SourceAddress_QNAME, ExtEngineAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "railStationCode")
    public JAXBElement<String> createRailStationCode(String value) {
        return new JAXBElement<String>(_RailStationCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "province")
    public JAXBElement<String> createProvince(String value) {
        return new JAXBElement<String>(_Province_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "value")
    public JAXBElement<String> createValue(String value) {
        return new JAXBElement<String>(_Value_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "longitude")
    public JAXBElement<Double> createLongitude(Double value) {
        return new JAXBElement<Double>(_Longitude_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "provinceCode")
    public JAXBElement<String> createProvinceCode(String value) {
        return new JAXBElement<String>(_ProvinceCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "validFlag", defaultValue = "false")
    public JAXBElement<Boolean> createValidFlag(Boolean value) {
        return new JAXBElement<Boolean>(_ValidFlag_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "city")
    public JAXBElement<String> createCity(String value) {
        return new JAXBElement<String>(_City_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.oracle.com/apps/otm/distanceengine", name = "locationName")
    public JAXBElement<String> createLocationName(String value) {
        return new JAXBElement<String>(_LocationName_QNAME, String.class, null, value);
    }

}
