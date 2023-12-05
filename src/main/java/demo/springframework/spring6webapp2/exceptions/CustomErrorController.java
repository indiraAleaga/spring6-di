package demo.springframework.spring6webapp2.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler
    ResponseEntity handleJPAViolation(TransactionSystemException exception){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        List listError = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;

        }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(listError);
    }
}
