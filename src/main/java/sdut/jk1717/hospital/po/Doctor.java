package sdut.jk1717.hospital.po;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

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
    private Date creatDate = new Date(System.currentTimeMillis());
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OrderColumn
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Patient> patients = new HashSet<>();
}
