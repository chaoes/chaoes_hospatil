package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Administrator;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/


public interface AdministratorService {
    Administrator check(String name,String password);
}
