import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CurrencyCalculator {
    CurrencyDatabase currencyDatabase = CurrencyDatabase.getInstance();

    public CurrencyCalculator() { }

    public void calculateCurrencies() {
        Scanner scanner =  new Scanner(System.in);
        boolean loop = true;

        while(loop) {
            System.out.println("Przelicz z: ");
            String fromCode = scanner.nextLine();

            System.out.println("Przelicz na: ");
            String toCode = scanner.nextLine();

            System.out.println("Kwota: ");
            String money = scanner.nextLine();
            money = money.replaceAll(",", ".");

            try {
                BigDecimal amount = new BigDecimal(money).setScale(2, RoundingMode.DOWN);

                checkInput(fromCode,toCode,amount);

                BigDecimal exchangedMoney = calculateExchange(fromCode,toCode,amount);
                System.out.format("%.2f %s = %.2f %s",amount,fromCode,exchangedMoney,toCode);

                loop = false;
            } catch (NumberFormatException e) {
                System.out.println("Wpisano niepoprawna kwote");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkInput(String fromCode, String toCode, BigDecimal amount) throws Exception {
        if(amount.doubleValue() <=0 )
            throw new Exception("Wpisano niepoprawna kwote");
        if(currencyDatabase.isInvalidCurrencyCode(fromCode) || currencyDatabase.isInvalidCurrencyCode(toCode))
            throw new Exception("Wpisano niepoprawny kod waluty");
    }

    public BigDecimal calculateExchange(String from, String to, BigDecimal amount) {

        Currency fromCurrency = currencyDatabase.getCurrencyByCode(from);
        double fromConverter = Double.parseDouble(fromCurrency.getConverter());
        BigDecimal fromExchange = new BigDecimal(fromCurrency.getExchange());

        Currency toCurrency =  currencyDatabase.getCurrencyByCode(to);
        double toConverter = Double.parseDouble(toCurrency.getConverter());
        BigDecimal toExchange = new BigDecimal(toCurrency.getExchange());

        BigDecimal converter = new BigDecimal(toConverter/fromConverter);
        return amount.multiply(converter).multiply(fromExchange).divide(toExchange,2,RoundingMode.DOWN);
    }
}