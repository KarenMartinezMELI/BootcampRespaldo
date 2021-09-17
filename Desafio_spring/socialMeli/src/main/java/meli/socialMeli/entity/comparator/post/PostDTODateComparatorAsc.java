package meli.socialMeli.entity.comparator.post;

import meli.socialMeli.dto.PostDTO;

import java.util.Comparator;
import java.util.Date;

public class PostDTODateComparatorAsc implements Comparator<PostDTO>{


    @Override
    public int compare(PostDTO o1, PostDTO o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
