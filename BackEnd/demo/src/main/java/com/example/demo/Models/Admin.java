package com.example.demo.Models;
import com.example.demo.Services.Database;

public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
    }
    public Admin(){super();}
    public void creerUser(String username, String password){
        Database database=new Database();
        database.ajouterUser(new User(username,password));
    }
    public void supprimerUser(String username,String password){
        Database database=new Database();
        database.supprimerUser(new User(username,password));
    }


}
