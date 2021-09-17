package meli.lottery.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lotteries")
public class Lottery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    private String name;
    @Column(name="created_at", updatable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP") //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP") //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;

    @OneToMany()
    @JoinColumn(name = "lottery_id")
            /*
                @OneToMany(mapped by="lotterie")
                y en la otra poner
                @ManyToOne
                @JoinColumn(name="lottery_id")
             */
    List<Student> students;
}
