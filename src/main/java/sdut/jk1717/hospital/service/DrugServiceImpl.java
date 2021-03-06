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

    @Override
    public List<Drug> findAll() {
        return drugRepository.findAll();
    }

    @Override
    public Drug findById(Long id) {
        return drugRepository.findById(id).get();
    }

    @Override
    public List<Date> findDistinctDate(Long id) {
        return drugRepository.findDistinctDate(id);
    }

    @Override
    public List<Drug> findAllByDateAndPatient_Id(Date date, Long id) {
        return drugRepository.findAllByCreatDateAndPatient_Id(date,id);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try{
            drugRepository.deleteById(id);
        }catch (RuntimeException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public Drug update(Drug drug) {
        return drugRepository.save(drug);
    }

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
