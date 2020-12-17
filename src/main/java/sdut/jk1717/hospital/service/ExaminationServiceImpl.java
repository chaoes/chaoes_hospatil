package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdut.jk1717.hospital.dao.ExaminationRepository;
import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;

import java.sql.Date;
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

    @Transactional
    @Override
    public Examination save(Examination examination, Patient patient) {
        examination.setPatient(patient);
        return examinationRepository.save(examination);
    }

    @Override
    public List<Examination> findAllByDate(Date date) {
        return examinationRepository.findAllByCreatTime(date);
    }

    @Override
    public int countAllByDate(Date date) {
        return examinationRepository.countAllByCreatTime(date);
    }

    @Override
    public List<Examination> findAll() {
        return examinationRepository.findAll();
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try {
            examinationRepository.deleteById(id);
        }catch (RuntimeException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public Examination update(Examination examination) {
        return examinationRepository.save(examination);
    }

    @Override
    public Examination findById(Long id) {
        return examinationRepository.findById(id).get();
    }
}
