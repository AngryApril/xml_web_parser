package main.edu.epam.tariffs.entity.tariff;

import main.edu.epam.tariffs.entity.CallPrices;
import main.edu.epam.tariffs.entity.Operator;
import main.edu.epam.tariffs.entity.Parameters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by alexey.valiev on 6/13/19.
 */

@XmlRootElement(name = "InternetIncludedTariff")
public class InternetIncludedTariff extends Tariff {

    @XmlElement(name = "gigaByteCount")
    private Integer gigaByteCount;

    public InternetIncludedTariff() {
    }

    public InternetIncludedTariff(Tariff tariff) {
        super(tariff);
    }

    public InternetIncludedTariff(String tariffName, String tariffID, Operator operator,
                                  CallPrices callPrices, Double payroll, Double smsPrice,
                                  Parameters parameters, Integer gigaByteCount) {
        super(tariffName, tariffID, operator, callPrices, payroll, smsPrice, parameters);
        this.gigaByteCount = gigaByteCount;
    }

    public Integer getGigaByteCount() {
        return gigaByteCount;
    }

    public void setGigaByteCount(Integer gigaByteCount) {
        this.gigaByteCount = gigaByteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InternetIncludedTariff that = (InternetIncludedTariff) o;
        return gigaByteCount == that.gigaByteCount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), gigaByteCount);
    }

    @Override
    public String toString() {
        return "InternetIncludedTariff{" +
                super.toString() +
                "gigaByteCount=" + gigaByteCount +
                '}';
    }
}
