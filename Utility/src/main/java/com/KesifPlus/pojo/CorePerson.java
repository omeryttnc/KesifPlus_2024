package com.KesifPlus.pojo;

public class CorePerson {
    private String name, email;

    public CorePerson(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void printUser() {
        System.out.println(name + " :  " + email);
    }
}
