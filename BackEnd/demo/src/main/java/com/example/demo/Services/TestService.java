package com.example.demo.Services;


import com.example.demo.Models.File;
import com.example.demo.Models.Folder;
import com.example.demo.Models.User;
import com.example.demo.Repositories.FileRepository;
import com.example.demo.Repositories.FolderRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestService {
    @Autowired
    FileRepository fileRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    FolderRepository folderRepository;
    @GetMapping("initiate_db")
    void initialDatabse(){
        //Folders
        Folder rootFolder=new Folder("root",1);
        folderRepository.save(rootFolder);
        Folder folder1=folderRepository.save(new Folder("riadHome",0,rootFolder,"riad"));
        Folder folder2=folderRepository.save(new Folder("medHome",0,rootFolder,"med"));
        Folder folder11=folderRepository.save(new Folder("riadSubfolder1",0,folder1,"riad"));
        Folder folder21=folderRepository.save(new Folder("medSubfolder1",0,folder2,"med"));
        Folder folder12=folderRepository.save(new Folder("riadSubfolder2",0,folder1,"riad"));
        Folder folder22=folderRepository.save(new Folder("medSubfolder2",0,folder2,"med"));
        Folder folder13=folderRepository.save(new Folder("riadSubfolder3",0,folder1,"riad"));
        Folder folder23=folderRepository.save(new Folder("medSubfolder3",0,folder2,"med"));
        ///Files
        File riadFile1=fileRepository.save(new File("file111.txt","hello i am riad from database","riad",folder11));
        File riadFile2=fileRepository.save(new File("file112.txt","hello i am riad from database","riad",folder11));
        File medFile21=fileRepository.save(new File("file211.txt","hello i am med from database","med",folder21));
        File medFile1=fileRepository.save(new File("file212.txt","hello i am med from database","med",folder21));



    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
