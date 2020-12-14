package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Examination;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/


public interface ExaminationService {
    List<Examination> findAllByPatient_Id(Long id);
}
