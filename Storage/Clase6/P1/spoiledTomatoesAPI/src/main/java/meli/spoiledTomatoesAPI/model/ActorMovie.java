package meli.spoiledTomatoesAPI.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class) //Para autogenerarDate
@Table(name = "actor_movie")
public class ActorMovie{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="actor_id")
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;


    @Column(name="created_at", updatable = false) //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at") //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;

}
