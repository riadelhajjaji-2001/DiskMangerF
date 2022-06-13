package com.example.demo.Services;

import com.example.demo.Models.*;
import com.example.demo.Repositories.FileRepository;
import com.example.demo.Repositories.FolderRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    UserRepository userRepository;
//
    public void deleteUser(int userId){
                User user=userRepository.findById(userId).get();
                userRepository.deleteById(userId);
              //  Folder homeFolder=folderRepository.findByParentFolderIdAndName(userId,user.getUsername()+"Home");
                //folderRepository.deleteById(homeFolder.getId());




    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    public List<Folder> getAllUserFolders(String username) {
        return folderRepository.findByOwner(username);
    }
////////////////////////////
    public void createFile(String username,String name,int parentId,String content) {
        Folder parentFolder = folderRepository.findById(parentId).get();
        File file=new File( name,  content,  username,  parentFolder);
        fileRepository.save(file);
    }

    public void createFolder(String username, String name, int parentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUser = authentication.getName();
        System.out.println("Create 1 "+authenticatedUser);
        //if (authenticatedUser.equals(username)) {
            System.out.println("Create 2 :"+authenticatedUser);
            Folder pfolder = folderRepository.findById(parentId).get();
            if (username.equals(pfolder.getOwner()) ||username.equals("admin")) {
                System.out.println("Create 3 :"+authenticatedUser);
                Folder newFolder = new Folder(name);
                newFolder.setOwner(pfolder.getOwner());
                newFolder.setParentFolder(pfolder);
                folderRepository.save(newFolder);
            }
        //}
    }


    // get folder content(folders) for a specifique user

    public List<Folder>getFolderForUser(String username, int folderId) {
            return folderRepository.findByParentFolderOwnerAndParentFolderId(username, folderId);

    }

    // get folder content(files) for a specifique user
    public List<File> getFilesForUser(String username, int parentId) {
            return fileRepository.findByParentFolderOwnerAndParentFolderId(username, parentId);

    }
        //The combination of the two
    public ResponseEntity<?>getFolderChildren(String username, int parentId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUser = authentication.getName();
        System.out.println("Create 1 "+authenticatedUser);

        if (username.equals(authenticatedUser) || authenticatedUser.equals("admin") || parentId==1) {
            return new ResponseEntity(new FolderChildren(getFolderForUser(username, parentId), getFilesForUser(username, parentId)),HttpStatus.OK);
        }else{
            return new ResponseEntity("not authorized", HttpStatus.UNAUTHORIZED);
    }}
    // get a file for a specifique user
    public File getFileForUser(String username, int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUser = authentication.getName();
        if (authenticatedUser.equals(username) || authenticatedUser.equals("admin") || id==1) {
            return fileRepository.findByOwnerAndId(username, id);
        } else {
            return null;
        }
    }
    public File getFile(int id) {
        return fileRepository.findById(id).get();
    }
    // get Users Home Directory or Workspace
    public FolderChildren getUsersHomeDirectory() {
        Folder root = folderRepository.findByOwner("all").get(0);
        return new FolderChildren(folderRepository.findByParentFolderId(root.getId()),
                fileRepository.findByOwnerAndParentFolderId("all", root.getId()));
    }

    // remove a file
    public void removeFile(int fileId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String authenticatedUser = authentication.getName();
//        File file = fileRepository.findById(fileId).get();
//        System.out.println(file.getOwner());
//        if (authenticatedUser.equals(file.getOwner()) || file.getContent().equals("all")) {
            fileRepository.deleteById(fileId);
//            System.out.println("File is removed");
//        } else {
//            System.out.println("Not Authorised");
//        }
    }

    // remove a folder
    public void removeFolder(String username, int folderId) {
        // String AuthenticatedUser="User from security context";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUser = authentication.getName();
        if (authenticatedUser.equals(username)) {
            Folder folder = folderRepository.findById(folderId).get();

            if (folder != null) {
                if (authenticatedUser.equals(folder.getOwner()) || authenticatedUser.equals("admin")) {
                    folderRepository.deleteById(folder.getId());
                    System.out.println("Folder is deleted" + folderId);

                } else {
                    System.out.println("Not Authorised");
                }
            }
        }
    }
    public FolderChildren search(String name){
        return new FolderChildren(folderRepository.searchFolder(name),fileRepository.searchFile(name));
    }
    public void modifyFile(int id ,String content){
            File file=fileRepository.findById(id).get();
            file.setContent(content);
            fileRepository.save(file);
    }
}
