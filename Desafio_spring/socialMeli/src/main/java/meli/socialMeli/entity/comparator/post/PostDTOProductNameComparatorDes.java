package meli.socialMeli.entity.comparator.post;

import meli.socialMeli.dto.PostDTO;

import java.util.Comparator;

public class PostDTOProductNameComparatorDes implements Comparator<PostDTO>{


    @Override
    public int compare(PostDTO o1, PostDTO o2) {
        return o2.getDetail().getProductName().compareTo(o1.getDetail().getProductName());
    }
}
