package models;

import interfaces.ICurrencyAdapter;

public class CurrencyAdapter implements ICurrencyAdapter {
    private final CurrencyConverter converter;

    public CurrencyAdapter(CurrencyConverter converter){
        this.converter = converter;
    }

    @Override
    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equals(toCurrency))
            return amount;

        double amountUSD = convertToUSD(fromCurrency, amount);
        return convertFromUSD(toCurrency, amountUSD);
    }

    private double convertToUSD(String fromCurrency, double amount){
        return switch (fromCurrency) {
            case "USD" -> amount;
            case "EUR" -> converter.convertToUSD("EUR", amount);
            case "GBP" -> converter.convertToUSD("GBP", amount);
            default -> throw new IllegalArgumentException("Unsupported currency: " + fromCurrency);
        };
    }

    private double convertFromUSD(String toCurrency, double amountInUSD) {
        return switch (toCurrency) {
            case "USD" -> amountInUSD;
            case "EUR" -> converter.convertFromUSD("EUR", amountInUSD);
            case "GBP" -> converter.convertFromUSD("GBP", amountInUSD);
            default -> throw new IllegalArgumentException("Unsupported currency: " + toCurrency);
        };
    }
}
