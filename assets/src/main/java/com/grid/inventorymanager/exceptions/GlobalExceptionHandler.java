package com.grid.inventorymanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

//oders exceptions
@RestControllerAdvice//(assignableTypes = {ComputerController.class, VendorController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation failed");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/validation"));

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        problemDetail.setProperty("errors", errors);
        return problemDetail;

    }

    @ExceptionHandler(AssetNotFoundException.class)
    public ProblemDetail handleAssetNotFound(AssetNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Asset not found");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/not-found"));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("User not found");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/not-found"));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(ComputerNotFoundException.class)
    public ProblemDetail handleUserNotFound(ComputerNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Computer not found");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/not-found"));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ProblemDetail handleUserNotFound(EmployeeNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Employee not found");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/not-found"));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(PurchaseDetailNotFoundException.class)
    public ProblemDetail handleUserNotFound(PurchaseDetailNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Purchase detail not found");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/not-found"));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ProblemDetail handleUserNotFound(PurchaseNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Purchase not found");
        problemDetail.setType(URI.create("https://api.gridRFC-7807.com/errors/not-found"));
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleJsonParse(HttpMessageNotReadableException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setType(URI.create("https://api.gridRFC-7807.com/errors/malformed-json"));
        pd.setTitle("Malformed JSON request");
        String detail = ex.getMostSpecificCause().getMessage();
        pd.setDetail(detail);
        return pd;
    }

    @ExceptionHandler(InvalidFieldNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleInvalidSortField(InvalidFieldNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setType(URI.create("https://api.gridRFC-7807.com/errors/malformed-json"));
        pd.setTitle("Invalid sort parameter");
        String detail = ex.getMessage();
        pd.setDetail(detail);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(pd);
    }

//agregar una default
}
