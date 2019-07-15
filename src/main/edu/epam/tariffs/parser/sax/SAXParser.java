package main.edu.epam.tariffs.parser.sax;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SAXParser implements Parser {

    @Override
    public Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        try {
            TariffsHandler tariffHandler = new TariffsHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(tariffHandler);
            reader.parse(xmlFilePath);

            Tariffs tariffs = new Tariffs();
            List<Tariff> listOfTariffs = tariffHandler.getListOfTariffs();
            tariffs.setTariffList(listOfTariffs);

            return tariffs;

        } catch (SAXException exception) {
            throw new XMLParserException("SAX parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
        }
    }
}
