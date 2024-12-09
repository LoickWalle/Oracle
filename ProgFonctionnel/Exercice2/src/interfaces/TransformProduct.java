package interfaces;

import models.Product;

@FunctionalInterface
public interface TransformProduct<T> {
    Product transform(Product product, T param);
}
