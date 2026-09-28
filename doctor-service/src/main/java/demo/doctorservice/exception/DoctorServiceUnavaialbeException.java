package demo.doctorservice.exception;

public class DoctorServiceUnavaialbeException extends RuntimeException {
    public DoctorServiceUnavaialbeException(String message) {
        super(message);
    }
}
