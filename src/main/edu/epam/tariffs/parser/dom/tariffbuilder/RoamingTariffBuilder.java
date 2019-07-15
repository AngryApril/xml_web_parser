package main.edu.epam.tariffs.parser.dom.tariffbuilder;

import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import org.w3c.dom.Element;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class RoamingTariffBuilder {

    public RoamingTariff buildRoamingTariff(Element element) {
        TariffBuilder tariffBuilder = new TariffBuilder();
        Tariff tariff = tariffBuilder.buildTariff(element);

        RoamingTariff roamingTariff = new RoamingTariff(tariff);

        Integer gigaByteCount = injectIntegerValueFromElement(element, GIGABYTE_COUNT_ELEMENT_NAME);
        Double internationalCallPrice = injectDoubleValueFromElement(element, INTERNATIONAL_CALL_PRICE_ELEMENT_NAME);

        roamingTariff.setGigaByteCount(gigaByteCount);
        roamingTariff.setInternationalCallPrice(internationalCallPrice);

        return roamingTariff;
    }

}
