package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdut.jk1717.hospital.dao.ExaminationRepository;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@Service
public class ExaminationServiceImpl implements ExaminationService{
    @Autowired
    ExaminationRepository examinationRepository;
    @Override
    public List<Examination> findAllByPatient_Id(Long id) {
        return examinationRepository.findAllByPatient_Id(id);
    }

    @Override
    public Examination save(Examination examination, Patient patient) {
        examination.setPatient(patient);
        return examinationRepository.save(examination);
    }
}
