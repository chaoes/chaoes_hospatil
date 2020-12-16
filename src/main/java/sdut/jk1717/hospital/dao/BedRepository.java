package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sdut.jk1717.hospital.po.Bed;
import sdut.jk1717.hospital.po.Patient;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Repository
public interface BedRepository extends JpaRepository<Bed,Long> {
    long count();
    List<Bed> findAllByPatient(Patient patient);
    @Query(value="SELECT tempp.id,tempp.number FROM (SELECT bed.*,patient.bed_id AS temp FROM bed LEFT JOIN patient on bed.id=patient.bed_id) AS tempp WHERE tempp.temp is null",nativeQuery=true)
    List<Bed> findUnLive();
}
