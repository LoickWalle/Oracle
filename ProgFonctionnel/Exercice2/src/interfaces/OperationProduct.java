package interfaces;

import models.Product;

@FunctionalInterface
public interface OperationProduct<T> {
    Product operate(Product product, T param);
}
