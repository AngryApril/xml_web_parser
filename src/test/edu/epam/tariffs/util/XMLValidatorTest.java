package test.edu.epam.tariffs.util;

import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.util.XMLValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static test.edu.epam.tariffs.DataForTests.INCORRECT_TARIFF_TYPE;
import static test.edu.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;


public class XMLValidatorTest {

    private static XMLValidator xmlValidator;

    private final static String XSD_SCHEMA_FILE_PATH = "./src/main/resources/schema.xsd";

    @BeforeClass
    public static void setXmlValidator() {
        xmlValidator = new XMLValidator();
    }

    @DataProvider
    public static Object[][] incorrectInputParameters() {
        String nullXMLFilePath = null;
        String emptyXMLFileParh = "";
        String nullXSDFilePath = null;
        String emptyXSDFilePath = "";

        return new Object[][]{
                {nullXMLFilePath, XSD_SCHEMA_FILE_PATH},
                {emptyXMLFileParh, XSD_SCHEMA_FILE_PATH},
                {VALID_DATA_FILE_PATH, nullXSDFilePath},
                {VALID_DATA_FILE_PATH, emptyXSDFilePath}
        };

    }

    @DataProvider
    public static Object[][] notValidXMLFiles() {
        String incorrectAttributeOperator = "./src/test/resources/incorrect_attribute_operator.xml";
        String incorrectAttributeName = "./src/test/resources/incorrect_attribute_name.xml";
        String missingAttribute = "./src/test/resources/missing_attribute.xml";

        return new Object[][]{
                {incorrectAttributeName},
                {missingAttribute},
                {incorrectAttributeOperator},
                {INCORRECT_TARIFF_TYPE}
        };
    }


    @Test
    public void shouldValidationBeSuccessful() throws IncorrectFileException {
        boolean validationResult = xmlValidator.validateXMLFIle(VALID_DATA_FILE_PATH, XSD_SCHEMA_FILE_PATH);

        Assert.assertTrue(validationResult);
    }

    @Test(dataProvider = "notValidXMLFiles")
    public void shouldValidationBeNotSuccessful(String xmlFilePath) throws IncorrectFileException {
        boolean validationResult = xmlValidator.validateXMLFIle(xmlFilePath, XSD_SCHEMA_FILE_PATH);

        Assert.assertFalse(validationResult);
    }

    @Test(expectedExceptions = IncorrectFileException.class)
    public void shouldValidationCauseIncorrectFileException() throws IncorrectFileException {
        String incorrectFilePath = ".src/test/resources/incorrect_attribute_smsPrice.xml";

        xmlValidator.validateXMLFIle(incorrectFilePath, XSD_SCHEMA_FILE_PATH);
    }
}
