package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdut.jk1717.hospital.Util.DateUtil;
import sdut.jk1717.hospital.dao.DrugRepository;
import sdut.jk1717.hospital.dao.PatientRepository;
import sdut.jk1717.hospital.po.Doctor;
import sdut.jk1717.hospital.po.Drug;
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


    @Override
    public List<Patient> findPatientsByDoctorId(Long id) {
        return patientRepository.findAllByDoctor_Id(id);
    }

    @Override
    public Patient findById(Long id) {
        return (Patient) patientRepository.findById(id).get();
    }

    @Override
    public long count() {
        return patientRepository.count();
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Transactional
    @Override
    public Patient addOne(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    @Override
    public Patient update(Patient patient) {
        patient.setUpdateDate(DateUtil.getTodayDate());
        return patientRepository.save(patient);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try {
            patientRepository.deleteById(id);
        }catch (RuntimeException e){
            System.out.println("删除id: " + id + "失败");
            return false;
        }
        return true;
    }
}
