package test.edu.epam.tariffs.util.parsers;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.stax.StAXParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.edu.epam.tariffs.DataForTests;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static test.edu.epam.tariffs.DataForTests.INCORRECT_FILE_PATH;
import static test.edu.epam.tariffs.DataForTests.INCORRECT_TARIFF_TYPE;
import static test.edu.epam.tariffs.DataForTests.VALID_DATA_FILE_PATH;

public class StAXParserTest {

    private static Tariffs validTariffs;
    private static StAXParser stAXParser;

    @BeforeClass
    public static void setTestingObjects() {
        validTariffs = DataForTests.getTariffs();
        stAXParser = new StAXParser();
    }

    @Test
    public void shouldParsingBeSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs parsedTariffs = stAXParser.parse(VALID_DATA_FILE_PATH);

        assertEquals(validTariffs, parsedTariffs);
    }

    @Test
    public void shouldParsingBeNotSuccessful() throws IncorrectFileException, XMLParserException {
        Tariffs incorrectTariffs = stAXParser.parse(INCORRECT_TARIFF_TYPE);

        assertNotEquals(validTariffs, incorrectTariffs);
    }

    @Test(expectedExceptions = IncorrectFileException.class)
    public void shouldParsingCauseIncorrectFileException() throws IncorrectFileException, XMLParserException {
        stAXParser.parse(INCORRECT_FILE_PATH);
    }
}
