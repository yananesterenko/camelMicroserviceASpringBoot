package com.yana.microservices.camelmicroservicesa.routes.dbconnection;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import java.io.IOException;

@Component
public class XmlValidator {

    public void validate(){
        File schemaFile = new File("C:\\Projects\\Json\\xsd\\schema.xsd"); // etc.
        {
            Source xmlFile = new StreamSource(new File("web.xml"));
            SchemaFactory schemaFactory = SchemaFactory
                    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                Schema schema = schemaFactory.newSchema(schemaFile);
                Validator validator = schema.newValidator();
                validator.validate(xmlFile);
                System.out.println(xmlFile.getSystemId() + " is valid");
            } catch (SAXException e) {
                System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
            } catch (IOException e) {}

        }
    }







}
