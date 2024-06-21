package med.voll.vollapirest.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import med.voll.vollapirest.infra.errors.exceptions.ValidacionDeIntegridadException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidacionErroresDTO>> tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(ValidacionErroresDTO::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionDeIntegridadException.class)
    public ResponseEntity<String> tratarValidacionesDeIntegridadConsulta(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> tratarValidacionesDeNegocioConsulta(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record ValidacionErroresDTO(String campo, String error) {
        public ValidacionErroresDTO(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
