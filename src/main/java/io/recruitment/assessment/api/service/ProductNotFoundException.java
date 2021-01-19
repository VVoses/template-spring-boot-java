package io.recruitment.assessment.api.service;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id) {
        super("Could not find Product" + id);
    }
}
