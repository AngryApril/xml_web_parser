package test.edu.epam.tariffs.util.parsers;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.sax.SAXParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.edu.epam.tariffs.DataForTests;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static test.edu.epam.tariffs.DataForTests.INCORRECT_FILE_PATH;
import static test.edu.epam.tariffs.DataForTests.INCORRECT_TARIFF_TYPE;
import static test.edu.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;

public class SAXParserTest {

    private static Tariffs validTariffs;
    private static SAXParser saxParser;

    @BeforeClass
    public static void setTestingObjects() {
        validTariffs = DataForTests.getTariffs();
        saxParser = new SAXParser();
    }

    @Test
    public void shouldParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs actualTariffs = saxParser.parse(VALID_DATA_FILE_PATH);

        assertEquals(validTariffs, actualTariffs);
    }

    @Test
    public void shouldParsingBeNotSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs actualTariffs = saxParser.parse(INCORRECT_TARIFF_TYPE);

        assertNotEquals(validTariffs, actualTariffs);
    }

    @Test(expectedExceptions = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        saxParser.parse(INCORRECT_FILE_PATH);
    }

}
