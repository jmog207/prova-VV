package org.example;

public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }
}
