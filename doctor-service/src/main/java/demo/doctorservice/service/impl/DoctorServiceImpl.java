package demo.doctorservice.service.impl;

import demo.doctorservice.entity.Doctor;
import demo.doctorservice.exception.DoctorServiceUnavaialbeException;
import demo.doctorservice.repository.DoctorServiceRepository;
import demo.doctorservice.service.DoctorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorServiceRepository doctorServiceRepository;
    @Override
    @CircuitBreaker(name = "doctorServiceCB", fallbackMethod = "fallBackDoctor")
    public List<Doctor> getAllDoctors() {
        return doctorServiceRepository.findAll();
    }
    public  Doctor fallBackDoctor(Long doctorId, Exception e){
        log.warn("Fallback kích hoạt lí do {}", e.getMessage());
        throw new DoctorServiceUnavaialbeException("Hiện tại không thể kiểm tra thông tin bác sĩ, vui lòng thử lại sau");
    }

}
