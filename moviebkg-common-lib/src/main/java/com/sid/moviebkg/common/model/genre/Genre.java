package com.sid.moviebkg.common.model.genre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_Genre")
public class Genre {
    @Id
    @Column(name = "A_Genre_Id")
    private Integer genreId;
    @Column(name = "A_Name")
    private String genreName;
}
