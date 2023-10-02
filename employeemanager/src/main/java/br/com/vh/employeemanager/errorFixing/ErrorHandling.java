package br.com.vh.employeemanager.errorFixing;

import br.com.vh.employeemanager.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity errorFixing404() {
        return ResponseEntity.notFound().build();
    }
}
