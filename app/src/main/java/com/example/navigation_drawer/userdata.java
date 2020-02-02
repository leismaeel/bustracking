package com.example.navigation_drawer;

public class userdata {
    public  String name,email,busno,regno,contact;

    public userdata(String name, String email, String busno,String regno,String contact) {
        this.name = name;
        this.email = email;
        this.busno = busno;
        this.regno=regno;
        this.contact = contact;
    }
    public userdata(){}

    public String getContact() {
        return contact;
    }
    public String getRegno() {
        return regno;
    }
    public void setContact(String regno) {
        this.contact= contact;
    }

    public void setRegno(String regno) {
        this.regno= regno;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }
}
