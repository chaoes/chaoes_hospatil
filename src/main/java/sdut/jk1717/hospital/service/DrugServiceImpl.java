package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdut.jk1717.hospital.dao.DrugRepository;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Patient;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@Service
public class DrugServiceImpl implements DrugService{
    @Autowired
    DrugRepository drugRepository;
    @Override
    public Drug save(Drug drug, Patient patient) {
        drug.setPatient(patient);
        return drugRepository.save(drug);
    }

    @Override
    public List<Drug> findAllByPatient_Id(Long id) {
        return drugRepository.findAllByPatient_Id(id);
    }
}
