package com.tecsup.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validaciones(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errores.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    // cuando cliente-service o producto-service responden 404
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<Map<String, String>> noEncontrado(FeignException.NotFound ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "El cliente o el producto no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // cuando no se puede conectar con otro servicio
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> errorServicio(FeignException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "No se pudo comunicar con el otro servicio");
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }
}
