package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Patient;

import java.sql.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/


public interface DrugService {
    Drug save(Drug drug, Patient patient);
    List<Drug> findAllByPatient_Id(Long Id);
    List<Drug> findAllByDate(Date date);
    int countAllByDate(Date date);
}
