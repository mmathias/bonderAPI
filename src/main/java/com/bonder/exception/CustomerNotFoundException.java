package com.bonder.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Couldn't find customer " + id);
    }
}
