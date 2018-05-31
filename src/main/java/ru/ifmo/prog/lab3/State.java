package ru.ifmo.prog.lab3;

public enum State {ALLRIGHT, NOT_ALLRIGHT;


    public static State stringToState(String s){
        if (s.equals(new String("ALLRIGHT")))
            return State.ALLRIGHT;
        else if(s.equals(new String("NOT_ALLRIGHT")))
            return State.NOT_ALLRIGHT;
        else return null;
    }
}
