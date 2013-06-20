//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.20 at 11:48:38 PM MESZ 
//


package jaxb.userDatabase;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="eintrag" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nachname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="vorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
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
@XmlType(name = "", propOrder = {
    "eintrag"
})
@XmlRootElement(name = "userDatabase")
public class UserDatabase {

    protected List<UserDatabase.Eintrag> eintrag;

    /**
     * Gets the value of the eintrag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eintrag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEintrag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserDatabase.Eintrag }
     * 
     * 
     */
    public List<UserDatabase.Eintrag> getEintrag() {
        if (eintrag == null) {
            eintrag = new ArrayList<UserDatabase.Eintrag>();
        }
        return this.eintrag;
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
     *         &lt;element name="nachname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="vorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nachname",
        "vorname",
        "status"
    })
    public static class Eintrag {

        @XmlElement(required = true)
        protected String nachname;
        @XmlElement(required = true)
        protected String vorname;
        @XmlElement(required = true)
        protected String status;
        @XmlAttribute
        protected BigInteger id;

        /**
         * Gets the value of the nachname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNachname() {
            return nachname;
        }

        /**
         * Sets the value of the nachname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNachname(String value) {
            this.nachname = value;
        }

        /**
         * Gets the value of the vorname property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVorname() {
            return vorname;
        }

        /**
         * Sets the value of the vorname property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVorname(String value) {
            this.vorname = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

        /**
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setId(BigInteger value) {
            this.id = value;
        }

    }

}
