package io.recruitment.assessment.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    List<Product> allProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/products/news")
    List<Product> getNews() {
        List<Product> list = new ArrayList<>(this.productService.getProducts());
        return list.stream().filter(Product::getNews).collect(Collectors.toList());
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return this.productService.addProduct(newProduct);
    }

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable Long id) {
        return this.productService.getProduct(id);
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return this.productService.updateProduct(newProduct, id);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProduct(id);
    }

}
