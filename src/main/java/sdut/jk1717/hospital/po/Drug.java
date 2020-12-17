package sdut.jk1717.hospital.po;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "drug")
@Getter
@Setter
@NoArgsConstructor
public class Drug implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String drugname;
    private Integer number;
    private Float price;
    private boolean isfinished=false;
    @Temporal(TemporalType.DATE)
    private Date creatDate = new Date(System.currentTimeMillis());
    @ManyToOne
    private Patient patient;
}
