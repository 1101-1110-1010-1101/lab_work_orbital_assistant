package ru.ifmo.prog.lab3;


public class User {
    private String login;
    private String pswrd;

    public User (String log, String pswrdHash){
        this.login = log;
        this.pswrd = pswrdHash;
    }

    public String getLogin() {
        return login;
    }

    public String getPswrd() {
        return pswrd;
    }
}
