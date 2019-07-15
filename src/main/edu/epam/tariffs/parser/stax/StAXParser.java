package main.edu.epam.tariffs.parser.stax;

import main.edu.epam.tariffs.entity.*;
import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.MobileCallsTariff;
import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import main.edu.epam.tariffs.exceptions.IncorrectFileException;
import main.edu.epam.tariffs.exceptions.XMLParserException;
import main.edu.epam.tariffs.parser.Parser;
import org.apache.log4j.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.edu.epam.tariffs.util.ValueInjector.*;


public class StAXParser implements Parser {

    private static final Logger logger = Logger.getLogger(StAXParser.class);

    public Tariffs parse(String xmlFilePath) throws XMLParserException, IncorrectFileException {
        if (xmlFilePath == null || xmlFilePath.isEmpty()) {
            throw new IllegalArgumentException("Incorrect path for xml file");
        }

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        List<Tariff> listOfTariffs = new ArrayList<>();

        logger.info("Start StAX parsing.");

        try (FileInputStream fileInputStream = new FileInputStream(xmlFilePath)) {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            while (xmlStreamReader.hasNext()) {
                int elementType = xmlStreamReader.next();

                if (elementType == XMLStreamConstants.START_ELEMENT) {
                    String currentElementName = xmlStreamReader.getLocalName();

                    switch (currentElementName) {
                        case INTERNET_INCLUDED_ELEMENT_NAME: {
                            InternetIncludedTariff internetIncludedTariff = readInternetIncludedTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(internetIncludedTariff);

                            break;
                        }
                        case MOBILE_CALLS_TARIFF_ELEMENT_NAME: {
                            MobileCallsTariff mobileCallsTariff = readMobileCallsTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(mobileCallsTariff);

                            break;
                        }
                        case ROAMING_TARIFF_ELEMENT_NAME: {
                            RoamingTariff roamingTariff = readRoamingTariffFromXml(xmlStreamReader);
                            listOfTariffs.add(roamingTariff);

                            break;
                        }
                    }
                }
            }

            Tariffs tariffs = new Tariffs();
            tariffs.setTariffList(listOfTariffs);

            logger.info("StAX parsing was made successfully.");

            return tariffs;

        } catch (XMLStreamException exception) {
            throw new XMLParserException("StAX parsing failed.", exception);
        } catch (IOException exception) {
            throw new IncorrectFileException("Something wrong with file.",exception);
        }
    }

    private InternetIncludedTariff readInternetIncludedTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = readAbstractTariffFromXml(reader);
        InternetIncludedTariff internetIncludedTariff = new InternetIncludedTariff(tariff);

        String currentElementName;

        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case GIGABYTE_COUNT_ELEMENT_NAME: {
                        Integer gigaByteCount = injectIntegerValueFromElement(reader);
                        internetIncludedTariff.setGigaByteCount(gigaByteCount);

                        break;
                    }
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();

