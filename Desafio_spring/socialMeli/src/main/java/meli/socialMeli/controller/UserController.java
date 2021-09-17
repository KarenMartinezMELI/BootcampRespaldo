package meli.socialMeli.controller;


import meli.socialMeli.dto.GenericResponseDTO;
import meli.socialMeli.entity.modelEnum.OrderNameEnum;
import meli.socialMeli.service.IPostUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final IPostUserService postUserService;

    public UserController(IPostUserService postUserService){
        this.postUserService = postUserService;
    }

    @PostMapping(path="/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSeller(@PathVariable int userId,@PathVariable int userIdToFollow){
        postUserService.followSellerById(userId,userIdToFollow);
        return new ResponseEntity<>(new GenericResponseDTO("El usuario "+userId+" ahora sigue a "+userIdToFollow), HttpStatus.OK);
    }

    @GetMapping(path="/{userId}/followers/count")
    public ResponseEntity<?> getFollowersOfSellerCount(@PathVariable int userId){
        return new ResponseEntity<>(postUserService.getFollowersOfSellerWCount(userId), HttpStatus.OK);
    }

    @GetMapping(path="/{userId}/followers/list")
    public ResponseEntity<?> getFollowersOfSeller(@PathVariable int userId, @RequestParam(required = false) OrderNameEnum order){
        return new ResponseEntity<>(postUserService.getFollowersOfSeller(userId,order), HttpStatus.OK);
    }

    @GetMapping(path="/{userId}/followed/list")
    public ResponseEntity<?> getUserFollows(@PathVariable int userId, @RequestParam(required = false) OrderNameEnum order){
        return new ResponseEntity<>(postUserService.getUserFollowed(userId,order), HttpStatus.OK);
    }

    @PostMapping(path="/{userId}/unfollow/{userIdToFollow}")
    public ResponseEntity<?> unfollowSeller(@PathVariable int userId,@PathVariable int userIdToFollow){
        postUserService.unfollowSellerById(userId,userIdToFollow);
        return new ResponseEntity<>(new GenericResponseDTO("El usuario "+userId+" ha dejdo de seguir a "+userIdToFollow), HttpStatus.OK);
    }

    @GetMapping(path="/list")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(postUserService.getUsers(), HttpStatus.OK);
    }
}
