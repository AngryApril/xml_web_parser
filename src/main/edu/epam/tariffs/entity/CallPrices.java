package main.edu.epam.tariffs.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by alexey.valiev on 6/11/19.
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class CallPrices {

    @XmlAttribute(name = "innerCallPrice",required = true)
    private Double innerCallPrice;
    @XmlAttribute(name = "outerCallPrice")
    private Double outerCallPrice;
    @XmlAttribute(name = "cityCallPrice", required = true)
    private Double cityCallPrice;

    public CallPrices() {
    }

    public CallPrices(Double innerCallPerMinutePrice, Double outerCallPerMinutePrice, Double cityCallPerMinutePrice) {
        this.innerCallPrice = innerCallPerMinutePrice;
        this.outerCallPrice = outerCallPerMinutePrice;
        this.cityCallPrice = cityCallPerMinutePrice;
    }

    public Double getInnerCallPrice() {
        return innerCallPrice;
    }

    public void setInnerCallPrice(Double innerCallPrice) {
        this.innerCallPrice = innerCallPrice;
    }

    public Double getOuterCallPrice() {
        return outerCallPrice;
    }

    public void setOuterCallPrice(Double outerCallPrice) {
        this.outerCallPrice = outerCallPrice;
    }

    public Double getCityCallPrice() {
        return cityCallPrice;
    }

    public void setCityCallPrice(Double cityCallPrice) {
        this.cityCallPrice = cityCallPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallPrices that = (CallPrices) o;
        return Objects.equals(innerCallPrice, that.innerCallPrice) &&
                Objects.equals(outerCallPrice, that.outerCallPrice) &&
                Objects.equals(cityCallPrice, that.cityCallPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(innerCallPrice, outerCallPrice, cityCallPrice);
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "innerCallPrice=" + innerCallPrice +
                ", outerCallPrice=" + outerCallPrice +
                ", cityCallPrice=" + cityCallPrice +
                '}';
    }
}
