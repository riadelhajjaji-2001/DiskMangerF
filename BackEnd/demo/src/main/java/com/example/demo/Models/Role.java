package com.example.demo.Models;

import javax.persistence.*;

@Entity
@Table(name="authorities")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column
    String authority;
//    @ManyToOne(fetch  = FetchType.EAGER)
//    @JoinColumn(name = "user_id",referencedColumnName = "id",nullable=true)
//    User user;

    public Role() {

    }
    public Role(String authority) {
        this.authority = authority;

    }
    public Role(int id, String authority) {
        this.id = id;
        this.authority = authority;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
