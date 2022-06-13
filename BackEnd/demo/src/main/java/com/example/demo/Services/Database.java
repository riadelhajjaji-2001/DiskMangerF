package com.example.demo.Services;
import com.example.demo.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Database {
    List<User> users=new ArrayList<>();
    public User ajouterUser(User user){
        users.add(user);
        return user;
    }
    public void supprimerUser(User user){
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
