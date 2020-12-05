package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sdut.jk1717.hospital.po.Drug;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/


public interface DrugRepository extends JpaRepository<Drug,Long> {
}
