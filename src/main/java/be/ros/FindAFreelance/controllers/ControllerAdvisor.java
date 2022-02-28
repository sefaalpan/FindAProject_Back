package be.ros.FindAFreelance.controllers;

import be.ros.FindAFreelance.models.dtos.FieldErrorDto;
import be.ros.FindAFreelance.models.dtos.ValidationErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handle(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Problème lié au serveur");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationErrorDto dto = new ValidationErrorDto();

        for (ObjectError error : ex.getGlobalErrors()) {
            dto.globalErrors.add(error.getDefaultMessage());
            System.out.println("ex.getGlobalErrors()"+error.getDefaultMessage());
        }

        for (FieldError error : ex.getFieldErrors()) {
            System.out.println("ex.getFieldErrors()"+error.getDefaultMessage());
            dto.fieldErrors.add(
                    FieldErrorDto.builder()
                            .message(error.getDefaultMessage())
                            .fieldName(error.getField())
                            .build()
            );
        }

        return ResponseEntity
                .badRequest()
                .body(dto);

    }
}
