package meli.socialMeli.controller;

import meli.socialMeli.entity.modelEnum.OrderDateEnum;
import meli.socialMeli.entity.modelEnum.OrderNameEnum;
import meli.socialMeli.dto.PostDTO;
import meli.socialMeli.service.IPostUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    private final IPostUserService postUserService;

    public ProductController(IPostUserService postUserService){
        this.postUserService = postUserService;
    }


    @PostMapping(path="/newpost")
    public ResponseEntity<?> createPost(@RequestBody PostDTO post){
        return new ResponseEntity<>(postUserService.addPost(post,false), HttpStatus.OK);
    }

    @GetMapping(path="followed/{userId}/list")
    public ResponseEntity<?> getUserFollowedPosts(@PathVariable int userId, @RequestParam(required = false) OrderDateEnum order){
        return new ResponseEntity<>(postUserService.getUserFollowedPosts(userId,order), HttpStatus.OK);
    }

    @PostMapping(path="/newpromopost")
    public ResponseEntity<?> createPromoPost(@RequestBody PostDTO post){
        return new ResponseEntity<>(postUserService.addPost(post,true), HttpStatus.OK);
    }

    @GetMapping(path="/{userId}/countPromo")
    public ResponseEntity<?> getPromoPostsOfSellerCount(@PathVariable int userId){
        return new ResponseEntity<>(postUserService.getPromoPostsOfSellerCount(userId), HttpStatus.OK);
    }

    @GetMapping(path="/{userId}/list")
    public ResponseEntity<?> getPromoPostsOfSeller(@PathVariable int userId, @RequestParam(required = false) OrderNameEnum order){
        return new ResponseEntity<>(postUserService.getPromoPostsOfSeller(userId,order), HttpStatus.OK);
    }




}
