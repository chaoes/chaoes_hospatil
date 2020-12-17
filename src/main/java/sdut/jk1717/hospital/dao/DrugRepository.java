package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    List<Drug> findAllByCreatDateAndPatient_Id(Date date,Long id);
    @Query(value="SELECT DISTINCT drug.creat_date FROM drug left join patient on patient.id = drug.patient_id WHERE patient_id = :id",nativeQuery=true)
    List<Date> findDistinctDate(@Param("id")Long patientid);
}