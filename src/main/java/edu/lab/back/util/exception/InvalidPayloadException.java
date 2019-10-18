package edu.lab.back.util.exception;

import lombok.NonNull;

public class InvalidPayloadException extends Exception {

    public InvalidPayloadException(@NonNull final String message) {
        super(message);
    }

}
