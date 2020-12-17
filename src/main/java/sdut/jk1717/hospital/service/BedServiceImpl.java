package sdut.jk1717.hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sdut.jk1717.hospital.dao.BedRepository;
import sdut.jk1717.hospital.po.Bed;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/16
 **/

@Service
public class BedServiceImpl implements BedService{
    @Autowired
    BedRepository bedRepository;

    @Override
    public List<Bed> findAllUnLive() {
        return bedRepository.findUnLive();
    }

    @Override
    public long count() {
        return bedRepository.count();
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        try {
            bedRepository.deleteById(id);
        }catch (RuntimeException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Transactional
    @Override
    public Bed addOne(Bed bed) {
        return bedRepository.save(bed);
    }

    @Transactional
    @Override
    public Bed update(Bed bed) {
        return bedRepository.save(bed);
    }

    @Override
    public List<Bed> findAll() {
        return bedRepository.findAll();
    }

    @Override
    public Bed findById(Long id) {
        return bedRepository.findById(id).get();
    }
}