                if (INTERNET_INCLUDED_ELEMENT_NAME.equals(currentElementName)) {
                    return internetIncludedTariff;
                }
            }
        }

        throw new XMLStreamException("Unknown element in tag InternetIncludedTariff.");
    }

    private MobileCallsTariff readMobileCallsTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = readAbstractTariffFromXml(reader);
        MobileCallsTariff mobileCallsTariff = new MobileCallsTariff(tariff);

        String currentElementName;

        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();

                if (MOBILE_CALLS_TARIFF_ELEMENT_NAME.equals(currentElementName)) {
                    return mobileCallsTariff;
                }
            }
        }

        throw new XMLStreamException("Unknown element in tag InternetForMobileTariff.");
    }

    private RoamingTariff readRoamingTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = readAbstractTariffFromXml(reader);
        RoamingTariff roamingTariff = new RoamingTariff(tariff);

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case GIGABYTE_COUNT_ELEMENT_NAME: {
                        Integer gigaByteCount = injectIntegerValueFromElement(reader);
                        roamingTariff.setGigaByteCount(gigaByteCount);

                        break;
                    }
                    case INTERNATIONAL_CALL_PRICE_ELEMENT_NAME: {
                        Double internationalCallPrice = injectDoubleValueFromElement(reader);
                        roamingTariff.setInternationalCallPrice(internationalCallPrice);

                        break;
                    }
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();

                if (ROAMING_TARIFF_ELEMENT_NAME.equals(currentElementName)) {
                    return roamingTariff;
                }
            }
        }

        throw new XMLStreamException("Unknown element in tag RoamingTariff.");
    }

    private Tariff readAbstractTariffFromXml(XMLStreamReader reader) throws XMLStreamException {
        Tariff abstractTariff = new Tariff() {
        };

        String tariffName = reader.getAttributeValue(null, TARIFF_NAME_ELEMENT_NAME);
        abstractTariff.setTariffName(tariffName);

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case TARIFF_ID_ELEMENT_NAME: {
                        String tariffID = reader.getElementText();
                        abstractTariff.setTariffID(tariffID);

                        break;
                    }
                    case SMS_PRICE_ELEMENT_NAME: {
                        Double smsPrice = injectDoubleValueFromElement(reader);
                        abstractTariff.setSmsPrice(smsPrice);

                        break;
                    }
                    case PAYROLL_ELEMENT_NAME: {
                        Double payroll = injectDoubleValueFromElement(reader);
                        abstractTariff.setPayroll(payroll);

                        break;
                    }
                    case OPERATOR_ELEMENT_NAME: {
                        Operator operator = (Operator) injectEnumValueFromElement(reader, Operator.class);
                        abstractTariff.setOperator(operator);

                        break;
                    }
                    case CALL_PRICES_ELEMENT_NAME: {
                        CallPrices callPrices = readCallPricesFromXml(reader);
                        abstractTariff.setCallPrices(callPrices);

                        break;
                    }
                    case PARAMETERS_ELEMENT_NAME: {
                        Parameters parameters = readParametersFromXml(reader);
                        abstractTariff.setParameters(parameters);

                        return abstractTariff;
                    }
                }

            }
        }

        throw new XMLStreamException("Unknown element in tag Tariff.");
    }

    private CallPrices readCallPricesFromXml(XMLStreamReader reader) {
        CallPrices callPrices = new CallPrices();

        Double inComingCallPerMinutePrice = injectDoubleValueFromElement(reader, INNER_CALLS_PRICE_ELEMENT_NAME);
        Double outComingCallPerMinutePrice = injectDoubleValueFromElement(reader, OUTER_CALLS_PRICE_ELEMENT_NAME);
        Double cityLineCallPerMinutePrice = injectDoubleValueFromElement(reader, CITY_CALL_PRICE_ELEMENT_NAME);

        callPrices.setInnerCallPrice(inComingCallPerMinutePrice);
        callPrices.setOuterCallPrice(outComingCallPerMinutePrice);
        callPrices.setCityCallPrice(cityLineCallPerMinutePrice);

        return callPrices;
    }

    private Parameters readParametersFromXml(XMLStreamReader reader) throws XMLStreamException {
        Parameters parameters = new Parameters();

        String currentElementName;
        while (reader.hasNext()) {
            int elementType = reader.next();

            if (elementType == XMLStreamConstants.START_ELEMENT) {
                currentElementName = reader.getLocalName();

                switch (currentElementName) {
                    case FAVORITE_NUMBERS_AVAILABLE_ELEMENT_NAME: {
                        int favoriteNumbersAvailable = injectIntegerValueFromElement(reader);
                        parameters.setFavoriteNumbersAvailable(favoriteNumbersAvailable);

                        break;
                    }
                    case TARIFFICATION_ELEMENT_NAME: {
                        Tariffication tariffication = (Tariffication) injectEnumValueFromElement(reader, Tariffication.class);
                        parameters.setTariffication(tariffication);

                        break;
                    }
                    case CONNECT_PRICE_ELEMENT_NAME: {
                        Double connectPrice = injectDoubleValueFromElement(reader);
                        parameters.setConnectPrice(connectPrice);

                        break;
                    }
                }
            } else if (elementType == XMLStreamConstants.END_ELEMENT) {
                currentElementName = reader.getLocalName();
                if (PARAMETERS_ELEMENT_NAME.equals(currentElementName)) {
                    return parameters;
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag Parameters.");
    }
}
