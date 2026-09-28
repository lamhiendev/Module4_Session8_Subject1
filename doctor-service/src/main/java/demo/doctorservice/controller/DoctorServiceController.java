package demo.doctorservice.controller;

import demo.doctorservice.dto.DoctorServiceResponseDTO;
import demo.doctorservice.entity.Doctor;
import demo.doctorservice.repository.DoctorServiceRepository;
import demo.doctorservice.service.impl.DoctorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorServiceController {
    private final DoctorServiceImpl doctorService;
    private final DoctorServiceRepository doctorServiceRepository;
    @GetMapping("/{id}")
    public ResponseEntity<DoctorServiceResponseDTO> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorServiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tồn tại"));
        return new ResponseEntity<>(new DoctorServiceResponseDTO(
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getExperienceYears(),
                doctor.getEmail(),
                doctor.getStatus()
        ), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<DoctorServiceResponseDTO>> getAllDoctors(){
        List<Doctor> listDoctor = doctorService.getAllDoctors();
        List<DoctorServiceResponseDTO> responseDTOs = listDoctor.stream().map(doctor ->
                DoctorServiceResponseDTO.builder()
                        .name(doctor.getName())
                        .specialization(doctor.getSpecialization())
                        .experienceYears(doctor.getExperienceYears())
                        .email(doctor.getEmail())
                        .status(doctor.getStatus())
                        .build()
        ).toList();
        return ResponseEntity.ok(responseDTOs);
    }

}
