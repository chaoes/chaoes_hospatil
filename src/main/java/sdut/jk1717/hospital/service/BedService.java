package sdut.jk1717.hospital.service;

import sdut.jk1717.hospital.po.Bed;

import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/16
 **/


public interface BedService {
    Bed findById(Long id);
    List<Bed> findAll();
    List<Bed> findAllUnLive();
    long count();
}
