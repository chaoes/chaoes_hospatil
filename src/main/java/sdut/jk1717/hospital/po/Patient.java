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
@Table(name = "patient")
@NoArgsConstructor
@Data
@Proxy(lazy = false)
public class Patient {
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
    @OneToOne(fetch = FetchType.EAGER)
    private Bed bed;
    @OrderColumn
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Drug> drugs = new HashSet<>();
    @OrderColumn
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Examination> examinations = new HashSet<>();
}
