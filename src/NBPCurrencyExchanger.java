public class NBPCurrencyExchanger {

    public NBPCurrencyExchanger() {
        CurrencyCalculator calculator = new CurrencyCalculator();
        calculator.currencyDatabase.printCurrencies();
        calculator.calculateCurrencies();
    }

}

