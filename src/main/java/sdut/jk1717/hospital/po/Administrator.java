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
@Table(name = "administrator")
@Getter
@Setter
@NoArgsConstructor
public class Administrator implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String phone;
    private String addr;
    private String avatar;
    @Temporal(TemporalType.DATE)
    private Date creatDate = new Date(System.currentTimeMillis());
    @Temporal(TemporalType.DATE)
    private Date updateDate;
}
