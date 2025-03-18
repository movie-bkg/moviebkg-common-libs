package com.sid.moviebkg.common.model.screen;

import com.sid.moviebkg.common.model.showtime.Showtime;
import com.sid.moviebkg.common.model.theater.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_Screen")
public class Screen {
    @Id
    @UuidGenerator
    @Column(name = "A_Screen_Id")
    private String screenId;

    @ManyToOne
    @JoinColumn(
            name = "A_Theater_Id",
            referencedColumnName = "A_Theater_Id"
    )
    private Theater theater;

    @Column(name = "A_Scrn_No")
    private Integer screenNo;
    @Column(name = "A_Tot_Seats")
    private Integer totalSeats;
    @Column(name = "A_Cr_Dtm")
    private LocalDateTime createdDateTime;
    @Column(name = "A_Upd_Dtm")
    private LocalDateTime updatedDateTime;

    @OneToMany(
            mappedBy = "screen",
            fetch = FetchType.LAZY
    )
    private List<Showtime> showtimes;
}
