//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.24 at 05:46:34 AM MESZ 
//


package dozenten;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ct_newsticker complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ct_newsticker">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eintrag" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
 *                 &lt;attribute name="verfasser" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="modul" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "ct_newsticker", propOrder = {
    "eintrag"
})
public class CtNewsticker {

    protected List<CtNewsticker.Eintrag> eintrag;

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
     * {@link CtNewsticker.Eintrag }
     * 
     * 
     */
    public List<CtNewsticker.Eintrag> getEintrag() {
        if (eintrag == null) {
            eintrag = new ArrayList<CtNewsticker.Eintrag>();
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
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="datum" type="{http://www.w3.org/2001/XMLSchema}date" />
     *       &lt;attribute name="verfasser" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="modul" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Eintrag {

        @XmlValue
        protected String value;
        @XmlAttribute
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datum;
        @XmlAttribute
        protected String verfasser;
        @XmlAttribute
        protected String modul;

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
         * Gets the value of the datum property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatum() {
            return datum;
        }

        /**
         * Sets the value of the datum property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatum(XMLGregorianCalendar value) {
            this.datum = value;
        }

        /**
         * Gets the value of the verfasser property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVerfasser() {
            return verfasser;
        }

        /**
         * Sets the value of the verfasser property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVerfasser(String value) {
            this.verfasser = value;
        }

        /**
         * Gets the value of the modul property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModul() {
            return modul;
        }

        /**
         * Sets the value of the modul property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModul(String value) {
            this.modul = value;
        }

    }

}
