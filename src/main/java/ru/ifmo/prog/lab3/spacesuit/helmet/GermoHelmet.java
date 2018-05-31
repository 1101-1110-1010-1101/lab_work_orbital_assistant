package ru.ifmo.prog.lab3.spacesuit.helmet;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.UserClass;
import ru.ifmo.prog.lab3.elements.Material;
import ru.ifmo.prog.lab3.elements.Illuminator;
import ru.ifmo.prog.lab3.spacesuit.helmet.radio.Radio;

public class GermoHelmet extends Helmet {
    @UserClass
    private Illuminator illuminator;
    private Material material1;
    private Material material2;
    @UserClass
    private Radio radio;

    public Radio radio() {
        return this.radio;
    }

    @CVSConstructor
    public GermoHelmet(Material m1, Material m2, boolean console){
        if (console)
        System.out.println("- Гермошлема из:");
        this.material1 = m1;
        if (console)
        System.out.println("-- " + m1);
        this.material2 = m2;
        if (console)
        System.out.println("-- " + m2);
        this.illuminator = new Illuminator(Material.Стекло, console);
        this.radio = new Radio(console);
    }

    public GermoHelmet(Illuminator illuminator, Radio radio, Material m1, Material m2){
        this.illuminator = illuminator;
        this.radio = radio;
        this.material1 = m1;
        this.material2 = m2;
    }

    @Override
    public int hashCode(){
        int result = 29;
        result = result*37 + illuminator.hashCode();
        result = result*37 + material1.hashCode();
        result = result*37 + material2.hashCode();
        result = result*37 + radio.hashCode();
        return result;
    }
    @Override
    public String toString(){
        return "Самый герметичный шлем.";
    }
    @Override
    public boolean equals(Object anoter) {
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            GermoHelmet other = (GermoHelmet) anoter;
            return other.radio.equals(this.radio) && other.material2.equals(this.material2) && other.material1.equals(this.material1) && other.illuminator.equals(this.illuminator);
        }
        return false;
    }
}