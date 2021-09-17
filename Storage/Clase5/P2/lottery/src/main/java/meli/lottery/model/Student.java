package meli.lottery.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class) //Para autogenerarDate
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(unique = true)
    private String ci;
    @Column(name="created_at", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP") //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP") //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;
    private String name;
    private String surname;
    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer active;

}
