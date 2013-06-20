//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.17 at 08:34:34 PM MESZ 
//


package jaxb.payload;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="verfasser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="topic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nachricht" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "datum",
    "verfasser",
    "topic",
    "nachricht"
})
@XmlRootElement(name = "notification")
public class Notification {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(required = true)
    protected String verfasser;
    @XmlElement(required = true)
    protected String topic;
    @XmlElement(required = true)
    protected String nachricht;

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
     * Gets the value of the topic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the value of the topic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopic(String value) {
        this.topic = value;
    }

    /**
     * Gets the value of the nachricht property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNachricht() {
        return nachricht;
    }

    /**
     * Sets the value of the nachricht property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNachricht(String value) {
        this.nachricht = value;
    }

}
