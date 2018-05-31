package ru.ifmo.prog.lab3.spacesuit.helmet.radio;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.elements.Electric;
import ru.ifmo.prog.lab3.spacesuit.backpack.Device;

public class Radio extends Device {

    private float freq;

    public Radio(boolean console){
        freq = (float)(Math.random()*1000);
        if (console)
        System.out.println("-- Радио");
    }

    @CVSConstructor
    public Radio(float freq){this.freq = freq;}

    @Override
    public Boolean isWorking(Electric e) {
            return true;
    }


    @Override
    public int hashCode(){
        int result = 31;
        result = result*37 + (int)freq;
        return result;
    }
    @Override
    public String toString(){
        return "Я есть радиостанция";
    }
    @Override
    public boolean equals(Object anoter) {
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Radio other = (Radio) anoter;
            return other.freq == this.freq;
        }
        return false;
    }
}