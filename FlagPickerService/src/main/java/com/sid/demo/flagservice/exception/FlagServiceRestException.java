package com.sid.demo.flagservice.exception;

import java.util.Optional;

import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice @RequestMapping(produces = "application/vnd.error+json")
public class FlagServiceRestException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContinentNotFoundException.class) 
	public ResponseEntity <VndErrors> notFoundException(final ContinentNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getContinentName());
	}
    @ExceptionHandler(CountryNotFoundException.class) 
	public ResponseEntity <VndErrors> notFoundException(final CountryNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getName());
	}
    private ResponseEntity < VndErrors > error(final Exception exception, final HttpStatus httpStatus, final String value) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity < > (new VndErrors(value, message), httpStatus);
    }
    @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity < VndErrors > assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
}
