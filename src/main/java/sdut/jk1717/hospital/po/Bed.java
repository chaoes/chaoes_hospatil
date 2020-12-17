package sdut.jk1717.hospital.po;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "bed")
@Getter
@Setter
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
