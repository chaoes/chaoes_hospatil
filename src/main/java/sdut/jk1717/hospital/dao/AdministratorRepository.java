package sdut.jk1717.hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdut.jk1717.hospital.po.Administrator;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
}
