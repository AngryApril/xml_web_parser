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

@XmlRootElement(name = "RoamingTariff")
public class RoamingTariff extends Tariff {

    @XmlElement(name = "gigaByteCount")
    private Integer gigaByteCount;
    @XmlElement(name = "internationalCallPrice")
    private Double internationalCallPrice;

    public RoamingTariff() {
    }

    public RoamingTariff(Tariff tariff) {
        super(tariff);
    }

    public RoamingTariff(String tariffName, String tariffID, Operator operator,
                         CallPrices callPrices, Double payroll, Double smsPrice,
                         Parameters parameters, Integer gigaByteCount, Double internationalCallPricePerMin) {
        super(tariffName, tariffID, operator, callPrices, payroll, smsPrice, parameters);
        this.gigaByteCount = gigaByteCount;
        this.internationalCallPrice = internationalCallPricePerMin;
    }

    public Integer getGigaByteCount() {
        return gigaByteCount;
    }

    public void setGigaByteCount(Integer gigaByteCount) {
        this.gigaByteCount = gigaByteCount;
    }

    public Double getInternationalCallPrice() {
        return internationalCallPrice;
    }

    public void setInternationalCallPrice(Double internationalCallPrice) {
        this.internationalCallPrice = internationalCallPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoamingTariff that = (RoamingTariff) o;
        return gigaByteCount == that.gigaByteCount &&
                Double.compare(that.internationalCallPrice, internationalCallPrice) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), gigaByteCount, internationalCallPrice);
    }

    @Override
    public String toString() {
        return "RoamingTariff{" +
                super.toString() +
                "gigaByteCount=" + gigaByteCount +
                ", internationalCallPrice=" + internationalCallPrice +
                '}';
    }
}
