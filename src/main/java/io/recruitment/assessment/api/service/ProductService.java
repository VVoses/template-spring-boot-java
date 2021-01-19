package io.recruitment.assessment.api.service;

import io.recruitment.assessment.api.model.Customer;
import io.recruitment.assessment.api.model.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);

    List<Product> getProducts();

    void deleteProduct(Long id);

    Product updateProduct(Product Product, Long id);

    Product addProduct(Product Product);

}
