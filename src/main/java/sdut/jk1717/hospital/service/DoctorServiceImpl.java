package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdut.jk1717.hospital.dao.DoctorRepository;
import sdut.jk1717.hospital.po.Doctor;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor check(String name, String password) {
        return doctorRepository.findByNameAndPassword(name,password);
    }
}
