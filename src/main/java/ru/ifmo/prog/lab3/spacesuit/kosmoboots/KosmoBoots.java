package ru.ifmo.prog.lab3.spacesuit.kosmoboots;

import ru.ifmo.prog.lab3.CVSConstructor;
import ru.ifmo.prog.lab3.UserClass;
import ru.ifmo.prog.lab3.elements.Material;

public class KosmoBoots extends Boots {

    private Material sole;
    @UserClass
    private Boots boots;

    @CVSConstructor
    public KosmoBoots(Material m, Boots b){
        this.sole = m;
        this.boots = b;
    }

    public KosmoBoots(Material m, boolean console){
        if (console)
        System.out.println("- Космических сапог из:");
        this.sole = m;
        if (console)
        System.out.println("-- " + sole);
        this.boots = new Boots();
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + boots.hashCode();
        result = result*37 + sole.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "Космическая обувь";
    }
    @Override
    public boolean equals(Object anoter){
        if (this == anoter)
            return true;
        else if (this.getClass() == anoter.getClass()) {
            KosmoBoots other = (KosmoBoots) anoter;
            return other.boots.equals(this.boots) && other.sole.equals(this.sole);
        }
        return false;
    }
}
