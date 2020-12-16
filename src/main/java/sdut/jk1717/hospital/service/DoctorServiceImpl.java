package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdut.jk1717.hospital.Util.DateUtil;
import sdut.jk1717.hospital.Util.PwdUtil;
import sdut.jk1717.hospital.dao.DoctorRepository;
import sdut.jk1717.hospital.po.Doctor;

import java.util.List;

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
        return doctorRepository.findByNameAndPassword(name, PwdUtil.md5(password));
    }

    @Override
    public Doctor findByName(String name) {
        return doctorRepository.findByName(name);
    }
    @Override
    public long count(){
        return doctorRepository.count();
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Transactional
    @Override
    public Doctor addOne(Doctor doctor) {
        if(!doctor.getPassword().isEmpty()){
            doctor.setPassword(PwdUtil.md5(doctor.getPassword()));
        }
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Doctor update(Doctor doctor) {
        doctor.setUpdateDate(DateUtil.getTodayDate());
        return doctorRepository.save(doctor);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try {
            doctorRepository.deleteById(id);
        }catch (RuntimeException e){
            System.out.println("删除id:" + id + "失败");
            return false;
        }
        return true;
    }
}
