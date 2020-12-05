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
@Table(name = "examination")
@Data
@NoArgsConstructor
public class Examination {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Integer price;
    @Temporal(TemporalType.DATE)
    private Date creatTime;
    @ManyToOne
    private Patient patient;
}
