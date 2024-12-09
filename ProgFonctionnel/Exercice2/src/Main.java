import interfaces.FilterProduct;
import interfaces.OperationProduct;
import interfaces.TransformProduct;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Produit A", 100.0, 5));
        products.add(new Product("Produit B", 200.0, 0));
        products.add(new Product("Produit C", 150.0, 10));

        FilterProduct productInStock = (product, quantity) -> product.getQuantity() > 0;
        FilterProduct findProductByName = (product, name) -> product.getName().equals(name);

        TransformProduct<Double> increasePrice = (product, input) -> {
            product.setPrice(product.getPrice() + input);
            return product;
        };

        TransformProduct<Double> decreasePrice = (product, input) -> {
            product.setPrice(product.getPrice() - input);
            return product;
        };

        TransformProduct<Integer> restockProduct = (product, input) -> {
            product.setQuantity(product.getQuantity() + input);
            return product;
        };

        TransformProduct<Integer> decreaseStockProduct = (product, input) -> {
            product.setQuantity(product.getQuantity() - input);
            return product;
        };

        TransformProduct<String> changeProductName = (product, prefix) -> {
            product.setName(prefix + product.getName());
            return product;
        };

        OperationProduct<Double> productPromotion = (product, param) -> {
            decreasePrice.transform(product, param);
            changeProductName.transform(product, "PROMOTION - ");
            return product;
        };

        List<Product> productsInStock = products.stream()
                .filter(prd -> productInStock.filter(prd, ""))
                .toList();

        System.out.println("Produits en stock : " + productsInStock);

        List<Product> productsByName = products.stream()
                .filter(prd -> findProductByName.filter(prd, "Produit A"))
                .toList();
        System.out.println("Produits par nom : " + productsByName);

        List<Product> productsIncreased = products.stream()
                .map(pr -> increasePrice.transform(pr, 5.0))
                .toList();
        System.out.println("Produits augmentés : " + productsIncreased);

        List<Product> productsDecreased = products.stream()
                .map(pr -> decreasePrice.transform(pr, 5.0))
                .toList();
        System.out.println("Produits diminués : " + productsDecreased);

        List<Product> productsRestock = products.stream()
                .map(pr -> restockProduct.transform(pr, 2))
                .toList();
        System.out.println("Produits restockés : " + productsRestock);

        List<Product> productsDecreaseStock = products.stream()
                .map(pr -> decreaseStockProduct.transform(pr, 2))
                .toList();
        System.out.println("Produits avec stock diminué : " + productsDecreaseStock);

        Product productInPromotion = productPromotion.operate(products.get(2), 50.0);
        System.out.println("Produit en promo : " + productInPromotion);

    }
}