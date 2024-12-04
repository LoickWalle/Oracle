import interfaces.ICurrencyAdapter;
import models.CurrencyAdapter;
import models.CurrencyConverter;

public class Main {
    public static void main(String[] args) {

        CurrencyConverter converter = new CurrencyConverter();

        ICurrencyAdapter converterAdapted = new CurrencyAdapter(converter);

        System.out.println("10 EUR to GBP => " + converterAdapted.convert("EUR", "GBP", 10));
        System.out.println("10 USD to GBP => " + converterAdapted.convert("USD", "GBP", 10));
        System.out.println("10 GBP to EUR => " + converterAdapted.convert("GBP", "EUR", 10));
        System.out.println("10 USD to EUR => " + converterAdapted.convert("USD", "EUR", 10));
        System.out.println("10 EUR to USD => " + converterAdapted.convert("EUR", "USD", 10));
        System.out.println("10 GBP to USD => " + converterAdapted.convert("GBP", "USD", 10));
    }
}