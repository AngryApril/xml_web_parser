package main.edu.epam.tariffs.parser.sax;

import main.edu.epam.tariffs.entity.CallPrices;
import main.edu.epam.tariffs.entity.Operator;
import main.edu.epam.tariffs.entity.Parameters;
import main.edu.epam.tariffs.entity.Tariffication;
import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.MobileCallsTariff;
import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import org.apache.log4j.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class TariffsHandler extends DefaultHandler {

    private static final Logger logger = Logger.getLogger(TariffsHandler.class);

    private static final int FIRST_ATTRIBUTE_INDEX = 0;
    private static final int SECOND_ATTRIBUTE_INDEX = 1;
    private static final int THIRD_ATTRIBUTE_INDEX = 2;

    private List<Tariff> listOfTariffs;
    private Tariff currentTariff;
    private String currentElementName;

    public TariffsHandler() {
        this.listOfTariffs = new ArrayList<>();
    }

    @Override
    public void startDocument() {
        logger.info("Start SAX parsing.");
    }

    @Override
    public void endDocument() {
        logger.info("SAX parsing was made successfully.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        switch (localName) {
            case MOBILE_CALLS_TARIFF_ELEMENT_NAME: {
                Tariff tariff = buildAbstractTariff();
                currentTariff = new MobileCallsTariff(tariff);

                String tariffName = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                currentTariff.setTariffName(tariffName);

                break;
            }
            case ROAMING_TARIFF_ELEMENT_NAME: {
                Tariff tariff = buildAbstractTariff();
                currentTariff = new RoamingTariff(tariff);

                String tariffName = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                currentTariff.setTariffName(tariffName);

                break;
            }
            case INTERNET_INCLUDED_ELEMENT_NAME: {
                Tariff tariff = buildAbstractTariff();
                currentTariff = new InternetIncludedTariff(tariff);

                String tariffName = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                currentTariff.setTariffName(tariffName);

                break;
            }
            case CALL_PRICES_ELEMENT_NAME: {
                String innerCallPriceValue = attributes.getValue(FIRST_ATTRIBUTE_INDEX);
                Double innerCallPrice = Double.parseDouble(innerCallPriceValue);
                String outerCallPriceValue = attributes.getValue(SECOND_ATTRIBUTE_INDEX);
                Double outerCallPrice = Double.parseDouble(outerCallPriceValue);
                String cityCallPriceValue = attributes.getValue(THIRD_ATTRIBUTE_INDEX);
                Double cityCallPrice = Double.parseDouble(cityCallPriceValue);

                CallPrices callPrices = currentTariff.getCallPrices();
                callPrices.setInnerCallPrice(innerCallPrice);
                callPrices.setOuterCallPrice(outerCallPrice);
                callPrices.setCityCallPrice(cityCallPrice);

                break;
            }
            default: {
                currentElementName = localName;

                break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ROAMING_TARIFF_ELEMENT_NAME.equals(localName) || INTERNET_INCLUDED_ELEMENT_NAME.equals(localName)
                || MOBILE_CALLS_TARIFF_ELEMENT_NAME.equals(localName)) {
            listOfTariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();
        if (currentElementName != null && !value.isEmpty()) {

            switch (currentElementName) {

                case FAVORITE_NUMBERS_AVAILABLE_ELEMENT_NAME: {
                    int favoriteNumbersAvailable = Integer.parseInt(value);
                    Parameters parameters = currentTariff.getParameters();
                    parameters.setFavoriteNumbersAvailable(favoriteNumbersAvailable);

                    break;
                }
                case TARIFFICATION_ELEMENT_NAME: {
                    Tariffication tariffication = Tariffication.valueOf(value);
                    Parameters parameters = currentTariff.getParameters();
                    parameters.setTariffication(tariffication);

                    break;
                }
                case CONNECT_PRICE_ELEMENT_NAME: {
                    Double connectPrice = Double.parseDouble(value);
                    Parameters parameters = currentTariff.getParameters();
                    parameters.setConnectPrice(connectPrice);

                    break;
                }
                case PAYROLL_ELEMENT_NAME: {
                    Double payroll = Double.parseDouble(value);
                    currentTariff.setPayroll(payroll);
                    break;
                }
                case SMS_PRICE_ELEMENT_NAME: {
                    Double smsPrice = Double.parseDouble(value);
                    currentTariff.setSmsPrice(smsPrice);

                    break;
                }
                case OPERATOR_ELEMENT_NAME: {
                    Operator operator = Operator.valueOf(value);
                    currentTariff.setOperator(operator);

                    break;
                }
                case GIGABYTE_COUNT_ELEMENT_NAME: {
                    Integer gigaByteCount = Integer.parseInt(value);
                    try{
                        InternetIncludedTariff internetIncludedTariff = (InternetIncludedTariff) currentTariff;
                        internetIncludedTariff.setGigaByteCount(gigaByteCount);
                    }
                    catch (ClassCastException e){
                        logger.error(e.getMessage(), e);
                        RoamingTariff roamingTariff = (RoamingTariff) currentTariff;
                        roamingTariff.setGigaByteCount(gigaByteCount);
                    }

                    break;
                }
                case INTERNATIONAL_CALL_PRICE_ELEMENT_NAME: {
                    Double internationalCallPrice = Double.parseDouble(value);
                    RoamingTariff roamingTariff = (RoamingTariff) currentTariff;
                    roamingTariff.setInternationalCallPrice(internationalCallPrice);

                    break;
                }
                case TARIFF_ID_ELEMENT_NAME: {
                    currentTariff.setTariffID(value);

                    break;
                }

            }

        }
    }

    private Tariff buildAbstractTariff() {
        Tariff abstractTariff = new Tariff() {
        };
        CallPrices callPrices = new CallPrices();
        Parameters parameters = new Parameters();

        abstractTariff.setCallPrices(callPrices);
        abstractTariff.setParameters(parameters);

        return abstractTariff;
    }

    public List<Tariff> getListOfTariffs() {
        return listOfTariffs;
    }
}
