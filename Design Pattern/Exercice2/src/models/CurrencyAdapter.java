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

        double amountUSD = converter.convertToUSD(fromCurrency, amount);
        return converter.convertFromUSD(toCurrency, amountUSD);
    }
}
