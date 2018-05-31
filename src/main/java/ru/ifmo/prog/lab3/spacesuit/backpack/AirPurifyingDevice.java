package ru.ifmo.prog.lab3.spacesuit.backpack;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.elements.Electric;

public class AirPurifyingDevice extends Device {

    private int kpd;
    private int clas;

    @CVSConstructor
    public AirPurifyingDevice(int kpd, int clas){
        this.kpd = kpd;
        this.clas = clas;
    }

    public int getKpd(){return this.kpd;}
    public int getClas(){return this.clas;}

    public AirPurifyingDevice(boolean console){
        class ParamGenerator {
            public int generateParam() {
                int a = (int) (Math.random() * 50 + 50);
                return a;
            }
        }
        ParamGenerator r = new ParamGenerator();
            kpd = r.generateParam();
            clas = r.generateParam();
        if (console)
        System.out.println("-- Воздухоочистительное устройство");
    }

    @Override
    public Boolean isWorking(Electric e) {
        float a = (float)Math.random();
        return a > 0.1f;
    }

    @Override
    public int hashCode(){
        int result = 23;
        result = result*37 + kpd;
        result = result*37 + clas;
        return result;
    }
    @Override
    public String toString(){
        return "Очищаю воздух";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            AirPurifyingDevice other = (AirPurifyingDevice) anoter;
            return other.clas == this.clas && other.kpd == this.kpd;
        }
        return false;
    }
}
