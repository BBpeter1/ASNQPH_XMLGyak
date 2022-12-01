package hu.domparse.asnqph;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {

public static void main(String[] args) {
		
		System.out.println("Validation succesful? "+validateXMLSchema("XMLSchemaASNQPH.xsd", "XMLASNQPH.xml"));

	} //end main
	
	public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("XMLSchemaASNQPH.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("XMLASNQPH.xml")));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

}
