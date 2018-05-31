package ru.ifmo.prog.lab3.spacesuit.backpack;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.elements.Electric;

public class Parashute extends Device {

    private int kpd;
    private int clas;

    @CVSConstructor
    public Parashute(int kpd, int clas){
        this.kpd = kpd;
        this.clas = clas;
    }

    public int getKpd(){return this.kpd;}
    public int getClas(){return this.clas;}

    public Parashute(boolean console){
        kpd = (int)(Math.random()*50 + 50);
        clas = (int)(Math.random()*5 + 5);
        if (console)
        System.out.println("-- Парашут");
    }

    @Override
    public Boolean isWorking(Electric e) {
        float a = (float)Math.random();
                return a > 0.1f;
    }

    @Override
    public int hashCode(){
        int result = 29;
        result = result*37 + kpd;
        result = result*37 + clas;
        return result;
    }
    @Override
    public String toString(){
        return "На всякий случай";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Parashute other = (Parashute) anoter;
            return other.clas == this.clas && other.kpd == this.kpd;
        }
        return false;
    }
}
