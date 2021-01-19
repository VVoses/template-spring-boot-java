package io.recruitment.assessment.api.service.impl;

import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.ProductNotFoundException;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        this.productRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product newProduct, Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setPrice(newProduct.getPrice());
                    product.setNews(newProduct.getNews());
                    product.setDescription(newProduct.getDescription());
                    return productRepository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
    }

    @Override
    public Product addProduct(Product newProduct) {
        return this.productRepository.save(newProduct);
    }
}
