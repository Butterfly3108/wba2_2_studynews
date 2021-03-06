//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.17 at 10:35:41 AM MESZ 
//


package jaxb.module;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="zustand" type="{}st_zustand"/>
 *         &lt;element name="bezeichnung" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="aufwand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="termin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="beschreibung" type="{}ct_beschreibung"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "zustand",
    "bezeichnung",
    "status",
    "aufwand",
    "termin",
    "beschreibung"
})
@XmlRootElement(name = "modul")
public class Modul {

    @XmlElement(required = true)
    protected StZustand zustand;
    @XmlElement(required = true)
    protected String bezeichnung;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected String aufwand;
    @XmlElement(required = true)
    protected String termin;
    @XmlElement(required = true)
    protected CtBeschreibung beschreibung;
    @XmlAttribute
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger id;

    /**
     * Gets the value of the zustand property.
     * 
     * @return
     *     possible object is
     *     {@link StZustand }
     *     
     */
    public StZustand getZustand() {
        return zustand;
    }

    /**
     * Sets the value of the zustand property.
     * 
     * @param value
     *     allowed object is
     *     {@link StZustand }
     *     
     */
    public void setZustand(StZustand value) {
        this.zustand = value;
    }

    /**
     * Gets the value of the bezeichnung property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Sets the value of the bezeichnung property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBezeichnung(String value) {
        this.bezeichnung = value;
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
     * Gets the value of the aufwand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAufwand() {
        return aufwand;
    }

    /**
     * Sets the value of the aufwand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAufwand(String value) {
        this.aufwand = value;
    }

    /**
     * Gets the value of the termin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermin() {
        return termin;
    }

    /**
     * Sets the value of the termin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermin(String value) {
        this.termin = value;
    }

    /**
     * Gets the value of the beschreibung property.
     * 
     * @return
     *     possible object is
     *     {@link CtBeschreibung }
     *     
     */
    public CtBeschreibung getBeschreibung() {
        return beschreibung;
    }

    /**
     * Sets the value of the beschreibung property.
     * 
     * @param value
     *     allowed object is
     *     {@link CtBeschreibung }
     *     
     */
    public void setBeschreibung(CtBeschreibung value) {
        this.beschreibung = value;
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
