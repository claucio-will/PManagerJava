package com.claucio.pmanager.infrastructure.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleRequestException(RequestException ex, WebRequest request) {
        return handleException(ex, ex.getErrorCode(), ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(RequestException ex, WebRequest request) {
        return handleException(ex, null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    private ResponseEntity<Object> handleException(
            RequestException ex,
            String errorCode,
            String message,
            HttpStatus status,
            WebRequest request
    ) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
                ex,
                RestError.builder()
                        .errorMessage(errorCode)
                        .status(status.value())
                        .path(servletWebRequest.getRequest().getRequestURI()),
                new HttpHeaders(),
                status,
                request
        );
    }

}
