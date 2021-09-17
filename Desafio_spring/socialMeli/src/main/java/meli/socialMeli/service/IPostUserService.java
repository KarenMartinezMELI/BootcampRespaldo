package meli.socialMeli.service;

import meli.socialMeli.dto.*;
import meli.socialMeli.entity.modelEnum.OrderDateEnum;
import meli.socialMeli.entity.modelEnum.OrderNameEnum;

import java.util.List;

public interface IPostUserService {

    void followSellerById(int idUser, int idUserToFollow);
    FollowersDTO getFollowersOfSeller(int idUser, OrderNameEnum order);
    FollowersCountDTO getFollowersOfSellerWCount(int idUser);
    FollowedDTO getUserFollowed(int idUser,OrderNameEnum order);
    PostDTO addPost(PostDTO post,boolean isPromo);
    FollowedPostsDTO getUserFollowedPosts(int idUser, OrderDateEnum order);
    void unfollowSellerById(int idUser, int idUserToUnfollow);
    SellerPromoCountDTO getPromoPostsOfSellerCount(int idUser);
    SellerPromoDTO getPromoPostsOfSeller(int idUser,OrderNameEnum order);
    List<UserDTO> getUsers();
}
