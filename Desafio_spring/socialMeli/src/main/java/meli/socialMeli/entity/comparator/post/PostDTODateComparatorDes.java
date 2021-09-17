package meli.socialMeli.entity.comparator.post;

import meli.socialMeli.dto.PostDTO;

import java.util.Comparator;

public class PostDTODateComparatorDes implements Comparator<PostDTO>{


    @Override
    public int compare(PostDTO o1, PostDTO o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
