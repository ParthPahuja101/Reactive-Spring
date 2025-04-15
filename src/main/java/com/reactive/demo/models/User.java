package com.reactive.demo.models;

public class User {
    public User(int id,String name, String email){
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int id;
    public String name;
    public String email;
}
