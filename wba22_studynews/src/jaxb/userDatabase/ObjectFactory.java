//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.20 at 06:19:33 AM MESZ 
//


package jaxb.userDatabase;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.userDatabase package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.userDatabase
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UserDatabase }
     * 
     */
    public UserDatabase createUserDatabase() {
        return new UserDatabase();
    }

    /**
     * Create an instance of {@link Eintrag }
     * 
     */
    public Eintrag createEintrag() {
        return new Eintrag();
    }

}