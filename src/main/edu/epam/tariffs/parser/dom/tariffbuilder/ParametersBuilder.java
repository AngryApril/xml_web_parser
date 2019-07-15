package main.edu.epam.tariffs.parser.dom.tariffbuilder;

import main.edu.epam.tariffs.entity.Parameters;
import main.edu.epam.tariffs.entity.Tariffication;
import org.w3c.dom.Element;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class ParametersBuilder {

    public Parameters buildParameters(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Incorrect element was detected.");
        }

        Parameters parameters = new Parameters();

        Integer favoriteNumbersAvailable = injectIntegerValueFromElement(element, FAVORITE_NUMBERS_AVAILABLE_ELEMENT_NAME);
        Tariffication tariffication = (Tariffication) injectEnumValueFromElement(element, TARIFFICATION_ELEMENT_NAME, Tariffication.class);
        Double connectPrice = injectDoubleValueFromElement(element, CONNECT_PRICE_ELEMENT_NAME);

        parameters.setConnectPrice(connectPrice);
        parameters.setFavoriteNumbersAvailable(favoriteNumbersAvailable);
        parameters.setTariffication(tariffication);

        return parameters;
    }

}
