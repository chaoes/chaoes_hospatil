package sdut.jk1717.hospital.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "patient")
@NoArgsConstructor
@Data
public class Patient implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String addr;
    private String avatar;
    @Temporal(TemporalType.DATE)
    private Date creatDate = new Date(System.currentTimeMillis());
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @JsonIgnoreProperties({"patient"})
    @OneToOne
    private Bed bed;
    @ManyToOne
    private Doctor doctor;
    @JsonIgnore
    @OneToMany(mappedBy = "patient",cascade=CascadeType.REMOVE)
    private Set<Drug> drugs = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "patient",cascade=CascadeType.REMOVE)
    private Set<Examination> examinations = new HashSet<>();
}
