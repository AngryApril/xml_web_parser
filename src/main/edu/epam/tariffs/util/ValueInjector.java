package main.edu.epam.tariffs.util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ValueInjector {

    public static final String MOBILE_CALLS_TARIFF_ELEMENT_NAME = "MobileCallsTariff";

    public static final String ROAMING_TARIFF_ELEMENT_NAME = "RoamingTariff";
    public static final String INTERNATIONAL_CALL_PRICE_ELEMENT_NAME = "internationalCallPrice";

    public static final String INTERNET_INCLUDED_ELEMENT_NAME = "InternetIncludedTariff";
    public static final String GIGABYTE_COUNT_ELEMENT_NAME = "gigaByteCount";

    public static final String CALL_PRICES_ELEMENT_NAME = "callPrices";
    public static final String INNER_CALLS_PRICE_ELEMENT_NAME = "innerCallPrice";
    public static final String OUTER_CALLS_PRICE_ELEMENT_NAME = "outerCallPrice";
    public static final String CITY_CALL_PRICE_ELEMENT_NAME = "cityCallPrice";

    public static final String PARAMETERS_ELEMENT_NAME = "parameters";
    public static final String FAVORITE_NUMBERS_AVAILABLE_ELEMENT_NAME = "favoriteNumbersAvailable";
    public static final String TARIFFICATION_ELEMENT_NAME = "tariffication";
    public static final String CONNECT_PRICE_ELEMENT_NAME = "connectPrice";

    public static final String TARIFF_NAME_ELEMENT_NAME = "tariffName";
    public static final String TARIFF_ID_ELEMENT_NAME = "tariffID";
    public static final String PAYROLL_ELEMENT_NAME = "payroll";
    public static final String SMS_PRICE_ELEMENT_NAME = "smsPrice";
    public static final String OPERATOR_ELEMENT_NAME = "operator";
    public static final String TARIFF_ELEMENT_NAME = "Tariff";

    public static final int CURRENT_ELEMENT_INDEX = 0;

    public static String injectValueFromElement(Element element, String valueName) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }
        if (valueName == null || valueName.isEmpty()) {
            throw new IllegalArgumentException("Incorrect value name was detected.");
        }

        if (element.hasAttribute(valueName)) {
            String value = element.getAttribute(valueName);

            return value;
        } else {
            NodeList nList = element.getElementsByTagName(valueName);
            Node node = nList.item(CURRENT_ELEMENT_INDEX);
            String value = node.getTextContent();

            return value;
        }
    }

    public static String getValueFromXml(XMLStreamReader reader) throws XMLStreamException {
        String value = null;
        if (reader.hasNext()) {
            reader.next();
            value = reader.getText();
        }

        return value;
    }

    public static Double injectDoubleValueFromElement(Element element, String valueName) {
        String value = injectValueFromElement(element, valueName);
        Double result = Double.parseDouble(value);

        return result;
    }

    public static Double injectDoubleValueFromElement(XMLStreamReader reader) throws XMLStreamException {
        String value = getValueFromXml(reader);
        Double result = Double.parseDouble(value);

        return result;
    }

    public static Double injectDoubleValueFromElement(XMLStreamReader reader, String elementName) {
        String value = reader.getAttributeValue(null, elementName);
        Double result = Double.parseDouble(value);

        return result;
    }

    public static Integer injectIntegerValueFromElement(Element element, String valueName) {
        String value = injectValueFromElement(element, valueName);

        Integer result = Integer.parseInt(value);

        return result;
    }

    public static Integer injectIntegerValueFromElement(XMLStreamReader reader) throws XMLStreamException {
        String value = getValueFromXml(reader);
        Integer result = Integer.parseInt(value);

        return result;
    }

    public static Boolean injectBooleanValueFromElement(Element element, String valueName) {
        String value = injectValueFromElement(element, valueName);

        Boolean result = Boolean.parseBoolean(value);

        return result;
    }

    public static Boolean injectBooleanValueFromElement(XMLStreamReader reader) throws XMLStreamException {
        String value = getValueFromXml(reader);
        Boolean result = Boolean.parseBoolean(value);

        return result;
    }

    public static Boolean injectBooleanValueFromElement(XMLStreamReader reader, String elementName) {
        String value = reader.getAttributeValue(null, elementName);
        Boolean result = Boolean.parseBoolean(value);

        return result;
    }

    public static Enum injectEnumValueFromElement(Element element, String valueName, Class enumType) {
        String value = injectValueFromElement(element, valueName);
        Enum result = Enum.valueOf(enumType, value);

        return result;
    }

    public static Enum injectEnumValueFromElement(XMLStreamReader reader, Class enumType) throws XMLStreamException {
        String value = getValueFromXml(reader);
        Enum result = Enum.valueOf(enumType, value);

        return result;
    }

    public static Calendar injectDateValueFromElement(Element element, String valueName){
        String value = injectValueFromElement(element, valueName);
        Date date = Time.valueOf(value);
        Calendar result = new GregorianCalendar();
        result.setTime(date);

        return result;

    }

    public static Calendar injectDateValueFromElement(XMLStreamReader reader) throws XMLStreamException{
        String value = getValueFromXml(reader);
        Date date = Time.valueOf(value);
        Calendar result = new GregorianCalendar();
        result.setTime(date);

        return result;
    }
}