package meli.spoiledTomatoesAPI.model;

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
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name="created_at", updatable = false) //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at") //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;
    private String name;
    @Column(name="ranking", columnDefinition = "INT UNSIGNED")
    private Long ranking;
    private Integer active;

}
