package com.example.demo.Services;

import com.example.demo.Models.Folder;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repositories.FolderRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class AdminService extends UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    FolderRepository folderRepository;
    public List<User> getAllUsers(){
         List<User> users=userRepository.findByUsernameNotIn(new ArrayList<>(Arrays.asList("admin","all")));
         return users;
    }
    public void createUser(User user){
        List<Role> userRoles=new ArrayList<>();
        userRoles.add(new Role(user.getUsername()));
        user.setAuthorities(userRoles);
        Folder parentFolder=folderRepository.findById(1).get();
        Folder homeFolder=new Folder(user.getUsername()+"Home",parentFolder,user.getUsername());
        userRepository.save(user);
        folderRepository.save(homeFolder);
    }
    public void deleteUser(String username,String password){

        User user=userRepository.findByUsernameAndPassword(username,password);
        Folder userRootFolder=folderRepository.findByParentFolderIdAndName(1,user.getUsername()+"Home");
        if(userRootFolder!=null) {
            folderRepository.deleteById(userRootFolder.getId());
            userRepository.deleteById(user.getId());
        }
    }

}
