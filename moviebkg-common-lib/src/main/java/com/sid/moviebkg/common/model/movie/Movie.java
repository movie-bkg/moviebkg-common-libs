package com.sid.moviebkg.common.model.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_Movie")
public class Movie {
    @Id
    @UuidGenerator
    @Column(name = "A_Movie_Id")
    private String movieId;
    @Column(name = "A_Id")
    private Long id;
    @Column(name = "A_Title")
    private String title;
    @Column(name = "A_Org_Title")
    private String originalTitle;
    @Column(name = "A_Desc")
    private String description;
    @Column(name = "A_Poster_Path")
    private String posterPath;
    @Column(name = "A_Backdrop_Path")
    private String backdropPath;
    @Column(name = "A_Release_Dt")
    private LocalDate releaseDate;
    @Column(name = "A_Org_Lang")
    private String originalLanguage;
    @Column(name = "A_Duratn")
    private Integer duration;
    @Column(name = "A_Ratng")
    private Double rating;
    @Column(name = "A_Genre_Ids")
    private String genreIds;
    @Column(name = "A_Is_Adult")
    private String isAdult;
    @Column(name = "A_Cr_Dtm")
    private LocalDateTime createdDateTime;

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER
    )
    private List<MovieReview> reviews;
}
