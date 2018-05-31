package ru.ifmo.prog.lab3.spacesuit.backpack;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.elements.Electric;

public class Battery {

    private boolean isCharged;
    private int charge;

    @CVSConstructor
    public Battery(boolean isCharged, int charge){
        this.isCharged = isCharged;
        this.charge = charge;
    }

    private void setCharged(){
        float a = (float)Math.random();
        if (a < 0.0)
            isCharged = false;
        else isCharged = true;
    }

    public int getCharge(){return this.charge;}

    public Battery(boolean console){
        setCharged();
        if (isCharged)
            charge = (int)(Math.random()*100);
            else charge = 0;
        if (console)
        System.out.println(this.toString());
    }

    public Electric isWorking() {
                return new Electric();
        }

    @Override
    public int hashCode(){
        int result = 31;
        result = result*37 + (isCharged ? 1 : 0);
        result = result*37 + charge;
        return result;
    }
    @Override
    public String toString(){
        return "-- Батарея";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Battery other = (Battery) anoter;
            return other.charge == this.charge && other.isCharged == this.isCharged;
        }
        return false;
    }
}
