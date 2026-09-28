package demo.doctorservice.service;

import demo.doctorservice.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DoctorService {
    List<Doctor> getAllDoctors();

}
