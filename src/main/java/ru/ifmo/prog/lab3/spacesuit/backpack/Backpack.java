package ru.ifmo.prog.lab3.spacesuit.backpack;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.UserClass;
import ru.ifmo.prog.lab3.elements.Electric;

public class Backpack {
    @UserClass
    private AirPurifyingDevice airPurifyingDevice;
    @UserClass
    private Lantern lantern;
    @UserClass
    private VentillatingDevice ventillatingDevice;
    @UserClass
    private Battery battery;
    @UserClass
    private Parashute parashute;

    @CVSConstructor
    public Backpack(AirPurifyingDevice a, Lantern l, VentillatingDevice v, Battery b, Parashute p){
        this.airPurifyingDevice = a;
        this.lantern = l;
        this.ventillatingDevice = v;
        this.battery = b;
        this.parashute = p;
    }


    public Battery battery() {return battery;}
    public AirPurifyingDevice airPurifyingDevice(){return this.airPurifyingDevice;}
    public Lantern lantern(){return this.lantern;}
    public Parashute parashute() {return parashute;}

    public VentillatingDevice ventillatingDevice() {return ventillatingDevice;}

    public boolean check(Electric e){
        if (this.ventillatingDevice.isWorking(e) && this.lantern.isWorking(e) && this.airPurifyingDevice.isWorking(e))
            return true;
        else
            return false;
    }


    public Backpack(boolean console){
        if (console)
        System.out.println("- Ранца с:");
        this.airPurifyingDevice = new AirPurifyingDevice(console);
        this.lantern = new Lantern(console);
        this.ventillatingDevice = new VentillatingDevice(console);
        this.battery = new Battery(console);
        this.parashute = new Parashute(console);
    }

    @Override
    public int hashCode(){
        int result = 13;
        result = result*37 + airPurifyingDevice.hashCode();
        result = result*37 + lantern.hashCode();
        result = result*37 + ventillatingDevice.hashCode();
        result = result*37 + battery.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "Я - рюкзак!";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            Backpack other = (Backpack) anoter;
            return other.battery.equals(this.battery) && other.ventillatingDevice.equals(this.ventillatingDevice) && other.lantern.equals(this.lantern) && other.airPurifyingDevice.equals(this.airPurifyingDevice);
        }
        return false;
    }
}
