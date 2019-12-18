package edu.lab.back.util.exception;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(@NonNull final String message) {
        super(message);
    }

}
