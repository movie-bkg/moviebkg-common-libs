package com.sid.moviebkg.common.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDetailsDto {
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<PreferenceDto> preferences;
    private List<CardDetailsDto> cardDetails;
}
