package main.edu.epam.tariffs.parser.dom.tariffbuilder;

import main.edu.epam.tariffs.entity.Parameters;
import main.edu.epam.tariffs.entity.Tariffication;
import main.edu.epam.tariffs.entity.tariff.MobileCallsTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;
import org.w3c.dom.Element;

import static main.edu.epam.tariffs.util.ValueInjector.*;

public class MobileCallsTariffBuilder {

    public MobileCallsTariff buildMobileCallsTariff(Element element) {
        TariffBuilder tariffBuilder = new TariffBuilder();
        Tariff tariff = tariffBuilder.buildTariff(element);

        MobileCallsTariff mobileCallsTariff = new MobileCallsTariff(tariff);

        return mobileCallsTariff;
    }

}
