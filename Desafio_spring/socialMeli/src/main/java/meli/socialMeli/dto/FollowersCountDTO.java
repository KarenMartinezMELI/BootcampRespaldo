package meli.socialMeli.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.socialMeli.entity.User;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FollowersCountDTO {
    private int userId;
    private String userName;
    private int followers_count;
}
