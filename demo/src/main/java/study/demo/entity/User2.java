package study.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * jpa 实体类
 */
@Entity
@Data
public class User2 {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private Date regTime;
}
