package io.recruitment.assessment.api.model;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(Long id) {
        super("Could not find Customer" + id);
    }
}