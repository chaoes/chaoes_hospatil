package sdut.jk1717.hospital.po;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "bed")
@Data
@NoArgsConstructor
public class Bed implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
    @JsonIgnoreProperties({"bed"})
    @OneToOne(mappedBy = "bed")
    private Patient patient;
}
