package meli.socialMeli.repository;

import meli.socialMeli.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    User add(User user);
    boolean modify(int id, User user);
    Optional<User> findById(int id);
    List<User> getUserList();
}
