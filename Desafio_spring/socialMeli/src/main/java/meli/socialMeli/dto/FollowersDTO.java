package meli.socialMeli.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FollowersDTO {
    private int userId;
    private String userName;
    private List<UserDTO> followers;
}
