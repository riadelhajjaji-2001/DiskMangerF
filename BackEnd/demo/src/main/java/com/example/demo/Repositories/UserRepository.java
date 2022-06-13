package com.example.demo.Repositories;
import com.example.demo.Models.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String name);
    List<User>findByUsernameNotIn(List<String> username);
    User findByUsernameAndPassword(String username,String password);
}