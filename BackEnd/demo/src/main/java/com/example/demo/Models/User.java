package com.example.demo.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    int id;
    @Column
    String username;
    @Column

    String password;
//    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    List<Role> authorities=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();
    public User() {
    }
    public User(String username,String password) {
        this.username = username;
        this.password = password;


    }
    public User(String username,String password,int id) {
        this.username = username;
        this.password = password;
        this.id=id;

    }



    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> userAuthorities= new ArrayList<>();
        for(Role role:roles) {
            userAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        userAuthorities.add(new SimpleGrantedAuthority("all"));
        return userAuthorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.roles = authorities;
    }
//    public void addAuthorities(String authority) {
//        authorities.add(new SimpleGrantedAuthority(authority));
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void createFile(){
    }
    public void modifyFile(){

    }
    public long calculateSize(File file){
        return file.getContent().length();
    }
    public List<File> afficherElement(){
        return null;
    }

    public File chercherElement(File file){
        return new File();
    }






}
