package edu.lab.back.util.exception;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(@NonNull final String message) {
        super(message);
    }

}
