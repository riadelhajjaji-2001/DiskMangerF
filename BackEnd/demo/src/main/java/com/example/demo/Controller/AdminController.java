package com.example.demo.Controller;

import com.example.demo.Models.Folder;
import com.example.demo.Models.User;
import com.example.demo.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RequestMapping(value="/manager/v1/admin")
@RestController
public class AdminController {
    @Autowired(required = true)
    AdminService adminService;
    @GetMapping(value="/users")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();

    }
    @GetMapping(value="/users/add/{username}/{password}")
    public void createUser(@PathVariable String username,@PathVariable String password){
         User user=new User(username,password);
         adminService.createUser(user);

    }
    @DeleteMapping(value="/users/delete/{username}/{password}")
    public void deleteUser(@PathVariable String username,@PathVariable String password){
        adminService.deleteUser(username,password);

    }
    @GetMapping(value="/test")
    public String test(){
        return "test";
    }


}
