public class Currency {
    private final String name;
    private final String converter;
    private final String code;
    private final String exchange;

    public Currency(String name, String converter, String code, String exchange) {
        this.name = name;
        this.converter = converter;
        this.code = code;
        this.exchange = exchange.replaceAll(",", ".");
    }

    public String getName() {
        return name;
    }

    public String getConverter() {
        return converter;
    }

    public String getCode() {
        return code;
    }

    public String getExchange() {
        return exchange;
    }
}