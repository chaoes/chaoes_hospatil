package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdut.jk1717.hospital.po.Examination;

import java.sql.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Repository
public interface ExaminationRepository extends JpaRepository<Examination,Long> {
    List<Examination> findAllByPatient_Id(Long id);
    long count();
    List<Examination> findAllByCreatTime(Date date);
    int countAllByCreatTime(Date date);
}
