package demo.doctorservice.service.impl;

import demo.doctorservice.entity.Doctor;
import demo.doctorservice.repository.DoctorServiceRepository;
import demo.doctorservice.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorServiceRepository doctorServiceRepository;
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorServiceRepository.findAll();
    }

}
