package main.edu.epam.tariffs.entity;

import main.edu.epam.tariffs.entity.tariff.InternetIncludedTariff;
import main.edu.epam.tariffs.entity.tariff.MobileCallsTariff;
import main.edu.epam.tariffs.entity.tariff.RoamingTariff;
import main.edu.epam.tariffs.entity.tariff.Tariff;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by alexey.valiev on 6/12/19.
 */

@XmlRootElement(name = "Tariffs")
@XmlAccessorType(XmlAccessType.NONE)
public class Tariffs {

    @XmlElements({
            @XmlElement(name = "InternetIncludedTariff", type = InternetIncludedTariff.class),
            @XmlElement(name = "MobileCallsTariff", type = MobileCallsTariff.class),
            @XmlElement(name = "RoamingTariff", type = RoamingTariff.class)
    })

    private List<Tariff> tariffList = new ArrayList<>();

    public Tariffs() {
    }

    public Tariffs(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    public List<Tariff> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<Tariff> tariffList) {
        this.tariffList = tariffList;
    }

    public void addTariff(Tariff tariff){
        this.tariffList.add(tariff);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariffs tariffs = (Tariffs) o;
        return Objects.equals(tariffList, tariffs.tariffList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tariffList);
    }

    @Override
    public String toString() {
        return "Tariffs{" +
                "tariffList=" + tariffList +
                '}';
    }
}
