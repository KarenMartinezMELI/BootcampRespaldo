package meli.socialMeli.repository;

import meli.socialMeli.entity.User;
import meli.socialMeli.util.Calculator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements IUserRepository{
    List<User> users;
    Calculator nextCount;

    public UserRepositoryImp(){
        users=new ArrayList<>();
        nextCount = new Calculator(1);
        loadHarcodedRepository();
    }

    private void loadHarcodedRepository(){
        User user= new User(0,"Comprador 1",false);
        User user2= new User(1,"Comprador 2",false);
        User user3= new User(0,"Vendedor 1",true);
        User user4= new User(1,"Vendedor 2",true);
        add(user);
        add(user2);
        add(user3);
        add(user4);
    }

    @Override
    public User add(User user) {
        user.setUserId(nextCount.getCount());
        users.add(user);
        nextCount.increment();
        return user;
    }

    @Override
    public boolean modify(int id, User user) {
        Optional<User> userModify = findById(id);
        if(userModify==null) {
            return false;
        }
        userModify.get().setSeller(user.isSeller());
        userModify.get().setUserName(user.getUserName());
        return true;
    }

    @Override
    public Optional<User> findById(int id) {
       return users.stream().filter(u->u.getUserId()==id).findFirst();
    }

    @Override
    public List<User> getUserList() {
        return users;
    }
}
