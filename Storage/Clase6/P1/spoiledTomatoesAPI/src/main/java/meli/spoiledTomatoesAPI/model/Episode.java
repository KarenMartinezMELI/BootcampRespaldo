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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column( nullable = false)
    private String title;
    @Column( nullable = false, columnDefinition = "DECIMAL(3,1) UNSIGNED")
    private Double rating;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="season_id")
    private Season season;

    @Column(name="release_date")
    private Date releaseDate;

    @Column(name="number",columnDefinition = "INT UNSIGNED")
    private Long number;

    @Column(name="created_at", updatable = false,nullable = false) //Para autogenerarDate
    @CreatedDate
    private Date createdAt;
    @Column(name="updated_at",nullable = false) //Para autogenerarDate
    @LastModifiedDate
    private Date updatedAt;
}
