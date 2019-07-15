package main.edu.epam.tariffs.parser.dom.tariffbuilder;

import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import org.w3c.dom.Element;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class InternetIncludedTariffBuilder {

    public InternetIncludedTariff buildInternetIncludedTariff(Element element) {
        TariffBuilder tariffBuilder = new TariffBuilder();
        Tariff tariff = tariffBuilder.buildTariff(element);

        InternetIncludedTariff internetIncludedTariff = new InternetIncludedTariff(tariff);

        Integer gigaByteCount = injectIntegerValueFromElement(element, GIGABYTE_COUNT_ELEMENT_NAME);

        internetIncludedTariff.setGigaByteCount(gigaByteCount);

        return internetIncludedTariff;
    }

}
