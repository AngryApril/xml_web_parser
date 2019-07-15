package main.edu.epam.tariffs.parser.dom.tariffbuilder;

import main.edu.epam.tariffs.entity.CallPrices;
import main.edu.epam.tariffs.entity.Operator;
import main.edu.epam.tariffs.entity.Parameters;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.log4j.*;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class TariffBuilder {

    private static final Logger logger = Logger.getLogger(TariffBuilder.class);


    public Tariff buildTariff(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        //NodeList nodeList = element.getElementsByTagName(TARIFF_ID_ELEMENT_NAME);


        String tariffName = element.getAttribute(TARIFF_NAME_ELEMENT_NAME);
        String tariffID = injectValueFromElement(element, TARIFF_ID_ELEMENT_NAME);
        Double payroll = injectDoubleValueFromElement(element, PAYROLL_ELEMENT_NAME);
        Double smsPrice = injectDoubleValueFromElement(element, SMS_PRICE_ELEMENT_NAME);
        Operator operator = (Operator) injectEnumValueFromElement(element, OPERATOR_ELEMENT_NAME, Operator.class);

        NodeList callPricesElements = element.getElementsByTagName(CALL_PRICES_ELEMENT_NAME);
        Element callPricesElement = (Element) callPricesElements.item(CURRENT_ELEMENT_INDEX);
        CallPricesBuilder callPricesBuilder = new CallPricesBuilder();
        CallPrices callPrices = callPricesBuilder.buildCallPrices(callPricesElement);

        NodeList parametersElements = element.getElementsByTagName(PARAMETERS_ELEMENT_NAME);
        Element parametersElement = (Element) parametersElements.item(CURRENT_ELEMENT_INDEX);
        ParametersBuilder parametersBuilder = new ParametersBuilder();
        Parameters parameters = parametersBuilder.buildParameters(parametersElement);

        Tariff tariff = new Tariff() {
        };

        tariff.setTariffName(tariffName);
        tariff.setTariffID(tariffID);
        tariff.setPayroll(payroll);
        tariff.setSmsPrice(smsPrice);
        tariff.setOperator(operator);
        tariff.setCallPrices(callPrices);
        tariff.setParameters(parameters);

        return tariff;
    }
}
