package com.example.demo.exception;

import com.example.demo.entity.Dependent;

public class DependentNotFoundException extends RuntimeException {

    public DependentNotFoundException(String message) {
        super(message);
    }
}
