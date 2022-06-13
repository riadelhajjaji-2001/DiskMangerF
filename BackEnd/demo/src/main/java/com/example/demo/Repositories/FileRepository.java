package com.example.demo.Repositories;
import com.example.demo.Models.File;
import com.example.demo.Models.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    File findByOwnerAndParentFolder(String owner, int parentId);
    List<File> findByOwnerAndParentFolderId(String username, int parentId);
    List<File> findByParentFolderOwnerAndParentFolderId(String username, int parentId);
    File findByOwnerAndId(String owner, int id);

    List<File> findByNameContainsIgnoreCase( String name);
    @Query("SELECT f FROM File f WHERE f.name LIKE %:name%")
    List<File> searchFile(@Param("name") String name);

}