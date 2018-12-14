package com.pret.lalala.pret.Model;

public class User {

    public String nama;
    public String email;
    public String uname;
    public String id;
    public String noHp;

    public User() {

    }

    public User(String nama, String email, String uname, String id, String noHp) {

        this.nama = nama;
        this.email = email;
        this.uname = uname;
        this.id = id;
        this.noHp = noHp;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getUname() {
        return uname;
    }

    public String getId() {
        return id;
    }

    public String getNoHp() {
        return noHp;
    }
}
