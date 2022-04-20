package model;

import dataLayer.Serializable;

public class Cont extends Serializable {

    private int id;
    String email;
    String pass;
    int k;

    public Cont(int id, String email, String pass, int k) {

        this.id = id;
        this.email = email;
        this.pass = pass;
        this.k = k;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return id + "  " + email;
    }
}
