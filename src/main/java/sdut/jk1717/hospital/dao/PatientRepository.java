package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sdut.jk1717.hospital.po.Patient;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/


public interface PatientRepository extends JpaRepository<Patient,Long> {
}
