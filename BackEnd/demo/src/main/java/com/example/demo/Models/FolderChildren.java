package com.example.demo.Models;

import java.util.List;

public class FolderChildren {
    private List<Folder> folders;
    private List<File> files;

    public FolderChildren(List<Folder> folders,List<File>files) {
        this.folders = folders;
        this.files=files;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
}
