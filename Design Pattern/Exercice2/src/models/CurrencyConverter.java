package models;

import java.util.Objects;

public class CurrencyConverter {

    private final double EUR_TO_USD = 1.1;
    private final double GBP_TO_USD = 1.3;

    public double convertToUSD(String currency, double amount) {
        if(Objects.equals(currency, "EUR"))
            return amount * EUR_TO_USD;
        if(Objects.equals(currency, "GBP"))
            return amount * GBP_TO_USD;
        else{
            System.out.println("Currency not available.");
            return 0;
        }
    }

    public double convertFromUSD(String currency, double amount) {
        if(Objects.equals(currency, "EUR"))
            return amount / EUR_TO_USD;
        if(Objects.equals(currency, "GBP"))
            return amount / GBP_TO_USD;
        else{
            System.out.println("Currency not available.");
            return 0;
        }
    }
}