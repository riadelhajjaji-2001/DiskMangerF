package com.example.demo.Repositories;
import com.example.demo.Models.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//folder repo
@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
        List<Folder> findByOwner(String owner);

        List <Folder> findByOwnerAndParentFolderId(String owner,int parentId);

        List <Folder> findByParentFolderOwnerAndParentFolderId(String owner,int parentId);

        List <Folder> findByParentFolderId(int parentId);
        Folder findByParentFolderIdAndName(int id ,String name);
        // void DeleteAllChildrenFolders();
        @Query("SELECT f FROM Folder f WHERE f.name LIKE %:name%")
        List<Folder> searchFolder(@Param("name") String name);
       // List<Folder> findByNameContainsIgnoreCase(String name);

}