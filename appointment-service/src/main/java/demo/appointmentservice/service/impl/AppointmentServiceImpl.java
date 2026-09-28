package demo.appointmentservice.service.impl;

import demo.appointmentservice.dto.AppointmentServiceRequest;

import demo.appointmentservice.entity.Appointment;
import demo.appointmentservice.repository.AppointmentRepository;
import demo.appointmentservice.service.AppointmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final RestTemplate restTemplate;

    @Override
    @CircuitBreaker(name = "doctorServiceCB", fallbackMethod = "fallbackAppointment")
    public Appointment createAppointment(AppointmentServiceRequest request) {
        boolean isPatientValid = checkEntityExists("http://patient-service/api/v1/patients/"+request.getPatientId());
        if(!isPatientValid){
            throw new RuntimeException("Bệnh nhân với ID" + request.getPatientId() + " không tồn tại");
        }

        boolean isDoctorValid = checkEntityExists("http://doctor-service/api/v1/doctors/" + request.getDoctorId());
        if (!isDoctorValid){
            throw new RuntimeException("Bác sĩ với ID" + request.getDoctorId() + " không tồn tại");
        }

        Appointment newAppointment = Appointment.builder()
                .patientId(request.getPatientId())
                .doctorId(request.getDoctorId())
                .appointmentDate(request.getAppointmentDate())
                .reason(request.getReason())
                .status(request.getStatus())
                .build();
        return newAppointment;
    }
    public Appointment fallbackAppointment(Long doctorId,Exception e){
        log.error("Fallback kích hoạt lý do: {}",e.getMessage());
        return null;
    }

    private boolean checkEntityExists(String url){
        try {
            restTemplate.getForObject(url, Object.class);
            return true;
        }catch (HttpClientErrorException.NotFound e) {
            return false;
        }catch(Exception e){
            return false;
        }
    }
}
