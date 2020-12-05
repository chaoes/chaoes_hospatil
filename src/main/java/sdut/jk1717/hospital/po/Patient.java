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
@Table(name = "patient")
@NoArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String addr;
    private String avatar;
    @Temporal(TemporalType.DATE)
    private Date creatDate;
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @OneToOne
    private Bed bed;
    @ManyToOne
    private Doctor doctor;
    @OneToMany(mappedBy = "patient")
    private List<Drug> drugs = new ArrayList<>();
    @OneToMany(mappedBy = "patient")
    private List<Examination> examinations = new ArrayList<>();
}
