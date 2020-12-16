package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdut.jk1717.hospital.Util.PwdUtil;
import sdut.jk1717.hospital.dao.AdministratorRepository;
import sdut.jk1717.hospital.po.Administrator;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    AdministratorRepository administratorRepository;
    @Override
    public Administrator check(String name,String password){
        return administratorRepository.findByNameAndPassword(name, PwdUtil.md5(password));
    }
}
