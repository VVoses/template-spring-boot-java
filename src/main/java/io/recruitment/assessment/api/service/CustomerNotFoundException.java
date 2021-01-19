package io.recruitment.assessment.api.service;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id) {
        super("Could not find Customer" + id);
    }
}