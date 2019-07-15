package main.edu.epam.tariffs.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

/**
 * Created by alexey.valiev on 6/12/19.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Parameters")
public class Parameters {

    @XmlAttribute(name = "favoriteNumbersAvailable")
    private Integer favoriteNumbersAvailable;

    @XmlElement(name = "tariffication")
    private Tariffication tariffication;
    @XmlElement(name = "connectPrice")
    private Double connectPrice;

    public Parameters() {
    }

    public Parameters(int favoriteNumbersAvailable, Tariffication tariffication, Double connectPrice) {
        this.favoriteNumbersAvailable = favoriteNumbersAvailable;
        this.tariffication = tariffication;
        this.connectPrice = connectPrice;
    }

    public Integer getFavoriteNumbersAvailable() {
        return favoriteNumbersAvailable;
    }

    public void setFavoriteNumbersAvailable(Integer favoriteNumbersAvailable) {
        this.favoriteNumbersAvailable = favoriteNumbersAvailable;
    }

    public Tariffication getTariffication() {
        return tariffication;
    }

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    public Double getConnectPrice() {
        return connectPrice;
    }

    public void setConnectPrice(Double connectPrice) {
        this.connectPrice = connectPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameters that = (Parameters) o;
        return favoriteNumbersAvailable == that.favoriteNumbersAvailable &&
                tariffication == that.tariffication &&
                Objects.equals(connectPrice, that.connectPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(favoriteNumbersAvailable, tariffication, connectPrice);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "favoriteNumbersAvailable=" + favoriteNumbersAvailable +
                ", tariffication=" + tariffication +
                ", connectPrice=" + connectPrice +
                '}';
    }
}
