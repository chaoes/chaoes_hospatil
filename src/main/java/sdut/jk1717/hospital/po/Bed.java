package sdut.jk1717.hospital.po;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

/**
 * @auther:chaoe
 * @date:2020/12/5
 **/

@Entity
@Table(name = "bed")
@Data
@NoArgsConstructor
public class Bed {
    @Id
    @GeneratedValue
    private Long id;
    private Integer number;
}
