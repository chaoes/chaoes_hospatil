package sdut.jk1717.hospital.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String addr;
    private String avatar;
    @Temporal(TemporalType.DATE)
    private Date creatDate;
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToMany(mappedBy = "doctor")
    private List<Patient> patients = new ArrayList<>();
}
