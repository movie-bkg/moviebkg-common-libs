package com.sid.moviebkg.common.model.showtime;

import com.sid.moviebkg.common.model.movie.Movie;
import com.sid.moviebkg.common.model.screen.Screen;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_Showtime")
public class Showtime {
    @Id
    @UuidGenerator
    @Column(name = "A_Showtime_Id")
    private String showtimeId;

    @ManyToOne
    @JoinColumn(
            name = "A_Movie_Id",
            referencedColumnName = "A_Movie_Id"
    )
    private Movie movie;

    @ManyToOne
    @JoinColumn(
            name = "A_Screen_Id",
            referencedColumnName = "A_Screen_Id"
    )
    private Screen screen;

    @Column(name = "A_Strt_Dtm")
    private LocalDateTime startDateTime;
    @Column(name = "A_End_Dtm")
    private LocalDateTime endDateTime;
    @Column(name = "A_Avl_Seats")
    private Integer availableSeats;
}
