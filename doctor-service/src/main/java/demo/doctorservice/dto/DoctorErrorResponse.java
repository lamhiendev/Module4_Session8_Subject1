package demo.doctorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorErrorResponse {
    private Instant timeStamp;
    private int errorCode;
    private String error;
    private String message;
}
