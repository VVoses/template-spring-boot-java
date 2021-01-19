package io.recruitment.assessment.api.service;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Long id) {
        super("Could not find Order" + id);
    }
}

