package io.recruitment.assessment.api.model;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(Long id) {
        super("Could not find Cart" + id);
    }
}
