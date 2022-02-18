package com.osintsev.market.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {super(message);}
}