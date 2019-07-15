package main.edu.epam.tariffs.entity.tariff;

/**
 * Created by alexey.valiev on 6/12/19.
 */

import main.edu.epam.tariffs.entity.CallPrices;
import main.edu.epam.tariffs.entity.Operator;
import main.edu.epam.tariffs.entity.Parameters;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;


@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Tariff {

    private static final Logger logger = Logger.getLogger(Tariff.class);

    @XmlAttribute(name = "tariffName", required = true)
    private String tariffName;
    @XmlElement(name = "tariffID", required = true)
    private String tariffID;

    @XmlElement(name = "operator")
    private Operator operator;
    @XmlElement(name = "callPrices")
    private CallPrices callPrices;
    @XmlElement(name = "payroll")
    private Double payroll;
    @XmlElement(name = "smsPrice")
    private Double smsPrice;
    @XmlElement(name = "parameters")
    private Parameters parameters;

    public Tariff() {
    }

    public Tariff(String tariffName, String tariffID, Operator operator, CallPrices callPrices,
                  Double payroll, Double smsPrice, Parameters parameters) {
        this.tariffName = tariffName;
        this.tariffID = tariffID;
        this.operator = operator;
        this.callPrices = callPrices;
        this.payroll = payroll;
        this.smsPrice = smsPrice;
        this.parameters = parameters;
    }

    public Tariff(Tariff tariff) {
        this.tariffName = tariff.getTariffName();
        this.tariffID = tariff.getTariffID();
        this.operator = tariff.getOperator();
        this.callPrices = tariff.getCallPrices();
        this.payroll = tariff.getPayroll();
        this.smsPrice = tariff.getSmsPrice();
        this.parameters = tariff.getParameters();
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public String getTariffID() {
        return tariffID;
    }

    public void setTariffID(String tariffID) {
        this.tariffID = tariffID;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public Double getPayroll() {
        return payroll;
    }

    public void setPayroll(Double payroll) {
        this.payroll = payroll;
    }

    public Double getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(Double smsPrice) {
        this.smsPrice = smsPrice;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(tariffName, tariff.tariffName) &&
                Objects.equals(tariffID, tariff.tariffID) &&
                operator == tariff.operator &&
                Objects.equals(callPrices, tariff.callPrices) &&
                Objects.equals(payroll, tariff.payroll) &&
                Objects.equals(smsPrice, tariff.smsPrice) &&
                Objects.equals(parameters, tariff.parameters);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tariffName, tariffID, operator, callPrices, payroll, smsPrice, parameters);
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "tariffName='" + tariffName + '\'' +
                ", tariffID='" + tariffID + '\'' +
                ", operator=" + operator +
                ", callPrices=" + callPrices +
                ", payroll=" + payroll +
                ", smsPrice=" + smsPrice +
                ", parameters=" + parameters +
                '}';
    }
}
