package demo.doctorservice.exception;

import demo.doctorservice.dto.DoctorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DoctorServiceUnavaialbeException.class)
    public ResponseEntity<DoctorErrorResponse> doctorErrorResponseResponseEntity(DoctorServiceUnavaialbeException e){
        return new ResponseEntity<>(new DoctorErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage()
        ),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DoctorErrorResponse> exceptionHandler(Exception e){
        return new ResponseEntity<>(new DoctorErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage()
        ),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
