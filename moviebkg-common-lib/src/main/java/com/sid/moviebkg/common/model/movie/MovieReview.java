package com.sid.moviebkg.common.model.movie;

import com.sid.moviebkg.common.model.user.UserLogin;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user", "movie"})
@Entity
@Table(name = "T_Movie_Review")
public class MovieReview {
    @Id
    @UuidGenerator
    @Column(name = "A_Review_Id")
    private String reviewId;

    @ManyToOne
    @JoinColumn(
            name = "A_Movie_Id",
            referencedColumnName = "A_Movie_Id"
    )
    private Movie movie;

    @ManyToOne
    @JoinColumn(
            name = "A_Usr_Id",
            referencedColumnName = "A_Usr_Id"
    )
    private UserLogin user;

    @Column(name = "A_Ratng")
    private Double rating;
    @Column(name = "A_Review")
    private String review;
    @Column(name = "A_Cr_Dtm")
    private LocalDateTime createdDateTime;
}
