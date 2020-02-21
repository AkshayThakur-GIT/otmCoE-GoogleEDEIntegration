
package com.insp.service.distengine;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}extEngineAuxInput" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://xmlns.oracle.com/apps/otm/distanceengine}extEngineAuxValueList" maxOccurs="unbounded"/>
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
    "extEngineAuxInput",
    "extEngineAuxValueList"
})
@XmlRootElement(name = "extEngineAuxInputList")
public class ExtEngineAuxInputList {

    @XmlElement(required = true)
    protected List<KeyValue> extEngineAuxInput;
    @XmlElement(required = true)
    protected List<KeyValues> extEngineAuxValueList;

    /**
     * Gets the value of the extEngineAuxInput property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extEngineAuxInput property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtEngineAuxInput().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValue }
     * 
     * 
     */
    public List<KeyValue> getExtEngineAuxInput() {
        if (extEngineAuxInput == null) {
            extEngineAuxInput = new ArrayList<KeyValue>();
        }
        return this.extEngineAuxInput;
    }

    /**
     * Gets the value of the extEngineAuxValueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extEngineAuxValueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtEngineAuxValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValues }
     * 
     * 
     */
    public List<KeyValues> getExtEngineAuxValueList() {
        if (extEngineAuxValueList == null) {
            extEngineAuxValueList = new ArrayList<KeyValues>();
        }
        return this.extEngineAuxValueList;
    }

}
