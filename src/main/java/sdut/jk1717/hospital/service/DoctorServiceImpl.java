package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    @Override
    public Doctor check(String name, String password) {
        return doctorRepository.findByNameAndPassword(name,password);
    }

    @Override
    public Doctor findByName(String name) {
        return doctorRepository.findByName(name);
    }
    @Override
    public long count(){
        return doctorRepository.count();
    }
}
