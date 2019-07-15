package test.edu.epam.tariffs.util.parsers;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.dom.DOMParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.edu.epam.tariffs.DataForTests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static test.edu.epam.tariffs.DataForTests.INCORRECT_FILE_PATH;
import static test.edu.epam.tariffs.DataForTests.INCORRECT_TARIFF_TYPE;
import static test.edu.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;

public class DOMParserTest {
    private static Tariffs validTariffs;
    private static DOMParser domParser;

    @BeforeClass
    public static void setTestingObjects() {
        validTariffs = DataForTests.getTariffs();
        domParser = new DOMParser();
    }

    @Test
    public void shouldParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs parsedTariffs = domParser.parse(VALID_DATA_FILE_PATH);

        assertEquals(validTariffs, parsedTariffs);
    }

    @Test
    public void shouldParsingBeNotSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs incorrectTariffs = domParser.parse(INCORRECT_TARIFF_TYPE);

        assertNotEquals(validTariffs, incorrectTariffs);
    }

    @Test(expectedExceptions = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        domParser.parse(INCORRECT_FILE_PATH);
    }
}
