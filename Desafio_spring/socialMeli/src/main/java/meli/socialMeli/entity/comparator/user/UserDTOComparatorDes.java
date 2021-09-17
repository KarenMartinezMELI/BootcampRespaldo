package meli.socialMeli.entity.comparator.user;

import meli.socialMeli.dto.PostDTO;
import meli.socialMeli.dto.UserDTO;

import java.util.Comparator;

public class UserDTOComparatorDes implements Comparator<UserDTO>{


    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        return o2.getUserName().compareTo(o1.getUserName());
    }
}
