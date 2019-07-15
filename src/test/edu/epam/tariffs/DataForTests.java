package test.edu.epam.tariffs;

import main.edu.epam.tariffs.entity.*;
import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.MobileCallsTariff;
import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;

import java.util.ArrayList;
import java.util.List;

public class DataForTests {

    public static final String VALID_DATA_FILE_PATH = "./src/test/resources/tariffs.xml";
    public static final String INCORRECT_TARIFF_TYPE = "./src/test/resources/incorrect_tariff_type.xml";
    public static final String INCORRECT_FILE_PATH = "./src/test/resources/data5.xml";

    public static Tariffs getTariffs() {
        CallPrices callPrices = new CallPrices(0.9, 1.9, 1.9);
        Parameters parameters = new Parameters(0, Tariffication.PER_MINUTES, 0d);
        RoamingTariff roamingTariff = new RoamingTariff("Velcom-Roaming", "v325",
                Operator.VELCOM, callPrices, 0.0, 0.3, parameters, 10, 2.6);

        parameters = new Parameters(1, Tariffication.PER_MINUTES, 10.0);
        callPrices = new CallPrices(0.5, 0.9, 1.0);
        InternetIncludedTariff internetIncludedTariff = new InternetIncludedTariff("StarLight", "M321",
                Operator.MTS, callPrices, 0.0, 0.1, parameters, 50);

        parameters = new Parameters(3, Tariffication.PER_12_SECONDS, 0.0);
        callPrices = new CallPrices(0.1, 0.6, 1.0);
        InternetIncludedTariff internetIncludedTariffNumberThree = new InternetIncludedTariff("LifeStartsNow", "L323",
                Operator.LIFE, callPrices, 2.3, 0.1, parameters, 100);

        parameters = new Parameters(1, Tariffication.PER_MINUTES, 5.0);
        callPrices = new CallPrices(0.3, 1.2, 1.0);
        InternetIncludedTariff internetIncludedTariffNumberTwo = new InternetIncludedTariff("ComingHome", "v322",
                Operator.VELCOM, callPrices, 2.2, 0.2, parameters, 50);

        parameters = new Parameters(5, Tariffication.PER_MINUTES, 0.0);
        callPrices = new CallPrices(0.1, 0.5, 4.4);
        MobileCallsTariff mobileCallsTariff = new MobileCallsTariff("BackTo90s", "L324",
                Operator.LIFE, callPrices, 5.0, 0.1, parameters);


        List<Tariff> listOfTariffs = new ArrayList<>();

        listOfTariffs.add(internetIncludedTariff);
        listOfTariffs.add(internetIncludedTariffNumberTwo);
        listOfTariffs.add(internetIncludedTariffNumberThree);
        listOfTariffs.add(mobileCallsTariff);
        listOfTariffs.add(roamingTariff);


        Tariffs tariffs = new Tariffs(listOfTariffs);

        return tariffs;
    }
}
