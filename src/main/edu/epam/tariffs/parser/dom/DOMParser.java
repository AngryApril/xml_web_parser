package main.edu.epam.tariffs.parser.dom;

import main.edu.epam.tariffs.entity.Tariffs;
import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.MobileCallsTariff;
import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.Parser;
import main.edu.epam.tariffs.parser.dom.tariffbuilder.InternetIncludedTariffBuilder;
import main.edu.epam.tariffs.parser.dom.tariffbuilder.MobileCallsTariffBuilder;
import main.edu.epam.tariffs.parser.dom.tariffbuilder.RoamingTariffBuilder;
import org.apache.log4j.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class DOMParser implements Parser {

    private static final Logger logger = Logger.getLogger(DOMParser.class);

    @Override
    public Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        logger.info("Start DOM parsing.");

        try {

            List<Tariff> listOfTariffs = new ArrayList<>();
            Tariffs tariffs = new Tariffs();

            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            Element root = document.getDocumentElement();
            NodeList internetIncludedTariffsListFromXml = root.getElementsByTagName(INTERNET_INCLUDED_ELEMENT_NAME);
            NodeList mobileCallsTariffsListFromXml = root.getElementsByTagName(MOBILE_CALLS_TARIFF_ELEMENT_NAME);
            NodeList roamingTariffsListFromXml = root.getElementsByTagName(ROAMING_TARIFF_ELEMENT_NAME);

            List<Tariff> internetIncludedTariffs =
                    ejectTariffsFromNodeList(internetIncludedTariffsListFromXml, INTERNET_INCLUDED_ELEMENT_NAME);
            List<Tariff> mobileCallsTariffs = ejectTariffsFromNodeList(mobileCallsTariffsListFromXml, MOBILE_CALLS_TARIFF_ELEMENT_NAME);
            List<Tariff> roamingTariffs = ejectTariffsFromNodeList(roamingTariffsListFromXml, ROAMING_TARIFF_ELEMENT_NAME);

            listOfTariffs.addAll(internetIncludedTariffs);
            listOfTariffs.addAll(mobileCallsTariffs);
            listOfTariffs.addAll(roamingTariffs);

            tariffs.setTariffList(listOfTariffs);

            logger.info("DOM parsing was made successfully.");

            return tariffs;

        } catch (ParserConfigurationException | SAXException exception) {
            throw new XMLParserException("DOM parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
        }

    }

    private List<Tariff> ejectTariffsFromNodeList(NodeList nodeList, String tariffXmlType) {
        List<Tariff> tariffs = new ArrayList<>();

        for (int listIndex = 0; listIndex < nodeList.getLength(); listIndex++) {
            Element tariffElement = (Element) nodeList.item(listIndex);

            if (tariffXmlType.equals(ROAMING_TARIFF_ELEMENT_NAME)) {
                RoamingTariffBuilder roamingTariffBuilder = new RoamingTariffBuilder();
                RoamingTariff roamingTariff = roamingTariffBuilder.buildRoamingTariff(tariffElement);

                tariffs.add(roamingTariff);
            }

            if (tariffXmlType.equals(INTERNET_INCLUDED_ELEMENT_NAME)) {
                InternetIncludedTariffBuilder internetIncludedTariffBuilder = new InternetIncludedTariffBuilder();
                InternetIncludedTariff internetIncludedTariff = internetIncludedTariffBuilder.buildInternetIncludedTariff(tariffElement);

                tariffs.add(internetIncludedTariff);
            }

            if (tariffXmlType.equals(MOBILE_CALLS_TARIFF_ELEMENT_NAME)) {
                MobileCallsTariffBuilder mobileCallsTariffBuilder = new MobileCallsTariffBuilder();
                MobileCallsTariff mobileCallsTariff = mobileCallsTariffBuilder.buildMobileCallsTariff(tariffElement);

                tariffs.add(mobileCallsTariff);
            }

        }

        return tariffs;
    }

}
