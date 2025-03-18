package com.sid.moviebkg.common.model.theater;

import com.sid.moviebkg.common.model.screen.Screen;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_Theater")
public class Theater {
    @Id
    @UuidGenerator
    @Column(name = "A_Theater_Id")
    private String theaterId;
    @Column(name = "A_Nm")
    private String name;
    @Column(name = "A_Addrss")
    private String address;
    @Column(name = "A_Gpos_Lat")
    private BigDecimal latitude;
    @Column(name = "A_Gpos_Long")
    private BigDecimal longitude;
    @Column(name = "A_Tot_Scrns")
    private Integer totalScreens;
    @Column(name = "A_Cr_Dtm")
    private LocalDateTime createdDateTime;
    @Column(name = "A_Upd_Dtm")
    private LocalDateTime updatedDateTime;

    @OneToMany(
            mappedBy = "theater",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Screen> screens;
}
