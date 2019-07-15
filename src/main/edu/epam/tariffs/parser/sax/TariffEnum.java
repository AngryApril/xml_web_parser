package main.edu.epam.tariffs.parser.sax;

public enum TariffEnum {

    TARIFFS("tariffs"),
    TARIFF("tariff"),
    TARIFFNAME("tariffName"),
    TARIFFTYPE("tariffType"),
    OPERATOR("operator"),
    INNERCALLPRICE("innercallprice"),
    OUTERCALLPRICE("OUTercallprice"),
    CITYCALLPRICE("citycallprice"),
    CALLPRICES("callprices"),
    PAYROLL("payroll"),
    SMSPRICE("smsprice"),
    FAVOURITENUMBERSAVAILABLE("favouritenumbersavailable"),
    TARIFFICATION("tariffication"),
    CONNECTPRICE("connectprice"),
    PARAMETERS("parameters"),
    GIGABYTECOUNT("gigabytecount"),
    INTERNATIONALCALLPRICE("internationalcallprice");


    private String value;

    TariffEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
