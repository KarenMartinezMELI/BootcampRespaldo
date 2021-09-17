package meli.socialMeli.repository;

import meli.socialMeli.entity.Post;
import meli.socialMeli.entity.Product;
import meli.socialMeli.util.Calculator;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImp implements IPostRepository{

    List<Post> posts;
    Calculator nextCount;

    public PostRepositoryImp(){
        posts=new ArrayList<>();
        nextCount = new Calculator(1);
    }

    @Override
    public Post add(Post post) {
        post.setIdPost(nextCount.getCount());
        posts.add(post);
        nextCount.increment();
        return post;
    }

    @Override
    public List<Post> getPostList() {
        return posts;
    }


}
