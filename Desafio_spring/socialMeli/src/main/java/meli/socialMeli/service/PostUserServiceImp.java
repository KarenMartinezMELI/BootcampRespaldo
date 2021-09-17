package meli.socialMeli.service;

import meli.socialMeli.dto.*;
import meli.socialMeli.entity.modelEnum.OrderDateEnum;
import meli.socialMeli.entity.modelEnum.OrderNameEnum;
import meli.socialMeli.entity.*;
import meli.socialMeli.entity.comparator.post.PostDTODateComparatorAsc;
import meli.socialMeli.entity.comparator.post.PostDTODateComparatorDes;
import meli.socialMeli.entity.comparator.post.PostDTOProductNameComparatorAsc;
import meli.socialMeli.entity.comparator.post.PostDTOProductNameComparatorDes;
import meli.socialMeli.entity.comparator.user.UserDTOComparatorAsc;
import meli.socialMeli.entity.comparator.user.UserDTOComparatorDes;
import meli.socialMeli.exception.*;
import meli.socialMeli.exception.ExceptionInvalidDate;
import meli.socialMeli.exception.post.ExceptionInvalidDiscount;
import meli.socialMeli.exception.ExceptionParseDate;
import meli.socialMeli.exception.user.*;
import meli.socialMeli.repository.IPostRepository;
import meli.socialMeli.repository.IUserRepository;
import meli.socialMeli.util.DateValidator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class PostUserServiceImp implements IPostUserService {

    private final IUserRepository userRepository;
    private final IPostRepository postRepository;
    private final static int daysUS005=14;

    public PostUserServiceImp(IUserRepository userRepository, IPostRepository postRepository){
        this.userRepository=userRepository;
        this.postRepository = postRepository;

    }

    @Override
    public void followSellerById(int idUser, int idUserToFollow) {
        User userToFollow = findUserById(idUserToFollow);
        if(userExistsById(idUser)){
            if(idUser==idUserToFollow){
                throw new ExceptionSameUser(Integer.toString(idUser));
            }
            if(userToFollow.isSeller()) {
                if(!userToFollow.subscribe(findUserById(idUser))){
                    throw new ExceptionAlreadyFollows(Integer.toString(idUser));
                }
            }else{
                throw new ExceptionIsNotASeller(Integer.toString(idUserToFollow));
            }
        }
    }


    @Override
    public FollowersCountDTO getFollowersOfSellerWCount(int idUser) {
        User anUser=userRepository.findById(idUser).get();
        if(!anUser.isSeller()) {
            throw new ExceptionIsNotASeller(Integer.toString(anUser.getUserId()));
        }
        return new FollowersCountDTO(anUser.getUserId(),anUser.getUserName(), (int) anUser.getSubscribers().stream().count());
    }

    private List<UserDTO> getUserFollowedStandar(User anUser) {
        List<User> userList = userRepository.getUserList().stream().filter(us->us.getSubscribers().stream().filter(u->u.equals(anUser)).findFirst().isPresent()).collect(Collectors.toList());
        List<UserDTO> followed= new ArrayList<>();
        for(User us:userList){
            followed.add(parseUserToUserDTO(us));
        }
        return followed;
    }

    @Override
    public FollowedDTO getUserFollowed(int idUser, OrderNameEnum order) {
        User anUser=findUserById(idUser);
        if(order==null){
            order=OrderNameEnum.name_asc;
        }
        List<UserDTO> followed;
        switch(order){
            case name_asc:
                followed=getUserFollowedStandar(anUser);
                followed.sort(new UserDTOComparatorAsc());
                return new FollowedDTO(anUser.getUserId(),anUser.getUserName(),followed);
            case name_desc:
                followed=getUserFollowedStandar(anUser);
                followed.sort(new UserDTOComparatorDes());
                return new FollowedDTO(anUser.getUserId(),anUser.getUserName(),followed);
            default:
                throw new ExceptionInvalidParameter(order.toString());
        }
    }

    @Override
    public FollowersDTO getFollowersOfSeller(int idUser, OrderNameEnum order) {
        User anUser=findUserById(idUser);
        if(!anUser.isSeller()) {
            throw new ExceptionIsNotASeller(Integer.toString(anUser.getUserId()));
        }
        if(order==null){
            order=OrderNameEnum.name_asc;
        }
        List<UserDTO> follows;
        switch(order){
            case name_asc:
                follows=convertSubsToUsersDTO(anUser.getSubscribers());
                follows.sort(new UserDTOComparatorAsc());
                return new FollowersDTO(anUser.getUserId(),anUser.getUserName(),follows);
            case name_desc:
                follows=convertSubsToUsersDTO(anUser.getSubscribers());
                follows.sort(new UserDTOComparatorDes());
                return new FollowersDTO(anUser.getUserId(),anUser.getUserName(),follows);
            default:
                throw new ExceptionInvalidParameter(order.toString());
        }
    }



    @Override
    public PostDTO addPost(PostDTO post,boolean isPromo) {
        if(!DateValidator.isValid(post.getDate())){
            throw new ExceptionInvalidDate(post.getDate().toString());
        }
        PostDTO postReturn=null;
        User anUser = findUserById(post.getUserId());
        if(!anUser.isSeller()&&isPromo){
           throw new ExceptionIsNotASeller(Integer.toString(anUser.getUserId()));
        }
        if(post.getPrice()<0){
            throw new ExceptionNegativeValue(Double.toString(post.getPrice()));
        }
        if(!isPromo){
            post.setDiscount(0);
            post.setHasPromo(false);
        }else{
            post.setHasPromo(true);
            if(post.getDiscount()>=1||post.getDiscount()<=0){
                throw new ExceptionInvalidDiscount(Double.toString(post.getDiscount()));
            }
        }
        postReturn = parsePostToPostDTO(postRepository.add(parsePostDTOtoPost(post)));
        if(postReturn!=null&&!anUser.isSeller()){
            anUser.setSeller(true);
            userRepository.modify(anUser.getUserId(),anUser);
        }
        return postReturn;
    }

    @Override
    public FollowedPostsDTO getUserFollowedPosts(int idUser, OrderDateEnum order) {
        User anUser=findUserById(idUser);
        FollowedPostsDTO followedPostsDTO;
        if(order==null){
            order=OrderDateEnum.date_desc;
        }
        switch(order){
            case date_asc:
                followedPostsDTO=getUserFollowedPostsStandar(idUser);
                followedPostsDTO.getPosts().sort(new PostDTODateComparatorAsc());
                return followedPostsDTO;
            case date_desc:
                followedPostsDTO=getUserFollowedPostsStandar(idUser);
                followedPostsDTO.getPosts().sort(new PostDTODateComparatorDes());
                return followedPostsDTO;
            default:
                throw new ExceptionInvalidParameter(order.toString());
        }
    }

    private FollowedPostsDTO getUserFollowedPostsStandar(int idUser) {
        try {
            final Date aDate = new SimpleDateFormat("yyyy-MM-dd").parse(java.time.LocalDate.now().minusDays(daysUS005).toString());
            return new FollowedPostsDTO(idUser,getUserFollowedPostsList(idUser).stream()
                    .filter(p->p.getDate().after(aDate))
                    .collect(Collectors.toList()));
        } catch (ParseException e) {
            throw new ExceptionParseDate(java.time.LocalDate.now().toString());
        }
    }

    @Override
    public void unfollowSellerById(int idUser, int idUserToUnfollow) {
        User userToFollow = findUserById(idUserToUnfollow);
        if(userExistsById(idUser)){
            if(idUser==idUserToUnfollow){
                throw new ExceptionSameUser(Integer.toString(idUser));
            }
            if(userToFollow.isSeller()) {
                if(!userToFollow.unsubscribe(findUserById(idUser))){
                    throw new ExceptionNoFollows(Integer.toString(idUser));
                }
            }else{
                throw new ExceptionIsNotASeller(Integer.toString(idUserToUnfollow));
            }
        }
    }

    @Override
    public SellerPromoCountDTO getPromoPostsOfSellerCount(int idUser) {
        User anUser = findUserById(idUser);
        if(!anUser.isSeller()){
            throw new ExceptionIsNotASeller(Integer.toString(idUser));
        }
        return new SellerPromoCountDTO(anUser.getUserId(),anUser.getUserName(), getPromoPostOfSelleStandar(anUser).size());
    }

    @Override
    public SellerPromoDTO getPromoPostsOfSeller(int idUser, OrderNameEnum order) {
        User anUser = findUserById(idUser);
        if(!anUser.isSeller()){
            throw new ExceptionIsNotASeller(Integer.toString(idUser));
        }
        List<PostDTO> posts= new ArrayList<>();
        getPromoPostOfSelleStandar(anUser).forEach(p->posts.add(parsePostToPostDTO(p)));
        if(order!=null){
            switch(order){
                case name_asc:
                    posts.sort(new PostDTOProductNameComparatorAsc());
                    break;
                case name_desc:
                    posts.sort(new PostDTOProductNameComparatorDes());
                    break;
                default:
                    throw new ExceptionInvalidParameter(order.toString());
            }
        }
        return new SellerPromoDTO(anUser.getUserId(),anUser.getUserName(),posts);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> users= new ArrayList<>();
        userRepository.getUserList().forEach(u->users.add(parseUserToUserDTO(u)));
        return users;
    }

    private List<Post> getPromoPostOfSelleStandar(User user){
        return postRepository.getPostList().stream().filter(p->p.getUser().equals(user)&&p.isHasPromo()).collect(Collectors.toList());
    }

    private List<PostDTO> getUserFollowedPostsList(int idUser){
        User anUser=findUserById(idUser);
        List<User> userList = userRepository.getUserList().stream().filter(us->us.getSubscribers().stream().filter(u->u.equals(anUser)).findFirst().isPresent()).collect(Collectors.toList());
        List<PostDTO> posts = new ArrayList<>();
        for(User user:userList){
            getUserPosts(user).forEach(p2->posts.add(parsePostToPostDTO(p2)));
        }
        return posts;
    }

    private Post parsePostDTOtoPost(PostDTO postDTO){
        return new Post(postDTO.getId_post(),findUserById(postDTO.getUserId()),postDTO.getDate(),parseProductDTOToProduct(postDTO.getDetail()),postDTO.getCategory(),postDTO.getPrice(),postDTO.isHasPromo(),postDTO.getDiscount());
    }

    private PostDTO parsePostToPostDTO(Post post){
        return new PostDTO(post.getIdPost(),post.getUser().getUserId(),post.getDate(),parseProductToProductDTO(post.getDetail()),post.getCategory(),post.getPrice(),post.isHasPromo(),post.getDiscount());
    }

    private ProductDTO parseProductToProductDTO(Product product){
        return new ProductDTO(product.getProductId(),product.getProductName(),product.getType(),product.getBrand(),product.getColor(),product.getNotes());
    }

    private Product parseProductDTOToProduct(ProductDTO productDTO){
        return new Product(productDTO.getProduct_id(),productDTO.getProductName(),productDTO.getType(),productDTO.getBrand(),productDTO.getColor(),productDTO.getNotes());
    }

    private List<UserDTO> convertSubsToUsersDTO(List<ISubscriber> subs){
        List<UserDTO> users=new ArrayList<>();
        for(ISubscriber s:subs){
            users.add(parseUserToUserDTO((User)s));
        }
        return users;
    }

    private UserDTO parseUserToUserDTO(User user){
        return new UserDTO(user.getUserId(),user.getUserName(),user.isSeller());
    }

    private boolean userExistsById(int userId){
        if(!userRepository.findById(userId).isPresent()){
            throw new ExceptionUserNotExist(Integer.toString(userId));
        }
        return true;
    }

    private User findUserById(int userId){
        if(!userRepository.findById(userId).isPresent()){
            throw new ExceptionUserNotExist(Integer.toString(userId));
        }else{
            return userRepository.findById(userId).get();
        }
    }

    private List<Post> getUserPosts(User anUser){
        return postRepository.getPostList().stream().filter(p->p.getUser().equals(findUserById(anUser.getUserId()))).collect(Collectors.toList());
    }

}
