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
@EntityListeners(AuditingEntityListener.class) //Para autogenerarDate
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED ")
    private Long id;
    @Column(name="created_at", updatable = false) //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at") //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;
    @Column(name="first_name",unique = true)
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    private Double rating;
    private Integer active;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="favorite_movie_id")
    private Movie favoriteMovie;

    @OneToMany(mappedBy = "miniSerie") //nombre de la propiedad en ActorMiniSerie
    private List<ActorMiniSerie> miniSeries;

    @OneToMany(mappedBy = "movie")
    private List<ActorMovie> movies;

    @OneToMany(mappedBy = "episode")
    private List<ActorEpisode> episodes;

}
