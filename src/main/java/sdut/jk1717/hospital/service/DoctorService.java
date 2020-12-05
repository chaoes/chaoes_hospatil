package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Doctor;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/


public interface DoctorService {
    Doctor check(String name,String password);
}
