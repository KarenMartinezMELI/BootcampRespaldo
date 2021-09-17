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
@Table(name = "seasons")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    private String title;

    @Column(name="number",columnDefinition = "INT UNSIGNED")
    private Long number;

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

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="serie_id")
    private Serie serie;
}
