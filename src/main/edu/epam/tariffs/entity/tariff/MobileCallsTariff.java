package main.edu.epam.tariffs.entity.tariff;

import main.edu.epam.tariffs.entity.CallPrices;
import main.edu.epam.tariffs.entity.Operator;
import main.edu.epam.tariffs.entity.Parameters;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by alexey.valiev on 6/13/19.
 */

@XmlRootElement(name = "MobileCallsTariff")
public class MobileCallsTariff extends Tariff {


    public MobileCallsTariff() {
    }

    public MobileCallsTariff(Tariff tariff) {
        super(tariff);
    }

    public MobileCallsTariff(String tariffName, String tariffID, Operator operator,
                             CallPrices callPrices, Double payroll, Double smsPrice,
                             Parameters parameters) {
        super(tariffName, tariffID, operator, callPrices, payroll, smsPrice, parameters);
    }

    @Override
    public String toString() {
        return "MobileCallsTariff{}" + super.toString();
    }
}
