//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.20 at 06:47:46 AM MESZ 
//


package jaxb.dozenten;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for ct_lehre complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ct_lehre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lehrgebiet" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="schwerpunkte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sprechzeit" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="veranstaltungen">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="list" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="modulId" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ct_lehre", propOrder = {
    "lehrgebiet",
    "schwerpunkte",
    "url",
    "sprechzeit",
    "veranstaltungen"
})
public class CtLehre {

    @XmlElement(required = true)
    protected String lehrgebiet;
    protected String schwerpunkte;
    @XmlElement(required = true)
    protected String url;
    protected java.util.List<String> sprechzeit;
    @XmlElement(required = true)
    protected CtLehre.Veranstaltungen veranstaltungen;

    /**
     * Gets the value of the lehrgebiet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLehrgebiet() {
        return lehrgebiet;
    }

    /**
     * Sets the value of the lehrgebiet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLehrgebiet(String value) {
        this.lehrgebiet = value;
    }

    /**
     * Gets the value of the schwerpunkte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchwerpunkte() {
        return schwerpunkte;
    }

    /**
     * Sets the value of the schwerpunkte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchwerpunkte(String value) {
        this.schwerpunkte = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the sprechzeit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sprechzeit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSprechzeit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public java.util.List<String> getSprechzeit() {
        if (sprechzeit == null) {
            sprechzeit = new ArrayList<String>();
        }
        return this.sprechzeit;
    }

    /**
     * Gets the value of the veranstaltungen property.
     * 
     * @return
     *     possible object is
     *     {@link CtLehre.Veranstaltungen }
     *     
     */
    public CtLehre.Veranstaltungen getVeranstaltungen() {
        return veranstaltungen;
    }

    /**
     * Sets the value of the veranstaltungen property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtLehre.Veranstaltungen }
     *     
     */
    public void setVeranstaltungen(CtLehre.Veranstaltungen value) {
        this.veranstaltungen = value;
    }


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
     *         &lt;element name="list" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="modulId" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "list"
    })
    public static class Veranstaltungen {

        protected java.util.List<CtLehre.Veranstaltungen.List> list;

        /**
         * Gets the value of the list property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the list property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CtLehre.Veranstaltungen.List }
         * 
         * 
         */
        public java.util.List<CtLehre.Veranstaltungen.List> getList() {
            if (list == null) {
                list = new ArrayList<CtLehre.Veranstaltungen.List>();
            }
            return this.list;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="modulId" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class List {

            @XmlValue
            protected String value;
            @XmlAttribute
            @XmlSchemaType(name = "nonNegativeInteger")
            protected BigInteger modulId;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the modulId property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getModulId() {
                return modulId;
            }

            /**
             * Sets the value of the modulId property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setModulId(BigInteger value) {
                this.modulId = value;
            }

        }

    }

}
