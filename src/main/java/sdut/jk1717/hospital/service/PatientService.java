package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Patient;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/8
 **/


public interface PatientService {
    List<Patient> findPatientsByDoctorId(Long id);
    Patient findById(Long id);
}
