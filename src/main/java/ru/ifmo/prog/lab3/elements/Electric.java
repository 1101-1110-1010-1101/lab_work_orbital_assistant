package ru.ifmo.prog.lab3.elements;

import ru.ifmo.prog.lab3.CVSConstructor;

public class Electric {
    private int resistance;
    private int power;

    @CVSConstructor
    public Electric(int r, int p){
        this.resistance = r;
        this.power = p;
    }

    public Electric(){
        resistance = (int)(Math.random()*100);
        power = (int)(Math.random()*500);
    }

    public int getPower(){
        return this.power;
    }
    @Override
    public int hashCode(){
        int result = 29;
        result = result*37 + resistance;
        result = result*37 + power;
        return result;
    }
    @Override
    public String toString(){
        return "Ток.";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Electric other = (Electric) anoter;
            return other.power == this.power && other.resistance == this.resistance;
        }
        return false;
    }
}
