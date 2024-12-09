package interfaces;

import models.Product;

@FunctionalInterface
public interface FilterProduct {
    Boolean filter(Product product, String input);
}
