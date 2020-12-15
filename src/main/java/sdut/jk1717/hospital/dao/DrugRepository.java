package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sdut.jk1717.hospital.po.Drug;

import java.sql.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/


public interface DrugRepository extends JpaRepository<Drug,Long>, JpaSpecificationExecutor<Drug> {
    List<Drug> findAllByPatient_Id(Long id);
    long count();
    List<Drug> findAllByCreatDate(Date date);
    int countAllByCreatDate(Date date);
}
