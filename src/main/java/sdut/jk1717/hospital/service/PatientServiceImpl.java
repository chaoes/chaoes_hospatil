package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdut.jk1717.hospital.dao.PatientRepository;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Patient;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/8
 **/

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientRepository patientRepository;
}
