package meli.spoiledTomatoesAPI.model;

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
@Table(name = "mini_series")
public class MiniSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column( nullable = false)
    private String title;
    @Column( nullable = false, columnDefinition = "DECIMAL(3,1) UNSIGNED")
    private Double rating;
    @Column(nullable = false, columnDefinition="INT UNSIGNED DEFAULT 1")
    private Integer awards;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="genre_id")
    private Genre genre;

    @Column(name="release_date")
    private Date releaseDate;
    @Column(name="end_date")
    private Date endDate;

    @Column(name="created_at", updatable = false,nullable = false) //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at",nullable = false) //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;

    @OneToMany(mappedBy = "actor")
    List<ActorMiniSerie> actors;

}
