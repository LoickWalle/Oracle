package org.example.tp_bonus.model.mapper;

import org.example.tp_bonus.model.dto.ProductDTO;
import org.example.tp_bonus.model.entity.Product;

import java.util.UUID;

public class ProductMapper {

    public static ProductDTO ProductEntityToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        return productDTO;
    }

    public static Product ProductDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        return product;
    }
}
