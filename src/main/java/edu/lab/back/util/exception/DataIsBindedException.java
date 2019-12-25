package edu.lab.back.util.exception;

import lombok.NonNull;

public class DataIsBindedException extends RuntimeException {

    public DataIsBindedException(@NonNull final String message) {
        super(message);
    }

}
