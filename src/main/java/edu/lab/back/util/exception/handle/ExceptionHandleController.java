package edu.lab.back.util.exception.handle;

import edu.lab.back.json.response.ErrorMessageJson;
import edu.lab.back.util.exception.DataIsBindedException;
import edu.lab.back.util.exception.InvalidPayloadException;
import edu.lab.back.util.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandleController {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> entityNotFoundHandle(ResourceNotFoundException exception) {
        final ErrorMessageJson json = new ErrorMessageJson(exception.getMessage());
        return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidPayloadException.class)
    public ResponseEntity<Object> validationErrorHandle(InvalidPayloadException exception) {
        final ErrorMessageJson json = new ErrorMessageJson(exception.getMessage());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DataIsBindedException.class)
    public ResponseEntity<Object> validationErrorHandle(DataIsBindedException exception) {
        final ErrorMessageJson json = new ErrorMessageJson(exception.getMessage());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

}
