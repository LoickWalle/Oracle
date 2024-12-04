package interfaces;

public interface ICurrencyAdapter {
    double convert(String fromCurrency, String toCurrency, double amount);
}
