package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Examination;
import sdut.jk1717.hospital.po.Patient;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/


public interface ExaminationService {
    List<Examination> findAllByPatient_Id(Long id);
    Examination save(Examination examination, Patient patient);
}
