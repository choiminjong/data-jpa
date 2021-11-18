package study.datajpa.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED )
public class Test {

    @Id
    @GeneratedValue
    @Column(name="test_id")
    private Long id;
    private String username;
    private int age;

    @CreationTimestamp
    private Timestamp regdata;
    @UpdateTimestamp
    private Timestamp updatadate;

}
