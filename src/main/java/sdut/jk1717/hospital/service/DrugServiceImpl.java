package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdut.jk1717.hospital.dao.DrugRepository;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Patient;

import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@Service
public class DrugServiceImpl implements DrugService{
    @Autowired
    DrugRepository drugRepository;

    @Transactional
    @Override
    public Drug save(Drug drug, Patient patient) {
        drug.setPatient(patient);
        return drugRepository.save(drug);
    }

    @Override
    public List<Drug> findAllByPatient_Id(Long id) {
        return drugRepository.findAllByPatient_Id(id);
    }
    @Override
    public List<Drug> findAllByDate(Date date){
        return drugRepository.findAll(new Specification<Drug>(){

            @Override
            public Predicate toPredicate(Root<Drug> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Path<Date> tdate = root.get("creatDate");
                Predicate predicate = criteriaBuilder.equal(tdate,date);
                return predicate;
            }
        });
    }
    @Override
    public int countAllByDate(Date date){
        return drugRepository.countAllByCreatDate(date);
    }
}
