package sdut.jk1717.hospital.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "drug")
@Data
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue
    private Long id;
    private String drugname;
    private Integer number;
    private Integer price;
    @Temporal(TemporalType.DATE)
    private Date creatDate;
    @ManyToOne
    private Patient patient;
}
