package ru.ifmo.prog.lab3;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String login;
    private String pswrdHash;
    private List<String> commands;
    private int idVK;                   //For password changing

    public User (String log, String pswrdHash, int idVK){
        this.login = log;
        this.pswrdHash = pswrdHash;
        this.idVK = idVK;
        this.commands = new LinkedList<>();
    }
}
