package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="folders")
public class Folder extends Element{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=true)//
    int id;
    @Column
    private String name;
    @Column
    private String owner;
    //parent folder
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentFolder",referencedColumnName = "id",nullable=true)//problem
    private Folder parentFolder;
    //children folders
    @OneToMany(mappedBy = "parentFolder",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Folder> childrenFolders;

    //Children files
    @OneToMany(mappedBy = "parentFolder",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<File> childrenFiles;

    public Folder(){

    }
    public Folder(String name){
                this.name=name;
    }
    public Folder(String name,int id){
        this.name=name;
        this.id=id;
        this.owner="all";
        this.id=0;
    }
    public Folder(String name,int id,Folder parentFolder,String owner){
        this.name=name;
        this.id=id;
        this.parentFolder=parentFolder;
        this.owner=owner;
    }
    public Folder(String name,Folder parentFolder,String owner){
        this.name=name;
        this.parentFolder=parentFolder;
        this.owner=owner;
    }
    public List<Folder> getChildrenFolders() {
        return childrenFolders;
    }

    public void setChildrenFiles(List<File> childrenFiles) {
        this.childrenFiles = childrenFiles;
    }

    public void setChildrenFolders(List<Folder> childrenFolders) {
        this.childrenFolders = childrenFolders;
    }

    public List<File> getChildrenFiles() {
        return childrenFiles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getTaille() {
        return 0;
    }
}
