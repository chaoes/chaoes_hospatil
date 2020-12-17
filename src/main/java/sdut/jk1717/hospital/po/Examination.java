package sdut.jk1717.hospital.po;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "examination")
@Getter
@Setter
@NoArgsConstructor
public class Examination implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Float price;
    private boolean isfinished=false;
    @Temporal(TemporalType.DATE)
    private Date checkTime;
    @Temporal(TemporalType.DATE)
    private Date creatTime = new Date(System.currentTimeMillis());
    @ManyToOne
    private Patient patient;
}
