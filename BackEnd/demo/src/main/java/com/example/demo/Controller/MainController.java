package com.example.demo.Controller;
import com.example.demo.Models.File;
import com.example.demo.Models.Folder;
import com.example.demo.Models.FolderChildren;
import com.example.demo.Models.User;
import com.example.demo.Repositories.FolderRepository;
import com.example.demo.Services.SecurityService;
import com.example.demo.Services.TestService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@CrossOrigin
//(origins = "http://localhost:3000")
@RequestMapping(value="/manager/v1/")
@RestController
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    TestService testService;
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    SecurityService securityService;
    @GetMapping(value="/home")
    public String Home(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUser= authentication.getName();

        return "Hello in Home"+authenticatedUser;
    }
//

    @GetMapping(value="/files")
    public List<File> files(){
        return userService.getAllFiles();

    }
    @GetMapping(value="/folders")
    public List<Folder> folders(){
        return userService.getAllFolders();

    }


//    @PostMapping(value="/userFile/createFile")
//    public void createFolders(@RequestBody File file){
//         userService.createFile(file);
//
//    }
    //get Users home directories
    @GetMapping(value="/users_home_directories")
    public FolderChildren UsersHomeDirectories(){
        return userService.getUsersHomeDirectory();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value="/userFolder/createFolder/{username}/{name}/{parentId}")
    public void createFolder(@PathVariable String username, @PathVariable String name,@PathVariable int parentId){
        userService.createFolder(username,name,parentId);

    }

    //get a specifique folder by id and username
    @GetMapping(value="/userFolder/{username}/{folderId}")
    public ResponseEntity<?> folderAndFilesOfThatFolderUser(@PathVariable String username, @PathVariable int folderId){
        return userService.getFolderChildren(username,folderId) ;
    }
    //..........
    @GetMapping(value="/userFile/{username}/{id}")
    public File getFileForUser(@PathVariable String username, @PathVariable int id){
        return userService.getFileForUser(username,id);
    }
    @GetMapping(value="/userFile/{id}")
    public File getFile(@PathVariable int id){
        return userService.getFile(id);
    }

    //remove file
    @GetMapping(value="/userFile/remove_file/{fileId}")
    public void removeFile(@PathVariable int fileId){
         userService.removeFile(fileId);
    }
    //remove Folder
    @DeleteMapping(value="/userFolder/remove_folder/{AuthenticatedUser}/{folderId}")
    public void removeFolder(@PathVariable String AuthenticatedUser,@PathVariable int folderId){
        userService.removeFolder(AuthenticatedUser,folderId);

    }
    @DeleteMapping(value="/userFolder/removefolder/{folderId}")
    public void removeFolderTest(@PathVariable int folderId){
       folderRepository.deleteById(folderId);

    }

    ///
    @GetMapping(value="/logout")
    public void login() {

    }
    //////testtt
    @GetMapping("/test/users")
    public List<User> getUsers() {
        return testService.getUsers();
    }
    @GetMapping("/userFolder/search/{name}")
    public ResponseEntity<?>search(@PathVariable String name){
        try {
            return new ResponseEntity<>(userService.search(name), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/userFile/update/{id}/{content}")
    public void updateFile(@PathVariable int id,@PathVariable String content) {

            userService.modifyFile(id, content);

    }
    //create a file

    @GetMapping(value="/userFile/createFile/{username}/{name}/{parentId}/{content}")
    public void createFile(@PathVariable String username,@PathVariable String name,@PathVariable int parentId,@PathVariable String content){
        userService.createFile(username,name,parentId,content);

    }
}
