package ru.ifmo.prog.lab3.spacesuit.backpack;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.elements.Electric;

public class Lantern extends Device {

    private int power;

    @CVSConstructor
    public Lantern(int power){this.power = power;}

    public int getPower() {
        return power;
    }

    public Lantern(boolean console) {
        power = (int)(Math.random()*500);
        if (console)
        System.out.println("-- Фонарь");
    }

    @Override
    public Boolean isWorking(Electric e) {
        float a = (float)Math.random();
            return a > 0.1f;
    }
    @Override
    public int hashCode(){
        int result = 33;
        result = result*37 + power;
        return result;
    }
    @Override
    public String toString(){
        return "Освещаю";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Lantern other = (Lantern) anoter;
            return other.power == this.power;
        }
        return false;
    }
}
