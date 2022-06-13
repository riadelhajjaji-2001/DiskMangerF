package com.example.demo.Models;

import javax.persistence.*;

@Entity
@Table(name="files")
public class File extends Element{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",nullable=true)
    private int id;
    @Column
    String name;
    @Column
    String content;
    @Column
    private int taille;
    @Column(name="owner")
    String owner;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "parentFolder",referencedColumnName ="id" )
   // @JsonIgnore
    private Folder parentFolder;

   public File(){
        this.taille=0;
        this.content="";
    }

    public File(File file){
        this.taille=file.taille;
        this.content=file.content;
    }
    public File(String name,String content){
        this.name=name;
        this.content=content;
        this.taille=content.length();
    }


    public File(String name, String content, String owner, Folder parentFolder) {
        this(name,content);
        this.owner = owner;
        this.parentFolder = parentFolder;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Folder getParentfolder() {
        return parentFolder;
    }

    public void setParentfolder(Folder parentfolder) {
        this.parentFolder = parentfolder;
    }

    @Override
    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }
}
