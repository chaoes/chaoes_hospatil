package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Doctor;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/


public interface DoctorService {
    Doctor check(String name,String password);
    Doctor findByName(String name);
    long count();
    List<Doctor> findAll();
    Doctor addOne(Doctor doctor);
    Doctor findById(Long id);
    Doctor update(Doctor doctor);
    boolean deleteById(Long id);
}
