package main.edu.epam.tariffs.parser.dom.tariffbuilder;

import main.edu.epam.tariffs.entity.CallPrices;
import org.w3c.dom.Element;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class CallPricesBuilder {

    public CallPrices buildCallPrices(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        CallPrices callPrices = new CallPrices();

        Double innerCallPrice = injectDoubleValueFromElement(element, INNER_CALLS_PRICE_ELEMENT_NAME);
        Double outerCallPrice = injectDoubleValueFromElement(element, OUTER_CALLS_PRICE_ELEMENT_NAME);
        Double cityCallPrice = injectDoubleValueFromElement(element, CITY_CALL_PRICE_ELEMENT_NAME);

        callPrices.setInnerCallPrice(innerCallPrice);
        callPrices.setOuterCallPrice(outerCallPrice);
        callPrices.setCityCallPrice(cityCallPrice);

        return callPrices;
    }

}
