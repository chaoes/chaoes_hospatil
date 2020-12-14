package sdut.jk1717.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sdut.jk1717.hospital.dao.DrugRepository;
import sdut.jk1717.hospital.po.Drug;
import sdut.jk1717.hospital.po.Patient;
import sdut.jk1717.hospital.service.DrugService;
import sdut.jk1717.hospital.service.PatientService;

/**
 * @auther:chaoe
 * @date:2020/12/14
 **/

@RestController
public class PatientDrugController {
    @Autowired
    PatientService patientService;
    @Autowired
    DrugService drugService;
    @GetMapping("/doctor/patient/adddrug/{id}")
    public String patientAddDrug(Long id){
        Drug drug =new Drug();
        drug.setDrugname("qoq");
        Patient patient = new Patient();
        patient.setId(1L);
//        drug.setPatient(patient);
        drugService.save(drug,patient);
        return drug.toString();
    }
}
