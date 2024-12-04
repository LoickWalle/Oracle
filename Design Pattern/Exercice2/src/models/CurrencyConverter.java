package models;

import java.util.Objects;

public class CurrencyConverter {
    private final double EUR_TO_USD = 1.1;
    private final double GBP_TO_USD = 1.3;

    public double convertToUSD(String currency, double amount) {
        return switch (currency){
            case "EUR" -> amount * EUR_TO_USD;
            case "GBP" -> amount * GBP_TO_USD;
            default -> amount;
        };
    }

    public double convertFromUSD(String currency, double amount) {
        return switch (currency){
            case "EUR" -> amount / EUR_TO_USD;
            case "GBP" -> amount / GBP_TO_USD;
            default -> amount;
        };
    }
}