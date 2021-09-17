package meli.socialMeli.repository;

import meli.socialMeli.entity.Post;

import java.util.List;
import java.util.Optional;


public interface IPostRepository {

    Post add(Post post);
    List<Post> getPostList();
}
