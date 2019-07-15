package main.edu.epam.tariffs.util;

import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.*;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidator {

    private static final Logger logger = Logger.getLogger(XMLValidator.class);

    public boolean validateXMLFIle(String xmlFilePath, String xsdSchemaFilePath) throws IncorrectFileException, IllegalArgumentException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }
        if (xsdSchemaFilePath == null || xsdSchemaFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xsd schema file.");
        }

        String schemaLanguage = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaLanguage);
        File xsdSchemaFile = new File(xsdSchemaFilePath);

        try {
            Schema schema = schemaFactory.newSchema(xsdSchemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilePath);
            validator.validate(source);

            return true;

        } catch (SAXException exception) {
            logger.info("XML validation failed.", exception);

            return false;

        }
        catch (IOException exception) {
            throw new IncorrectFileException(exception);
        }
    }



}
